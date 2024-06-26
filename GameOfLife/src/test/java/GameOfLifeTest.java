import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOfLifeTest {

    @Test
    void emptyGridRemainsEmpty() {
        Set<Cell> seed = new HashSet<>();
        Game game = new Game(seed);
        game.tick();
        assertEquals(0, game.liveCells().size());
    }

    @Test
    void loneCellDies() {
        Set<Cell> seed = new HashSet<>();
        Cell cell = new Cell(0,0);
        seed.add(cell);
        Game game = new Game(seed);
        game.tick();
        assertEquals(0, game.liveCells().size());
    }

    @Test
    void cellWithTwoNeighborsLives() {
        Set<Cell> seed = new HashSet<>();
        Cell cell1 = new Cell(0,0);
        Cell cell2 = new Cell(1,0);
        Cell cell3 = new Cell(0,1);
        seed.add(cell1);
        seed.add(cell2);
        seed.add(cell3);
        Game game = new Game(seed);
        game.tick();
        assertEquals(3, game.liveCells().size());
    }
}
