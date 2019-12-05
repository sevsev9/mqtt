import org.json.simple.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JSONObject sampleObject = new JSONObject();
        sampleObject.put("name", "Stackabuser");
        sampleObject.put("age", 35);

        JSONArray messages = new JSONArray();
        messages.add("Hey!");
        messages.add("What's up?!");

        sampleObject.put("messages", messages);
        try(FileWriter fw = new FileWriter("testfile.txt")){
            fw.write(sampleObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
