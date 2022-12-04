package TimNekk.model.enums;

/**
 * Mode of the game.
 */
public enum GameMode {
    PLAYER_VS_SIMPLE_BOT, PLAYER_VS_ADVANCED_BOT, PLAYER_VS_PLAYER;

    /**
     * Returns the game mode from the given menu item.
     *
     * @param menuItem the menu item
     * @return the game mode
     */
    public static GameMode fromMenuItem(MenuItem menuItem) {
        return switch (menuItem) {
            case PLAYER_VS_SIMPLE_BOT -> PLAYER_VS_SIMPLE_BOT;
            case PLAYER_VS_ADVANCED_BOT -> PLAYER_VS_ADVANCED_BOT;
            case PLAYER_VS_PLAYER -> PLAYER_VS_PLAYER;
            default -> throw new IllegalArgumentException("Menu item is not a game mode");
        };
    }
}
