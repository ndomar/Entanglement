import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import org.junit.Test;

import eg.edu.guc.entanglement.engine.Board;
import eg.edu.guc.entanglement.engine.BoardInterface;

public class ScoreTest
{
	// Private Test
    @Test(timeout = 1000)
    public void testZeroSizedBoard()
    {
        BoardInterface singliton = new Board(new StringReader(
                TestUtil.TEST_SINGLITON_BOARD));
        assertEquals("Score should be initially 0", 0, singliton.getScore(0));

        TestUtil.playSequence(singliton, "f");
        assertEquals(
                "score remains 0 for singliton board as game is initially in a game over state",
                0, singliton.getScore(0));
    }

    @Test(timeout = 1000)
    public void testPathWithoutLoopBackToOlderTiles()
    {
        BoardInterface b = new Board(new StringReader(
                TestUtil.TEST_BOARD_CONFIG));
        assertEquals("Score should be initially 0", 0, b.getScore(0));

        TestUtil.playSequence(b, "fffffff");
        assertEquals(
                "each fix of a tile should increment score by 1, totalling 7.",
                7, b.getScore(0));
    }

    @Test(timeout = 1000)
    public void testPathWithLoopBackToOlderTiles()
    {
        BoardInterface test2PerSide = new Board(new StringReader(
                TestUtil.TEST_SCORE_2PERSIDE));
        assertEquals("Score should be initially 0", 0, test2PerSide.getScore(0));

        // path goes through 1 tile in 1 steps
        TestUtil.playSequence(test2PerSide, "f");
        assertEquals("fixing tile increments score by 1, totalling 1.", 1,
                test2PerSide.getScore(0));

        // path goes through 2 tiles in 1 steps
        TestUtil.playSequence(test2PerSide, "f");
        assertEquals("fixing tile increments score by 2, totalling 3.", 3,
                test2PerSide.getScore(0));
    }

	// Private Test
    @Test(timeout = 1000)
    public void testLongerPathWithLoopBackToOlderTiles()
    {
        BoardInterface toughTest = new Board(new StringReader(
                TestUtil.TEST_SCORE_TOUGH));
        assertEquals("Score should be initially 0", 0, toughTest.getScore(0));

        // path goes through 2 tiles in 2 steps
        TestUtil.playSequence(toughTest, "0f1f");
        assertEquals("two tile fixes increment score by 2, totalling 2. "
                + "This step includes switching tiles.", 2, toughTest
                .getScore(0));

        // path goes through 2 tiles in 1 steps
        TestUtil.playSequence(toughTest, "2f");
        assertEquals("next tile fix increments score by 2, totalling 4. "
                + "This step includes switching tiles.", 4, toughTest
                .getScore(0));

        // path goes through 5 tiles in 1 step
        TestUtil.playSequence(toughTest, "2f");
        assertEquals("next tile fix increments score by 5, totalling 9.", 9,
                toughTest.getScore(0));

        // path goes through 3 tiles in 1 step
        TestUtil.playSequence(toughTest, "2f");
        assertEquals("next tile fix increments score by 3, totalling 12.", 12,
                toughTest.getScore(0));

        // game over, further actions should not affect the score
        TestUtil.playSequence(toughTest, "2f");
        assertEquals("next tile fix should not affect score as game is over.",
                12, toughTest.getScore(0));
    }
}
