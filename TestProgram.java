import javax.swing.*;
import java.awt.*;

public class TestProgram 
{
    public static void main(String[] argument)
    {

        JFrame frame = new JFrame("Grid Layout");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,400);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5, 5, 10));
        
        
        JButton label1 = new JButton("alpha");
        JButton label2 = new JButton("bravo");
        JButton label3 = new JButton("Charlie");
        JButton label4 = new JButton("Delta");
        JButton label5 = new JButton("Echo");
        JButton label6 = new JButton("Foxtrot");
        JButton label7 = new JButton("Golf");
        JButton label8 = new JButton("Hotel");
        JButton label9 = new JButton("India");
        JButton label10 = new JButton("Juliet");
        JButton label11 = new JButton("Kilo ");
        JButton label12 = new JButton("Hello");
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        panel.add(label8);
        panel.add(label9);
        panel.add(label10);
        panel.add(label11);
        panel.add(label12);
        frame.add(panel);
        
    }



}

