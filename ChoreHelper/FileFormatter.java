import java.io.*;
import java.util.*;
public class FileFormatter {
    //Utility class that reads and writes to files to store Chore data
    Scanner s;
    PrintWriter p;
    ArrayList<Chore> chores;
    public FileFormatter(ArrayList<Chore> arr) {
        //Creates a new FileFormatter object for reading and writing
        File file = new File(System.getProperty("user.home") + "/output.txt");
        Scanner scan = null;
        PrintWriter print = null;
        try {
            scan = new Scanner(file);
            print = new PrintWriter(file);
        }
        catch(FileNotFoundException e) {
            System.out.println("Unknown file");
        }
        s = scan;
        p = print;
        chores = arr;
    }
    public FileFormatter() {
        //Creates a new FileFormatter object for writing
        File file = new File(System.getProperty("user.home") + "/output.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        }
        catch(FileNotFoundException e) {
            System.out.println("Unknown file");
        }
        s = scan;
    }
    public void write() {
        //Writes all the chores onto a file
        for(int i = 0; i < chores.size(); i++) {
            p.println(chores.get(i).toString());
            for(int j = 0; j < chores.get(i).times.size(); j++) {
                p.println(chores.get(i).times.get(j).toString());
                for(int k = 0; k < chores.get(i).times.get(j).splits.size(); k++) {
                    p.println(chores.get(i).times.get(j).splits.get(k).toString());
                }
            }
        }
        p.close();
    }
    public ArrayList<Chore> read() {
        //reads the file and creates a list of chores from the file
        try {
            ArrayList<Chore> arr = new ArrayList<Chore>();
            while(s.hasNextLine()) {
                String line = s.nextLine();
                while(line.substring(0, 6).equals("Chore ")) {
                    Chore c = new Chore(line.substring(6));
                    if(s.hasNextLine()) {
                        line = s.nextLine();
                    }
                    else {
                        line = "aaaaaaa";
                    }
                    while(line.substring(0, 5).equals("Time ")) {
                        String str = "";
                        int index = 5;
                        while(!line.substring(index, index + 1).equals(" ")) {
                            str += line.substring(index, index + 1);
                            index++;
                        }
                        Time t = new Time(Float.parseFloat(str), Integer.parseInt(line.substring(index + 1)));
                        if(s.hasNextLine()) {
                            line = s.nextLine();
                        }
                        else {
                            line = "aaaaaaa";
                        }
                        while(line.substring(0, 6).equals("Split ")) {
                            String str1 = "";
                            int index1 = 6;
                            while(!line.substring(index1, index1 + 1).equals(">")) {
                                str1 += line.substring(index1, index1 + 1);
                                index1++;
                            }
                            index1++;
                            String str2 = "";
                            while(!line.substring(index1, index1 + 1).equals(" ")) {
                                str2 += line.substring(index1, index1 + 1);
                                index1++;
                            }
                            index1++;
                            Split split = new Split(str1, Float.parseFloat(str2), Integer.parseInt(line.substring(index1)));
                            t.addSplit(split);
                            if(s.hasNextLine()) {
                                line = s.nextLine();
                            }
                            else {
                                line = "aaaaaaaaaa";
                            }
                        }
                        c.addTime(t);
                    }
                    arr.add(c);
                }
            }
            return arr;
        }
        catch(Exception e) {
            e.printStackTrace();
        }        
        return null;
    }
}