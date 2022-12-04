package TimNekk.controller;

import java.util.Scanner;

public abstract class Controller {
    protected final Scanner scanner;

    public Controller(Scanner scanner) {
        this.scanner = scanner;
    }
}
