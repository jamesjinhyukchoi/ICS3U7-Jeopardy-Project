import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 * Reads a data file and processes the categroy game data suitable for class JeopardyGame.
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
     * @param fileName the name of the file that is being read from; must be a category file
     */
    public CategoryGameData(String fileName)
    {
        // Attempt to establish a connection to the data file.
        if (fileName != null)
        {
            try
            {
                fileReader = new BufferedReader(new FileReader(fileName));
            }
            catch (FileNotFoundException exception)
            {
                return;
            } // catch (FileNotFoundException exception)
            for (int index = 0; index < NUMBER_OF_CATEGORIES; index++)
            {
                try
                {
                    category[index] = fileReader.readLine();
                }
                catch (IOException exception)
                {
                    category[index] = "";
                } // end of catch (IOException exception)
            } // end of for (int index = 0; index < NUMBER_OF_CATEGORIES = 5; index++)
            for (int index = 0; index < NUMBER_OF_CATEGORIES; index++)
            {
                category[index] = "";
            } // end of for (int index = 0; index < NUMBER_OF_CATEGORIES; index++)
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
