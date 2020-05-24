package game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void boardConstructorTest() {
        Board board = new Board();

        assertNotNull(board);
        assertEquals(Color.NONE, board.getBoard().get(0).get(0).getColor());
        assertEquals(Color.PLAYER2, board.getBoard().get(1).get(0).getColor());
        assertEquals(Color.PLAYER1, board.getBoard().get(0).get(1).getColor());
        assertEquals(Color.NONE, board.getBoard().get(1).get(1).getColor());
    }
}
