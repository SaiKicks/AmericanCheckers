package test.unitTests;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import production.business.AmericanCheckersGame;

public class CheckMoveTest extends TestCase {

    private AmericanCheckersGame game;

    @Before
    public void setUp() throws Exception {
        game = new AmericanCheckersGame();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFirstMoveByBlack() {

        assertEquals("First turn should be played by black", "BLACK", game.getTurn());

        assertTrue("First move should be by black", game.validMove(5,0,4,1));
        assertTrue("First move should be by black", game.validMove(5,2,4,3));
    }

    @Test
    public void testFirstMoveByRed() {

        assertNotSame("First turn should be played by black", "RED", game.getTurn());

        assertFalse("First move should be by black", game.validMove(2,1,3,0));
        assertFalse("First move should be by black", game.validMove(2,3,3,3));
    }
}
