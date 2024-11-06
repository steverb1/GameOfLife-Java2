import java.util.HashSet;

public class Game {
    HashSet<Cell> currentCellState;
    public Game(HashSet<Cell> seed) {
        currentCellState=seed;
    }

    public HashSet<Cell> tick() {
        HashSet<Cell> newState=new HashSet<>();
        for (Cell cell : currentCellState) {
            int numberOfNeighbors = countNeighbors(cell);

            if (numberOfNeighbors == 4) {
                continue;
            }

            if (numberOfNeighbors == 2 || numberOfNeighbors == 3) {
                newState.add(cell);
            }

            HashSet<Cell> neighbors = findNeighbors(cell);
            for (Cell neighborCell : neighbors) {
                if (!currentCellState.contains(neighborCell)) {
                    if (countNeighbors(neighborCell) == 3) {
                        newState.add(neighborCell);
                    }
                }
            }
        }
        
        return newState;
    }

    private HashSet<Cell> findNeighbors(Cell cell) {
        HashSet<Cell> neighbors = new HashSet<>();

        neighbors.add(new Cell(cell.x - 1,cell.y + 1));
        neighbors.add(new Cell(cell.x,cell.y + 1));
        neighbors.add(new Cell(cell.x + 1,cell.y + 1));
        neighbors.add(new Cell(cell.x + 1,cell.y));
        neighbors.add(new Cell(cell.x + 1,cell.y - 1));
        neighbors.add(new Cell(cell.x,cell.y - 1));
        neighbors.add(new Cell(cell.x - 1,cell.y - 1));
        neighbors.add(new Cell(cell.x - 1,cell.y));

        return neighbors;
    }

    private int countNeighbors(Cell cell) {
        int count = 0;
        HashSet<Cell> neighbours = findNeighbors(cell);
        for (Cell neighbor: neighbours) {
            if (currentCellState.contains(neighbor)) {
                count++;
            }
        }

        return count;
    }
}
