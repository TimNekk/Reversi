package TimNekk.model.enums;

/**
 * Direction of a move.
 */
public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    UP_LEFT(-1, -1),
    UP_RIGHT(-1, 1),
    DOWN_LEFT(1, -1),
    DOWN_RIGHT(1, 1);

    private final int dx;
    private final int dy;

    /**
     * Constructor.
     *
     * @param dx change in x
     * @param dy change in y
     */
    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Get change in x.
     *
     * @return change in x
     */
    public int getDx() {
        return dx;
    }

    /**
     * Get change in y.
     *
     * @return change in y
     */
    public int getDy() {
        return dy;
    }
}
