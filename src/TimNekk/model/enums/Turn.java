package TimNekk.model.enums;

/**
 * Turn of the game.
 */
public enum Turn {
    BLACK, WHITE;

    /**
     * Gets the next turn.
     *
     * @return the next turn
     */
    public Turn next() {
        return this == BLACK ? WHITE : BLACK;
    }
}
