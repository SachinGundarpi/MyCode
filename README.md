import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class JsonComparator {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON files
            JsonNode node1 = objectMapper.readTree(new File("file1.json"));
            JsonNode node2 = objectMapper.readTree(new File("file2.json"));

            // Compare JSON files
            compareJson(node1, node2, "");
            System.out.println("\nJSON files comparison completed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void compareJson(JsonNode node1, JsonNode node2, String path) {
        if (node1.equals(node2)) {
            return;
        }

        if (node1.isObject() && node2.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields1 = node1.fields();
            while (fields1.hasNext()) {
                Map.Entry<String, JsonNode> entry1 = fields1.next();
                String key = entry1.getKey();
                JsonNode value1 = entry1.getValue();
                JsonNode value2 = node2.get(key);
                if (value2 == null || !value1.equals(value2)) {
                    System.out.println("Mismatched key '" + key + "' at path: " + path + "." + key);
                } else {
                    compareJson(value1, value2, path + "." + key);
                }
            }

            Iterator<Map.Entry<String, JsonNode>> fields2 = node2.fields();
            while (fields2.hasNext()) {
                Map.Entry<String, JsonNode> entry2 = fields2.next();
                String key = entry2.getKey();
                JsonNode value2 = entry2.getValue();
                JsonNode value1 = node1.get(key);
                if (value1 == null) {
                    System.out.println("Key '" + key + "' not found in JSON 1 at path: " + path + "." + key);
                }
            }
        }

        if (node1.isArray() && node2.isArray()) {
            if (node1.size() != node2.size()) {
                System.out.println("Array size mismatch at path: " + path);
            }
            for (int i = 0; i < Math.min(node1.size(), node2.size()); i++) {
                compareJson(node1.get(i), node2.get(i), path + "[" + i + "]");
            }
        }
    }
}
