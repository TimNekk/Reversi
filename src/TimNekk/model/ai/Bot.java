package TimNekk.model.ai;

import TimNekk.model.Coordinates;
import TimNekk.model.Field;
import TimNekk.model.Turn;

public abstract class Bot {
    protected final Field field;

    public Bot(Field field) {
        this.field = field;
    }

    public abstract Coordinates getMove(Turn turn);
}
