package test.acceptanceTests;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import production.business.AmericanCheckersGame;


public class HopTest extends TestCase {

    private AmericanCheckersGame game;

    @Before
    public void setUp() throws Exception {
        game = new AmericanCheckersGame();
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void testValidHop() {
        game.makeMove(2,3,4,1);
        assertTrue("Hop is Invalid", game.validMove(5,0,3,2));
    }

    @Test
    public void testInvalidHop() {
        game.makeMove(5,2,4,1);
        assertFalse("Hop is Invalid", game.validMove(5,0,3,2));
    }

    @Test
    public void testHopValidHop() {
        game.makeMove(2,3,4,1);
        assertTrue("Hop is Invalid", game.validMove(5,0,3,2));
    }


}
