package test.acceptanceTests;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import production.business.AmericanCheckersGame;
import production.business.CheckersPiece;
import production.gui.AmericanCheckersGUI;


public class CheckersBoardTest extends TestCase {

    private AmericanCheckersGame game;

    @Before
    public void setUp() throws Exception {
        game = new AmericanCheckersGame();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCheckersBoard() {
        boolean boardUi = true;
        try {
            new AmericanCheckersGUI(game);
        } catch (Exception e) {
            boardUi = false;
            assertFalse("Error occured while loading board!", boardUi);
            e.printStackTrace();
        }
        assertTrue("Board loaded successfully!", boardUi);
    }

    @Test
    public void testCheckersBoardPiecePlacement() {
        game.resetGame();
        CheckersPiece piece;
        for(int row=0; row<8; row++) {
            for(int col=0; col<8; col++) {
                piece = game.getCheckersPiece(row, col);
                if(row<3 && (row+col)%2==1) {
                    assertNotNull(piece);
                    assertEquals(String.format("Red piece should exist at position row: %s column: %s",row,col), "RED", piece.getColor());
                    assertEquals(String.format("King piece should not exist at position row: %s column: %s",row,col), false, piece.isKing());
                }else if(row>4 && (row+col)%2==1) {
                    assertNotNull(piece);
                    assertEquals(String.format("Black piece should exist at position row: %s column: %s",row,col), "BLACK", piece.getColor());
                    assertEquals(String.format("King piece should not exist at position row: %s column: %s",row,col), false, piece.isKing());
                }else{
                    assertNull(piece);
                }
            }
        }
    }
}
