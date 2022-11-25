package TimNekk.model;

public class GameFlow {
    private final Field field;
    private final GameMode gameMode;
    private Turn turn = Turn.WHITE;
    private Bot bot;

    public GameFlow(Field field, GameMode gameMode) {
        this.field = field;
        this.gameMode = gameMode;

        if (gameMode == GameMode.PLAYER_VS_BOT) {
            bot = new Bot(field);
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
        return gameMode == GameMode.PLAYER_VS_BOT && turn == Turn.WHITE ||
                gameMode == GameMode.PLAYER_VS_PLAYER && turn == Turn.WHITE;
    }

    public void makePlayerMove(Coordinates coordinates) throws NoMoreMovesException {
        field.setCellState(coordinates, turn);
        nextTurn();
    }

    public void makeBotMove() throws NoMoreMovesException {
        Coordinates coordinates = bot.getMove(turn);
        System.out.println("Bot move: " + coordinates);
        field.setCellState(coordinates, turn);
        nextTurn();
    }
}
