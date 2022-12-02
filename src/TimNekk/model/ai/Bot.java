package TimNekk.model.ai;

import TimNekk.model.Coordinates;
import TimNekk.model.Field;
import TimNekk.model.Turn;

import java.util.List;

public abstract class Bot {
    private final Field field;

    public Bot(Field field) {
        this.field = field;
    }

    protected List<Coordinates> getAvailableCellsCoordinates(Turn turn) {
        return field.getAvailableCellsCoordinates(turn);
    }

    public abstract Coordinates getMove(Turn turn);
}
