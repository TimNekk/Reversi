package TimNekk.model.enums;

/**
 * State of a cell on the board.
 */
public enum CellState {
    EMPTY, BLACK, WHITE;

    /**
     * Returns the cell state from the given turn.
     *
     * @param turn the turn
     * @return the cell state
     */
    public static CellState fromTurn(Turn turn) {
        return switch (turn) {
            case BLACK -> BLACK;
            case WHITE -> WHITE;
        };
    }
}
