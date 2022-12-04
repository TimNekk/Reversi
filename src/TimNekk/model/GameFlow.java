package TimNekk.model;

import TimNekk.model.ai.AdvancedBot;
import TimNekk.model.ai.Bot;
import TimNekk.model.ai.SimpleBot;
import TimNekk.model.exceptions.IllegalMoveException;
import TimNekk.model.exceptions.NoMoreMovesException;

public class GameFlow {
    private final Field field;
    private GameMode gameMode;
    private Turn turn = Turn.WHITE;
    private Bot bot;

    public GameFlow(Field field) {
        this.field = field;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;

        switch (gameMode) {
            case PLAYER_VS_SIMPLE_BOT -> bot = new SimpleBot(field);
            case PLAYER_VS_ADVANCED_BOT -> bot = new AdvancedBot(field);
        }
    }

    private void nextTurn() throws NoMoreMovesException {
        Turn nextTurn = turn == Turn.WHITE ? Turn.BLACK : Turn.WHITE;

        if (field.isAnyCellAvailable(nextTurn)) {
            turn = nextTurn;
        } else if (!field.isAnyCellAvailable(turn)) {
            throw new NoMoreMovesException("No available moves");
        }
    }

    public Turn getTurn() {
        return turn;
    }

    public Field getField() {
        return field;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public boolean isPlayerTurn() {
        return gameMode == GameMode.PLAYER_VS_PLAYER || turn == Turn.WHITE;
    }

    public void makePlayerMove(Coordinates coordinates) throws NoMoreMovesException, IllegalMoveException {
        field.setCellState(coordinates, turn);
        nextTurn();
    }

    public void makeBotMove() throws NoMoreMovesException, IllegalMoveException {
        Coordinates coordinates = bot.getMove(turn);
        field.setCellState(coordinates, turn);
        nextTurn();
    }
}
