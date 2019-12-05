import java.sql.Timestamp;

/**
 * Created by Nico Ohler on 21.11.2019.
 */
public class Alarm {
    private int id;
    private Timestamp timestamp;
    private String description;
    private String handler;
    private String origin;

    public Alarm(int id, Timestamp timestamp, String description, String handler, String origin) {
        this.id = id;
        this.timestamp = timestamp;
        this.description = description;
        this.handler = handler;
        this.origin = origin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Alarm: {" +
                "id: " + id +
                ", timestamp: " + timestamp +
                ", description: " + description +
                ", handler: " + handler +
                ", origin: " + origin +
                '}';
    }
}
