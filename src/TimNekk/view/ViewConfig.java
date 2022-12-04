package TimNekk.view;

import TimNekk.model.enums.Turn;

/**
 * Configurations for the views.
 */
public record ViewConfig(String blackCell, String whiteCell, String emptyCell, String availableCell) {
    /**
     * Creates a new view config.
     *
     * @param turn current turn.
     */
    public String getCellFromTurn(Turn turn) {
        return switch (turn) {
            case BLACK -> blackCell;
            case WHITE -> whiteCell;
        };
    }
}
