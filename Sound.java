import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Event;
import java.awt.Toolkit;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.embed.swing.JFXPanel;
import javax.swing.Timer;

/**
 * Plays the sounds of the class JeopardyGame
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public class Sound
{
    // static constants
    private static final int themeLength = 33000;
    private static final String THEME_LOCATION = "Jeopardy-theme-song.mp3";

    // instance variables - replace the example below with your own
    private JFXPanel fxPanel;
    private MediaPlayer mediaPlayer;
    private Timer timer;

    /**
     * Creates the audioplayer responsible for playing the audio.
     */
    public Sound()
    {
        // Initialize the fxPanel, and mediaPlayer.
        fxPanel = new JFXPanel();
        mediaPlayer = new MediaPlayer(new Media(new File (THEME_LOCATION).toURI().toString()));

        // Create the actionListener for the timer.
        ThemeOver timeExpired = new ThemeOver();
        timer = new Timer (themeLength, timeExpired);
    } // end of constructor Sound()

    // mutators
    /**
     * Plays the jeopardy theme song.
     */
    public void playTheme()
    {
        mediaPlayer.play();
        
        // Start the timer.
        timer.setRepeats(false);
        timer.start();
    } // end of method playTheme()

    
    /**
     * Plays a beep.
     */
    public void beep()
    {
        Toolkit.getDefaultToolkit().beep();
    } // end of method beep()
    
    /**
     * Stops the jeopardy theme song.
     */
    public void stopTheme()
    {
        mediaPlayer.stop();
    } // end of method stopTheme()
    
    // private classes
    private class ThemeOver implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           stopTheme();
        }
    } // end of class timeExpired implements ActionListener
    
} // end of public class Sound()
