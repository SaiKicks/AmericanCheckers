package test.unitTests;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import production.business.AmericanCheckersGame;
import production.business.CheckersPiece;

public class BotGameTest extends TestCase {
    private AmericanCheckersGame game;

    @Before
    public void setUp() throws Exception {
        game = new AmericanCheckersGame();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMoveByBot() {
        game.setBot(true);

        game.makeMove(1,5,2,4);
        assertEquals("Bot move is not made", "RED", game.getCheckersPiece(2,1).getColor());
        assertEquals("First turn should be played by black", "BLACK", game.getTurn());

    }

    @Test
    public void testHopMoveByBot() {
        game.setBot(true);

        game.makeMove(1,5,2,4);
        assertEquals("Bot move is not made", "RED", game.getCheckersPiece(2,1).getColor());
        assertEquals("Killed piece should be removed from board", null, game.getCheckersPiece(6,2));

    }

    @Test
    public void testTurnAfterBotMove() {
        game.setBot(true);

        game.makeMove(1,5,2,4);
        assertEquals("First turn should be played by black", "BLACK", game.getTurn());
    }

}
