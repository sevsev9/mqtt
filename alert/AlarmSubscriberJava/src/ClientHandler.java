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

    private static String BAD_REQUEST = "400 Bad Request";
    private static String OK = "200 OK";
    private static String VERSION = "HTTP/1.1";
    private final static String LINE = "-----------------------------------";

    private ArrayList<Alarm> alarms;
    private HTTPAlarmServer server;

    private Timestamp from;
    private Timestamp to;

    private String status = OK;
    private String filename;
    private String data = "";

    private String request;
    private String response;

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
            request = in.readLine();
            interpretRequest();
            //alarms = server.dbManager.getAlarm(from, to);
            createResponse();
            System.out.println("\n\tSend response back to client");
            out.write(response);
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

    /*
    POST /test HTTP/1.1
    Host: foo.example
    Content-Type: text/plain
    Content-Length: 48

    From=2019-12-24 23:59:591&To=2020-01-20 00:00:00
     */

    public void interpretRequest() throws IOException {
        // todo NullPointerException, request is null, 40s
        if (request == null) {
            System.out.println("Empty request");
            status = BAD_REQUEST;
        } else {
            String[] lines = request.split("\n");
            String[] args = lines[0].split(" ");
            String[] data = lines[5].split("=|&");

            // check if POST
            if (args[0].toUpperCase().compareTo("POST") != 0)
                status = BAD_REQUEST;

            // check version
            if (args[2].compareTo(VERSION) != 0)
                status = BAD_REQUEST;

            from = Timestamp.valueOf(data[1]);
            to = Timestamp.valueOf(data[3]);
        }
    }


    public void createResponse() {

        String as = " POST /test HTTP/1.1\n" +
                "    Host: GeilaHund.com\n" +
                "    Content-Type: application/x-www-form-urlencoded\n" +
                "    Content-Length: 48\n" +
                "\n" +
                "    From=2019-12-24 23:59:591&To=2020-01-20 00:00:00";
        System.out.println("\n\tCreate response\n\t" + LINE);
        String contentLength = "Content-Length: " + data.length() + "\n";
        String contentType = "Content-Type: text/html; charset=UTF-8" + "\n";

        response = VERSION + " " + status + "\n" + server + contentLength + contentType + "\n" + data;
        System.out.println("\t" + VERSION + " " + status + "\n\t" + "\t" + contentLength + "\t" + contentType + "\n\t" + data);

        response = "POST "
    }
}