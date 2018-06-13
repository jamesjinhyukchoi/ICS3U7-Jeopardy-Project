import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Times how long a player has to answer a question.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class QuestionTimer
{
    // static constants
    private static final int questionTime = 100;
    
    // instance variables - replace the example below with your own
    private Timer timer;

    /**
     * Constructor for objects of class Timer
     */
    public QuestionTimer()
    {
        timer = new Timer(questionTime, new timerListener());
    } // end of constructor QuestionTimer()
    
    public void startTimer()
    {
        // Call the method only once.
        timer.setRepeats(false);
        timer.start();
    } // end of method startTimer()
    
    // private class
    
    private class timerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            System.out.println("success");
        } // end of method actionPerformed(ActionEvent event)
    } // end of class timerListener implements ActionListener
} // end of class QuestionTimer
