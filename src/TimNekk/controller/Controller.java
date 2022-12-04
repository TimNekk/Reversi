package TimNekk.controller;

import java.util.Scanner;

/**
 * Base class for all controllers.
 */
public abstract class Controller {
    protected final Scanner scanner;

    /**
     * Creates a new instance of the controller.
     *
     * @param scanner Scanner for reading user input.
     */
    protected Controller(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads an integer from the user.
     * If the user enters a non-integer value, the method will ask the user to enter a new value.
     *
     * @return Integer value.
     */
    protected int readInteger() {
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        return scanner.nextInt();
    }
}
