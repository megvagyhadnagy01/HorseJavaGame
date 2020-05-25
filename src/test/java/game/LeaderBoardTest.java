package game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;


public class LeaderBoardTest {

    @BeforeAll
    private static void initialize() {

        LeaderBoard.addName("TestName2");
        for (int i = 0; i < 3; i++) {
            LeaderBoard.addName("TestName");
        }
    }

    private Map.Entry getLastEntry(Map<String, Integer> ranks) {
        Map.Entry lastEntry = null;
        for (Map.Entry<String, Integer> e : ranks.entrySet()) {
            lastEntry = e;
        }
        return lastEntry;
    }

    @Test
    public void leaderBoardSortingRanksTest() {

        Map<String, Integer> ranks = LeaderBoard.getRanks();
        Map.Entry lastEntry = getLastEntry(ranks);

        assertNotNull(lastEntry);
        assertEquals("TestName2", lastEntry.getKey());
        assertEquals(1, lastEntry.getValue());
    }

    @Test
    public void leaderBoardAddSameNameTest() {

        assertNotNull(LeaderBoard.getRanks());
        assertEquals(3, LeaderBoard.getRanks().get("TestName"));

        var firstEntry = LeaderBoard.getRanks().entrySet().iterator().next();
        assertEquals("TestName", firstEntry.getKey());
        assertEquals(3, firstEntry.getValue());
    }
}
