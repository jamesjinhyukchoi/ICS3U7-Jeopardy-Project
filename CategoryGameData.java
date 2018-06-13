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
    //private static final int CATEGORY_COUNT = 5;
    private int categoryCount;
    //private static final int QUESTION_COUNT = 5;
    private int questionCount;
    
    // instance fields
    String[] category;
    QuestionData[] gameData;

    Welcome welcome;
    
    // constructors

    /**
     * Creates an object of class CategoryGameData containing the category game data from the specified file.
     * 
     * @param fileName the specified file; must be in the category format,
     * example can be found @ http://touque.ca/EC/programming/Java/assignments/ca/Jeopardy/dataFiles/categories.data
     */
    public CategoryGameData()
    {
        // Get the count of category
        categoryCount = welcome.categoryNumber;
        questionCount = Integer.parseInt(welcome.questionNumber);
        
        // Initialize the arrays.
        category = new String[categoryCount];
        gameData = new QuestionData[questionCount];

        // Load the category data.
        importCategories("category.txt");

        // Load the question data.
        for (int categoryNumber = 0; categoryNumber < categoryCount; categoryNumber++)
        {
            importQuestionData(category[categoryNumber] + ".data");
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
        if (categoryNumber < categoryCount && categoryNumber >= 0)
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
        return gameData[categoryNumber].getData(setNumber, responseNumber);
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
            for (int categoryNumber = 0; categoryNumber < categoryCount; categoryNumber++)
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
                for (int categoryNumber = 0; categoryNumber < categoryCount; categoryNumber++)
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
                for (int categoryNumber = 0; categoryNumber < categoryCount; categoryNumber++)
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
            for (int categoryNumber = 0; categoryNumber < categoryCount; categoryNumber++)
            {
                if (fileName.compareToIgnoreCase(category[categoryNumber] + ".data") == 0)
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
