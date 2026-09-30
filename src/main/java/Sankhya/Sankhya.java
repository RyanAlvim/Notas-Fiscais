package Sankhya;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Sankhya {

	
	public static String Connect() throws UnirestException {
		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.post("http://app.mgmix.com.br:8180/mge/service.sbr?serviceName=MobileLoginSP.login&outputType=json")
				.header("Content-Type", "text/plain")
				.body("{\n" +
                        "   \"serviceName\":\"MobileLoginSP.login\",\n" +
                        "   \"requestBody\":{\n" +
                        "      \"NOMUSU\":{\n" +
                        "         \"$\":\"Google\"\n" +
                        "      },\n" +
                        "      \"INTERNO\":{\n" +
                        "         \"$\":\"Google123\"\n" +
                        "      },\n" +
                        "      \"KEEPCONNECTED\":{\n" +
                        "         \"$\":\"S\"\n" +
                        "      }\n" +
                        "   }\n" +
                        "}")
                .asString();
		
		JSONObject jsonObject = new JSONObject(response.getBody());
        JSONObject id = new JSONObject(String.valueOf(jsonObject.get("responseBody")));
        JSONObject jsessionId = new JSONObject(String.valueOf(id.get("jsessionid")));


        return String.valueOf(jsessionId.get("$"));
				
	}
	
	
}
