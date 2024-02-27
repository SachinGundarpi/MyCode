import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.util.Iterator;

public class JsonReaderExample {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try {
            // Path to your JSON file
            FileReader reader = new FileReader("path_to_your_json_file.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            Iterator<?> iterator = jsonObject.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = (String) jsonObject.get(key);
                System.out.println("Key: " + key + ", Value: " + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
