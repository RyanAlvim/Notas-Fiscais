package Notas_Automaticas;

import java.math.BigDecimal;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Select {
	
	public static String SelectQuery(String jsessionID,String query) throws UnirestException, UnirestException {
            try {
                    Unirest.setTimeouts(0, 0);
                    HttpResponse<String> response = Unirest.post("http://app.mgmix.com.br:8180/mge/service.sbr?serviceName=DbExplorerSP.executeQuery&outputType=json")
                            .header("Cookie", String.format("JSESSIONID=%s", jsessionID))
                            .header("Content-Type", "application/json")
                            .body(String.format("{\n" +
                                    "  \"serviceName\": \"DbExplorerSP.executeQuery\",\n" +
                                    "  \"requestBody\": {\n" +
                                    "    \"sql\": \"%s\"\n" +
                                    "  }\n" +
                                    "}", query)).asString();


                    JSONObject status = new JSONObject(response.getBody());
                    JSONObject row = new JSONObject(String.valueOf(status.get("responseBody")));
                    JSONArray array = new JSONArray(String.valueOf(row.get("rows")));
                    JSONArray nunota = new JSONArray(String.valueOf(array.get(0)));


                    return String.valueOf(nunota.get(0) + ":" + status.get("status"));

            }catch(Exception e){
                    return "null:0";
            }
    }

}
