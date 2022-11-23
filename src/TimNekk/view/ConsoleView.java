package TimNekk.view;

import TimNekk.model.Cell;
import TimNekk.model.Field;

public class ConsoleView {
    private final Field field;

    public ConsoleView(Field field) {
        this.field = field;
    }

    public void update() {
        clear();
        draw_field();
    }

    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void draw_field() {
        for (int y = 0; y < field.getHeight(); y++) {
            for (int x = 0; x < field.getWidth(); x++) {
                Cell cell = field.getCell(x, y);
                draw_cell(cell);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    private void draw_cell(Cell cell) {
        switch (cell.getState()) {
            case EMPTY -> System.out.print("⬛");
            case BLACK -> System.out.print("\uD83D\uDD37");
            case WHITE -> System.out.print("\uD83D\uDD36");
        }
    }
}
