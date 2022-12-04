package TimNekk.model.ai;

import TimNekk.model.Coordinates;
import TimNekk.model.Field;
import TimNekk.model.enums.Turn;

import java.util.Set;

/**
 * Game bot abstract class.
 */
public abstract class Bot {
    protected final Field field;

    /**
     * Constructor for Bot.
     *
     * @param field field to play on.
     */
    public Bot(Field field) {
        this.field = field;
    }

    /**
     * Get move for bot.
     *
     * @param turn turn to make move.
     * @return coordinates of move.
     */
    public abstract Coordinates getMove(Turn turn);

    /**
     * Get move rating.
     *
     * @param coordinates coordinates of move.
     * @param turn turn to make move.
     * @return move rating.
     */
    protected float GetMoveRating(Coordinates coordinates, Turn turn) {
        float rating = GetPlacedCellRating(coordinates);

        Set<Coordinates> surrenderedCoordinatesSet = field.getSurrenderedCellsCoordinates(coordinates, turn);

        for (Coordinates surrenderedCoordinates : surrenderedCoordinatesSet) {
            rating += GetSurrenderedCellRating(surrenderedCoordinates);
        }

        return rating;
    }

    /**
     * Get rating for surrendered cell.
     *
     * @param coordinates coordinates of surrendered cell.
     * @return rating for surrendered cell.
     */
    private int GetSurrenderedCellRating(Coordinates coordinates) {
        return field.isEdgeCell(coordinates) ? 2 : 1;
    }

    /**
     * Get rating for placed cell.
     *
     * @param coordinates coordinates of placed cell.
     * @return rating for placed cell.
     */
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
