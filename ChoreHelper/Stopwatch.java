import java.util.ArrayList;
public class Stopwatch extends Thread {
    //Class that functions as a stopwatch
    long start;
    String stop;
    String time;
    boolean isInUse;
    ChoreComponent chorecomp;
    public Stopwatch(ChoreComponent c) {
       //Creates a new Stopwatch object
       start = 0;
       stop = null;
       isInUse = false;
       chorecomp = c;
    }
    @Override
    public void run() {
        //Starts the stopwatch and updates the time label in the ChoreComponent object
        Begin();
        while(true) {
            time = getTime();
            chorecomp.time.setText(time);
        }
    }
    public void Begin() {
        //Starts the stopwatch
        if(!isInUse) {
            start = System.nanoTime() / 1000000000;
            isInUse = true;
        }
    }
    public String getTime() {
        //Starts a new thread and represents the time in a standard form
        Thread t = new Thread();
        try {
            t.sleep(1000);
            long time = System.nanoTime();
            time /= 1000000000;
            float currentTime = time - start;
            currentTime -= (currentTime - (int)currentTime);
            if(isInUse) {
                int counter = 0;
                String str = "";
                if(currentTime < 60) {
                    return "0:0:" + Math.round(currentTime);
                }
                while(currentTime >= 60 && counter < 2) {
                     currentTime /= 60;
                     counter++;
                }
                if(counter == 2) {
                    if(currentTime % 1 == 0) {
                        return (int)currentTime + ":0:0";
                    }
                    else {
                        int temp = (int)currentTime;
                        str += temp + ":";
                        currentTime -= temp;
                        currentTime *= 60;
                        if(currentTime % 1 == 0) {
                             return str += (int)currentTime + ":0";
                          }
                          else {
                            int temp1 = (int)currentTime;
                            str += temp1 + ":";
                            currentTime -= temp1;
                            currentTime *= 60;
                            str += Math.round(currentTime);
                         }
                    }
                }   
                else {
                    str += "0:";
                     if(currentTime % 1 == 0) {
                        return str += (int)currentTime + ":0";
                    }
                    else {
                        int temp1 = (int)currentTime;
                        str += temp1 + ":";
                        currentTime -= temp1;
                        currentTime *= 60;
                        str += Math.round(currentTime);
                    }
                }
                return str;
            }
            else {
                return "-1";
            }
        }
        catch(Exception e) {
            
        }
        return "-1";
    }
    public void Stop() {
        //Stops the stopwatch
        isInUse = false;
        chorecomp.time.setText("0:0:0");
    }
    public float getSeconds() {
        //returns the amount of seconds that has passed since the stopwatch has been started
        long time = System.nanoTime();
        time /= 1000000000;
        float currentTime = time - start;
        return currentTime;
    }
}