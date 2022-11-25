package TimNekk.controller;

import TimNekk.model.Coordinates;
import TimNekk.model.GameFlow;
import TimNekk.model.NoMoreMovesException;
import TimNekk.view.ConsoleView;

import java.util.Scanner;

public class ConsoleController {
    private final ConsoleView view;
    private final GameFlow gameFlow;
    private final Scanner scanner;

    public ConsoleController(ConsoleView view, GameFlow gameFlow) {
        this.view = view;
        this.gameFlow = gameFlow;
        scanner = new Scanner(System.in);
    }

    public void makeMove() throws NoMoreMovesException {
        if (gameFlow.isPlayerTurn()) {
            Coordinates coordinates = getPlayerMove();
            gameFlow.makePlayerMove(coordinates);
        } else {
            waitForEnter();
            gameFlow.makeBotMove();
        }

        view.update();
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
