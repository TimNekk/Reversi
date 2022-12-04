package TimNekk;

import TimNekk.controller.MenuController;
import TimNekk.controller.MovesController;
import TimNekk.model.Field;
import TimNekk.model.GameFlow;
import TimNekk.model.MenuItem;
import TimNekk.model.exceptions.IllegalMoveException;
import TimNekk.model.exceptions.NoMoreMovesException;
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
        MenuItem menuItem = menuController.handlerMenuInput();

        if (menuItem == MenuItem.PRINT_HIGHEST_PLAYER_SCORE) {
            messageView.printHighestPlayerScore();
        } else if (menuItem == MenuItem.EXIT) {
            throw new IllegalStateException("Exit");
        } else {
            startGame();
            endGame();
        }
    }

    private void showMenu() {
        messageView.printMenu();
        messageView.printMenuPrompt();
    }

    private void startGame() {
        while (true) {
            boardView.printField();
            messageView.printTurnInfo();
            messageView.printMovePrompt();
            try {
                makeMove();
            } catch (NoMoreMovesException e) {
                return;
            }
        }
    }

    private void makeMove() throws NoMoreMovesException {
        while (true) {
            try {
                movesController.makeMove();
                break;
            } catch (IllegalMoveException e) {
                messageView.printIllegalMove();
                messageView.printMovePrompt();
            }
        }
    }

    private void endGame() {
        boardView.printField();
        messageView.printFinalScore();
        messageView.printGameOverMessage();
        gameFlow.endGame();
    }
}
