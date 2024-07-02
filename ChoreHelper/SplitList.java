import java.util.ArrayList;
public class SplitList extends ArrayList<Float> {
    //represents an ArrayList with a name
    String name; 
    public SplitList(String n) {
        name = n;
    }
    public String getName() {
        return name;
    }
    public void addSplitTime(float f) {
        this.add(f);
    }
    public float getAverage() {
        //returns the average of the differences in the times between splits in a SplitList
        float sum = 0.0f;
        int counter = 0;
        for(int i = 0; i < this.size(); i++) {
            sum += this.get(i); 
            counter++;
        }
        return sum / counter;
    }
}