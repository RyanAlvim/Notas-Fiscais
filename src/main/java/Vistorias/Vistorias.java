package Vistorias;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.net.ftp.FTPFile;

import com.mashape.unirest.http.exceptions.UnirestException;

import Bombas.BuscaPedido;
import Ftp.Ftp;
import Google.FileBase64;
import Google.Google;
import Json.BuscarStr;
import Json.Json;
import Sankhya.Insert;
import Sankhya.Sankhya;

public class Vistorias {

	public static void Vistorias() throws SocketException, IOException, UnirestException {
		Ftp ftp = new Ftp("", "", "");
		ftp.joinDirectory("Scanner/Vistorias");
		for(FTPFile arquivo : ftp.listFiles()) {
			if(arquivo.getName().contains(".jpg")) {
				ftp.downloadFile(arquivo.getName(), arquivo.getName());
				File arquivoBase64 = new File(arquivo.getName());
				String fileBase64 = FileBase64.Base64(arquivoBase64);
				String GoogleAPI = Google.Request(fileBase64);
				String json = Json.Json(GoogleAPI);
				
				String buscar = BuscaPedido.Busca(json, "Pedido: ");
				arquivoBase64.delete();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");        
				String horaFormatada = dtf.format(LocalDateTime.now());
				String nomeArq = buscar + "-" + horaFormatada.replaceAll(":", "")+".jpg";
				if(Insert.Insert_Table(buscar, String.format("https://topmix.com.br/Scanner/Vistorias/Processadas/%s", nomeArq), Sankhya.Connect(), "VISTORIAS","BH_CCTCAB").equals("1")) {
					ftp.FtpMove(arquivo.getName(), String.format("Processadas/%s", nomeArq));
					System.out.println("Vistorias -> Arquivo " + nomeArq + " Movido para Processadas");
				}else {
					ftp.FtpMove(arquivo.getName(), String.format("Lixeira/%s.jpg", arquivo.getName()));
					System.out.println("Vistorias -> Arquivo " + arquivo.getName() + " Movido para Lixeira");
				}
				
			}
			
		}
		
	}
}
