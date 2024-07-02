import java.util.ArrayList;
public class Time {
    //represents a time of a Chore
    float time;
    int date;
    ArrayList<Split> splits;
    public Time(float t, int d) {
        time = t;
        date = d;
        splits = new ArrayList<Split>();
    }
    public Time(float t, int d, ArrayList<Split> arr) {
        time = t;
        date = d;
        splits = arr;
    }
    public float getTime() {
        return time;
    }
    public int getDate() {
        return date;
    }
    public void setSplit(int ind, Split s) throws InvalidSplitException {
        //checks to make sure a proper Split time has been inputted and adds it to the list of Splits
        for(int i = 0; i < splits.size(); i++) {
            if(s.getPosition() == splits.get(i).getPosition()) {
                throw new InvalidSplitException("Duplicate position of split");
            }
            else if(splits.get(i).getPosition() == (s.getPosition() - 1)) {
                if(splits.get(i).getPoint() > s.getPoint()) {
                    throw new InvalidSplitException("Split time incorrect");
                }
            }
        }
        splits.add(ind, s);
    }
    public void addSplit(Split s) {
        splits.add(s);
    }
    public String toString() {
        return "Time " + time + " " + date;
    }
}