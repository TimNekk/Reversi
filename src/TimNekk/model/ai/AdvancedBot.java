package TimNekk.model.ai;

import TimNekk.model.Coordinates;
import TimNekk.model.Field;
import TimNekk.model.Turn;
import TimNekk.model.exceptions.IllegalMoveException;

import java.util.Set;

public class AdvancedBot extends Bot {

    public AdvancedBot(Field field) {
        super(field);
    }

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

    private float getNextMaxRating(Coordinates coordinate, Turn turn) {
        try {
            field.setCellState(coordinate, turn);
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
