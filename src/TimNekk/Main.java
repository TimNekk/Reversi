package TimNekk;

import TimNekk.controller.ConsoleController;
import TimNekk.model.CellState;
import TimNekk.model.Field;
import TimNekk.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Field field = new Field(8, 8);
        ConsoleView view = new ConsoleView(field);
        ConsoleController controller = new ConsoleController(field, view);

        view.update();

    while (true) {
            controller.ReadInput();
        }
    }
}