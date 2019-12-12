import java.io.Serializable;
import java.util.HashMap;

public class SensorCollection implements Serializable {

    Integer lastKey;
    HashMap<Integer, Sensor> bois;

    public SensorCollection() {
        this.bois = new HashMap<Integer, Sensor>();
        this.lastKey = 0;
    }

    public void addBoi(Sensor ab) {
        bois.put(++lastKey, ab);
    }
}
