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
        HashSet<Cell> neighbours=new HashSet<>();

        neighbours.add(new Cell(cell.x - 1,cell.y + 1));
        neighbours.add(new Cell(cell.x + 1,cell.y - 1));
        for (Cell neighbor: neighbours) {
            if (currentCellState.contains(neighbor)) {
                count++;
            }
        }
        return count;

    }
}
