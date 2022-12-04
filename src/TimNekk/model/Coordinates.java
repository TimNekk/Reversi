package TimNekk.model;

import TimNekk.model.enums.Direction;

/**
 * Coordinates record.
 */
public record Coordinates(int x, int y) {
    /**
     * Returns the coordinates of the cell in the specified direction.
     *
     * @param direction direction.
     * @return coordinates of the cell in the specified direction.
     */
    public Coordinates getCoordinatesInDirection(Direction direction) {
        return new Coordinates(x + direction.getDx(), y + direction.getDy());
    }
}
