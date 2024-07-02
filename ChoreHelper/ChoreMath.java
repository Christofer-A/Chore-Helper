import java.util.ArrayList;
public class ChoreMath {
    //Utility class that does the math calculations for chores
    public ChoreMath() {
        
    }
    public float getAverage(Chore c) {
        //returns the average times of a Chore
        if(c.times.size() == 0) {
            return 0;
        }
        float average = 0;
        int counter = 0;
        for(int i = 0; i < c.times.size(); i++) {
            average += c.times.get(i).getTime();
            counter++;
        }
        return average / counter;
    }
    public float getStandardDeviation(Chore c) {
        //returns the standard deviation of times in a Chore
        if(c.times.size() == 0) {
            return 0;
        }
        float average = getAverage(c);
        int counter = 0;
        float std = 0;
        for(int i = 0; i < c.times.size(); i++) {
            std += (c.times.get(i).getTime() - average) * (c.times.get(i).getTime() - average);
            counter++;
        }
        return (float)Math.sqrt(std/counter);
    }
    public float getChangeOverTime(Chore c) {
        //returns the percent change over time between the first time and the last time in a Chore
        if(c.times.size() == 0) {
            return 0;
        }
        float t1 = c.times.get(0).getTime();
        float t2 = c.times.get(c.times.size() - 1).getTime();
        return 100 * ((t1 - t2) / t2);
    }
    public String getBestSplit(Chore c) {
        //returns the Split name with the lowest average time
        if(c.times.size() == 0) {
            return "N/A";
        }
        int counter1 = 0;
        for(int i = 0; i < c.times.size(); i++) {
            if(c.times.get(i).splits.size() != 0) {
                counter1 = 1;
            }
        }
        if(counter1 == 0) {
            return "N/A";
        }
        int highestSplit = 0;
        ArrayList<SplitList> namesOfSplits = new ArrayList<SplitList>();
        for(int i = 0; i < c.times.size(); i++) {
            for(int j = 0; j < c.times.get(i).splits.size(); j++) {
                Split s = c.times.get(i).splits.get(j);
                int counter = 0;
                for(int k = 0; k < namesOfSplits.size(); k++) {
                    if(s.getName().equals(namesOfSplits.get(k).getName())) {
                        namesOfSplits.get(k).addSplitTime(s.getDifference(s.getPosition(), c.times.get(i)));
                        counter++;
                    }
                }
                if(counter == 0){
                    namesOfSplits.add(new SplitList(s.getName()));
                    namesOfSplits.get(namesOfSplits.size() - 1).addSplitTime(s.getDifference(s.getPosition(), c.times.get(i))); 
                }
            }
        }
        float min = namesOfSplits.get(0).getAverage();
        String name = namesOfSplits.get(0).getName();
        for(int i = 0; i < namesOfSplits.size(); i++) {
            if(namesOfSplits.get(i).getAverage() < min) {
                min = namesOfSplits.get(i).getAverage();
                name = namesOfSplits.get(i).getName();
            }
        }
        return name;
    }
    public String getWorstSplit(Chore c) {
        //returns the Split name with the highest average time
        if(c.times.size() == 0) {
            return "N/A";
        }
        int counter1 = 0;
        for(int i = 0; i < c.times.size(); i++) {
            if(c.times.get(i).splits.size() != 0) {
                counter1 = 1;
            }
        }
        if(counter1 == 0) {
            return "N/A";
        }
        int highestSplit = 0;
        ArrayList<SplitList> namesOfSplits = new ArrayList<SplitList>();
        for(int i = 0; i < c.times.size(); i++) {
            for(int j = 0; j < c.times.get(i).splits.size(); j++) {
                Split s = c.times.get(i).splits.get(j);
                int counter = 0;
                for(int k = 0; k < namesOfSplits.size(); k++) {
                    if(s.getName().equals(namesOfSplits.get(k).getName())) {
                        namesOfSplits.get(k).addSplitTime(s.getDifference(s.getPosition(), c.times.get(i)));
                        counter++;
                    }
                }
                if(counter == 0){
                    namesOfSplits.add(new SplitList(s.getName()));
                    namesOfSplits.get(namesOfSplits.size() - 1).addSplitTime(s.getDifference(s.getPosition(), c.times.get(i))); 
                }
            }
        }
        float max = namesOfSplits.get(0).getAverage();
        String name = namesOfSplits.get(0).getName();
        for(int i = 0; i < namesOfSplits.size(); i++) {
            if(namesOfSplits.get(i).getAverage() > max) {
                max = namesOfSplits.get(i).getAverage();
                name = namesOfSplits.get(i).getName();
            }
        }
        return name;
    }
}