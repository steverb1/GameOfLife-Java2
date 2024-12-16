import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GameOfLifeTests {
    @Test
    void emptyGrid_RemainsEmpty() {
        HashSet<Cell> seed = new HashSet<>();
        Game game = new Game(seed);
        Set<Cell> newState = game.tick();
        assertEquals(0, newState.size());
    }

    @Test
    void oneLivingCell_Dies() {
        HashSet<Cell> seed = new HashSet<>();
        Cell cell=new Cell(0,0);
        seed.add(cell);
        Game game = new Game(seed);
        Set<Cell> newState = game.tick();
        assertEquals(0, newState.size());
    }

    @Test
    void cellsWithThreeNeighbors_Live() {
        HashSet<Cell> seed = new HashSet<>();
        seed.add(new Cell(0, 0));
        seed.add(new Cell(0, 1));
        seed.add(new Cell(1, 0));
        seed.add(new Cell(1, 1));
        Game game = new Game(seed);
        Set<Cell> newState = game.tick();
        assertEquals(seed, newState);
    }

    @Test
    void cellsWithTwoNeighbors_Live() {
        HashSet<Cell> seed = new HashSet<>();
        seed.add(new Cell(0, 0));
        seed.add(new Cell(0, 1));
        seed.add(new Cell(1, 0));
        seed.add(new Cell(2, 1));
        Game game = new Game(seed);
        Set<Cell> newState = game.tick();
        assertTrue(newState.contains(new Cell(0, 0)));
        assertTrue(newState.contains(new Cell(0, 1)));
        assertTrue(newState.contains(new Cell(1, 0)));
        assertFalse(newState.contains(new Cell(2, 1)));
    }

    @Test
    void liveCellWithOneNeighbor_Dies() {
        HashSet<Cell> seed = new HashSet<>();
        seed.add(new Cell(0, 0));
        seed.add(new Cell(0, 1));
        Game game = new Game(seed);
        Set<Cell> newState = game.tick();
        assertFalse(newState.contains(new Cell(0, 0)));
        assertFalse(newState.contains(new Cell(0, 1)));
    }

    @Test
    void deadCellWithThreeNeighbors_ComesToLife() {
        HashSet<Cell> seed = new HashSet<>();
        seed.add(new Cell(0, 1));
        seed.add(new Cell(1, 0));
        seed.add(new Cell(1, 1));
        Game game = new Game(seed);
        Set<Cell> newState = game.tick();
        assertTrue(newState.contains(new Cell(0, 0)));
    }

    @Test
    void liveCellWithMoreThanThreeNeighbors_Dies() {
        HashSet<Cell> seed = new HashSet<>();
        seed.add(new Cell(0, 0));
        seed.add(new Cell(0, 1));
        seed.add(new Cell(1, 0));
        seed.add(new Cell(1, 1));
        seed.add(new Cell(0, -1)); // This cell ensures overpopulation for (0, 0)
        Game game = new Game(seed);
        Set<Cell> newState = game.tick();
        assertFalse(newState.contains(new Cell(0, 0))); // Cell (0, 0) should die due to overpopulation
    }
}
