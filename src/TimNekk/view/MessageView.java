package TimNekk.view;

import TimNekk.model.*;
import TimNekk.model.enums.CellState;
import TimNekk.model.enums.GameMode;
import TimNekk.model.enums.MenuItem;

/**
 * View that represents the messages.
 */
public class MessageView {
    private final GameFlow gameFlow;
    private final Field field;
    private final ViewConfig config;

    /**
     * Creates a new message view.
     *
     * @param config view config.
     * @param gameFlow game flow.
     */
    public MessageView(ViewConfig config, GameFlow gameFlow) {
        this.gameFlow = gameFlow;
        this.field = gameFlow.getField();
        this.config = config;
    }

    /**
     * Prints every menu item.
     */
    public void printMenu() {
        for (MenuItem item : MenuItem.values()) {
            System.out.println(item.getNumber() + ". " + item.getName());
        }
    }

    /**
     * Prints information about the current turn.
     */
    public void printTurnInfo() {
        GameMode gameMode = gameFlow.getGameMode();

        if (gameMode == GameMode.PLAYER_VS_PLAYER) {
            System.out.println(config.getCellFromTurn(gameFlow.getTurn()) + " turn");
            return;
        }

        String cell = config.getCellFromTurn(gameFlow.getTurn());
        String subject = gameFlow.isPlayerTurn() ? "Your" : "Bot's";
        System.out.println(cell + " " + subject + " turn");
    }

    /**
     * Prints information about the winner and the score.
     */
    public void printGameOverMessage() {
        System.out.println("Game over");
    }

    public void printFinalScore() {
        int blackCount = field.getCellsCount(CellState.BLACK);
        int whiteCount = field.getCellsCount(CellState.WHITE);

        if (blackCount > whiteCount) {
            System.out.println(config.blackCell() + " won" + " (" + blackCount + ":" + whiteCount + ")");
        } else if (whiteCount > blackCount) {
            System.out.println(config.whiteCell() + " won" + " (" + whiteCount + ":" + blackCount + ")");
        } else {
            System.out.println("Draw" + " (" + blackCount + ":" + whiteCount + ")");
        }
    }

    /**
     * Prints prompt for entering the coordinates, continuing the game or undoing the last move.
     */
    public void printMovePrompt() {
        if (gameFlow.isPlayerTurn()) {
            System.out.print("Insert coordinates: ");
        } else {
            System.out.print("Press ENTER to continue or U to undo: ");
        }
    }

    /**
     * Prints prompt for entering the menu item number.
     */
    public void printMenuPrompt() {
        System.out.print("Insert number: ");
    }

    /**
     * Prints warning about the invalid coordinates.
     */
    public void printIllegalMove() {
        System.out.println("Illegal move!");
    }

    /**
     * Prints the highest player's score.
     */
    public void printHighestPlayerScore() {
        System.out.println("Highest player score: " + gameFlow.getPlayerMaxScore());
    }
}
