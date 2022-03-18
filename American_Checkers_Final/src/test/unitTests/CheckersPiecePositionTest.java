package test.unitTests;

import junit.framework.TestCase;
import production.business.CheckersPiece;

public class CheckersPiecePositionTest extends TestCase {
    public void testCheckersPieceColor() {
        CheckersPiece piece = new CheckersPiece("RED");
        assertEquals("Checkers piece color should be red", piece.getColor(), "RED");
        piece.setColor("BLACK");
        assertEquals("Checkers piece color should be black", piece.getColor(), "BLACK");
    }

    public void testCheckersKingPiece() {
        CheckersPiece piece = new CheckersPiece("RED");
        assertEquals("Checkers piece color should not be king", piece.isKing(), false);
        piece.setKing();
        assertEquals("Checkers piece color should be king", piece.isKing(), true);
    }
}
