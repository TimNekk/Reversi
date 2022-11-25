package TimNekk.model;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final int width = 8;
    private final int height = 8;
    private final CellState[][] cellStates = new CellState[width][height];

    public Field() throws IndexOutOfBoundsException {
        buildEmptyField();
        initializeStartingPositions();
    }

    private void buildEmptyField() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cellStates[y][x] = CellState.EMPTY;
            }
        }
    }

    private void initializeStartingPositions() throws IndexOutOfBoundsException {
        cellStates[3][3] = CellState.WHITE;
        cellStates[4][4] = CellState.WHITE;
        cellStates[3][4] = CellState.BLACK;
        cellStates[4][3] = CellState.BLACK;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CellState getCellState(Coordinates coordinates) {
        return cellStates[coordinates.y()][coordinates.x()];
    }

    public void setCellState(Coordinates coordinates, Turn turn) {
        if (!isCellAvailable(coordinates, turn)) {
            throw new IllegalArgumentException("Cell is not available");
        }

        cellStates[coordinates.y()][coordinates.x()] = turn.toCellState();

        for (Direction direction : Direction.values()) {
            List<Coordinates> coordinatesList = getSurrenderedCellsCoordinates(coordinates, turn, direction);
            for (Coordinates coordinates1 : coordinatesList) {
                cellStates[coordinates1.y()][coordinates1.x()] = turn.toCellState();
            }
        }
    }

    public List<Coordinates> getAvailableCellsCoordinates(Turn turn) {
        List<Coordinates> availableCells = new ArrayList<>();

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

        // iterate through all directions
        for (Direction direction : Direction.values()) {
            List<Coordinates> coordinatesList = getSurrenderedCellsCoordinates(coordinates, turn, direction);
            if (coordinatesList.size() > 0) {
                return true;
            }
        }

        return false;
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

    private List<Coordinates> getSurrenderedCellsCoordinates(Coordinates coordinates, Turn turn, Direction direction) {
        List<Coordinates> surrenderedCells = new ArrayList<>();

        Coordinates currentCoordinates = coordinates;
        while (true) {
            currentCoordinates = currentCoordinates.getCoordinatesInDirection(direction);
            if (!isCoordinatesInField(currentCoordinates)) {
                return new ArrayList<>();
            }

            CellState currentCellState = getCellState(currentCoordinates);
            if (currentCellState == CellState.EMPTY) {
                return new ArrayList<>();
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
                if (cellStates[y][x] == cellState) {
                    count++;
                }
            }
        }

        return count;
    }
}
