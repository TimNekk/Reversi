package TimNekk.controller;

import TimNekk.model.Coordinates;
import TimNekk.model.GameFlow;
import TimNekk.model.exceptions.IllegalMoveException;
import TimNekk.model.exceptions.NoMoreMovesException;

import java.util.Scanner;

/**
 * Controller for handling moves
 */
public class MovesController extends Controller {
    private final GameFlow gameFlow;

    /**
     * Creates a new instance of the controller.
     *
     * @param scanner  Scanner for user input
     * @param gameFlow GameFlow instance
     */
    public MovesController(Scanner scanner, GameFlow gameFlow) {
        super(scanner);
        this.gameFlow = gameFlow;
    }

    /**
     * Handles user input for moves and makes a move.
     * If the user enters an invalid move, the method will ask the user to enter a new value.
     */
    public void makeMove() throws NoMoreMovesException, IllegalMoveException {
        if (gameFlow.isPlayerTurn()) {
            Coordinates coordinates = getPlayerMove();
            gameFlow.makePlayerMove(coordinates);
        } else {
            if (isUndoInInput()) {
                gameFlow.undoPlayersMove();
            } else {
                gameFlow.makeBotMove();
            }
        }
    }

    /**
     * Reads string from the user.
     *
     * @return true if the user entered "u" or "U", false otherwise
     */
    private boolean isUndoInInput() {
        scanner.nextLine();
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("u");
    }

    /**
     * Reads coordinates from the user.
     * If the user enters an invalid coordinates, the method will ask the user to enter a new value.
     *
     * @return Coordinates from user input
     */
    private Coordinates getPlayerMove() {
        int x = readInteger();
        int y = readInteger();

        return new Coordinates(x, y);
    }
}
