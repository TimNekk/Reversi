package TimNekk.model;

public record Coordinates(int x, int y) {
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public Coordinates getCoordinatesInDirection(Direction direction) {
        return new Coordinates(x + direction.getDx(), y + direction.getDy());
    }
}
