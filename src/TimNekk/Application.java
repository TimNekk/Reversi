package TimNekk;

import TimNekk.controller.MenuController;
import TimNekk.controller.MovesController;
import TimNekk.model.Field;
import TimNekk.model.GameFlow;
import TimNekk.model.NoMoreMovesException;
import TimNekk.view.BoardView;
import TimNekk.view.MessageView;
import TimNekk.view.ViewConfig;

import java.util.Scanner;

public final class Application {
    private GameFlow gameFlow;

    private MessageView messageView;
    private BoardView boardView;

    private MovesController movesController;
    private MenuController menuController;

    public Application() {
        initializeModels();
        initializeViews();
        initializeControllers();
    }

    private void initializeModels() {
        Field field = new Field();
        gameFlow = new GameFlow(field);
    }

    private void initializeViews() {
        ViewConfig config = new ViewConfig(
                "\uD83D\uDD37",
                "\uD83D\uDD36",
                "⬛",
                "⬜");
        boardView = new BoardView(config, gameFlow);
        messageView = new MessageView(config, gameFlow);
    }

    private void initializeControllers() {
        Scanner scanner = new Scanner(System.in);
        movesController = new MovesController(scanner, gameFlow);
        menuController = new MenuController(scanner, gameFlow);
    }

    public void run() {
        showMenu();
        startGame();
        endGame();
    }

    private void showMenu() {
        messageView.printMenu();
        messageView.printMenuPrompt();
        menuController.selectGameMode();
    }

    private void startGame() {
        while (true) {
            boardView.printField();
            messageView.printTurnInfo();
            messageView.printMovePrompt();
            try {
                movesController.makeMove();
            } catch (NoMoreMovesException e) {
                return;
            }
        }
    }

    private void endGame() {
        messageView.printFinalScore();
        messageView.printGameOverMessage();
    }
}
