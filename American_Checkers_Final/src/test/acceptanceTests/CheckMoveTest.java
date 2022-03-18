package test.acceptanceTests;

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
    }

    @Test
    public void testFirstMoveByRed() {

        assertNotSame("First turn should be played by black", "RED", game.getTurn());

        assertFalse("First move should be by black", game.validMove(2,1,3,0));
    }

    @Test
    public void testValidMove() {

        assertTrue("Move is Invalid", game.validMove(5,0,4,1));

    }

    @Test
    public void testInvalidMove() {

        assertFalse(" move is invalid", game.validMove(5,0,3,2));
    }

    @Test
    public void testValidMoveByKing() {
        game.makeMove(5,0,0,5);
        assertTrue(game.getCheckersPiece(0,5).isKing()); // king promotion
        game.makeMove(1,4,3,2);

        assertTrue("Move by black king should be valid", game.validMove(0,5,1,4));
    }

    @Test
    public void testInValidMoveByKing() {
        game.makeMove(5,0,0,5);

        game.makeMove(1,4,3,2);

        assertFalse("Move by black king should be valid", game.validMove(0,5,2,3));
    }



}
