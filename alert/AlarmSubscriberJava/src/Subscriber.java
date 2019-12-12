import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Subscriber implements MqttCallback {
    private static final String brokerUrl = "tcp://docker.htl-wels.at:1883";
    private static final String clientId = "clientId";

    private static final String topic = "test";
    String username = "ahit5";
    String password = "niceDay";

    Gson g = new Gson();
    AlertHandler alertHandler = new AlertHandler();

    public static void main(String[] args) {
        new Subscriber().subscribe(topic);
    }

    public void subscribe(String topic) {
        //	logger file name and pattern to log
        MemoryPersistence persistence = new MemoryPersistence();
        try {

            MqttClient sampleClient = new MqttClient(brokerUrl, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());

            connOpts.setCleanSession(true);

            System.out.println("checking");
            System.out.println("Mqtt Connecting to broker: " + brokerUrl);

            sampleClient.connect(connOpts);
            System.out.println("Mqtt Connected");

            sampleClient.setCallback(this);
            sampleClient.subscribe(topic);


            System.out.println("Subscribed");
            System.out.println("Listening");

        } catch (MqttException me) {
            System.out.println(me);
        }
    }

    //Called when the client lost the connection to the broker
    public void connectionLost(Throwable arg0) {

    }

    //Called when a outgoing publish is complete
    public void deliveryComplete(IMqttDeliveryToken arg0) {

    }

    // todo react to incoming alerts
    public void messageArrived(String topic, MqttMessage message) throws Exception {

        // todo notify by email
        // alertHandler.sendErrorMail(new Alarm(), Arrarylist, gmail, username, password);
    }
}