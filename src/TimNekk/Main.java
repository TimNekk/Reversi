package TimNekk;

/**
 * Main class.
 */
public class Main {
    /**
     * Main method.
     * Runs the game.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        Application application = new Application();

        while (true) {
            try {
                application.run();
            } catch (IllegalStateException e) {
                break;
            }
        }
    }
}