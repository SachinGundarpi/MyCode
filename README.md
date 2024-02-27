import org.json.JSONObject;

public class JsonPrinter {

    public static void main(String[] args) {
        String jsonStr = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\",\"children\":[{\"name\":\"Alice\",\"age\":5},{\"name\":\"Bob\",\"age\":8}],\"address\":{\"street\":\"123 Street\",\"zip\":\"10001\"}}";

        JSONObject jsonObject = new JSONObject(jsonStr);
        printJson(jsonObject);
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
