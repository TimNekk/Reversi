package TimNekk.controller;

import TimNekk.model.Coordinates;
import TimNekk.model.GameFlow;
import TimNekk.model.NoMoreMovesException;

import java.util.Scanner;

public class MovesController extends Controller {
    private final GameFlow gameFlow;

    public MovesController(Scanner scanner, GameFlow gameFlow) {
        super(scanner);
        this.gameFlow = gameFlow;
    }

    public void makeMove() throws NoMoreMovesException {
        if (gameFlow.isPlayerTurn()) {
            Coordinates coordinates = getPlayerMove();
            gameFlow.makePlayerMove(coordinates);
        } else {
            waitForEnter();
            gameFlow.makeBotMove();
        }
    }

    private void waitForEnter() {
        scanner.nextLine();
        scanner.nextLine();
    }

    private Coordinates getPlayerMove() {
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        return new Coordinates(x, y);
    }
}