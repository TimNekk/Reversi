package TimNekk.controller;

import TimNekk.model.Coordinates;
import TimNekk.model.GameFlow;
import TimNekk.model.exceptions.IllegalMoveException;
import TimNekk.model.exceptions.NoMoreMovesException;

import java.util.Scanner;

public class MovesController extends Controller {
    private final GameFlow gameFlow;

    public MovesController(Scanner scanner, GameFlow gameFlow) {
        super(scanner);
        this.gameFlow = gameFlow;
    }

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

    private boolean isUndoInInput() {
        scanner.nextLine();
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("u");
    }

    private Coordinates getPlayerMove() {
        int x = readInteger();
        int y = readInteger();

        return new Coordinates(x, y);
    }
}
