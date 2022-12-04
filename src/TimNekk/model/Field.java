package TimNekk.model;

import TimNekk.model.enums.CellState;
import TimNekk.model.enums.Direction;
import TimNekk.model.enums.Turn;
import TimNekk.model.exceptions.IllegalMoveException;

import java.util.HashSet;
import java.util.Set;

/**
 * Field class represents the game field.
 */
public final class Field {
    private final int width = 8;
    private final int height = 8;
    private final CellState[][] cellsStates = new CellState[width][height];
    private final CellState[][] previousCellsStates = new CellState[width][height];

    /**
     * Creates a new field with the initial state.
     */
    public Field() {
        reset();
    }

    /**
     * Gets the width of the field.
     *
     * @return the width of the field.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the field.
     *
     * @return the height of the field.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the state of the cell.
     *
     * @param coordinates the coordinates of the cell.
     * @return the state of the cell.
     */
    public CellState getCellState(Coordinates coordinates) throws IndexOutOfBoundsException {
        return cellsStates[coordinates.y()][coordinates.x()];
    }

    /**
     * Sets the state of the cell.
     *
     * @param coordinates the coordinates of the cell.
     * @param turn        current turn.
     */
    public void setCellState(Coordinates coordinates, Turn turn) throws IllegalMoveException {
        checkIfCellIsAvailable(coordinates, turn);

        savePreviousState();

        cellsStates[coordinates.y()][coordinates.x()] = CellState.fromTurn(turn);
        Set<Coordinates> surrenderedCellsCoordinates = getSurrenderedCellsCoordinates(coordinates, turn);
        for (Coordinates surrenderedCellCoordinates : surrenderedCellsCoordinates) {
            cellsStates[surrenderedCellCoordinates.y()][surrenderedCellCoordinates.x()] = CellState.fromTurn(turn);
        }
    }

    /**
     * Reverts the field to the previous state.
     */
    public void undo() {
        for (int y = 0; y < height; y++) {
            System.arraycopy(previousCellsStates[y], 0, cellsStates[y], 0, width);
        }
    }

    /**
     * Gets all cells coordinates that can be switched to the current turn.
     *
     * @param turn current turn.
     * @return available cells coordinates.
     */
    public Set<Coordinates> getAvailableCellsCoordinates(Turn turn) {
        Set<Coordinates> availableCells = new HashSet<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (isCellAvailable(coordinates, turn)) {
                    availableCells.add(new Coordinates(x, y));
                }
            }
        }

        return availableCells;
    }

    /**
     * Checks if the cell is available for the current turn.
     *
     * @param coordinates the coordinates of the cell.
     * @param turn        current turn.
     * @return true if the cell is available, false otherwise.
     */
    public boolean isCellAvailable(Coordinates coordinates, Turn turn) {
        if (getCellState(coordinates) != CellState.EMPTY) {
            return false;
        }

        Set<Coordinates> coordinatesSet = getSurrenderedCellsCoordinates(coordinates, turn);
        return !coordinatesSet.isEmpty();
    }

    /**
     * Gets all cells coordinates that will be switched to the current turn.
     *
     * @param coordinates the coordinates of the cell.
     * @param turn        current turn.
     * @return surrendered cells coordinates.
     */
    public Set<Coordinates> getSurrenderedCellsCoordinates(Coordinates coordinates, Turn turn) {
        Set<Coordinates> surrenderedCells = new HashSet<>();

        for (Direction direction : Direction.values()) {
            surrenderedCells.addAll(getSurrenderedCellsCoordinatesForDirection(coordinates, turn, direction));
        }

        return surrenderedCells;
    }

    /**
     * Gets the number of cells of the specified state.
     *
     * @param cellState the state of the cell.
     * @return the number of cells of the specified state.
     */
    public int getCellsCount(CellState cellState) {
        int count = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (cellsStates[y][x] == cellState) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Checks if the cell is near the edge.
     *
     * @param coordinates the coordinates of the cell.
     * @return true if the cell is near the edge, false otherwise.
     */
    public boolean isEdgeCell(Coordinates coordinates) {
        return coordinates.x() == 0 || coordinates.x() == width - 1 || coordinates.y() == 0 || coordinates.y() == height - 1;
    }

    /**
     * Checks if the cell is near the corner.
     *
     * @param coordinates the coordinates of the cell.
     * @return true if the cell is near the corner, false otherwise.
     */
    public boolean isCornerCell(Coordinates coordinates) {
        return (coordinates.x() == 0 || coordinates.x() == width - 1) && (coordinates.y() == 0 || coordinates.y() == height - 1);
    }

    /**
     * Resets the field.
     * All cells become empty.
     */
    public void reset() {
        buildEmptyField();
        initializeStartingPositions();
    }

    /**
     * Checks if there is at least one cell that can be switched to the current turn.
     *
     * @param turn current turn.
     * @return true if there is at available cell, false otherwise.
     */
    public boolean isAnyCellAvailable(Turn turn) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (isCellAvailable(coordinates, turn)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Fill the field with empty cells.
     */
    private void buildEmptyField() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cellsStates[y][x] = CellState.EMPTY;
            }
        }
    }

    /**
     * Fill the field with the initial state.
     */
    private void initializeStartingPositions() throws IndexOutOfBoundsException {
        cellsStates[3][3] = CellState.WHITE;
        cellsStates[4][4] = CellState.WHITE;
        cellsStates[3][4] = CellState.BLACK;
        cellsStates[4][3] = CellState.BLACK;
    }

    /**
     * Gets all cells coordinates that will be switched to the current turn in the specified direction.
     *
     * @param coordinates the coordinates of the cell.
     * @param turn        current turn.
     * @param direction   the direction.
     * @return surrendered cells coordinates.
     */
    private Set<Coordinates> getSurrenderedCellsCoordinatesForDirection(Coordinates coordinates, Turn turn, Direction direction) {
        Set<Coordinates> surrenderedCells = new HashSet<>();

        Coordinates currentCoordinates = coordinates;
        while (true) {
            currentCoordinates = currentCoordinates.getCoordinatesInDirection(direction);
            if (!isCoordinatesInField(currentCoordinates)) {
                return new HashSet<>();
            }

            CellState currentCellState = getCellState(currentCoordinates);
            if (currentCellState == CellState.EMPTY) {
                return new HashSet<>();
            }

            if (CellState.fromTurn(turn) == currentCellState) {
                return surrenderedCells;
            }

            surrenderedCells.add(currentCoordinates);
        }
    }

    /**
     * Checks if the coordinates are in the field.
     *
     * @param coordinates the coordinates.
     * @return true if the coordinates are in the field, false otherwise.
     */
    private boolean isCoordinatesInField(Coordinates coordinates) {
        return coordinates.x() >= 0 && coordinates.x() < width && coordinates.y() >= 0 && coordinates.y() < height;
    }

    /**
     * Saves the previous state of the field.
     */
    private void savePreviousState() {
        for (int y = 0; y < height; y++) {
            System.arraycopy(cellsStates[y], 0, previousCellsStates[y], 0, width);
        }
    }

    /**
     * Checks if the cell is available. If the cell is not available, throws an exception.
     *
     * @param coordinates the coordinates of the cell.
     * @param turn        current turn.
     */
    private void checkIfCellIsAvailable(Coordinates coordinates, Turn turn) throws IllegalMoveException {
        boolean isCellAvailable;
        try {
            isCellAvailable = isCellAvailable(coordinates, turn);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalMoveException("Cell is out of bounds", e);
        }
        if (!isCellAvailable) {
            throw new IllegalMoveException("Cell is not available for this turn");
        }
    }
}
