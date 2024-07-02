import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class ChoreComponent extends JPanel implements ActionListener {
    //represents the graphical components of a Chore
    JButton button, stopwatch, split;
    JLabel time, bestSplit, recommendation, average, percent, standardDeviation;
    JTextField field;
    Stopwatch s;
    Chore chore;
    int counter = 0;
    ArrayList<Split> arr = new ArrayList<Split>();
    ArrayList<Chore> listChores;
    int stopwatchcounter = 0;
    int splitcounter = 2;
    public ChoreComponent(Chore c, ArrayList<Chore> arr) {
        //Creates a new ChoreComponent by representing a chore on a panel after the start button was clicked
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        ChoreMath cm = new ChoreMath();
        JLabel name = new JLabel("<html><p>" + c.getName() + "</p></html>");
        JLabel average = new JLabel("<html><p>Average: " + cm.getAverage(c) + "</p></html>");
        JLabel percent = new JLabel("<html><p>Percent change over time: " + cm.getChangeOverTime(c) + "</p></html>");
        JLabel standardDeviation = new JLabel("<html><p>Standard Deviation: " + cm.getStandardDeviation(c) + "</html></p>");
        JLabel time = new JLabel("Enter Time:");
        JTextField field = new JTextField();
        JButton button = new JButton("Open");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        name.setFont(new Font("Serif", Font.BOLD, 30));
        average.setFont(new Font("Serif", Font.BOLD, 30));
        percent.setFont(new Font("Serif", Font.BOLD, 30));
        standardDeviation.setFont(new Font("Serif", Font.BOLD, 30));
        time.setFont(new Font("Serif", Font.BOLD, 30));
        this.add(name);
        this.add(Box.createRigidArea(new Dimension(0, 120)));
        this.add(average);
        this.add(Box.createRigidArea(new Dimension(0, 120)));
        this.add(percent);
        this.add(Box.createRigidArea(new Dimension(0, 120)));
        this.add(standardDeviation);
        this.add(Box.createRigidArea(new Dimension(0, 120)));
        this.add(time);
        this.add(field);
        this.add(Box.createRigidArea(new Dimension(0, 60)));
        this.add(button);
        this.button = button;
        this.field = field;
        this.average = average;
        this.percent = percent;
        this.standardDeviation = standardDeviation;
        field.addActionListener(this);
        button.addActionListener(this);
        chore = c;
        listChores = arr;
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            //Creates the frame in which the stopwatch, split button, recommendation, and bestSplit are on
            ChoreMath cm = new ChoreMath();
            String BestSplit = cm.getBestSplit(chore);
            String WorstSplit = cm.getWorstSplit(chore);
            JFrame frame = new JFrame();
            GridBagLayout layout = new GridBagLayout();
            GridBagConstraints constraints = new GridBagConstraints();
            JPanel panel = new JPanel(layout);
            JLabel time = new JLabel("0:0:0");
            JLabel bestSplit = new JLabel("<html><p>Best Split: " + BestSplit + "</html></p>");
            JLabel recommendation = new JLabel("<html><p>Recommendation: improve time on " + WorstSplit + "</html></p>");
            JButton stopwatch = new JButton("Start/Stop Stopwatch");
            JButton split = new JButton("Split"); 
            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 1;
            constraints.gridheight = 1;
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.insets = new Insets(0, 0, 0, 600);
            panel.add(new JLabel(""), constraints);
            constraints.insets = new Insets(0, 0, 0, 0);
            constraints.gridx = 1;
            panel.add(time, constraints);
            constraints.gridx = 1;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            constraints.gridheight = 1;
            constraints.insets = new Insets(0, 0, 100, 0);
            constraints.ipadx = 0;
            constraints.ipady = 20;
            panel.add(stopwatch, constraints);
            constraints.gridx = 1;
            constraints.gridy = 4;
            constraints.gridwidth = 1;
            constraints.gridheight = 2;
            constraints.ipady = 10;
            constraints.insets = new Insets(0, 0, 0, 0);
            panel.add(split, constraints);
            constraints.gridx = 2;
            constraints.gridy = 0;
            constraints.gridwidth = 1;
            constraints.gridheight = 1;
            constraints.ipadx = 100;
            constraints.ipady = 0;
            constraints.insets = new Insets(0, 300, 0, 0);
            constraints.anchor = GridBagConstraints.EAST;
            panel.add(bestSplit, constraints);
            constraints.gridy = 4;
            panel.add(recommendation, constraints);
            time.setFont(new Font("Serif", Font.BOLD, 100));
            bestSplit.setFont(new Font("Serif", Font.BOLD, 60));
            recommendation.setFont(new Font("Serif", Font.BOLD, 30));
            Stopwatch s = new Stopwatch(this);
            this.time = time;
            this.stopwatch = stopwatch;
            this.split = split;
            this.bestSplit = bestSplit;
            this.recommendation = recommendation;
            this.s = s;
            frame.add(panel);
            frame.pack();
            frame.setBounds(0, 0, 1920, 1080);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            stopwatch.addActionListener(this);
            split.addActionListener(this);
        }
        else if(e.getSource() == stopwatch) {
            if(counter == 0) {
                //starts the stopwatch
                counter = 1;
                s.start();
                stopwatchcounter = 1;
            }
            else {
                //stops the stopwatch and updates average, percent, standardDeviation, bestSplit, and recommendation as well as the file
                counter = 0;
                float seconds = s.getSeconds();
                ArrayList<Split> arr1 = new ArrayList<Split>();
                for(int i = 0; i < arr.size(); i++) {
                    arr1.add(arr.get(i));
                }
                if(chore.times.size() == 0) {
                    chore.addTime(new Time(seconds, 1, arr1));
                }
                else {
                    chore.addTime(new Time(seconds, chore.times.get(chore.times.size() - 1).getDate() + 1, arr1));
                }
                s.stop();
                s.Stop();
                stopwatchcounter = 0;
                splitcounter = 2;
                s = new Stopwatch(this);
                while(!arr.isEmpty()) {
                    arr.remove(0);
                }
                ChoreMath cm = new ChoreMath();
                FileFormatter f = new FileFormatter(listChores);
                bestSplit.setText("<html><p>Best Split: " + cm.getBestSplit(chore) + "</html></p>");
                recommendation.setText("<html><p>Recommendation: improve time on " + cm.getWorstSplit(chore) + "</html></p>");
                average.setText("<html><p>Average: " + cm.getAverage(chore) + "</p></html>");
                percent.setText("<html><p>Percent change over time: " + cm.getChangeOverTime(chore) + "</p></html>");
                standardDeviation.setText("<html><p>Standard Deviation: " + cm.getStandardDeviation(chore) + "</html></p>");
                f.write();
            }
        }
        else if(e.getSource() == split && stopwatchcounter == 1) {
            //creates a new split and adds it to the list of splits in a Time object
            if(arr.size() == 0) {
                arr.add(new Split("Split 1", s.getSeconds(), 0));
            }
            else {
                arr.add(new Split("Split " + splitcounter, s.getSeconds(), arr.get(arr.size() - 1).getPosition() + 1));
                splitcounter++;
            }
        }
        else if(e.getSource() == field) {
            //Receives input from the text field and updates the average, percent, and standardDeviation and updates the file as well
            try {
                float time = Float.parseFloat(field.getText());
                if(chore.times.size() == 0) {
                    chore.addTime(new Time(time, 1)); 
                }
                else {
                    chore.addTime(new Time(time, chore.times.get(chore.times.size() - 1).getDate() + 1));
                }
                field.setText("");
                ChoreMath cm = new ChoreMath();
                FileFormatter f = new FileFormatter(listChores);
                average.setText("<html><p>Average: " + cm.getAverage(chore) + "</p></html>");
                percent.setText("<html><p>Percent change over time: " + cm.getChangeOverTime(chore) + "</p></html>");
                standardDeviation.setText("<html><p>Standard Deviation: " + cm.getStandardDeviation(chore) + "</html></p>");
                f.write();
            }
            catch(NumberFormatException exc) {
                field.setText("Invalid Input");
            }
        }
    }
}