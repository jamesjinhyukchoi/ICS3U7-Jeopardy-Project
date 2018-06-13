import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import javax.swing.Box;
import java.io.PrintWriter;

public class Welcome
{
    private JFrame frame;
    private JPanel comboPanel;
    private JPanel labelPanel;
    private JPanel radioPanel;
    
    private JLabel categoryLabel;
    private JLabel questionLabel;
    private JLabel playerLabel;
    
    private JRadioButton basicRadio;
    private JRadioButton networkRadio;
    private JRadioButton careerRadio;
    private JRadioButton societyRadio;
    private JRadioButton historyRadio;
    
    Box box;
    
    //private String[] categoryCount = new String[] {"1", "2", "3", "4", "5"};
    private String[] questionCount = new String[] {"1", "2", "3", "4", "5"};
    private String[] playerCount = new String[] {"1", "2", "3", "4"};
    //private JComboBox<String> categoryCombo;
    private JComboBox<String> questionCombo;
    private JComboBox<String> playerCombo;
    
    
    
    private JButton okButton;
    
    // static variables for passing value to Jeopardy Game class
    static int categoryNumber = 0;
    static String questionNumber;
    static String playerNumber;
    
    JeopardyGame jeopardyGame;
    
    Welcome() {
               
        makeWelcomeFrame();

    }   

    private void makeWelcomeFrame() {
        frame = new JFrame("Welcome Jeopardy");
        comboPanel = new JPanel();
        labelPanel = new JPanel();
        radioPanel = new JPanel();
        
        okButton = new JButton("OK");

        categoryLabel = new JLabel("Select Category");
        questionLabel = new JLabel("No of Game");
        playerLabel = new JLabel("No of Player");    

        comboPanel.setBounds(20,40,100,100);        
        labelPanel.add(categoryLabel);
        labelPanel.add(questionLabel);
        labelPanel.add(playerLabel);        
        
        basicRadio = new JRadioButton("Basics");
        networkRadio = new JRadioButton("Networks");
        careerRadio = new JRadioButton("Careers");
        societyRadio = new JRadioButton("Society");
        historyRadio = new JRadioButton("History");
        
        box = Box.createVerticalBox();
        box.add(basicRadio);
        box.add(networkRadio);
        box.add(careerRadio);
        box.add(societyRadio);
        box.add(historyRadio);
        
        //categoryCombo = new JComboBox<String>(categoryCount);
        questionCombo = new JComboBox<String>(questionCount);
        playerCombo = new JComboBox<String>(playerCount);
        
        comboPanel.setBounds(60,100,200,200);
        //comboPanel.add(categoryCombo);
        comboPanel.add(box);
        comboPanel.add(questionCombo);
        comboPanel.add(playerCombo);
        
        frame.add(labelPanel, BorderLayout.NORTH);
        frame.add(comboPanel, BorderLayout.CENTER);
        frame.add(okButton, BorderLayout.SOUTH);
        
        frame.setSize(600,600);
        //frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);
        
        okButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                
                
                //sBasicButton = basicButton.isSelected();
                // Display value of status of sBasicButton in Terminal Window

                writeFile();
                
                setComboValue();
                
                // Call the JeopardyGame
                jeopardyGame = new JeopardyGame();
                
                //set Welcome frame invisible
                frame.hide();
            }
        });
    }


    // Setter to static values
    public void setComboValue() {
        
        try {
        
        questionNumber = (String)questionCombo.getSelectedItem();
        playerNumber = (String)playerCombo.getSelectedItem();
        

        }
        catch (IndexOutOfBoundsException e) {
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        }
    }
    public void writeFile() {
        categoryNumber = 0;
//    private JRadioButton networkRadio;
//    private JRadioButton careerRadio;
//    private JRadioButton societyRadio;
//    private JRadioButton historyRadio;
        
        try {
            PrintWriter writer = new PrintWriter("category.txt", "UTF-8");
            
            if(basicRadio.isSelected()) {
                categoryNumber++;
                writer.println("Basics");
                System.out.println("\nbasic is selected");
            }    
            if(networkRadio.isSelected()) {
                categoryNumber++;
                writer.println("Networks");                
                System.out.println("\nnetwork is selected");
            }    
            if(careerRadio.isSelected()) {
                categoryNumber++;
                writer.println("Careers");
                System.out.println("\ncareer is selected");
            }    
            if(societyRadio.isSelected()) {
                categoryNumber++;
                writer.println("Society");
                System.out.println("\nsociety is selected");
            }    
            if(historyRadio.isSelected()) {
                categoryNumber++;
                writer.println("History");
                System.out.println("\nhistory is selected");
            }    
            writer.close();
        }
        catch (IndexOutOfBoundsException e) {
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        }
        catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        }
        catch (UnsupportedEncodingException e) {
            System.err.println("UnsupportedEncodingException: " + e.getMessage());
        }     
        System.out.println("\nCount of category: " + categoryNumber);
    }
}
