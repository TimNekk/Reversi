package TimNekk.model.enums;

/**
 * Item of the menu.
 */
public enum MenuItem {
    PLAYER_VS_SIMPLE_BOT(1, "Player VS Bot"),
    PLAYER_VS_ADVANCED_BOT(2, "Player VS Advanced Bot"),
    PLAYER_VS_PLAYER(3, "Player VS Player"),
    PRINT_HIGHEST_PLAYER_SCORE(4, "Print Highest Player Score"),
    EXIT(5, "Exit");

    private final int number;
    private final String name;

    /**
     * Creates a new menu item.
     *
     * @param number the number of the menu item
     * @param name   the name of the menu item
     */
    MenuItem(int number, String name) {
        this.number = number;
        this.name = name;
    }

    /**
     * Returns the number of the menu item.
     *
     * @return the number of the menu item
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the name of the menu item.
     *
     * @return the name of the menu item
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the menu item from the given number.
     *
     * @param number the number of the menu item
     * @return the menu item
     * @throws IllegalArgumentException if the number is not a menu item
     */
    public static MenuItem fromNumber(int number) {
        for (MenuItem item : values()) {
            if (item.getNumber() == number) {
                return item;
            }
        }

        throw new IllegalArgumentException("No such menu item");
    }
}
