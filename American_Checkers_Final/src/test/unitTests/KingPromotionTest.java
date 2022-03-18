package test.unitTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import production.business.AmericanCheckersGame;


public class KingPromotionTest {

    private AmericanCheckersGame game;

    @Before
    public void setUp() throws Exception {
        game = new AmericanCheckersGame();
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void BlacktestSuccessfulKingPromotion() {
        game.makeMove(5,0,0,5);
        Assert.assertTrue("King Promotion is Unsuccessful", game.getCheckersPiece(0, 5).isKing()); // king promotion
    }

    @Test
    public void RedtestSuccessfulKingPromotion() {
        game.makeMove(0,1,7,2);
        Assert.assertTrue("King Promotion is Unsuccessful", game.getCheckersPiece(7, 2).isKing()); // king promotion
    }

    @Test
    public void RedtestUnSuccessfulKingPromotion() {
        game.makeMove(0,1,4,1);
        Assert.assertFalse("King Promotion Unsuccessful", game.getCheckersPiece(4, 1).isKing()); // king promotion
    }

    @Test
    public void BlacktestUnSuccessfulKingPromotion() {
        game.makeMove(5,0,1,5);
        Assert.assertFalse("King Promotion Unsuccessful", game.getCheckersPiece(1, 5).isKing()); // king promotion
    }



}
