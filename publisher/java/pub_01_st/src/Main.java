import com.google.gson.Gson;

import java.io.*;

public class Main {
    public static void main(String[] args) {


        Gson gs = new Gson();
        SensorCollection ab = new SensorCollection();

        try (BufferedReader br = new BufferedReader(new FileReader("arduino.json"))) {
            ab.addBoi(gs.fromJson(br, Sensor.class));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            ab.addBoi(new Sensor(String.format("%02d:%02d:%02d", (Math.round(Math.random() * 100) % 12), (Math.round(Math.random() * 100) % 60), (Math.round(Math.random() * 100) % 60)),
                    (Math.round(Math.random() * 10000d))/100d,
                    (Math.round(Math.random() * 10000d))/100d,
                    (Math.round(Math.random() * 10000d))/100d
            ));
        }

        try (FileWriter fw = new FileWriter("testfile.json")) {
            fw.write(gs.toJson(ab));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
