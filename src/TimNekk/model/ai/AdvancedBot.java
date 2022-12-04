package TimNekk.model.ai;

import TimNekk.model.Coordinates;
import TimNekk.model.Field;
import TimNekk.model.enums.Turn;
import TimNekk.model.exceptions.IllegalMoveException;

import java.util.Set;

/**
 * Advanced bot that predicts the next move of the player and tries to counter it.
 */
public class AdvancedBot extends Bot {
    /**
     * Creates a new advanced bot.
     *
     * @param field the field to play on.
     */
    public AdvancedBot(Field field) {
        super(field);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Coordinates getMove(Turn turn) {
        float maxRating = -Float.MAX_VALUE;
        Coordinates bestMove = null;

        Set<Coordinates> coordinates = field.getAvailableCellsCoordinates(turn);
        for (Coordinates coordinate : coordinates) {
            float rating = GetMoveRating(coordinate, turn) - getNextMaxRating(coordinate, turn);

            if (rating > maxRating) {
                maxRating = rating;
                bestMove = coordinate;
            }
        }

        return bestMove;
    }

    /**
     * Gets the rating of the move.
     *
     * @param coordinates the coordinates of the move.
     * @param turn the turn of the move.
     * @return the rating of the move.
     */
    private float getNextMaxRating(Coordinates coordinates, Turn turn) {
        try {
            field.setCellState(coordinates, turn);
        } catch (IllegalMoveException ignored) {
        }

        float nextMaxRating = 0;
        Set<Coordinates> nextCoordinates = field.getAvailableCellsCoordinates(turn.next());

        for (Coordinates nextCoordinate : nextCoordinates) {
            nextMaxRating = Math.max(GetMoveRating(nextCoordinate, turn.next()), nextMaxRating);
        }
        field.undo();

        return nextMaxRating;
    }
}
