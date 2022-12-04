package TimNekk.model;

import TimNekk.model.ai.AdvancedBot;
import TimNekk.model.ai.Bot;
import TimNekk.model.ai.SimpleBot;
import TimNekk.model.enums.CellState;
import TimNekk.model.enums.GameMode;
import TimNekk.model.enums.Turn;
import TimNekk.model.exceptions.IllegalMoveException;
import TimNekk.model.exceptions.NoMoreMovesException;

/**
 * GameFlow class represents the game flow.
 */
public class GameFlow {
    private final Field field;
    private GameMode gameMode;
    private final Turn defaultPlayerTurn = Turn.WHITE;
    private Turn turn = defaultPlayerTurn;
    private Bot bot;
    private int playerMaxScore = 0;

    /**
     * Creates a new game flow.
     *
     * @param field game field.
     */
    public GameFlow(Field field) {
        this.field = field;
    }

    /**
     * Sets the game mode.
     *
     * @param gameMode game mode.
     */
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;

        switch (gameMode) {
            case PLAYER_VS_SIMPLE_BOT -> bot = new SimpleBot(field);
            case PLAYER_VS_ADVANCED_BOT -> bot = new AdvancedBot(field);
        }
    }

    /**
     * Switches the turn.
     *
     * @throws NoMoreMovesException if there are no more moves.
     */
    private void nextTurn() throws NoMoreMovesException {
        Turn nextTurn = turn == Turn.WHITE ? Turn.BLACK : Turn.WHITE;

        if (field.isAnyCellAvailable(nextTurn)) {
            turn = nextTurn;
        } else if (!field.isAnyCellAvailable(turn)) {
            throw new NoMoreMovesException("No available moves");
        }
    }

    /**
     * Gets the current turn.
     *
     * @return the current turn.
     */
    public Turn getTurn() {
        return turn;
    }

    /**
     * Gets field.
     *
     * @return field.
     */
    public Field getField() {
        return field;
    }

    /**
     * Gets the game mode.
     *
     * @return the game mode.
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * Checks if it is player's turn.
     *
     * @return true if it is player's turn, false otherwise.
     */
    public boolean isPlayerTurn() {
        return gameMode == GameMode.PLAYER_VS_PLAYER || turn == defaultPlayerTurn;
    }

    /**
     * Makes a player move.
     * Switches the turn.
     *
     * @param coordinates coordinates of the cell.
     * @throws NoMoreMovesException if there are no more moves.
     * @throws IllegalMoveException if the move is illegal.
     */
    public void makePlayerMove(Coordinates coordinates) throws NoMoreMovesException, IllegalMoveException {
        field.setCellState(coordinates, turn);
        nextTurn();
    }

    /**
     * Makes a bot move.
     * Switches the turn.
     *
     * @throws NoMoreMovesException if there are no more moves.
     * @throws IllegalMoveException if the move is illegal.
     */
    public void makeBotMove() throws NoMoreMovesException, IllegalMoveException {
        Coordinates coordinates = bot.getMove(turn);
        field.setCellState(coordinates, turn);
        nextTurn();
    }

    /**
     * Updates the player's max score.
     * Resets the field and the turn.
     */
    public void endGame() {
        updatePlayerMaxScore();
        turn = defaultPlayerTurn;
        field.reset();
    }

    /**
     * Updates the player's max score.
     */
    private void updatePlayerMaxScore() {
        if (gameMode == GameMode.PLAYER_VS_PLAYER) {
            int whiteScore = field.getCellsCount(CellState.WHITE);
            int blackScore = field.getCellsCount(CellState.BLACK);
            playerMaxScore = Math.max(Math.max(whiteScore, blackScore), playerMaxScore);
        } else {
            int playerScore = field.getCellsCount(CellState.fromTurn(defaultPlayerTurn));
            playerMaxScore = Math.max(playerScore, playerMaxScore);
        }
    }

    /**
     * Gets the player's max score.
     *
     * @return the player's max score.
     */
    public int getPlayerMaxScore() {
        return playerMaxScore;
    }

    /**
     * Reverts the last move.
     */
    public void undoPlayersMove() {
        field.undo();

        turn = defaultPlayerTurn;
    }
}
