import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOfLifeTests {
    @Test
    void emptyGridRemainsEmpty() {
        HashSet<Cell> seed = new HashSet<>();
        Game game = new Game(seed);
        HashSet<Cell> newState = game.tick();
        assertEquals(0, newState.size());
    }

    @Test
    void oneLivingCellDies() {
        HashSet<Cell> seed = new HashSet<>();
        Cell cell=new Cell(0,0);
        seed.add(cell);
        Game game = new Game(seed);
        HashSet<Cell> newState = game.tick();
        assertEquals(0, newState.size());
    }

    //@Test
    void threeDiagonalCells_MiddleCellRemains() {
        HashSet<Cell> seed = new HashSet<>();
        Cell cell1 = new Cell(0,0);
        Cell cell2 = new Cell(-1,1);
        Cell cell3 = new Cell(1,-1);
        seed.add(cell1);
        seed.add(cell2);
        seed.add(cell3);
        Game game = new Game(seed);
        HashSet<Cell> newState = game.tick();
        assertEquals(1, newState.size());
    }
}
