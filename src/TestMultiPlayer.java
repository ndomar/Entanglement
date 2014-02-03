import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.StringReader;

import org.junit.Test;

import eg.edu.guc.entanglement.engine.Board;
import eg.edu.guc.entanglement.engine.BoardInterface;

public class TestMultiPlayer
{
    @Test(timeout = 1000)
    public void testBounds()
    {
        BoardInterface testPlayerAccess = new Board(new StringReader("4\n"
                + "1\n" + "1\n" + "2 3 0 1\n" + "3\n" + "3\n" + "4\n" + "4"));
        try
        {
            assertFalse(testPlayerAccess.isGameOver(-1));
            assertFalse(testPlayerAccess.isGameOver(6));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("Accessing out of range players should not throw exceptions");
        }
    }

    @Test(timeout = 1000)
    public void testPlayer1GameOversBeforePlayer2()
    {
        BoardInterface testMultiplayerGameOver = new Board(new StringReader(
                "4\n" + "2\n" + "1\n" + "5 6 3 2 7 0 1 4\n" + "3\n" + "3\n"
                        + "4\n" + "2"));

        assertEquals("Score should be initially 0 for both players", 0,
                testMultiplayerGameOver.getScore(0));
        assertEquals("Score should be initially 0 for both players", 0,
                testMultiplayerGameOver.getScore(1));

        // player 1 fixed 1 tile incrementing path by 1 step
        TestUtil.playSequence(testMultiplayerGameOver, "f");

        assertTrue("game should be over for player 1 and not for player 2",
                testMultiplayerGameOver.isGameOver(0)
                        && !testMultiplayerGameOver.isGameOver(1));
        assertTrue(
                "Score Should be 1 for player 1, and 0 for player 2 as player 2 has not played yet",
                testMultiplayerGameOver.getScore(0) == 1
                        && testMultiplayerGameOver.getScore(1) == 0);

        // player 2 fixes tiles 3 times. the first 2 increment the path by 1
        // step each
        // the last tile fixed increments path by 2 steps.
        // This results in a total of a 4 step increment of the path length
        TestUtil.playSequence(testMultiplayerGameOver, "fff");
        assertTrue("game should be over for player 1 and player 2",
                testMultiplayerGameOver.isGameOver(0)
                        && testMultiplayerGameOver.isGameOver(1));
        assertTrue("Score Should be 1 for player 1, and 4 for player 2 "
                + "after the game is over for both of them",
                testMultiplayerGameOver.getScore(0) == 1
                        && testMultiplayerGameOver.getScore(1) == 4);
    }

	// Private Test
    @Test(timeout = 1000)
    public void testClashingPathFor2Players()
    {
        BoardInterface testPathClashGameOver = new Board(new StringReader("4\n"
                + "2\n" + "1\n" + "2 7 0 5 6 3 4 1\n" + "3\n" + "3\n" + "4\n"
                + "2"));
        // both players fix their first tiles respectively
        TestUtil.playSequence(testPathClashGameOver, "ff");
        assertTrue(
                "game should not be over for players after the sequence of moves",
                !testPathClashGameOver.isGameOver(0)
                        && !testPathClashGameOver.isGameOver(1));

        // player 1 fixes next tile causing a clash in the path
        TestUtil.playSequence(testPathClashGameOver, "f");
        assertTrue(
                "game should be over for players after the last move because of clashing paths",
                testPathClashGameOver.isGameOver(0)
                        && testPathClashGameOver.isGameOver(1));
        assertTrue(
                "scores of players should be 2 and 1 respectively after path clash",
                testPathClashGameOver.getScore(0) == 2
                        && testPathClashGameOver.getScore(1) == 1);
    }

    @Test(timeout = 1000)
    public void testForcingPathOnAnotherPlayer()
    {
        BoardInterface testForcingOtherPlayer = new Board(new StringReader(
                "4\n" + "3\n" + "1\n" + "11 3 7 1 10 8 9 2 5 6 4 0\n" + "3\n"
                        + "3\n" + "4\n" + "2"));

        // the 2 players fix their tiles respectively
        TestUtil.playSequence(testForcingOtherPlayer, "ff");
        assertTrue(
                "game should not be over for players after the sequence of moves.",
                !testForcingOtherPlayer.isGameOver(0)
                        && !testForcingOtherPlayer.isGameOver(1));

        TestUtil.playSequence(testForcingOtherPlayer, "f");
        assertTrue("game should be over for player 2 after the last move "
                + "because of player 1 forcing a path,"
                + "but should not be over for player 1.",
                !testForcingOtherPlayer.isGameOver(0)
                        && testForcingOtherPlayer.isGameOver(1));
        assertTrue("scores of players should be 3 and 2 respectively, "
                + "after player 1 forces player 2 to a dead end.",
                testForcingOtherPlayer.getScore(0) == 3
                        && testForcingOtherPlayer.getScore(1) == 2);
    }

	// Private Test
    @Test(timeout = 1000)
    public void testFourPlayers()
    {
        BoardInterface testFourPlayers = new Board(new StringReader("4\n"
                + "2\n" + "1\n" + "3 6 4 0 2 7 1 5\n" + "3\n" + "3\n" + "4\n"
                + "4"));
        TestUtil.playSequence(testFourPlayers, "ffff");
        boolean allPlayersNotGameOver = true;
        boolean eachPlayerScoreEqualOne = true;
        for (int i = 0; i < 4; i++)
        {
            eachPlayerScoreEqualOne &= (testFourPlayers.getScore(i) == 1);
            allPlayersNotGameOver &= !testFourPlayers.isGameOver(i);
        }
        assertTrue(
                "After each of the players fixed a tile, non of them are in a game over state",
                allPlayersNotGameOver);
        assertTrue(
                "After each of the players fixed a tile, each of them has a score of 1",
                eachPlayerScoreEqualOne);

        // player 1 fixes the next tile, leading to a game over for both players
        // 1 and 4
        // this is because player 1 is forced into the tile placed by player 4,
        // and player 1 forces the path of player 4 through the placed tile
        TestUtil.playSequence(testFourPlayers, "f");
        assertTrue(
                "fixing next tile for player 1 ends the game for only player 1 and player 4.",
                testFourPlayers.isGameOver(0) && testFourPlayers.isGameOver(3)
                        && !testFourPlayers.isGameOver(1)
                        && !testFourPlayers.isGameOver(2));

        // players 2 and 3 placing their tiles
        TestUtil.playSequence(testFourPlayers, "ff");
        boolean allPlayersGameOver = true;
        for (int i = 0; i < 4; i++)
        {
            allPlayersGameOver &= testFourPlayers.isGameOver(i);
        }
        assertTrue(
                "All players have reached a dead end after players 2 and 3 have played.",
                allPlayersGameOver);

        // checking all scores at the end
        assertTrue(
                "Scores for players 1 to 4 are 3, 2, 3, and 2 respectively.",
                testFourPlayers.getScore(0) == 3
                        && testFourPlayers.getScore(1) == 2
                        && testFourPlayers.getScore(2) == 3
                        && testFourPlayers.getScore(3) == 2);
    }
}
