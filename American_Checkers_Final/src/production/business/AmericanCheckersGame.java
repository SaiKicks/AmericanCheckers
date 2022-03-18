package production.business;

public class AmericanCheckersGame {
    public int redKillCount=0, blackKillCount=0;
    public boolean makeMove(int rowSelected1, int colSelected1, int rowSelected2, int colSelected2) {
        if((rowSelected2 == 7 && grid[rowSelected1][colSelected1].getColor()=="RED")
        		|| (rowSelected2 == 0 && grid[rowSelected1][colSelected1].getColor()=="BLACK")) {
        	grid[rowSelected1][colSelected1].setKing();
        }
    	grid[rowSelected2][colSelected2] = grid[rowSelected1][colSelected1];
        grid[rowSelected1][colSelected1] = null;
        
        if(Math.abs(rowSelected1-rowSelected2)==2 && Math.abs(colSelected1-colSelected2)==2){
            grid[(rowSelected1+rowSelected2)/2][(colSelected1+colSelected2)/2] = null;
            if(getTurn()=="RED"){
                blackKillCount++;
            }else{
                redKillCount++;
            }
            int[] x = new int[]{-2, 2}, y = new int[]{-2,2};
            for(int dx:x) {
                if(!grid[rowSelected2][colSelected2].isKing()) {
                    if (dx == -2 && getTurn() == "RED") {
                        continue;
                    }
                    if (dx == 2 && getTurn() == "BLACK") {
                        continue;
                    }
                }
                    for (int dy : y) {
                        if (validMove(rowSelected2, colSelected2, rowSelected2 + dx, colSelected2 + dy)) {
                            return true;
                        }
                    }
            }
        }
        return false;
    }

    private static final int TOTALROWS = 8;
    private static final int TOTALCOLUMNS = 8;

    private CheckersPiece[][] grid;
    private String turn;

    public void changeTurn() {
        if(getTurn()=="RED"){
            turn = "BLACK";
        }else{
            turn="RED";
        }
    }

    public boolean validMove(int rowSelected1, int colSelected1, int rowSelected2, int colSelected2) {
        if(rowSelected2<0 || rowSelected2>7 || colSelected2<0 || colSelected2>7){
            return false;
        }
        int[] moveDirection = new int[] {-1};
        
        if(grid[rowSelected1][colSelected1].isKing()) {
        	moveDirection = new int[] {-1,1};
        }else if(getTurn()=="RED"){
            moveDirection = new int[] {1};
        }
        //empty cell
        if(grid[rowSelected2][colSelected2]!=null){
            return false;
        }
        for(int dir:moveDirection) {
            //no hop
            if(rowSelected1+dir==rowSelected2 && Math.abs(colSelected1-colSelected2)==1){
                return true;
            }
            //hop
            if(rowSelected1+dir*2==rowSelected2 && Math.abs(colSelected1-colSelected2)==2 && grid[(rowSelected1+rowSelected2)/2][(colSelected1+colSelected2)/2]!=null && grid[(rowSelected1+rowSelected2)/2][(colSelected1+colSelected2)/2].getColor()!=getTurn()){
                return true;
            }
        }
        
        return false;
    }

    public enum GameState {
        TOSS, PLAYING, DRAW, BLACK_WON, RED_WON
    }

    private GameState currentGameState;

    public AmericanCheckersGame() {
        grid = new CheckersPiece[TOTALROWS][TOTALCOLUMNS];
        initGame();
    }

    private boolean bot = false;
    
    private void initGame() {
        grid = new CheckersPiece[TOTALROWS][TOTALCOLUMNS];
        for (int row = 0; row < TOTALROWS; ++row) {
            for (int col = 0; col < TOTALCOLUMNS; ++col) {

                if((row+col)%2==1 && row<3) {
                    grid[row][col] = new CheckersPiece("RED");
                }
                if((row+col)%2==1 && row>4) {
                    grid[row][col] = new CheckersPiece("BLACK");
                }
            }
        }
        currentGameState = GameState.TOSS;
        redKillCount = 0;
        blackKillCount = 0;
        turn = "BLACK";
    }

    public void resetGame() {
        initGame();
    }

    public int getTotalRows() {
        return TOTALROWS;
    }

    public int getTotalColumns() {
        return TOTALCOLUMNS;
    }

    public CheckersPiece getCheckersPiece(int row, int column) {
        if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS) {
            return grid[row][column];
        } else {
            return null;
        }
    }

    public int[] findValidBotMove(){
        int[] validBotMove = new int[4];
        for(int row=0; row<8; row++){
            for(int col=0; col<8; col++){
                CheckersPiece botPiece = getCheckersPiece(row,col);
                if(!(botPiece!=null && botPiece.getColor()=="RED")){
                    continue;
                }
                int[] dx = new int[]{1,1,2,2}, dy = new int[]{1,-1,2,-2};
                if(botPiece.isKing()){
                    dx = new int[]{1,1,2,2,-1,-1,-2,-2};
                    dy = new int[]{1,-1,2,-2,1,-1,2,-2};
                }
                for(int i=0; i<dx.length; i++){
                    if(validMove(row,col,row+dx[i],col+dy[i])){
                        validBotMove[0]=row;
                        validBotMove[1]=col;
                        validBotMove[2]=row+dx[i];
                        validBotMove[3]=col+dy[i];
                        return validBotMove;
                    }
                }
            }
        }
        return validBotMove;
    }

    public String getTurn() {
        return turn;
    }

    public GameState getGameState() {
        return currentGameState;
    }

    public void setGameState(GameState value) {
        currentGameState = value;
    }
    
    public boolean isBot(){ return bot;}

    public void setBot(boolean bot) {
    	this.bot=bot;
    }
    
    public void playBotMove(){

    }
}
