import java.util.HashSet;

public class Game {
    private final HashSet<Cell> cells;

    public Game(HashSet<Cell> seed) {
        this.cells = seed;
    }

    public HashSet<Cell> tick() {
        HashSet<Cell> nextState = findSurvivingCells();
        HashSet<Cell> emptyNeighbors = findEmptyNeighbors();
        
        for (Cell neighbor : emptyNeighbors) {
            if (countNeighbors(neighbor) == 3) {
                nextState.add(neighbor);
            }
        }
        return nextState;
    }

    private HashSet<Cell> findSurvivingCells() {
        HashSet<Cell> survivors = new HashSet<>();
        for (Cell cell : cells) {
            int neighbors = countNeighbors(cell);
            if (neighbors == 2 || neighbors == 3) {
                survivors.add(cell);
            }
        }
        return survivors;
    }

    private HashSet<Cell> findEmptyNeighbors() {
        HashSet<Cell> emptyNeighbors = new HashSet<>();
        for (Cell cell : cells) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) continue;
                    Cell empty = new Cell(cell.x + dx, cell.y + dy);
                    if (!cells.contains(empty)) {
                        emptyNeighbors.add(empty);
                    }
                }
            }
        }
        return emptyNeighbors;
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
