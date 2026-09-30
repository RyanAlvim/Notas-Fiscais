package Main;

import java.io.IOException;
import java.net.SocketException;

import com.mashape.unirest.http.exceptions.UnirestException;

import Bombas.Bombas;
import Notas_Automaticas.Notas_Automaticas;
import Notas_Manuais.Notas_Manuais;
import Sankhya.QueryCount;
import Sankhya.Sankhya;
import Vistorias.Vistorias;

public class Main {
	
	public static void main(String[] args) throws SocketException, IOException, UnirestException {
		while(true) {
			try {
				Notas_Manuais.NManuais();
				Notas_Automaticas.NAutomaticas();
				Bombas.Bombas();
				Vistorias.Vistorias();
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	}
}
