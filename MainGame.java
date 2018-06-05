import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainGame 
{
    // static constants
    static final int CATEGORY_COUNT = 5;
    static final int QUESTION_COUNT = 5;

    // instance fields
    JPanel buttonPanel;
    JPanel content;
    JButton exitButton;
    JFrame mainFrame;
    JButton okayButton;
    JPanel endPanel;
    
    public MainGame()   // Constructor - change window appearance
    {
        mainFrame = new JFrame("mainGame");

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 5, 20, 20));

        content = new JPanel();
        
        endPanel = new JPanel();

        
        okayButton = new JButton("Okay");
        exitButton = new JButton("Exit");

        for (int questionNumber = 1; questionNumber <= QUESTION_COUNT; questionNumber++)
        {
            for (int categoryNumber = 1; categoryNumber <= CATEGORY_COUNT; categoryNumber++)
            {
                buttonPanel.add(new JButton(Integer.toString(questionNumber * 10)));
            } // end of for (int categoryNumber = 1; index <= CATEGORY_COUNT; index++)
        } // end of for (int questionNumber = 1; questionNumber <= QUESTION_COUNT; questioNumber++)

            JTextArea label = new JTextArea();
            label.setText("Jeopardy");
            label.setEditable(false);

            content.add(buttonPanel, BorderLayout.CENTER);
            content.add(label, BorderLayout.PAGE_START);

            content.add(okayButton, BorderLayout.PAGE_END);
            

            mainFrame.setContentPane(content);

            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(1100, 550);
            mainFrame.setVisible(true);
        }
    }