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
    private static final int NUMBER_OF_CATEGORIES = 5;

    // instance fields
    String[] category;
    BufferedReader fileReader;

    // constructors
    /**
     * Creates an object of class CategoryGameData containing the category game data from the specified file.
     * 
     * @param fileName the specified file; must be in the category format,
     * example can be found @ http://touque.ca/EC/programming/Java/assignments/ca/Jeopardy/dataFiles/categories.data
     */
    public CategoryGameData(String fileName)
    {
        // Initialize the array.
        category = new String[NUMBER_OF_CATEGORIES];

        // Attempt to establish a connection to the data file.
        if (fileName == null)
        {
            for (int categoryNumber = 0; categoryNumber < NUMBER_OF_CATEGORIES; categoryNumber++)
            {
                category[categoryNumber] = "";
            } // end of for (int categoryNumber = 0; categoryNumber < NUMBER_OF_CATEGORIES; categoryNumber++)
        }
        else
        {
            try
            {
                fileReader = new BufferedReader(new FileReader(fileName));
                
                // Load the data into the array.
                for (int categoryNumber = 0; categoryNumber < NUMBER_OF_CATEGORIES; categoryNumber++)
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
                } // end of for (int categoryNumber = 0; categoryNumber < NUMBER_OF_CATEGORIES = 5; categoryNumber++)
            }
            catch (FileNotFoundException exception)
            {
                for (int categoryNumber = 0; categoryNumber < NUMBER_OF_CATEGORIES; categoryNumber++)
                {
                    category[categoryNumber] = "";
                } // end of for (int categoryNumber = 0; categoryNumber < NUMBER_OF_CATEGORIES; categoryNumber++)
            } // catch (FileNotFoundException exception)
        } // end of catch (FileNotFoundException exception)

    } // end of constructor CategoryGameData()

    // accessors

    /**
     * Returns the categories of this file.
     * 
     * @return the categories of this file.
     */
    public String[] getData()
    {
        return category;
    } // end of method getData()

} // end of class FileReader
