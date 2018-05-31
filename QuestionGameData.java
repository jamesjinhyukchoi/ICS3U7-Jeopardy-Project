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
public class QuestionGameData
{
    // static constants
    private static final int NUMBER_OF_SETS = 5;
    private static final int NUMBER_OF_ANSWERS= 5;

    // instance fields
    String[][] set;

    /**
     * Loads the question game data from the specified data file.
     * 
     * @param fileName the specified file; must be in the question format,
     * example can be found @ http://touque.ca/EC/programming/Java/assignments/ca/Jeopardy/dataFiles/basics.data
     */
    public QuestionGameData(String fileName)
    {
        // Initialize the arrays.
        set = new String[NUMBER_OF_SETS][NUMBER_OF_ANSWERS + 1]; // Let 0 will be the question for the anwers subarray.

        // Attempt to establish a connection to the data file.
        if (fileName == null)
        {
            for (int setNumber = 0; setNumber < NUMBER_OF_SETS; setNumber++)
            {
                for (int questionAndAnswerNumber = 0; questionAndAnswerNumber < NUMBER_OF_ANSWERS + 1; questionAndAnswerNumber++)
                {
                    set[setNumber][questionAndAnswerNumber] = "";
                } // end of for (int questionAndAnswerNumber = 0; questionAndAnswerNumber < NUMBER_OF_ANSWERS + 1; questionAndAnswerNumber++)
            } // end of for (int setNumber = 0; setNumber < NUMBER_OF_SETS; setNumber++)
        }
        else
        {
            try
            {
                // Attempt to establish a connection to the console.
                BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

                for (int setNumber = 0; setNumber < NUMBER_OF_SETS; setNumber++)
                {
                    for (int questionAndAnswerNumber = 0; questionAndAnswerNumber < NUMBER_OF_ANSWERS + 1; questionAndAnswerNumber++)
                    {
                        try
                        {
                            set[setNumber][questionAndAnswerNumber] = fileReader.readLine();
                        }
                        catch (IOException exception)
                        {
                            set[setNumber][questionAndAnswerNumber] = "";
                        } // end of catch (IOException exception)
                    } // end of for (int questionAndAnswerNumber = 0; questionAndAnswerNumber < NUMBER_OF_ANSWERS + 1; questionAndAnswerNumber++)
                } // end of if (fileName == null)
            }
            catch (FileNotFoundException exception)
            {
                for (int setNumber = 0; setNumber < NUMBER_OF_SETS; setNumber++)
                {
                    for (int questionAndAnswerNumber = 0; questionAndAnswerNumber < NUMBER_OF_ANSWERS + 1; questionAndAnswerNumber++)
                    {
                        set[setNumber][questionAndAnswerNumber] = "";
                    } // end of for (int questionAndAnswerNumber = 0; questionAndAnswerNumber < NUMBER_OF_ANSWERS + 1; questionAndAnswerNumber++)
                } // end of for (int setNumber = 0; setNumber < NUMBER_OF_SETS; setNumber++)
            } // end of catch (FileNotFoundException exception)
        }
    } // end of constructor QuestionGameData
}
