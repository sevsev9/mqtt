import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nico Ohler on 28.11.2019.
 */

public class ClientHandler implements Runnable {
    private Socket client;
    private HTTPAlarmServer server;
    private Gson gson = new Gson();

    private Timestamp from;
    private Timestamp to;
    private ArrayList<Alarm> alarms = new ArrayList<>();
    private String alarmsAsJSON;

    public ClientHandler(HTTPAlarmServer server, Socket client) {
        this.client = client;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            // get input + outputstream
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            // read and interpret the request
            interpretRequest(in.readLine());
            // todo alarms = server.dbManager.getAlarm(from, to);
            alarmsAsJSON = gson.toJson(alarms);
            System.out.println("\n\tCreate response and send it back to client");
            out.write(createResponse());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("\tClose connection");
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void interpretRequest(String request) {

        String[] attr = request.split(" ");
        String[] data = attr[1].split("(=|%20|&)");

        from = Timestamp.valueOf(data[1] + " " + data[2]);
        to = Timestamp.valueOf(data[4] + " " + data[5]);
    }

    public String createResponse() {

        String response = "POST /test HTTP/1.1\n" +
                "Content-Length: " + alarmsAsJSON.length() + "\n" +
                "Content-Type: application/json; charset=UTF-8" + "\n\n" + alarmsAsJSON;

        System.out.println("Response: " + response);

        return response;
    }
}