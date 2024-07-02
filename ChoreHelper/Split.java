import java.util.ArrayList;
public class Split {
    //represents a subsection of a Time object
    String name;
    float point;
    int position;
    public Split(String n, float p, int pos) {
        name = n;
        point = p;
        position = pos;
    }
    public String getName() {
        return name;
    }
    public float getPoint() {
        return point;
    }
    public int getPosition() {
        return position;
    }
    public float getDifference(int pos, Time t) {
        //returns the difference in time between two positions of splits in a Time object
        if(pos == 0) {
            for(int i = 0; i < t.splits.size(); i++) {
                if(t.splits.get(i).getPosition() == 0) {
                    return t.splits.get(i).getPoint();
                }
            }
        }
        else {
            float tempPos = 0, tempPos1 = 0;
            for(int i = 0; i < t.splits.size(); i++) {
                if(t.splits.get(i).getPosition() == pos) {
                    tempPos = t.splits.get(i).getPoint();
                }
            }
            for(int i = 0; i < t.splits.size(); i++) {
                if(t.splits.get(i).getPosition() == pos - 1) {
                    tempPos1 = t.splits.get(i).getPoint();
                }
            }
            return tempPos - tempPos1;
        }
        return -1;
    }
    public String toString() {
        return "Split " + name + ">" + point + " " + position;
    }
}