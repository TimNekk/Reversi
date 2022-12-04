package TimNekk.controller;

import TimNekk.model.GameFlow;
import TimNekk.model.GameMode;

import java.util.Scanner;

public class MenuController extends Controller {
    private final GameFlow gameFlow;

    public MenuController(Scanner scanner, GameFlow gameFlow) {
        super(scanner);
        this.gameFlow = gameFlow;
    }

    public void selectGameMode() {
        int gameModeNumber = scanner.nextInt();
        GameMode gameMode = GameMode.parseInt(gameModeNumber);
        gameFlow.setGameMode(gameMode);
    }
}
