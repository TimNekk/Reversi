package TimNekk.model;

import TimNekk.model.exceptions.IllegalMoveException;

import java.util.HashSet;
import java.util.Set;

public class Field {
    private final int width = 8;
    private final int height = 8;
    private CellState[][] cellsStates = new CellState[width][height];
    private final CellState[][] previousCellsStates = new CellState[width][height];

    public Field() {
        reset();
    }

    private void buildEmptyField() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cellsStates[y][x] = CellState.EMPTY;
            }
        }
    }

    private void initializeStartingPositions() throws IndexOutOfBoundsException {
        cellsStates[3][3] = CellState.WHITE;
        cellsStates[4][4] = CellState.WHITE;
        cellsStates[3][4] = CellState.BLACK;
        cellsStates[4][3] = CellState.BLACK;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CellState getCellState(Coordinates coordinates) throws IndexOutOfBoundsException {
        return cellsStates[coordinates.y()][coordinates.x()];
    }

    public void setCellState(Coordinates coordinates, Turn turn) throws IllegalMoveException {
        checkIfCellIsAvailable(coordinates, turn);

        savePreviousState();

        cellsStates[coordinates.y()][coordinates.x()] = turn.toCellState();
        Set<Coordinates> surrenderedCellsCoordinates = getSurrenderedCellsCoordinates(coordinates, turn);
        for (Coordinates surrenderedCellCoordinates : surrenderedCellsCoordinates) {
            cellsStates[surrenderedCellCoordinates.y()][surrenderedCellCoordinates.x()] = turn.toCellState();
        }
    }

    private void savePreviousState() {
        for (int y = 0; y < height; y++) {
            System.arraycopy(cellsStates[y], 0, previousCellsStates[y], 0, width);
        }
    }

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

    protected void undo() {
        cellsStates = previousCellsStates;
    }

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

    public boolean isCellAvailable(Coordinates coordinates, Turn turn) {
        if (getCellState(coordinates) != CellState.EMPTY) {
            return false;
        }

        Set<Coordinates> coordinatesSet = getSurrenderedCellsCoordinates(coordinates, turn);
        return !coordinatesSet.isEmpty();
    }

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

    public Set<Coordinates> getSurrenderedCellsCoordinates(Coordinates coordinates, Turn turn) {
        Set<Coordinates> surrenderedCells = new HashSet<>();

        for (Direction direction : Direction.values()) {
            surrenderedCells.addAll(getSurrenderedCellsCoordinatesForDirection(coordinates, turn, direction));
        }

        return surrenderedCells;
    }

    private Set<Coordinates> getSurrenderedCellsCoordinatesForDirection(Coordinates coordinates,
                                                                        Turn turn, Direction direction) {
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

            if (turn.toCellState() == currentCellState) {
                return surrenderedCells;
            }

            surrenderedCells.add(currentCoordinates);
        }
    }

    private boolean isCoordinatesInField(Coordinates currentCoordinates) {
        return currentCoordinates.x() >= 0 && currentCoordinates.x() < width
                && currentCoordinates.y() >= 0 && currentCoordinates.y() < height;
    }

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

    public boolean isEdgeCell(Coordinates coordinates) {
        return coordinates.x() == 0 || coordinates.x() == width - 1
                || coordinates.y() == 0 || coordinates.y() == height - 1;
    }

    public boolean isCornerCell(Coordinates coordinates) {
        return (coordinates.x() == 0 || coordinates.x() == width - 1)
                && (coordinates.y() == 0 || coordinates.y() == height - 1);
    }

    public void reset() {
        buildEmptyField();
        initializeStartingPositions();
    }
}
