import java.io.Serializable;
import java.util.HashMap;

public class SensorCollection implements Serializable {

    Integer lastKey;
    HashMap<Integer, Sensor> sensors;

    public SensorCollection() {
        this.sensors = new HashMap<Integer, Sensor>();
        this.lastKey = 0;
    }

    public void addSensor(Sensor ab) {
        sensors.put(++lastKey, ab);
    }

    @Override
    public String toString() {
        return "SensorCollection{\n" +
                "lastKey=" + lastKey + '\n' +
                ", sensors=" + sensors.toString() + '\n' +
                '}' + '\n';
    }
}
