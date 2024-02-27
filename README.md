import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class JsonReaderExample {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try {
            // Path to your JSON file
            FileReader reader = new FileReader("path_to_your_json_file.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            printJsonObject(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printJsonObject(JSONObject jsonObject) {
        for (Object key : jsonObject.keySet()) {
            String keyStr = (String) key;
            Object value = jsonObject.get(keyStr);

            if (value instanceof JSONObject) {
                System.out.println("Key: " + keyStr);
                printJsonObject((JSONObject) value);
            } else if (value instanceof JSONArray) {
                System.out.println("Key: " + keyStr);
                JSONArray jsonArray = (JSONArray) value;
                printJsonArray(jsonArray);
            } else {
                System.out.println("Key: " + keyStr + ", Value: " + value);
            }
        }
    }

    private static void printJsonArray(JSONArray jsonArray) {
        for (Object obj : jsonArray) {
            if (obj instanceof JSONObject) {
                printJsonObject((JSONObject) obj);
            } else if (obj instanceof JSONArray) {
                printJsonArray((JSONArray) obj);
            } else {
                System.out.println("Value: " + obj);
            }
        }
    }
}
