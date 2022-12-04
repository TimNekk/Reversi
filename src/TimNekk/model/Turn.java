package TimNekk.model;

public enum Turn {
    BLACK, WHITE;

    public CellState toCellState() {
        return this == BLACK ? CellState.BLACK : CellState.WHITE;
    }

    public Turn next() {
        return this == BLACK ? WHITE : BLACK;
    }
}
