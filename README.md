import org.json.JSONException;
import org.json.JSONObject;

public class NestedJsonPrinter {

    public static void main(String[] args) {
        String jsonString = "{\"name\":\"John\",\"age\":30,\"address\":{\"street\":\"123 Main St\",\"city\":\"New York\"},\"children\":[{\"name\":\"Alice\",\"age\":5},{\"name\":\"Bob\",\"age\":8}]}";

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            printJsonObject(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void printJsonObject(JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                System.out.println("Key: " + key);
                printJsonObject((JSONObject) value);
            } else {
                System.out.println("Key: " + key + ", Value: " + value);
            }
        }
    }
}
