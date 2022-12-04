package TimNekk.controller;

import TimNekk.model.GameFlow;
import TimNekk.model.enums.GameMode;
import TimNekk.model.enums.MenuItem;

import java.util.Scanner;

/**
 * Controller for menu actions
 */
public class MenuController extends Controller {
    private final GameFlow gameFlow;

    /**
     * Creates a new instance of the controller.
     *
     * @param scanner  Scanner for user input
     * @param gameFlow GameFlow instance
     */
    public MenuController(Scanner scanner, GameFlow gameFlow) {
        super(scanner);
        this.gameFlow = gameFlow;
    }

    /**
     * Handles user input for menu actions
     *
     * @return MenuItem
     */
    public MenuItem handlerMenuInput() {
        MenuItem menuItem = getMenuItem();

        try {
            gameFlow.setGameMode(GameMode.fromMenuItem(menuItem));
        } catch (IllegalArgumentException ignored) {
        }

        return menuItem;
    }

    /**
     * Reads an integer from the user.
     * If the user enters an integer value that is not a valid menu item,
     * the method will ask the user to enter a new value.
     *
     * @return MenuItem from user input
     */
    private MenuItem getMenuItem() {
        while (true) {
            int menuNumber = readInteger();
            try {
                return MenuItem.fromNumber(menuNumber);
            } catch (IllegalArgumentException ignored) {
            }
        }
    }
}
