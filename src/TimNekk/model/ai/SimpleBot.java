package TimNekk.model.ai;

import TimNekk.model.Coordinates;
import TimNekk.model.Field;
import TimNekk.model.Turn;

import java.util.Set;

public class SimpleBot extends Bot {

    public SimpleBot(Field field) {
        super(field);
    }

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
