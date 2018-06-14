import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * The main game frame for Jeopardy.
 * 
 * @author Evan Chen & James Choi
 * @version 2018-06-05
 */
public class JeopardyGame
{
    // static constants
    private static final int ANSWER_COUNT = 5;
    private static final Color BACKGROUND_COLOUR = Color.YELLOW;
    private static final int CATEGORY_COUNT = 5;
    private static final int FRAME_HEIGHT = 750;
    private static final int FRAME_WIDTH = 1500;
    private static final int PLAYER_COUNT = 4;
    private static final int QUESTION_COUNT = 5;
    private static final int TOTAL_QUESTIONS = ANSWER_COUNT * CATEGORY_COUNT;

    //instance fields
    private JButton[][] answerButton;
    private CategoryGameData categoryData;
    private String categoryFile;
    public int currentCategoryNumber;
    private int currentQuestionNumber;
    private JPanel endScreen;
    private boolean isEndScreen;
    private boolean isShowingQuestion;
    private JPanel mainButtonPanel;
    private JFrame mainFrame;
    private PlayerData playerData;
    private JButton[][] questionButton;
    private QuestionButtonListener questionButtonListener;
    private JPanel questionPanel;
    private QuestionTimer timer;
    private int questionsCompleted;
    private JButton quitButton;
    private JButton resetButton;
    private JLabel[] scoreLabel;
    private JPanel scorePanel;
    private Sound sound;
    private UtilityButtonListener utilityButtonListener;
    private JPanel utilityButtonPanel;

    // constructors

    /**
     * Creates a JeopardyGame with the specified file as the data used.
     * 
     * @param fileName the category file of this game which contains the names of the question files; question files must follow the naming convention specified,
     * for example, category name: "Basics", basics question file name: "Basics.text"
     */
    public JeopardyGame(String fileName)
    {
        // Save the category file name.
        categoryFile = fileName;

        // Create objects with the input files.
        categoryData = new CategoryGameData(fileName);

        // Create the player data.
        playerData = new PlayerData();

        // Initialize the sound class.
        sound = new Sound();

        // Initialize the timer class.
        timer = new QuestionTimer();

        // Create the frame.
        makeFrame();

        isEndScreen = false;
    } // end of constructor JeopardyGame()

    // mutators

    /*
     * Makes the main frame.
     */
    private void makeFrame()
    {
        // Initialize the frame.
        mainFrame = new JFrame("Jeopardy Game");
        mainFrame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        mainFrame.getContentPane().setBackground(BACKGROUND_COLOUR);

        // Create the 

        // Make and add the main button panel.
        makeMainButtonPanel();
        mainFrame.add(mainButtonPanel, BorderLayout.CENTER);

        // Make and add the utility button panel.
        makeUtilityButtonPanel();
        mainFrame.add(utilityButtonPanel, BorderLayout.PAGE_END);

        // Make and add the score panel.
        makeScorePanel();
        mainFrame.add(scorePanel, BorderLayout.LINE_START);

        // Frame packing.
        mainFrame.pack();
        mainFrame.setVisible(true);
    } // end of method makeFrame()

    /*
     * Makes the main button panel.
     */
    private void makeMainButtonPanel()
    {
        // Initialize the panel.
        mainButtonPanel = new JPanel();
        mainButtonPanel.setBackground(BACKGROUND_COLOUR);

        // Set the layout, the first row will be the category names.
        mainButtonPanel.setLayout(new GridLayout(QUESTION_COUNT + 1, CATEGORY_COUNT));

        // Add the category names to the first row.
        for (int categoryNumber = 0; categoryNumber < CATEGORY_COUNT; categoryNumber++)
        {
            String category = categoryData.getCategoryData(categoryNumber);
            JLabel label = new JLabel(category, JLabel.CENTER);
            mainButtonPanel.add(label);
        } // end of for (int categoryNumber = 0; categoryNumber < CATEGORY_COUNT; categoryNumber++)

        // Initialize the button array.
        questionButton = new JButton[CATEGORY_COUNT][QUESTION_COUNT];

        // Initialize the action listener for the buttons.
        questionButtonListener = new QuestionButtonListener();

        // Create the buttons.
        for (int questionNumber = 0; questionNumber < QUESTION_COUNT; questionNumber++)
        {
            for (int categoryNumber = 0; categoryNumber < CATEGORY_COUNT; categoryNumber++)
            {
                // The dollar value of this question.
                String dollarValue = Integer.toString((questionNumber + 1) * 10);

                // Create and add the actionlistener.
                questionButton[categoryNumber][questionNumber] = new JButton(dollarValue);
                questionButton[categoryNumber][questionNumber].addActionListener(questionButtonListener);
                mainButtonPanel.add(questionButton[categoryNumber][questionNumber]);
            } // end of for (int questionNumber = 0; questionNumber < QUESTION_COUNT; questionNumber++)
        } // end of for (int categoryNumber = 0; categoryNumber < CATEGORY_COUNT; categoryNumber++)
    } // end of method makeMainButtonPanel

    /*
     * Creates the utility buton panel.
     */
    private void makeUtilityButtonPanel()
    {
        // Initialize the panel and the utility button listener.
        utilityButtonPanel = new JPanel();
        utilityButtonListener = new UtilityButtonListener();

        // Create and add the quit button.
        quitButton = new JButton("Quit");
        quitButton.addActionListener(utilityButtonListener);
        utilityButtonPanel.add(quitButton);

        // Create and add the reset button.
        resetButton = new JButton("Reset");
        resetButton.addActionListener(utilityButtonListener);
        utilityButtonPanel.add(resetButton);
    } // end of method makeUtilityButtonPanel()

    /*
     * Resets the frame.
     */
    private void reset()
    {
        // Remove and the widgets.
        mainFrame.remove(mainButtonPanel);
        mainFrame.remove(utilityButtonPanel);
        if (isShowingQuestion)
        {
            mainFrame.remove(questionPanel);
        } // end of if (isShowingQuestion)
        mainFrame.remove(scorePanel);
        if (isEndScreen)
        {
            mainFrame.remove(endScreen);
        } // end of if (isEndScreen)
        // Reset the questions completed.
        questionsCompleted = 0;

        // Reset the player scores.
        playerData.reset();

        // Create and add the widgets back.
        makeUtilityButtonPanel();
        mainFrame.add(utilityButtonPanel, BorderLayout.PAGE_END);

        makeMainButtonPanel();
        mainFrame.add(mainButtonPanel, BorderLayout.CENTER);

        makeScorePanel();
        mainFrame.add(scorePanel, BorderLayout.LINE_START);

        // Stop the theme.
        sound.stopTheme();

        // Repack and repaint the frame.
        mainFrame.pack();
        mainFrame.repaint();
    } // end of method reset()

    private void makeQuestionPanel(int categoryNumber, int questionNumber)
    {
        // Save the current question and category number.
        currentCategoryNumber = categoryNumber;
        currentQuestionNumber = questionNumber;

        // Create the question panel and the ActionListener for this panel.
        questionPanel = new JPanel();
        AnswerListener answerListener = new AnswerListener();

        // Set the layout of the main question panel.
        questionPanel.setLayout(new BorderLayout());

        // Create the question label.
        JLabel question = new JLabel(categoryData.getQuestionData(categoryNumber, questionNumber, 0), JLabel.CENTER);
        questionPanel.add(question, BorderLayout.PAGE_START);

        // Create the question button panel
        JPanel questionButtonPanel = new JPanel();

        // Set the layout for ths panel.
        questionButtonPanel.setLayout(new GridLayout(ANSWER_COUNT, 1));

        // Create and add the answer buttons.
        answerButton = new JButton[CATEGORY_COUNT][ANSWER_COUNT];
        for (int answerNumber = 0; answerNumber < ANSWER_COUNT; answerNumber++)
        {
            answerButton[currentQuestionNumber][answerNumber] = 
            new JButton(categoryData.getQuestionData(currentCategoryNumber, questionNumber, answerNumber + 1)); // answerNumber 0 is the question

            answerButton[currentQuestionNumber][answerNumber].addActionListener(answerListener);
            questionButtonPanel.add(answerButton[currentQuestionNumber][answerNumber]);
        } // end of for (int answerNumber = 0; answerNumber < ANSWER_COUNT; answerNumber++)

        // Add the question button panel to the main question panel.
        questionPanel.add(questionButtonPanel, BorderLayout.CENTER);

        // Add the main button panel to the frame, repack and repaint.
        mainFrame.add(questionPanel, BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.repaint();
    } // end of method makeQuestionPanel(int categoryNumber, int questionNumber)

    /*
     * Creates the score panel.
     */
    private void makeScorePanel()
    {
        // Initialize the panel, and score label array.
        scorePanel = new JPanel();
        scoreLabel = new JLabel[PLAYER_COUNT + 1]; // 0 is the current turn player label

        // Initialize the layout.
        scorePanel.setLayout(new GridLayout(PLAYER_COUNT + 1, 1)); // the first row will be for the current player tracker

        // Create and add the current turn player label
        scoreLabel[0] = new JLabel("It is " + playerData.getTurnPlayerName() + "'s turn");
        scorePanel.add(scoreLabel[0]);

        // Create the score labels and add them to the score panel.
        for (int playerNumber = 0; playerNumber < PLAYER_COUNT; playerNumber++)
        {
            scoreLabel[playerNumber + 1] = 
            new JLabel(playerData.getName(playerNumber) + " : " + playerData.getScore(playerNumber), JLabel.CENTER);
            scorePanel.add(scoreLabel[playerNumber + 1]);
        } // end of for (int playerNumber = 0; playerNumber < PLAYER_COUNT; playerNumber++)
    } // end of method makeScorePanel()

    private void makeEndScreen()
    {
        // Initialize the end screen panel.
        endScreen = new JPanel();
        endScreen.setLayout(new BorderLayout());

        // Add the title.
        JLabel title = new JLabel("GAME OVER!", JLabel.CENTER);
        endScreen.add(title, BorderLayout.PAGE_START);

        // Create the score panel.
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(PLAYER_COUNT, 1));

        // Add the scores.
        for (int playerNumber = 0; playerNumber < PLAYER_COUNT; playerNumber++)
        {
            JLabel scoreLabel = new JLabel(playerData.getName(playerNumber) + ": " + playerData.getScore(playerNumber), JLabel.CENTER);
            scorePanel.add(scoreLabel);
        } // end of for (int playerNumber = 0; playerNumber < PLAYER_COUNT; playerNumber++)

        // Add the score panel to the end screen panel.
        endScreen.add(scorePanel, BorderLayout.CENTER);

        // Declare that the end screen is showing
        isEndScreen = true;
    } // end of method makeScoreScreen()

    // private classes

    private class QuestionTimer
    {
        // static constants
        private static final int questionTime = 33000;

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
                sound.stopTheme();
            } // end of method actionPerformed(ActionEvent event)
        } // end of class timerListener implements ActionListener
    } // end of class QuestionTimer

    /*
     * Tracks clicks from the utility button panel.
     */
    private class UtilityButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            // Identify the source of the action.
            Object source = event.getSource();

            if (source == quitButton)
            {
                System.exit(0);
            } // end of if (source == quitButton)
            else if (source == resetButton)
            {
                reset();
            } // end of else if (source == quitButton)
        } //end of method actionPerformed(ActionEvent event)
    } // end of class ButtonListener implements ActionListener

    /*
     * Tracks clicks from the main button panel.
     */
    private class QuestionButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            // Identify the source of the action.
            Object source = event.getSource();

            for (int questionNumber = 0; questionNumber < QUESTION_COUNT; questionNumber++)
            {
                for (int categoryNumber = 0; categoryNumber < CATEGORY_COUNT; categoryNumber++)
                {
                    if (source == questionButton[categoryNumber][questionNumber])
                    {
                        //  Display the question panel.
                        mainButtonPanel.setVisible(false);
                        makeQuestionPanel(categoryNumber, questionNumber);
                        isShowingQuestion = true;

                        // Play the beep.
                        sound.beep();

                        // Start the jeopardy theme song
                        sound.playTheme();

                        // Start a 33 second timer in which the song will finish.
                        timer.startTimer();

                        // Track the questions completed.
                        questionsCompleted++;
                    } // end of if (source == questionButton[questionNumber][categoryNumber])
                } // end of for (int categoryNumber = 0; categoryNumber < CATEGORY_COUNT; categoryNumber++)
            } // end of for (int questionNumber = 0; questioNumber < QUESTION_COUNT; questionNumber++)
        } // end of method actionPerformed(ActionEvent event)
    } // end of class QuestionButtonListener implements ActionListener

    /*
     * Resonds to buttons clicked in the question panel.
     */
    private class AnswerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            // Identify the source of the action.
            Object source = event.getSource();

            // Identify which answer button was selected.
            for (int answerNumber = 0; answerNumber < ANSWER_COUNT; answerNumber++)
            {
                if (source == answerButton[currentQuestionNumber][answerNumber])
                {
                    // Return to the main button panel.
                    questionPanel.setVisible(false);
                    mainButtonPanel.setVisible(true);
                    isShowingQuestion = false;

                    // Stop the theme.
                    sound.stopTheme();

                    // Remove the previous question button selected.
                    questionButton[currentCategoryNumber][currentQuestionNumber].setVisible(false);

                    // Number 0 within the isCorrect array is the question.
                    if (categoryData != null && categoryData.gameData[currentCategoryNumber] != null)
                    {
                        if (categoryData.gameData[currentCategoryNumber].isCorrect(currentQuestionNumber, answerNumber + 1))
                        {
                            int turnPlayer = playerData.getTurnPlayer();

                            // Add the score to the current turn player.
                            playerData.changeScore(turnPlayer, (currentQuestionNumber + 1) * 10);

                            // Update the scoreboard.
                            scoreLabel[turnPlayer + 1].setText(playerData.getName(turnPlayer) + " : " + playerData.getScore(turnPlayer));

                            // Indicate that it is the next player's turn.
                            playerData.nextTurn();
                            turnPlayer = playerData.getTurnPlayer();
                            scoreLabel[0].setText("It is " + playerData.getName(turnPlayer) + "'s turn");
                        }
                        else
                        {
                            int turnPlayer = playerData.getTurnPlayer();

                            // Subtract the score from the current turn player.
                            playerData.changeScore(playerData.getTurnPlayer(), (currentQuestionNumber + 1) * -10);

                            // Update the scoreboard.
                            scoreLabel[turnPlayer + 1].setText(playerData.getName(turnPlayer) + " : " + playerData.getScore(turnPlayer));

                            // Indicate that it is the next player's turn.
                            playerData.nextTurn();
                            turnPlayer = playerData.getTurnPlayer();
                            scoreLabel[0].setText("It is " + playerData.getName(turnPlayer) + "'s turn");
                        } // end of if (isAnswerCorrect[categoryNumber][answerNumber])
                    } // end of if (categoryData.gameData != null)
                    
                    // When the questions are finished display the end screen.
                    if (questionsCompleted == TOTAL_QUESTIONS)
                    {
                        mainButtonPanel.setVisible(false);
                        scorePanel.setVisible(false);

                        makeEndScreen();
                        mainFrame.add(endScreen, BorderLayout.CENTER);
                    }// end of if (questionsCompleted == TOTAL_QUESTIONS)
                } // end of for (int answerNumber = 0; answerNumber < ANSWER_COUNT; answerNumber++)

                // Repaint.
                mainButtonPanel.repaint();
                mainFrame.repaint();
            } // end of if (source == answerButton[answerNumber])
        }// end of method public void actionPerformed(actionEvent event)
    } // end of class AnswerListener implements ActionListener
} // end of class JeopardyGame
