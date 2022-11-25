package TimNekk.view;

import TimNekk.model.*;

public class ConsoleView {
    private final GameFlow gameFlow;
    private final Field field;
    private final String blackCell = "\uD83D\uDD37";
    private final String whiteCell = "\uD83D\uDD36";
    private final String emptyCell = "⬛";
    private final String availableCell = "⬜";

    public ConsoleView(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
        this.field = gameFlow.getField();
    }

    public void update() {
        clear();
        draw_field();
        printTurnInfo();
    }

    private void printTurnInfo() {
        switch (gameFlow.getGameMode()) {
            case PLAYER_VS_BOT:
                if (gameFlow.getTurn() == Turn.WHITE) {
                    System.out.println(whiteCell + " Your turn");
                } else {
                    System.out.println(blackCell + " Bot turn");
                }
                break;
            case PLAYER_VS_PLAYER:
                System.out.println(gameFlow.getTurn() + " turn");
                break;
        }
    }

    public void clear() {
        for (int i = 0; i < 1; i++) {
            System.out.print("\n");
        }
    }

    private void draw_field() {
        System.out.print("\t0\t1\t2\t3\t4\t5\t6\t7\n");

        for (int y = 0; y < field.getHeight(); y++) {
            System.out.print(y + "\t");
            for (int x = 0; x < field.getWidth(); x++) {
                Coordinates coordinates = new Coordinates(x, y);
                draw_cell(coordinates);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    private void draw_cell(Coordinates coordinates) {
        if (gameFlow.isPlayerTurn() && field.isCellAvailable(coordinates, gameFlow.getTurn())) {
            System.out.print(availableCell);
            return;
        }

        CellState cellState = field.getCellState(coordinates);

        switch (cellState) {
            case EMPTY -> System.out.print(emptyCell);
            case BLACK -> System.out.print(blackCell);
            case WHITE -> System.out.print(whiteCell);
        }
    }

    public void showGameEnd() {
        System.out.println("\nGame over");
        draw_field();


        int blackCount = field.getCellsCount(CellState.BLACK);
        int whiteCount = field.getCellsCount(CellState.WHITE);

        if (blackCount > whiteCount) {
            System.out.println(blackCell + " won" + " (" + blackCount + ":" + whiteCount + ")");
        } else if (whiteCount > blackCount) {
            System.out.println(whiteCell + " won" + " (" + whiteCount + ":" + blackCount + ")");
        } else {
            System.out.println("Draw" + " (" + blackCount + ":" + whiteCount + ")");
        }
    }
}
