package Bombas;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.net.ftp.FTPFile;

import com.mashape.unirest.http.exceptions.UnirestException;

import Ftp.Ftp;
import Google.FileBase64;
import Google.Google;
import Json.BuscarStr;
import Json.Json;
import Sankhya.Insert;
import Sankhya.Sankhya;
import Vistorias.Vistorias;

public class Bombas {

	public static void Bombas() throws SocketException, IOException, UnirestException {
		Ftp ftp = new Ftp("topmix.com.br", "u622477631", "Mbk35WbfJTuz");
		ftp.joinDirectory("Scanner/Bombas");
		for(FTPFile arquivo : ftp.listFiles()) {
			if(arquivo.getName().contains(".jpg")) {
				ftp.downloadFile(arquivo.getName(), arquivo.getName());
				File arquivoBase64 = new File(arquivo.getName());
				String fileBase64 = FileBase64.Base64(arquivoBase64);
				String GoogleAPI = Google.Request(fileBase64);
				String json = Json.Json(GoogleAPI);
				String buscar = BuscaPedido.Busca(json, "Pedido: ");

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
				String horaFormatada = dtf.format(LocalDateTime.now());
				String nomeArq = buscar + "-" + horaFormatada.replaceAll(":", "")+".jpg";

				if (!buscar.equals("")) {
					if (Insert.Insert_Table(buscar, String.format("https://topmix.com.br/Scanner/Bombas/Processadas/%s", nomeArq), Sankhya.Connect(), "BOMBAS", "BH_CCTCAB").equals("1")) {
						ftp.FtpMove(arquivo.getName(), String.format("Processadas/%s", nomeArq));
						System.out.println("Bombas -> Arquivo " + nomeArq + " Movido para Processadas");
					} else {

						ftp.FtpMove(arquivo.getName(), String.format("Lixeira/%s", arquivo.getName()));
						System.out.println("Bombas -> Arquivo " + arquivo.getName() + " Movido para Lixeira");
					}
				}else{
					ftp.FtpMove(arquivo.getName(), String.format("Lixeira/%s", arquivo.getName()));
				}
				arquivoBase64.delete();
			}
		}
	}
}
