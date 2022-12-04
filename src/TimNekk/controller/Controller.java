package TimNekk.controller;

import java.util.Scanner;

public abstract class Controller {
    protected final Scanner scanner;

    public Controller(Scanner scanner) {
        this.scanner = scanner;
    }

    protected int readInteger() {
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        return scanner.nextInt();
    }
}
