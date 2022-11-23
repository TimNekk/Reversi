package TimNekk.model;

public class Field {
    private final int width;
    private final int height;
    private final Cell[][] cells;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x] = new Cell();
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell getCell(int x, int y) throws IndexOutOfBoundsException {
        return cells[y][x];
    }
}
