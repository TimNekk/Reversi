package TimNekk.view;

import TimNekk.model.*;

public class MessageView {
    private final GameFlow gameFlow;
    private final Field field;
    private final ViewConfig config;

    public MessageView(ViewConfig config, GameFlow gameFlow) {
        this.gameFlow = gameFlow;
        this.field = gameFlow.getField();
        this.config = config;
    }

    public void printMenu() {
        System.out.println("1. Player VS Bot");
        System.out.println("2. Player VS Advanced Bot");
        System.out.println("3. Player VS Player");
    }

    public void printTurnInfo() {
        GameMode gameMode = gameFlow.getGameMode();

        if (gameMode == GameMode.PLAYER_VS_PLAYER) {
            System.out.println(gameFlow.getTurn() + " turn");
            return;
        }

        if (gameFlow.getTurn() == Turn.WHITE) {
            System.out.println(config.whiteCell() + " Your turn");
        } else {
            System.out.println(config.blackCell() + " Bot turn");
        }
    }

    public void printGameOverMessage() {
        System.out.println("\nGame over");
    }

    public void printFinalScore() {
        int blackCount = field.getCellsCount(CellState.BLACK);
        int whiteCount = field.getCellsCount(CellState.WHITE);

        if (blackCount > whiteCount) {
            System.out.println(config.blackCell() + " won" + " (" + blackCount + ":" + whiteCount + ")");
        } else if (whiteCount > blackCount) {
            System.out.println(config.whiteCell() + " won" + " (" + whiteCount + ":" + blackCount + ")");
        } else {
            System.out.println("Draw" + " (" + blackCount + ":" + whiteCount + ")");
        }
    }

    public void printMovePrompt() {
        if (gameFlow.isPlayerTurn()) {
            System.out.print("Insert coordinates: ");
        } else {
            System.out.print("Press ENTER to continue..");
        }
    }

    public void printMenuPrompt() {
        System.out.print("Insert number: ");
    }
}
