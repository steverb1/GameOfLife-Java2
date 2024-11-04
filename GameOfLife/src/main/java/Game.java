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
            if (numberOfNeighbors == 2) {
                newState.add(cell);
            }
        }
        return newState;
    }

    private int countNeighbors(Cell cell) {
        int count = 0;
        Cell neighbor = new Cell(cell.x - 1,cell.y + 1);
        if (currentCellState.contains(neighbor)) {
            count++;
        }
        neighbor = new Cell(cell.x + 1,cell.y - 1);
        if (currentCellState.contains(neighbor)) {
            count++;
        }
        return count;
    }
}
