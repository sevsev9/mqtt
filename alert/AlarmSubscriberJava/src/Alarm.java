import java.sql.Date;

/**
 * Created by Nico Ohler on 21.11.2019.
 */
public class Alarm {
    private int id;
    private Date timestamp;
    private String description;
    private String handler;
    private String origin;

    public Alarm(int id, Date timestamp, String description, String handler, String origin) {
        this.id = id;
        this.timestamp = timestamp;
        this.description = description;
        this.handler = handler;
        this.origin = origin;
    }

    public Alarm(Date timestamp, String description, String handler, String origin) {
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
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
}