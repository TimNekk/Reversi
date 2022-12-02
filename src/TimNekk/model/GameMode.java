package TimNekk.model;

public enum GameMode {
    PLAYER_VS_SIMPLE_BOT, PLAYER_VS_ADVANCED_BOT, PLAYER_VS_PLAYER;

    public static GameMode parseInt(int number) {
        switch (number) {
            case 1: return PLAYER_VS_SIMPLE_BOT;
            case 2: return PLAYER_VS_ADVANCED_BOT;
            case 3: return PLAYER_VS_PLAYER;
        }

        throw new IllegalArgumentException("Game mode can only be converted from: 1, 2, 3");
    }
}
