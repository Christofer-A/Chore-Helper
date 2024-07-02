import java.util.ArrayList;
public class Chore {
    //represents a chore
    ArrayList<Time> times;
    String name;
    public Chore(String n) {
        name = n;
        times = new ArrayList<Time>();
    }
    public String getName() {
        return name;
    }
    public void addTime(Time t) {
        times.add(t);
    }
    public String toString() {
        return "Chore " + name;
    }
}