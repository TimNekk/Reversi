package TimNekk.model;

public enum MenuItem {
    PLAYER_VS_SIMPLE_BOT(1, "Player VS Bot"),
    PLAYER_VS_ADVANCED_BOT(2, "Player VS Advanced Bot"),
    PLAYER_VS_PLAYER(3, "Player VS Player"),
    PRINT_HIGHEST_PLAYER_SCORE(4, "Print Highest Player Score"),
    EXIT(5, "Exit");

    private final int number;
    private final String name;

    MenuItem(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public static MenuItem fromNumber(int number) {
        for (MenuItem item : values()) {
            if (item.getNumber() == number) {
                return item;
            }
        }

        throw new IllegalArgumentException("No such menu item");
    }
}
