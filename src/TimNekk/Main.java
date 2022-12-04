package TimNekk;

public class Main {
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