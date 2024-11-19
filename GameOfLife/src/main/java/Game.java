import java.util.HashSet;
import java.util.Set;

public class Game {
    private final HashSet<Cell> currentState;

    public Game(HashSet<Cell> seed) {
        this.currentState = seed;
    }

    public Set<Cell> tick() {
        Set<Cell> newState = new HashSet<>();
        Set<Cell> checkedCells = new HashSet<>();

        for (Cell cell : currentState) {
            int neighborCount = countNeighbors(cell);

            // Check survival rules
            if(neighborCount == 2 || neighborCount == 3) {
                newState.add(cell);
            }

            checkedCells.add(cell);

            // Check birth rule for the eight neighbors of the current cell
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) {
                        continue;
                    }
                    Cell neighborCell = new Cell(cell.x + dx, cell.y + dy);

                    if (!checkedCells.contains(neighborCell) && countNeighbors(neighborCell) == 3) {
                        newState.add(neighborCell);
                    }

                    checkedCells.add(neighborCell);
                }
            }
        }

        return newState;
    }

    private int countNeighbors(Cell cell) {
        int liveNeighbors = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) { // Skip the center cell
                    Cell neighborCell = new Cell(cell.x + dx, cell.y + dy);
                    if (this.currentState.contains(neighborCell)) {
                        liveNeighbors++;
                    }
                }
            }
        }
        return liveNeighbors;
    }
}
