import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
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
        assertEquals(cell1, newState.toArray()[0]);
    }

    @Test
    void cellWithThreeNeighbors_Survives() {
        HashSet<Cell> seed = new HashSet<>();
        Cell cell1 = new Cell(0,0);
        Cell cell2 = new Cell(0,1);
        Cell cell3 = new Cell(1,1);
        Cell cell4 = new Cell(1,0);
        seed.add(cell1);
        seed.add(cell2);
        seed.add(cell3);
        seed.add(cell4);

        Game game = new Game(seed);
        HashSet<Cell> newState = game.tick();
        assertEquals(4, newState.size());
        assertTrue(newState.contains(cell1));
        assertTrue(newState.contains(cell2));
        assertTrue(newState.contains(cell3));
        assertTrue(newState.contains(cell4));
    }

    @Test
    void cellWithOneNeighbor_Dies() {
        HashSet<Cell> seed = new HashSet<>();
        Cell cell1 = new Cell(0,0);
        Cell cell2 = new Cell(1,0);
        seed.add(cell1);
        seed.add(cell2);

        Game game = new Game(seed);
        HashSet<Cell> newState = game.tick();
        assertEquals(0, newState.size());
    }

    // Next test: cell with 4 or more neighbors dies.
    @Test
    void cellWithFourNeighbor_Dies(){
        HashSet<Cell> seed = new HashSet<>();
        Cell cell1 = new Cell(0,0);
        Cell cell2 = new Cell(0,1);
        Cell cell3 = new Cell(-1,0);
        Cell cell4 = new Cell(-1,1);
        Cell cell5 = new Cell(1,0);
        seed.add(cell1);
        seed.add(cell2);
        seed.add(cell3);
        seed.add(cell4);
        seed.add(cell5);

        Game game = new Game(seed);
        HashSet<Cell> newState = game.tick();
        assertEquals(5, newState.size());
        assertFalse(newState.contains(cell1));
    }
}
