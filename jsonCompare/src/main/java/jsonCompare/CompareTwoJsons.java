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
            if (compareJson(node1, node2, "")) {
                System.out.println("JSON files are equal.");
            } else {
                System.out.println("JSON files are not equal.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean compareJson(JsonNode node1, JsonNode node2, String path) {
        if (node1.equals(node2)) {
            return true;
        }

        if (node1.isObject() && node2.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields1 = node1.fields();
            while (fields1.hasNext()) {
                Map.Entry<String, JsonNode> entry1 = fields1.next();
                String key = entry1.getKey();
                JsonNode value1 = entry1.getValue();
                JsonNode value2 = node2.get(key);
                if (value2 == null || !compareJson(value1, value2, path + "." + key)) {
                    return false;
                }
            }
            return true;
        }

        if (node1.isArray() && node2.isArray()) {
            if (node1.size() != node2.size()) {
                return false;
            }
            for (int i = 0; i < node1.size(); i++) {
                if (!compareJson(node1.get(i), node2.get(i), path + "[" + i + "]")) {
                    return false;
                }
            }
            return true;
        }

        System.out.println("JSON not matching at path: " + path);
        return false;
    }
}
