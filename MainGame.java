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

    public MainGame()
    {
        makeFrame();
    }
    
    public static void main(String[] args)
    {
        new MainGame();
    }

    //label lmainGame = addlabel("Jeopardy",60,30,200,60,this);

    
    public MainGame()   // Constructor - change window appearance
    {
        JFrame f = new JFrame("mainGame");

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 5, 5, 5));

        JButton button1 = new JButton("10");
        JButton button2 = new JButton("10");

        JButton bOkay = new JButton("Okay");
        JButton bExit = new JButton("Exit");

        buttonsPanel.add(button1);
        buttonsPanel.add(button2);

        
        

        // random filling to demonstrate the result of the filled grid
        
        buttonsPanel.add(new JButton("10"));
        buttonsPanel.add(new JButton("10"));
        buttonsPanel.add(new JButton("10"));
        buttonsPanel.add(new JButton("20"));
        buttonsPanel.add(new JButton("20"));
        buttonsPanel.add(new JButton("20"));
        buttonsPanel.add(new JButton("20"));
        buttonsPanel.add(new JButton("20"));
        buttonsPanel.add(new JButton("30"));
        buttonsPanel.add(new JButton("30"));
        buttonsPanel.add(new JButton("30"));
        buttonsPanel.add(new JButton("30"));
        buttonsPanel.add(new JButton("30"));
        buttonsPanel.add(new JButton("40"));
        buttonsPanel.add(new JButton("40"));
        buttonsPanel.add(new JButton("40"));
        buttonsPanel.add(new JButton("40"));
        buttonsPanel.add(new JButton("40"));
        buttonsPanel.add(new JButton("50"));
        buttonsPanel.add(new JButton("50"));
        buttonsPanel.add(new JButton("50"));
        buttonsPanel.add(new JButton("50"));
        buttonsPanel.add(new JButton("50"));

        JTextArea label = new JTextArea();
        label.setText("Jeopardy");
        label.setEditable(false);

        content.add(buttonsPanel, BorderLayout.CENTER);
        content.add(label, BorderLayout.NORTH);

        content.add(bOkay, BorderLayout.SOUTH);

        /*
         * for(i=10;i<50;) {
         *     
         *     buttonsPanel.add(new JButton(i);
         *     i = i + 10;
         * }
         */

        f.setContentPane(content);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1100, 550);
        f.setVisible(true);

        /* 
        setSize(500,500);
        setTitle("Jeopardy - (c) 2018 James");
        lmainGame.setFont(new Font("Arial",1,36));

      
        lJeopardy.setBackground(new Color(255,255,180));
        lJeopardy.setForeground(Color.blue);
        lScore.setBackground(new Color(255,255,180));
        setBackground(new Color(255,255,180));
        bScience.setFont(new Font("Arial",1,16));
        bMath.setFont(new Font("Arial",1,16));      
        bHistory.setFont(new Font("Arial",1,16));      
        bSports.setFont(new Font("Arial",1,16));  
        bSports2.setFont(new Font("Arial",1,16));
        bPlayAgain.setFont(new Font("Arial",1,16));      
         */
    }
}