import org.json.JSONException;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NestedJsonComparator {

    public static void main(String[] args) {
        String filePath1 = "path_to_first_json_file.json";
        String filePath2 = "path_to_second_json_file.json";

        try {
            String jsonString1 = new String(Files.readAllBytes(Paths.get(filePath1)));
            String jsonString2 = new String(Files.readAllBytes(Paths.get(filePath2)));

            JSONObject jsonObject1 = new JSONObject(jsonString1);
            JSONObject jsonObject2 = new JSONObject(jsonString2);

            compareJsonObjects("", jsonObject1, jsonObject2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void compareJsonObjects(String currentKey, JSONObject jsonObject1, JSONObject jsonObject2) throws JSONException {
        for (String key : jsonObject1.keySet()) {
            String newKey = currentKey.isEmpty() ? key : currentKey + "." + key;

            Object value1 = jsonObject1.get(key);
            Object value2 = jsonObject2.get(key);

            if (value1 instanceof JSONObject && value2 instanceof JSONObject) {
                compareJsonObjects(newKey, (JSONObject) value1, (JSONObject) value2);
            } else if (!value1.equals(value2)) {
                System.out.println("Key: " + newKey + ", Value1: " + value1 + ", Value2: " + value2);
            }
        }
    }
}
