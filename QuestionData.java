import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 * Reads a data file and processes the question and answer game data suitable for class JeopardyGame.
 * 
 * @author Evan Chen & James Choi
 * @version 2018-05-31
 */
public class QuestionData
{
    // static constants
    private static final int ANSWER_COUNT = 5;
    //private static final int CATEGORY_COUNT = 5;
    private static final String CORRECT_MARKER = "*";
    //private static final int QUESTION_COUNT = 5;
    private static final int SET_COUNT = 5;
    
    private int categoryCount;
    private int questionCount;
    
    // instance fields
    boolean[][] isAnswerCorrect;
    String[][] set;
    
    Welcome welcome;

    /**
     * Loads the question game data from the specified data file.
     * 
     * @param fileName the specified file; must be in the question format,
     * example can be found @ http://touque.ca/EC/programming/Java/assignments/ca/Jeopardy/dataFiles/basics.data
     */
    public QuestionData(String fileName)
    {
        // Get the static value of count of category and question
        categoryCount = welcome.categoryNumber;
        questionCount = Integer.parseInt(welcome.questionNumber);
        
        
        // Initialize the arrays.
        set = new String[SET_COUNT][questionCount + 1]; // Let zero be the question.
        isAnswerCorrect = new boolean[SET_COUNT][questionCount + 1]; // Let 0 be the question.
        
        // Import the quesiton data.
        importData(fileName);
    } // end of constructor QuestionData

    // accessors

    /**
     * Returns the answer specified.
     * 
     * @param setNumber the set number specified; must be greater than or equal to 0, and less than SET_COUNT
     * @param questionNumber the question number specified; must be greater than or equal to 0, 
     * and less than QUESTION_COUNT + 1 (0 corresponds to question 1, 1 to 2, etc.
     * @return the question specified
     */
    public String getData(int setNumber, int questionNumber)
    {
        if (questionNumber >= 0 && questionNumber < ANSWER_COUNT + 1) // set[0] is the question
        {
            return set[setNumber][questionNumber];
        }
        else
        {
            return "";
        } // end of if (questionNumber >= 0 && questionNumber < 5)
    } // end of method getQuestion(int questionNumber)

    /**
     * Returns the correctness of the answer specified.
     * 
     * @param setNumber the question number specified; must be between greater than or equal to 0, 
     * and less than QUESTION_COUNT (0 corresponds to question 1, 1 to 2, etc.)
     * @param question Number the answer number of the answer specified; must be between greater than 0, 
     * and less than ANSWER_COUNT
     * @return true if the answer is correct; otherwise false
     */
    public boolean isCorrect(int setNumber, int questionNumber)
    {
        if (setNumber >= 0 && setNumber < categoryCount + 1)
        {
            if (questionNumber >= 0 && questionNumber < questionCount && questionNumber >= 0 && questionNumber < questionCount)
            {
                return isAnswerCorrect[setNumber][questionNumber];
            }
            else
            {
                return false;
            } // end of if (answerNumber >= 0 && answerNumber < 5)
        }
        else
        {
            return false;
        } // end of if (questionNumber >= 0 && questionNumber < 5)
    } // end of method isCorrect(int questionNumber, int answerNumber)
    
    // mutators
    
    /**
     * Imports the question data from the specified file.
     * 
     * @param fileName the file that the data is being imported from; must be in the question format
     */
    private void importData(String fileName)
    {
        if (fileName == null)
        {
            // Initialize everything to default values.
            for (int setNumber = 0; setNumber < SET_COUNT; setNumber++)
            {
                for (int questionNumber = 0; questionNumber < questionCount + 1; questionNumber++)
                {
                    set[setNumber][questionNumber] = "";
                } // end of for (int questionNumber = 0; questionNumber < ANSWER_COUNT; questionNumber++)
            } // end of for (int answerNumber = 0; answerNumber < ANSWER_COUNT; answerNumber++)
        }
        else
        {
            try
            {
                // Attempt to establish a connection to the console.
                BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

                // Import the data into a 2D array.
                for (int answerNumber = 0; answerNumber < SET_COUNT; answerNumber++)
                {
                    for (int questionNumber = 0; questionNumber < questionCount + 1; questionNumber++)
                    {
                        try
                        {
                            String lineOfText = fileReader.readLine();

                            // Check if the answer is correct and change the isAnwerCorrect array accordingly.
                            if (lineOfText.endsWith(CORRECT_MARKER))
                            {
                                // Remove the CORRECT_MARKER.
                                set[answerNumber][questionNumber] = lineOfText.substring(0, lineOfText.length() - 1);
                                isAnswerCorrect[answerNumber][questionNumber] = true;
                            }
                            else
                            {
                                // Input the data noramlly.
                                set[answerNumber][questionNumber] = lineOfText;
                                isAnswerCorrect[answerNumber][questionNumber] = false;
                            } // end of if (lineOfText.endsWith("*"))
                        }
                        catch (IOException exception)
                        {
                            set[answerNumber][questionNumber] = "";
                        } // end of catch (IOException exception)
                    } // end of for (int questionNumber = 0; questionNumber < ANSWER_COUNT + 1; questionNumber++)
                } // end of if (fileName == null)
            }
            catch (FileNotFoundException exception)
            {
                for (int answerNumber = 0; answerNumber < SET_COUNT; answerNumber++)
                {
                    for (int questionNumber = 0; questionNumber < questionCount + 1; questionNumber++)
                    {
                        set[answerNumber][questionNumber] = "";
                    } // end of for (int questionNumber = 0; questionNumber < ANSWER_COUNT + 1; questionNumber++)
                } // end of for (int answerNumber = 0; answerNumber < ANSWER_COUNT; answerNumber++)
            } // end of catch (FileNotFoundException exception)
        } // end of (fileName == null)
    } // end of method importData()
} // end of class QuestionData
