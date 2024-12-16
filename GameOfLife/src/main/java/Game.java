import java.util.HashSet;
import java.util.Set;

public class Game {
    private Set<Cell> liveCells;

    public Game(Set<Cell> seed) {
        this.liveCells = seed;
    }

    public Set<Cell> tick() {
        Set<Cell> newLiveCells = new HashSet<>();
        Set<Cell> potentialCells = new HashSet<>(liveCells);

        // Add all neighbors of live cells to potential cells
        for (Cell cell : liveCells) {
            potentialCells.addAll(getNeighbors(cell));
        }

        for (Cell cell : potentialCells) {
            int neighbors = countNeighbors(cell);
            if (liveCells.contains(cell)) {
                // Survival rule
                if (neighbors == 2 || neighbors == 3) {
                    newLiveCells.add(cell);
                }
            } else {
                // Birth rule
                if (neighbors == 3) {
                    newLiveCells.add(cell);
                }
            }
        }
        return newLiveCells;
    }

    private Set<Cell> getNeighbors(Cell cell) {
        Set<Cell> neighbors = new HashSet<>();
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},         {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };
        for (int[] direction : directions) {
            neighbors.add(new Cell(cell.x + direction[0], cell.y + direction[1]));
        }
        return neighbors;
    }

    private int countNeighbors(Cell cell) {
        int count = 0;
        for (Cell neighbor : getNeighbors(cell)) {
            if (liveCells.contains(neighbor)) {
                count++;
            }
        }
        return count;
    }
}
