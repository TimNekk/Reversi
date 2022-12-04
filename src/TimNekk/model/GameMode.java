package TimNekk.model;

public enum GameMode {
    PLAYER_VS_SIMPLE_BOT, PLAYER_VS_ADVANCED_BOT, PLAYER_VS_PLAYER;

    public static GameMode fromMenuItem(MenuItem menuItem) {
        return switch (menuItem) {
            case PLAYER_VS_SIMPLE_BOT -> PLAYER_VS_SIMPLE_BOT;
            case PLAYER_VS_ADVANCED_BOT -> PLAYER_VS_ADVANCED_BOT;
            case PLAYER_VS_PLAYER -> PLAYER_VS_PLAYER;
            default -> throw new IllegalArgumentException("Menu item is not a game mode");
        };
    }
}
