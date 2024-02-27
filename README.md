import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;

public class JsonReaderSeleniumExample {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try {
            // Path to your JSON file
            FileReader reader = new FileReader("path_to_your_json_file.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            // Initialize WebDriver
            System.setProperty("webdriver.chrome.driver", "path_to_chromedriver.exe");
            WebDriver driver = new ChromeDriver();

            // Example usage: Print the URL from the JSON
            String url = (String) jsonObject.get("url");
            System.out.println("URL: " + url);

            // Example usage: Print nested elements
            JSONObject elements = (JSONObject) jsonObject.get("elements");
            printNestedElements(elements);

            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printNestedElements(JSONObject elements) {
        for (Object key : elements.keySet()) {
            String keyStr = (String) key;
            Object value = elements.get(keyStr);

            if (value instanceof JSONObject) {
                System.out.println("Key: " + keyStr);
                printNestedElements((JSONObject) value);
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
                printNestedElements((JSONObject) obj);
            } else if (obj instanceof JSONArray) {
                printJsonArray((JSONArray) obj);
            } else {
                System.out.println("Value: " + obj);
            }
        }
    }
}
