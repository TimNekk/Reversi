package TimNekk.model;

public class Cell {
    CellState state = CellState.EMPTY;

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public void switchState() {
        if (state == CellState.EMPTY) {
            throw new IllegalStateException("Cell is empty");
        }

        if (state == CellState.BLACK) {
            state = CellState.WHITE;
        } else if (state == CellState.WHITE) {
            state = CellState.BLACK;
        }
    }
}
