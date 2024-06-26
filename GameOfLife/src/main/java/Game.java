import java.util.HashSet;
import java.util.Set;

public class Game {
    Set<Cell> grid;
    public Game(Set<Cell> seed) {
        grid = seed;
    }

    public void tick() {
        Set<Cell> nextGrid = new HashSet<>();
        for (Cell cell : grid) {
            if (numberOfNeighbors(cell) == 2) {
                nextGrid.add(cell);
            }
        }

        grid = nextGrid;
    }

    private int numberOfNeighbors(Cell cell) {
        int neighborCount = 0;
        Cell delta1 = new Cell(cell.x() - 1, cell.y() - 1);
        Cell delta2 = new Cell(cell.x(), cell.y() - 1);
        Cell delta3 = new Cell(cell.x() + 1, cell.y() - 1);
        Cell delta4 = new Cell(cell.x() - 1, cell.y());
        Cell delta5 = new Cell(cell.x() + 1, cell.y());
        Cell delta6 = new Cell(cell.x() - 1, cell.y() + 1);
        Cell delta7 = new Cell(cell.x(), cell.y() + 1);
        Cell delta8 = new Cell(cell.x() + 1, cell.y() + 1);

        if (grid.contains(delta1)) {
            neighborCount++;
        }

        if (grid.contains(delta2)) {
            neighborCount++;
        }

        if (grid.contains(delta3)) {
            neighborCount++;
        }

        if (grid.contains(delta4)) {
            neighborCount++;
        }

        if (grid.contains(delta5)) {
            neighborCount++;
        }

        if (grid.contains(delta6)) {
            neighborCount++;
        }

        if (grid.contains(delta7)) {
            neighborCount++;
        }

        if (grid.contains(delta8)) {
            neighborCount++;
        }

        return neighborCount;
    }

    public Set<Cell> liveCells() {
        return grid;
    }
}
