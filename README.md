import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileReader;

public class JsonPrinter {

    public static void main(String[] args) {
        try {
            // Path to your JSON file
            String filePath = "path_to_your_json_file.json";
            JSONObject jsonObject = readJsonFromFile(filePath);
            printJson(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONObject readJsonFromFile(String filePath) throws JSONException {
        try (FileReader reader = new FileReader(filePath)) {
            StringBuilder content = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                content.append((char) i);
            }
            return new JSONObject(content.toString());
        } catch (Exception e) {
            throw new JSONException("Error reading JSON file: " + e.getMessage());
        }
    }

    private static void printJson(JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                System.out.println("Key: " + key);
                printJson((JSONObject) value);
            } else {
                System.out.println("Key: " + key + ", Value: " + value);
            }
        }
    }
}
