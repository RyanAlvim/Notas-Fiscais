package Google;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Google {

	public static String Request(String base64) throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> resposta = Unirest.post("https://vision.googleapis.com/v1/images:annotate?key=AIzaSyDqcX1xrDe0sDH8R219Lw4FYu2K4T_aPx4")
                .header("Content-Type", "application/json")
                .body(String.format("{\n 'requests': [\n {\n 'image': {\n  'content':'%s'\n},\n 'features': [\n {\n 'type': 'TEXT_DETECTION'\n}\n ]\n  }\n ]\n }", base64))
                .asString();

        return resposta.getBody();

    }
}
