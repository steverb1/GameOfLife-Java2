import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Game {
    private Set<Cell> liveCells;

    public Game(Set<Cell> seed) {
        this.liveCells = seed;
    }

    public Set<Cell> tick() {
        Map<Cell, Integer> neighborCounts = new HashMap<>();

        // Count neighbors for each cell
        for (Cell cell : liveCells) {
            for (Cell neighbor : getNeighbors(cell)) {
                neighborCounts.put(neighbor, neighborCounts.getOrDefault(neighbor, 0) + 1);
            }
        }

        Set<Cell> newLiveCells = new HashSet<>();

        // Determine the next state of each cell
        for (Map.Entry<Cell, Integer> entry : neighborCounts.entrySet()) {
            Cell cell = entry.getKey();
            int count = entry.getValue();
            if (liveCells.contains(cell)) {
                // Survival rule
                if (count == 2 || count == 3) {
                    newLiveCells.add(cell);
                }
            } else {
                // Birth rule
                if (count == 3) {
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
}
