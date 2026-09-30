package Notas_Manuais;

import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.net.ftp.FTPFile;

import com.mashape.unirest.http.exceptions.UnirestException;

import Ftp.Ftp;
import Notas_Automaticas.Notas_Automaticas;
import Sankhya.Insert;
import Sankhya.Sankhya;

public class Notas_Manuais {
	
	public static void NManuais() throws SocketException, IOException, UnirestException {

		try {
			Ftp ftp = new Ftp("", "", "");
			System.out.println("Conectado: " + ftp.isConnected());
			System.out.println(ftp.joinDirectory("Scanner/Notas_Manuais"));

			for (FTPFile arquivo : ftp.listFiles()) {
				if (arquivo.getName().contains(".jpg")) {
					String nomeArq[] = arquivo.getName().split("\\.");

					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
					String horaFormatada = dtf.format(LocalDateTime.now());
					String arquivoFTP = nomeArq[0] + "-" + horaFormatada.replaceAll(":", "") + ".jpg";

					if (Insert.Insert_Table(nomeArq[0], String.format("https://topmix.com.br/Scanner/Notas_Manuais/Processadas/%s", arquivoFTP), Sankhya.Connect(), "NOTAS_MANUAIS", "TGFCAB").equals("1")) {

						System.out.println("Notas Manuais -> " + arquivo.getName() + "Arquivo Movido para processadas!");

						ftp.FtpMove(nomeArq[0] + ".jpg", String.format("Processadas/%s", arquivoFTP));
					} else {
						System.out.println("Notas Manuais ->" + arquivo.getName() + "Arquivo Movido para Lixeira!");

						ftp.FtpMove(arquivo.getName(), String.format("Lixeira/%s.jpg", arquivo.getName()));

					}
				}

			}

		}catch(Exception e){
			Notas_Automaticas.NAutomaticas();
		}
	}
		
	}
