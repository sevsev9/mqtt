import com.google.gson.Gson;

import java.io.*;

public class Main {

    /** The broker url. */
    //private static final String brokerUrl ="tcp://docker.htl-wels.at:1883";

    /** The client id. */
    //private static final String clientId = "clientId";

    /** The topic. */
    //private static final String topic = "test";


    public static void main(String[] args) {


        //String username = "ahit5";
        //String password = "niceDay";

        Gson gs = new Gson();
        SensorCollection ab = new SensorCollection();
        String[] str = {"testfile1.json", "testfile2.json",
                "testfile3.json", "testfile4.json",
                "testfile5.json", "testfile6.json",};

        for (String file : str) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                ab.addSensor(gs.fromJson(br, Sensor.class));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 10; i++) {
            ab.addSensor(new Sensor(String.format("%02d:%02d:%02d", (Math.round(Math.random() * 100) % 12), (Math.round(Math.random() * 100) % 60), (Math.round(Math.random() * 100) % 60)),
                    (Math.round(Math.random() * 10000d))/100d,
                    (Math.round(Math.random() * 10000d))/100d,
                    (Math.round(Math.random() * 10000d))/100d
            ));
        }

        System.out.println(ab.toString());

        try (FileWriter fw = new FileWriter("testfile_merge.json")) {
            fw.write(gs.toJson(ab));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
