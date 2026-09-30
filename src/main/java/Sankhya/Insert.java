package Sankhya;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Insert {

	 public static String Insert_Table(String nuscan, String caminho, String jsessionID,String evento,String tabela) throws UnirestException {
	        Unirest.setTimeouts(0, 0);
	        HttpResponse<String> response = Unirest.post("http://app.mgmix.com.br:8180/mge/service.sbr?serviceName=DatasetSP.save&outputType=json")
	                .header("Cookie", String.format("JSESSIONID=%s",jsessionID))
	                .header("Content-Type", "application/json")
	                .body(String.format("{\r\n"
	                		+ "      \"serviceName\":\"DatasetSP.save\",\r\n"
	                		+ "      \"requestBody\":{\r\n"
	                		+ "        \"dataSetID\":\"00C\",\r\n"
	                		+ "        \"entityName\":\"AD_SCANNERS\",\r\n"
	                		+ "        \"standAlone\":false,\r\n"
	                		+ "        \"fields\":[\r\n"
	                		+ "          \"NUSCAN\",\r\n"
	                		+ "          \"CHAVE1\",\r\n"
	                		+ "          \"CHAVE2\",\r\n"
	                		+ "          \"CHAVE3\",\r\n"
	                		+ "          \"CAMINHO\",\r\n"
	                		+ "          \"EVENTO\",\r\n"
	                		+ "          \"TABELA\"\r\n"
	                		+ "        ],\r\n"
	                		+ "        \"records\":[\r\n"
	                		+ "          {\r\n"
	                		+ "            \"pk\":{\r\n"
	                		+ "              \"NUSCAN\":\"%s\"\r\n"
	                		+ "            },\r\n"
	                		+ "            \"values\":{\r\n"
	                		+ "              \"1\":\"%s\",\r\n"
	                		+ "              \"2\":\"%s\",\r\n"
	                		+ "              \"3\":\"%s\",\r\n"
	                		+ "              \"4\":\"%s\",\r\n"
	                		+ "              \"5\":\"%s\",\r\n"
	                		+ "              \"6\":\"%s\"\r\n"
	                		+ "            }\r\n"
	                		+ "          }\r\n"
	                		+ "        ],\r\n"
	                		+ "        \"ignoreListenerMethods\":\"\",\r\n"
	                		+ "        \"clientEventList\":{\r\n"
	                		+ "        }\r\n"
	                		+ "      }\r\n"
	                		+ "    }", QueryCount.CountLines(jsessionID, "select max(nuscan) from AD_SCANNERS")+1,nuscan,"0","0",caminho,evento,tabela))
	                .asString();


	        JSONObject status = new JSONObject(response.getBody());

	        return String.valueOf(status.get("status"));

	    }
}
