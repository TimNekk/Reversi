package TimNekk.view;

import TimNekk.model.*;
import TimNekk.model.enums.CellState;

/**
 * View for representing the game field.
 */
public class BoardView {
    private final GameFlow gameFlow;
    private final Field field;
    private final ViewConfig config;

    /**
     * Creates a new board view.
     *
     * @param gameFlow game flow.
     * @param config view config.
     */
    public BoardView(ViewConfig config, GameFlow gameFlow) {
        this.gameFlow = gameFlow;
        this.field = gameFlow.getField();
        this.config = config;
    }

    /**
     * Prints the field.
     */
    public void printField() {
        System.out.print("\t0\t1\t2\t3\t4\t5\t6\t7\n");

        for (int y = 0; y < field.getHeight(); y++) {
            System.out.print(y + "\t");

            for (int x = 0; x < field.getWidth(); x++) {
                Coordinates coordinates = new Coordinates(x, y);
                printCell(coordinates);
                System.out.print("\t");
            }

            System.out.println();
        }
    }

    /**
     * Prints the cell.
     *
     * @param coordinates the coordinates of the cell.
     */
    private void printCell(Coordinates coordinates) {
        if (gameFlow.isPlayerTurn() && field.isCellAvailable(coordinates, gameFlow.getTurn())) {
            System.out.print(config.availableCell());
            return;
        }

        CellState cellState = field.getCellState(coordinates);

        switch (cellState) {
            case EMPTY -> System.out.print(config.emptyCell());
            case BLACK -> System.out.print(config.blackCell());
            case WHITE -> System.out.print(config.whiteCell());
        }
    }
}
