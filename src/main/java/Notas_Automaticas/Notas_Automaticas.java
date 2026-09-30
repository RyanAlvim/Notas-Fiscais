package Notas_Automaticas;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPFile;

import com.mashape.unirest.http.exceptions.UnirestException;

import Bombas.Bombas;
import Ftp.Ftp;
import Google.FileBase64;
import Google.Google;
import Json.BuscarStr;
import Json.Json;
import Sankhya.Insert;
import Sankhya.Sankhya;

public class Notas_Automaticas {

	
	public static void NAutomaticas() throws SocketException, IOException, UnirestException {
		Ftp ftp = new Ftp("topmix.com.br", "u622477631", "Mbk35WbfJTuz");
		ftp.joinDirectory("Scanner/Notas_Automaticas");
		for(FTPFile arquivo : ftp.listFiles()) {
			if(arquivo.getName().contains(".jpg")) {
				ftp.downloadFile(arquivo.getName(), arquivo.getName());
				File arquivoBase64 = new File(arquivo.getName());
				String base64 = FileBase64.Base64(arquivoBase64);
				String google = Google.Request(base64);

				String json = Json.Json(google);
				String Buscar = null;
				try {
					Buscar = BuscarStr.Busca(json, "CHAVE DE ACESSO");
				}catch(Exception e) {
					ftp.FtpMove(arquivo.getName(), String.format("Lixeira/%s.jpg", arquivo.getName()));
				}

				arquivoBase64.delete();


				String SankhyaSelect[] = Select.SelectQuery(Sankhya.Connect(), "select max(nunota) from tgfcab where chavenfe like'%"+Buscar.split(":")[0] +"%' or nunota = '"+Buscar.split(":")[1]+"'").split(":");

				if(SankhyaSelect[0].length() >= 41) {
					if (SankhyaSelect[1].equals("1")) {
						if (Insert.Insert_Table(SankhyaSelect[0], String.format("https://topmix.com.br/Scanner/Notas_Automaticas/Processadas/%s", Buscar.split(":")[0] + ".jpg"), Sankhya.Connect(), "NOTAS_AUTOMATICAS", "TGFCAB").equals("1")) {
							System.out.println("Notas Automáticas -> Arquivo " + Buscar.split(":")[0] + " Movido para Processados");
							ftp.FtpMove(arquivo.getName(), String.format("Processadas/%s.jpg", Buscar.split(":")[0]));
						} else {
							ftp.FtpMove(arquivo.getName(), String.format("Lixeira/%s.jpg", arquivo.getName()));
							System.out.println("Notas Automáticas -> Arquivo " + arquivo.getName() + " Movido para Lixeira");
						}
					} else {
						ftp.FtpMove(arquivo.getName(), String.format("Lixeira/%s.jpg", arquivo.getName()));
						System.out.println("Notas Automáticas -> Arquivo " + arquivo.getName() + " Movido para Lixeira");
					}

				}else{
					ftp.FtpMove(arquivo.getName(), String.format("Processadas/%s.jpg", SankhyaSelect[0]));
					Insert.Insert_Table(SankhyaSelect[0], String.format("https://topmix.com.br/Scanner/Notas_Automaticas/Processadas/%s", SankhyaSelect[0] + ".jpg"), Sankhya.Connect(), "NOTAS_AUTOMATICAS", "TGFCAB");
					System.out.println("Notas Automáticas -> Arquivo " + SankhyaSelect[0] + " Movido para Processadas");

				}

				arquivoBase64.delete();
			}
		}
	}
}
