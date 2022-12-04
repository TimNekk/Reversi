package TimNekk;

import TimNekk.controller.MenuController;
import TimNekk.controller.MovesController;
import TimNekk.model.Field;
import TimNekk.model.GameFlow;
import TimNekk.model.enums.MenuItem;
import TimNekk.model.exceptions.IllegalMoveException;
import TimNekk.model.exceptions.NoMoreMovesException;
import TimNekk.view.BoardView;
import TimNekk.view.MessageView;
import TimNekk.view.ViewConfig;

import java.util.Scanner;

/**
 * Application that runs the game.
 */
public final class Application {
    private GameFlow gameFlow;

    private MessageView messageView;
    private BoardView boardView;

    private MovesController movesController;
    private MenuController menuController;

    /**
     * Creates a new application.
     * Initializes the models, views, controllers.
     */
    public Application() {
        initializeModels();
        initializeViews();
        initializeControllers();
    }

    /**
     * Runs the application.
     */
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

    /**
     * Initializes the models.
     */
    private void initializeModels() {
        Field field = new Field();
        gameFlow = new GameFlow(field);
    }

    /**
     * Initializes the views.
     */
    private void initializeViews() {
        ViewConfig config = new ViewConfig(
                "\uD83D\uDD37",
                "\uD83D\uDD36",
                "⬛",
                "⬜");
        boardView = new BoardView(config, gameFlow);
        messageView = new MessageView(config, gameFlow);
    }

    /**
     * Initializes the controllers.
     */
    private void initializeControllers() {
        Scanner scanner = new Scanner(System.in);
        movesController = new MovesController(scanner, gameFlow);
        menuController = new MenuController(scanner, gameFlow);
    }

    /**
     * Shows the menu.
     * Prints the menu items.
     */
    private void showMenu() {
        messageView.printMenu();
        messageView.printMenuPrompt();
    }

    /**
     * Starts the game.
     * Prints the board, handles the user input.
     * Prints the turn info.
     * Makes the move.
     */
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

    /**
     * Makes the move.
     * Handles the user input.
     * Prints the error message if the move is illegal.
     */
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

    /**
     * Ends the game.
     * Prints the final score.
     * Resets the game flow.
     */
    private void endGame() {
        boardView.printField();
        messageView.printFinalScore();
        messageView.printGameOverMessage();
        gameFlow.endGame();
    }
}
