package TimNekk.controller;

import TimNekk.model.CellState;
import TimNekk.model.Field;
import TimNekk.view.ConsoleView;

import java.util.Scanner;

public class ConsoleController {
    private final ConsoleView view;
    private final Field field;
    private final Scanner scanner;

    public ConsoleController(Field field, ConsoleView view) {
        this.field = field;
        this.view = view;
        scanner = new Scanner(System.in);
    }

    public void ReadInput() {
        // read coordinates
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        // set cell state
        field.getCell(x, y).setState(CellState.BLACK);

        // update view
        view.update();
    }
}
