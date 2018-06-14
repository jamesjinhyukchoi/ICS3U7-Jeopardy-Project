import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 * Reads a data file and processes the category game data suitable for class JeopardyGame.
 * 
 * @author Evan Chen & James Choi
 * @version 2018-05-30
 */
public class CategoryGameData
{
    // static constants
    private static final int CATEGORY_COUNT = 5;
    private static final String EXTENSION = ".text";
    private static final int QUESTION_COUNT = 5;

    // instance fields
    String[] category;
    QuestionData[] gameData;

    // constructors

    /**
     * Creates an object of class CategoryGameData containing the category game data from the specified file.
     * 
     * @param fileName the specified file; must be in the category format,
     * example can be found @ http://touque.ca/EC/programming/Java/assignments/ca/Jeopardy/dataFiles/categories.data
     */
    public CategoryGameData(String fileName)
    {
        // Initialize the arrays.
        category = new String[CATEGORY_COUNT];
        gameData = new QuestionData[QUESTION_COUNT];

        // Load the category data.
        importCategories(fileName);

        // Load the question data.
        for (int categoryNumber = 0; categoryNumber < CATEGORY_COUNT; categoryNumber++)
        {
            importQuestionData(category[categoryNumber] + EXTENSION);
        } // end of for (int categoryNumber; categoryNumber < CATEGORY_COUNT; categoryNumber++)
    } // end of constructor CategoryGameData()

    // accessors

    /**
     * Returns the category of the specified number.
     * 
     * @return the category of the specified number
     * @param the category of the specified number; must be between 0 and CATEGORY_COUNT
     */
    public String getCategoryData(int categoryNumber)
    {
        if (categoryNumber < CATEGORY_COUNT && categoryNumber >= 0)
        {
            return category[categoryNumber];
        }
        else
        {
            return "";
        } // end of if (categoryNumber < CATEGORY_COUNT && categoryNumber > 0)
    } // end of method getCategoryData()

    /**
     * Returns the question data
     * 
     * @param categoryNumber the category of the question specified
     * @param setNumber the question set of the number specified
     * @param responseNumber returns the response of the set at the number specified; 0 will be the initial question
     * @return the question at the number specified
     */
    public String getQuestionData(int categoryNumber, int setNumber, int responseNumber)
    {
        if (gameData[categoryNumber] != null)
        {
            return gameData[categoryNumber].getData(setNumber, responseNumber);
        }
        else
        {
            return "";
        } // end of if (questionData != null)
    } // end of method getQuesionData(int setNumber, int responseNumber)
    // mutators

    /*
     * Imports the category data from the specified file.
     *
     */
    private void importCategories(String fileName)
    {
        // Attempt to establish a connection to the data file.
        if (fileName == null)
        {
            for (int categoryNumber = 0; categoryNumber < CATEGORY_COUNT; categoryNumber++)
            {
                category[categoryNumber] = "";
            } // end of for (int categoryNumber = 0; categoryNumber < CATEGORY_COUNT; categoryNumber++)
        }
        else
        {
            try
            {
                BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

                // Load the data into the array.
                for (int categoryNumber = 0; categoryNumber < CATEGORY_COUNT; categoryNumber++)
                {
                    try
                    {
                        category[categoryNumber] = fileReader.readLine();
                    }
                    catch (IOException exception)
                    {
                        // Account for errors.
                        category[categoryNumber] = "";
                    } // end of catch (IOException exception)
                } // end of for (int categoryNumber = 0; categoryNumber < CATEGORY_COUNT = 5; categoryNumber++)
            }
            catch (FileNotFoundException exception)
            {
                for (int categoryNumber = 0; categoryNumber < CATEGORY_COUNT; categoryNumber++)
                {
                    category[categoryNumber] = "";
                } // end of for (int categoryNumber = 0; categoryNumber < CATEGORY_COUNT; categoryNumber++)
            } // catch (FileNotFoundException exception)
        } // end of catch (FileNotFoundException exception)
    } // end of method importCategories(String fileName)

    /**
     * Imports the question data for the specified category.
     * 
     * @param fileName the file name of the specified question data; may not be null
     */
    public void importQuestionData(String fileName)
    {
        // Attempt to make a connection to the file.
        try
        {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            // Identify the category that this file belongs to.
            for (int categoryNumber = 0; categoryNumber < CATEGORY_COUNT; categoryNumber++)
            {
                if (fileName.compareToIgnoreCase(category[categoryNumber] + EXTENSION) == 0)
                {
                    gameData[categoryNumber] = new QuestionData(fileName);
                } // end of if (fileName.compareToIgnoreCase(category[index]) == 0)
            } // end of for ( int index = 0; index < CATEGORY_COUNT; index++)
        }
        catch (FileNotFoundException exception)
        {
        } // end of catch (FileNotFoundException exception)
    } // end of importQuestionData(String fileName, String category)
} // end of class FileReader
