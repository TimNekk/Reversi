package TimNekk;

import TimNekk.controller.ConsoleController;
import TimNekk.model.Field;
import TimNekk.model.GameFlow;
import TimNekk.model.GameMode;
import TimNekk.model.NoMoreMovesException;
import TimNekk.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        GameFlow gameFlow = new GameFlow(field, GameMode.PLAYER_VS_BOT);

        ConsoleView view = new ConsoleView(gameFlow);
        ConsoleController controller = new ConsoleController(view, gameFlow);

        view.update();

        while (true) {
            try {
                controller.makeMove();
            } catch (NoMoreMovesException e) {
                view.showGameEnd();
                break;
            }
        }
    }
}