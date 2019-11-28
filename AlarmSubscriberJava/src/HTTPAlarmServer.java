import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Nico Ohler on 28.11.2019.
 */

public class HTTPAlarmServer {

    private ExecutorService executorService = Executors.newCachedThreadPool();
    DBManager dbManager = new DBManager();
    // dbManager.connect();

    public HTTPAlarmServer(String directory, int port) {
        System.out.println("Server started\n" +
                "Listen for new clients\n-----------------------------------");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            SimpleDateFormat ft = new SimpleDateFormat("dd MM yyyy hh:mm:ss");
            while (true) {
                // listening for new clients
                Socket client = serverSocket.accept();

                // create log
                Date dNow = new Date();
                String log = "Client: " + client.getInetAddress() + " connected at " + ft.format(dNow) + "\n";
                System.out.println(log);

                // start new thread for each client
                executorService.execute(new ClientHandler(this, client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 8080;

        new HTTPAlarmServer("", port);
    }
}
