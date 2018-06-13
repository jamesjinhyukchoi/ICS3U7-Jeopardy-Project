public class PlayerData
{
    // static constants
    static final int PLAYER_COUNT = 4;

    // instance fields
    private String[] playerName;
    private int[] playerScore;
    private int currentTurnPlayer;

    // constructors
    public PlayerData()
    {
        // Initialize the arrays.
        playerName = new String[PLAYER_COUNT];
        playerScore = new int[PLAYER_COUNT];

        // Give default player names and scores.
        for (int playerNumber = 0; playerNumber < PLAYER_COUNT; playerNumber++)
        {
            playerName[playerNumber] = "Player " + (playerNumber + 1);
            playerScore[playerNumber] = 0;
        } // end of for (int playerNumber = 0; playerNumber < PLAYER_COUNT; playerNumber++)

        // Initialize current turn player.
        currentTurnPlayer = 0;
    } // end of constructor PlayerData()

    // mutators

    /**
     * Changes the score of the specified player by the specified amount.
     * 
     * @param scoreChange the change on the player's score
     * @param playerNumber the number corresponding to the player who's score is being changed; must be non-negative less than PLAYER_COUNT
     */
    public void changeScore(int playerNumber, int scoreChange)
    {
        playerScore[playerNumber] = playerScore[playerNumber] + scoreChange;
    } // end of method changeScore(int scoreChange, String player)

    public void nextTurn()
    {
        // Verify that the current turn player is not the maximum.
        if (currentTurnPlayer < PLAYER_COUNT - 1)
        {
            currentTurnPlayer++;
        }
        else
        {
            currentTurnPlayer = 0;
        } // end of if (currentTurnPlayer < PLAYER_COUNT)
    } // end of method nextTurn()

    /**
     * Resets the data to the starting state.
     * 
     */
    public void reset()
    {
        // Reset the scores
        for (int playerNumber = 0; playerNumber < PLAYER_COUNT; playerNumber++)
        {
            playerScore[playerNumber] = 0;
        } // end of for (int playerNumber = 0; playerNumber < PLAYER_COUNT; playerNumber++)
        
        // Reset the turn player
        currentTurnPlayer = 0;
    } // end of method reset()
    // accessors

    /**
     * Returns the score of the player specified.
     * 
     * @param playerNumber the number corresponding to the player whose score is required; must be non-negative less than PLAYER_COUNT
     * @return the score of the player specified
     */
    public int getScore(int playerNumber)
    {
        return playerScore[playerNumber];
    } // end of method getScore(int playerNumber)

    /**
     * Returns the name of the current turn player.
     * 
     * @param the number corresponding to the current turn player; must be non-negative less than PLAYER_COUNT
     * @return the name of the current turn player
     */
    public String getName(int playerNumber)
    {
        if (playerNumber < PLAYER_COUNT && playerNumber >= 0)
        {
            return playerName[playerNumber];
        }
        else
        {
            return "";
        } // end of if (playerNumber < PLAYER_COUNT && playerNumber >= 0)
    } // end of method getName((playerNumber)

    /**
     * Returns the number corresponding to the current turn player.
     * 
     * @return the number corresponding to the current turn player
     */
    public int getTurnPlayer()
    {
        return currentTurnPlayer;
    } // end of method String getTurnPlayer()

    /**
     * Returns the name of the current turn player.
     * 
     * @return the name of the current turn player
     */
    public String getTurnPlayerName()
    {
        return playerName[currentTurnPlayer];
    } // end of method String getTurnPlayerName()
} // end of class PlayerData