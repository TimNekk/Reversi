package TimNekk.model;

import java.util.List;

public class Bot {
    private final Field field;

    public Bot(Field field) {
        this.field = field;
    }

    public Coordinates getMove(Turn turn) {
        List<Coordinates> availableCellsCoordinates = field.getAvailableCellsCoordinates(turn);

        // random move
        return availableCellsCoordinates.get((int) (Math.random() * availableCellsCoordinates.size()));
    }
}
