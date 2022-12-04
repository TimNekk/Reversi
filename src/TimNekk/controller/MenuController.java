package TimNekk.controller;

import TimNekk.model.GameFlow;
import TimNekk.model.GameMode;
import TimNekk.model.MenuItem;

import java.util.Scanner;

public class MenuController extends Controller {
    private final GameFlow gameFlow;

    public MenuController(Scanner scanner, GameFlow gameFlow) {
        super(scanner);
        this.gameFlow = gameFlow;
    }

    public MenuItem handlerMenuInput() {
        MenuItem menuItem = getMenuItem();

        try {
            gameFlow.setGameMode(GameMode.fromMenuItem(menuItem));
        } catch (IllegalArgumentException ignored) {
        }

        return menuItem;
    }

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
