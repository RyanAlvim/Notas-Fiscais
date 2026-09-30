package Json;

import org.json.JSONArray;
import org.json.JSONObject;

public class Json {

    private static JSONObject jsonObject;
    private static JSONArray jArray;
    private static JSONArray textResponse;
    private static JSONObject descriptionJson;
    public static String Json(String body){
        jsonObject = new JSONObject(body);
        jArray = jsonObject.getJSONArray("responses");

        for(int i = 0; i < jArray.length(); i++){

            jsonObject = jArray.getJSONObject(i);
            textResponse = jsonObject.getJSONArray("textAnnotations");

        }
        String parseTextResponse = String.valueOf(textResponse.get(0));

        descriptionJson = new JSONObject(parseTextResponse);
        return descriptionJson.getString("description");
    }

}
