package TimNekk.model.ai;

import TimNekk.model.Coordinates;
import TimNekk.model.Field;
import TimNekk.model.enums.Turn;

import java.util.Set;

/**
 * Simple bot implementation.
 */
public class SimpleBot extends Bot {
    /**
     * Creates a new simple bot.
     *
     * @param field field to play on.
     */
    public SimpleBot(Field field) {
        super(field);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Coordinates getMove(Turn turn) {
        Set<Coordinates> coordinates = field.getAvailableCellsCoordinates(turn);

        float maxRating = 0;

        Coordinates bestMove = null;

        for (Coordinates coordinate : coordinates) {
            float rating = GetMoveRating(coordinate, turn);

            if (rating > maxRating) {
                maxRating = rating;
                bestMove = coordinate;
            }
        }

        return bestMove;
    }
}
