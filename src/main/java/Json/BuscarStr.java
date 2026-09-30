package Json;

public class BuscarStr {
	
	public static String Busca(String string, String split) {
		
		String stringNormal[] = string.split(split);

		String numeroEntrega[] = stringNormal[1].split("N. ENTREGA:");

		String numeroEntregaReal[] = numeroEntrega[1].split("\n");

		String chaveAcesso[] = stringNormal[1].split("\n");


		System.out.println(chaveAcesso[1].replaceAll(" ", ""));
		System.out.println(numeroEntregaReal[0].trim().equals(""));
		return chaveAcesso[1].replaceAll(" ", "")+":"+(numeroEntregaReal[0].trim().equals("") ? "-1" : numeroEntregaReal[0].trim());
	}

}
