package TimNekk.view;

import TimNekk.model.Turn;

public record ViewConfig(String blackCell, String whiteCell, String emptyCell, String availableCell) {
    public String getCellFromTurn(Turn turn) {
        return switch (turn) {
            case BLACK -> blackCell;
            case WHITE -> whiteCell;
        };
    }
}
