import java.util.HashSet;

public class Game {
    private final HashSet<Cell> cells;

    public Game(HashSet<Cell> seed) {
        this.cells = seed;
    }

    public HashSet<Cell> tick() {
        HashSet<Cell> survivors = new HashSet<>();
        HashSet<Cell> potentialBirths = new HashSet<>();

        for (Cell cell : cells) {
            int neighbors = countNeighbors(cell);
            if (neighbors == 2 || neighbors == 3) {
                survivors.add(cell);
            }
            
            // Collect empty neighbor positions
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) continue;
                    Cell empty = new Cell(cell.x + dx, cell.y + dy);
                    if (!cells.contains(empty)) {
                        potentialBirths.add(empty);
                    }
                }
            }
        }

        // Check which empty cells should come alive
        for (Cell empty : potentialBirths) {
            if (countNeighbors(empty) == 3) {
                survivors.add(empty);
            }
        }

        return survivors;
    }

    private int countNeighbors(Cell cell) {
        int count = 0;
        for (Cell other : cells) {
            if (other != cell && cell.isNeighbor(other)) {
                count++;
            }
        }
        return count;
    }
}
