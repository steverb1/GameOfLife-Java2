import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class GameOfLifeTests {
    @Test
    void emptyGrid_RemainsEmpty() {
        HashSet<Cell> seed = new HashSet<>();
        Game game = new Game(seed);
        HashSet<Cell> newState = game.tick();
        assertEquals(0, newState.size());
    }

    @Test
    void oneLivingCell_Dies() {
        HashSet<Cell> seed = new HashSet<>();
        Cell cell=new Cell(0,0);
        seed.add(cell);
        Game game = new Game(seed);
        HashSet<Cell> newState = game.tick();
        assertEquals(0, newState.size());
    }

    @Test
    void cellsWithOnlyOneNeighbor_Die() {
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
    void cellWithTwoNeighbors_Survives() {
        HashSet<Cell> seed = new HashSet<>();
        Cell cell1 = new Cell(0,0);
        Cell cell2 = new Cell(1,1);
        Cell cell3 = new Cell(-1,-1);
        seed.add(cell1);
        seed.add(cell2);
        seed.add(cell3);
        Game game = new Game(seed);
        HashSet<Cell> newState = game.tick();
        assertEquals(1, newState.size());
        assertEquals(cell1, newState.toArray()[0]);
    }

    @Test
    void emptyCellWithThreeNeighbors_ComesAlive() {
        HashSet<Cell> seed = new HashSet<>();
        Cell cell1 = new Cell(0,0);
        Cell cell2 = new Cell(-1,-1);
        Cell cell3 = new Cell(1,-1);
        seed.add(cell1);
        seed.add(cell2);
        seed.add(cell3);
        Game game = new Game(seed);
        HashSet<Cell> newState = game.tick();
        assertEquals(2, newState.size());
        assertTrue(newState.contains(cell1));
        assertTrue(newState.contains(new Cell(0, -1)));
    }

    @Test
    void cellWithThreeNeighbors_Survives() {
        HashSet<Cell> seed = new HashSet<>();
        Cell cell1 = new Cell(0,0);
        Cell cell2 = new Cell(0,1);
        Cell cell3 = new Cell(-1,1);
        Cell cell4 = new Cell(0,-1);
        seed.add(cell1);
        seed.add(cell2);
        seed.add(cell3);
        seed.add(cell4);
        Game game = new Game(seed);
        HashSet<Cell> newState = game.tick();
        assertEquals(4, newState.size());
        assertFalse(newState.contains(cell4));
        assertTrue(newState.contains(new Cell(1, 0)));
    }
    
    @Test
    void cellWithMoreThanThreeNeighbors_Dies() {
        HashSet<Cell> seed = new HashSet<>();
        Cell center = new Cell(0,0);
        Cell north = new Cell(0,1);
        Cell south = new Cell(0,-1);
        Cell east = new Cell(1,0);
        Cell west = new Cell(-1,0);
        
        seed.add(center);
        seed.add(north);
        seed.add(south);
        seed.add(east);
        seed.add(west);
        
        Game game = new Game(seed);
        HashSet<Cell> newState = game.tick();
        
        assertFalse(newState.contains(center));  // Center cell dies (4 neighbors)
    }
}
