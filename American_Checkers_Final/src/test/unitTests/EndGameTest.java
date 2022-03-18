package test.unitTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import production.business.AmericanCheckersGame;



public class EndGameTest {

    private AmericanCheckersGame game;

    @Before
    public void setUp() throws Exception {
        game = new AmericanCheckersGame();
    }

    @After
    public void tearDown() throws Exception {
    }




    @Test
    public void RedwinPosition() {
        int[] dx = new int[]{7,7,7,7,6,6,6,6,5,5,5,5}, dy = new int[]{0,2,4,6,1,3,5,7,0,2,4,6};
        game.changeTurn();
        for(int i=0;i<12;i++){
            game.makeMove(dx[i],dy[i],2,1);
            if(i%2==0){
                game.makeMove(1,0,3,2);
            }else{
                game.makeMove(3,2,1,0);
            }
        }

        Assert.assertEquals(12,game.blackKillCount);
    }

    @Test
    public void BlackwinPosition() {
        int[] dx = new int[]{0,0,0,0,1,1,1,1,2,2,2,2}, dy = new int[]{1,3,5,7,0,2,4,6,1,3,5,7};
        for(int i=0;i<12;i++){
            game.makeMove(dx[i],dy[i],5,1);
            if(i%2==0){
                game.makeMove(6,1,4,3);
            }else{
                game.makeMove(4,3,6,1);
            }
        }

        Assert.assertEquals(12,game.redKillCount);
    }

    @Test
    public void drawGameTest() {
        int moveCount = 0;
        for(int i=0;i<40;i++){
            if(i%2==0){
                game.makeMove(5,0,4,1);
                game.makeMove(2,1,3,2);
            }else{
                game.makeMove(4,1,5,0);
                game.makeMove(3,2,2,1);
            }
            moveCount++;
        }

        Assert.assertEquals(40,moveCount);
    }

}
