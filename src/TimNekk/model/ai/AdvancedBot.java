package TimNekk.model.ai;

import TimNekk.model.Coordinates;
import TimNekk.model.Field;
import TimNekk.model.Turn;

public class AdvancedBot extends Bot {

    public AdvancedBot(Field field) {
        super(field);
    }

    @Override
    public Coordinates getMove(Turn turn) {
        return null;
    }
}
