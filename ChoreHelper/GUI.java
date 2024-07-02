import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Dimension;
public class GUI implements ActionListener {
    //Represents the graphical structure of the program
    JFrame frame;
    CardLayout card;
    JPanel panel;
    JButton button, addChore;
    JTextField field, removeField;
    ArrayList<Chore> arr;
    //Creates the starting window when the application is open
    public GUI() {
        card = new CardLayout();
        panel = new JPanel(card);
        BorderLayout layout = new BorderLayout(400, 350);
        JFrame frame = new JFrame("StartFrame");
        JPanel panel1 = new JPanel(layout);
        JLabel label = new JLabel("Chore Helper", SwingConstants.CENTER);
        JButton button = new JButton("Start");
        JButton addChore = new JButton("Add/Remove Chore");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1.setBackground(Color.LIGHT_GRAY);
        label.setFont(new Font("Sans", Font.BOLD, 70));
        label.setForeground(Color.BLACK);
        button.setFont(new Font("Serif", Font.BOLD, 40));
        addChore.setFont(new Font("Serif", Font.BOLD, 40));
        button.setForeground(Color.GREEN);
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        addChore.setFocusPainted(false);
        addChore.setBackground(Color.WHITE);
        panel1.add(button, BorderLayout.CENTER);
        panel1.add(label, BorderLayout.NORTH);
        panel1.add(addChore, BorderLayout.SOUTH);
        panel1.add(new JLabel(""), BorderLayout.WEST);
        panel1.add(new JLabel(""), BorderLayout.EAST);
        panel.add(panel1);
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
        frame.pack();
        frame.setBounds(0, 0, 1920, 1080);
        frame.setVisible(true);
        button.addActionListener(this);
        addChore.addActionListener(this);
        this.frame = frame;
        this.button = button;
        this.addChore = addChore;
        FileFormatter f = new FileFormatter();
        arr = f.read();
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            //Creates a new panel that will fit all the ChoreComponents and displays it on screen
            GridLayout layout = new GridLayout(0, 7);
            JPanel panel1 = new JPanel(layout);
            if(arr.size() <= 7) {
                for(int i = 0; i < arr.size(); i++) {
                    panel1.add(new ChoreComponent(arr.get(i), arr));
                } 
            }
            else {
                for(int i = 0; i < 7; i++) {
                    panel1.add(new ChoreComponent(arr.get(i), arr));
                }
            }
            panel.add(panel1);
            card.next(panel);
        }
        else if(e.getSource() == addChore) {
            //Creates the window of adding or removing a chore from the list of chores
            JFrame frame1 = new JFrame();
            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame1.setBounds(0, 0, 1920, 1080);
            JPanel panel1 = new JPanel();
            BoxLayout layout = new BoxLayout(panel1, BoxLayout.Y_AXIS);
            panel1.setLayout(layout);
            JLabel name = new JLabel("Insert name to add");
            JLabel remove = new JLabel("Insert name to remove");
            JTextField field = new JTextField("");
            JTextField removeField = new JTextField("");
            name.setFont(new Font("Serif", Font.BOLD, 40));
            remove.setFont(new Font("Serif", Font.BOLD, 40));
            name.setAlignmentX(Component.CENTER_ALIGNMENT);
            remove.setAlignmentX(Component.CENTER_ALIGNMENT);
            field.setPreferredSize(new Dimension(250, 50));
            field.setMaximumSize(new Dimension(250, 50));
            field.setMinimumSize(new Dimension(150, 50));
            removeField.setPreferredSize(new Dimension(250, 50));
            removeField.setMaximumSize(new Dimension(250, 50));
            removeField.setMinimumSize(new Dimension(150, 50));
            panel1.add(name);
            panel1.add(field);
            panel1.add(Box.createRigidArea(new Dimension(0, 400)));
            panel1.add(remove);
            panel1.add(removeField);
            frame1.add(panel1);
            this.field = field;
            this.removeField = removeField;
            frame1.setVisible(true);
            field.addActionListener(this);
            removeField.addActionListener(this);
        }
        else if(e.getSource() == field) {
            //Receives input from the text field for adding a chore and adds it to the list of chores and updates the file
            String name = field.getText();
            Chore c = new Chore(name);
            arr.add(c);
            field.setText("");
            FileFormatter f = new FileFormatter(arr);
            f.write();
        }
        else if(e.getSource() == removeField) {
            //Receives input from the text field for removing a chore and removes from the list of chores and updates the file
            int counter = 0;
            String name = removeField.getText();
            for(int i = 0; i < arr.size(); i++) {
                if(arr.get(i).getName().equals(name)) {
                    arr.remove(i);
                    counter = 1;
                    break;
                }
            }
            if(counter == 0) {
                removeField.setText("Chore was unable to be removed");
            }
            else {
                removeField.setText("");
                FileFormatter f = new FileFormatter(arr);
                f.write();
            }
        }
    }
}