package TimNekk.model.ai;

import TimNekk.model.Coordinates;
import TimNekk.model.Field;
import TimNekk.model.Turn;

import java.util.Set;

public abstract class Bot {
    protected final Field field;

    public Bot(Field field) {
        this.field = field;
    }

    public abstract Coordinates getMove(Turn turn);

    protected float GetMoveRating(Coordinates coordinates, Turn turn) {
        float rating = GetPlacedCellRating(coordinates);

        Set<Coordinates> surrenderedCoordinatesSet = field.getSurrenderedCellsCoordinates(coordinates, turn);

        for (Coordinates surrenderedCoordinates : surrenderedCoordinatesSet) {
            rating += GetSurrenderedCellRating(surrenderedCoordinates);
        }

        return rating;
    }

    private int GetSurrenderedCellRating(Coordinates surrenderedCoordinates) {
        return field.isEdgeCell(surrenderedCoordinates) ? 2 : 1;
    }

    private float GetPlacedCellRating(Coordinates coordinates) {
        if (field.isCornerCell(coordinates)) {
            return 0.8f;
        }
        if (field.isEdgeCell(coordinates)) {
            return 0.4f;
        }
        return 0;
    }
}
