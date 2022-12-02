package TimNekk.model.ai;

import TimNekk.model.Coordinates;
import TimNekk.model.Field;
import TimNekk.model.Turn;

import java.util.List;

public class SimpleBot extends Bot {

    public SimpleBot(Field field) {
        super(field);
    }

    @Override
    public Coordinates getMove(Turn turn) {
        List<Coordinates> coordinates = getAvailableCellsCoordinates(turn);
        return coordinates.get((int) (Math.random() * coordinates.size()));
    }
}
