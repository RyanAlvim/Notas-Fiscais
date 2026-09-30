package Sankhya;

import java.math.BigDecimal;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class QueryCount {

	public static Integer CountLines(String jsessionID,String query) throws UnirestException, UnirestException {
        Unirest.setTimeouts(0,0);
        HttpResponse<String> response = Unirest.post("http://app.mgmix.com.br:8180/mge/service.sbr?serviceName=DbExplorerSP.executeQuery&outputType=json")
                .header("Cookie", String.format("JSESSIONID=%s",jsessionID))
                .header("Content-Type", "application/json")
                .body(String.format("{\n" +
                        "  \"serviceName\": \"DbExplorerSP.executeQuery\",\n" +
                        "  \"requestBody\": {\n" +
                        "    \"sql\": \"%s\"\n" +
                        "  }\n" +
                        "}",query)).asString();

        
        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONObject responseBody = jsonObject.getJSONObject("responseBody");

        JSONArray rows = responseBody.getJSONArray("rows");
        BigDecimal nupedido = rows.getJSONArray(0).getBigDecimal(0);
        return Integer.parseInt(String.valueOf(nupedido));


    }
}
