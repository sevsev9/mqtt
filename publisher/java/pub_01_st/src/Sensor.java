import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Sensor implements Serializable {
    String time;
    Double temp;
    Double humi;
    Double pres;

    public Sensor(String time, Double temp, Double humi, Double press) {
        this.time = time;
        this.temp = temp;
        this.humi = humi;
        this.pres = press;
    }

    @Override
    public String toString() {
        return "Sensor{" +'\n' +
                "\ttime='" + time + '\'' +'\n' +
                "\ttemp=" + temp +'\n' +
                "\thumi=" + humi +'\n' +
                "\tpres=" + pres +'\n' +
                '}';
    }
}
