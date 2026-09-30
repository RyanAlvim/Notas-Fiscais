package Bombas;

public class BuscaPedido {
	
	public static String Busca(String string, String split) {
		try {
			String stringNormal[] = string.split(split);
			String chaveAcesso[] = stringNormal[1].split("\n");
			return chaveAcesso[0].trim();
		}catch(Exception e){
			return "";
		}
	}

}
