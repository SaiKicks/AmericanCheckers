package production.gui;

import production.business.AmericanCheckersGame;
import production.business.CheckersPiece;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class AmericanCheckersGUI extends JFrame {

    public static final int CELL_SIZE = 100;
    public static int SELECTED_ROW = -1, SELECTED_COL = -1;
    public static final int CELL_PADDING = CELL_SIZE / 6;
    public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;
    public static final int SYMBOL_STROKE_WIDTH = 8;

    private GameBoardCanvas gameBoardCanvas;

    private AmericanCheckersGame game;
    
    public AmericanCheckersGUI() {
        this(new AmericanCheckersGame());
    }

    public AmericanCheckersGUI(AmericanCheckersGame game) {
        this.game = game;
        setContentPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setTitle("American Checkers Game");
        setVisible(true);
    }
    Container contentPane;
    private void setContentPane() {
        gameBoardCanvas = new GameBoardCanvas();
        gameBoardCanvas
                .setPreferredSize(new Dimension(CELL_SIZE * game.getTotalRows(), CELL_SIZE * game.getTotalColumns()));
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
        
        //added code
        gameBoardCanvas.panel.add(gameBoardCanvas.toss);
        gameBoardCanvas.panel.add(gameBoardCanvas.botplay);
        contentPane.add(gameBoardCanvas.panel,BorderLayout.NORTH);
        
        contentPane.add(gameBoardCanvas.jl,BorderLayout.SOUTH);
    }

    class GameBoardCanvas extends JPanel implements ActionListener,MouseListener{

    	JButton toss = new JButton("Click for Toss");
    	JTextField name1=new JTextField("Player1");
    	JTextField name2=new JTextField("Player2");

    	//added code
    	JPanel panel=new JPanel();
    	JFrame jf=new JFrame();
    	JButton botplay=new JButton("Human_Computer");	
    	
    	public JLabel jl=new JLabel();
    	public String Player1="Player1",Player2="Player2",tossWinner;    	
    	
        GameBoardCanvas(){
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (game.getGameState() == AmericanCheckersGame.GameState.PLAYING) {
                        int rowSelected = e.getY() / CELL_SIZE;
                        int colSelected = e.getX() / CELL_SIZE;
                        checkAndMakeMove(-1,-1, rowSelected, colSelected);
                        
                        //Code for Adding the Status bar below to show the turn of which player
                        if(game.getTurn()=="BLACK")
                        	jl.setText(Player1+"'s turn(Black)");
                        else if(game.getTurn()=="RED")
                        	jl.setText(Player2+"'s turn(Red)");
                        
                    } else {
                        //SHOW NOTIFICATION
                        game.resetGame();
                    }
                    repaint();
                    //show win message
                    if(game.getGameState().equals(AmericanCheckersGame.GameState.BLACK_WON) || game.getGameState().equals(AmericanCheckersGame.GameState.RED_WON)) {
                        String gameWinner = "Black";
                        if(game.getGameState() == AmericanCheckersGame.GameState.RED_WON){
                            gameWinner = "Red";
                        }
                        JOptionPane.showMessageDialog(null, new String(gameWinner + " won the game!\nGame will be restarted."));
                        game.resetGame();
                        contentPane.add(gameBoardCanvas.toss, BorderLayout.NORTH);
                        toss.setEnabled(true);
                        repaint();
                    }
                }
            });

                addMouseListener(this);
                setSize(500, 500);

                toss = new JButton("Make a Toss");
                toss.addActionListener(this);
                toss.setEnabled(true);
                
                botplay.addActionListener(this);
                toss.setEnabled(true);

        }
        
        void checkAndMakeMove(int rowSelected1, int colSelected1, int rowSelected2, int colSelected2){
            if(rowSelected1!=-1 && colSelected1!=-1){
                SELECTED_ROW = rowSelected1;
                SELECTED_COL = colSelected1;
            }
            boolean validPieceSelection=false;
            if(game.getCheckersPiece(rowSelected2, colSelected2)!=null && game.getCheckersPiece(rowSelected2, colSelected2).getColor()==game.getTurn()){
                validPieceSelection=true;
            }

            if(validPieceSelection){
                SELECTED_ROW = rowSelected2;
                SELECTED_COL = colSelected2;
            }else if(game.validMove(SELECTED_ROW, SELECTED_COL, rowSelected2, colSelected2)){
                boolean hop = game.makeMove(SELECTED_ROW, SELECTED_COL, rowSelected2, colSelected2);
                if(hop){
                    SELECTED_ROW = rowSelected2;
                    SELECTED_COL = colSelected2;
                }
                else{
                    SELECTED_ROW = -1;
                    SELECTED_COL = -1;
                    if(game.redKillCount == 12 || game.blackKillCount == 12){
                        if(game.redKillCount == 12) {
                            game.setGameState(AmericanCheckersGame.GameState.BLACK_WON);
                        }else{
                            game.setGameState(AmericanCheckersGame.GameState.RED_WON);
                        }

                    }
                    game.changeTurn();
                    repaint();

                    if(game.isBot() && game.getTurn()=="RED"){
                            int[] validBotMove = game.findValidBotMove();
                            checkAndMakeMove(validBotMove[0], validBotMove[1], validBotMove[2], validBotMove[3]);
                            if(game.getTurn()=="RED"){
                                game.changeTurn();
                                SELECTED_ROW=0;
                                SELECTED_COL=0;
                            }
                    }
                }
            }
    }



        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);
            drawBoard(g);
        }

        private void drawBoard(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            for (int row = 0; row < game.getTotalRows(); ++row) {
                for (int col = 0; col < game.getTotalColumns(); ++col) {
                    int x1 = col * CELL_SIZE + CELL_PADDING;
                    int y1 = row * CELL_SIZE + CELL_PADDING;

                    if((row+col)%2==1)
                    {
                        g2d.setColor(Color.GREEN.darker());
                        g2d.drawRoundRect(x1-12,y1-13,94,94,0,0);
                        g2d.fillRoundRect(x1-12,y1-13,94,94,0,0);
                    }
                    
                    if (game.getCheckersPiece(row, col) != null && game.getCheckersPiece(row, col).getColor() == "BLACK") {
                        g2d.setColor(Color.BLACK);
                        if(SELECTED_ROW==row && SELECTED_COL==col){
                            g2d.setColor(Color.WHITE);
                        }
                        g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
                        g2d.setColor(Color.BLACK);

                        g2d.fillOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
                        if(game.getCheckersPiece(row, col).isKing()) {
                        	Font myFont = new Font ("Courier New", 1, 40);
                        	g2d.setFont(myFont);
                    	
                        	g2d.setColor(Color.BLUE);
                        	g2d.drawString("K",x1+20,y1+45);
                    	}

                    } else if (game.getCheckersPiece(row, col) != null && game.getCheckersPiece(row, col).getColor() == "RED") {
                        g2d.setColor(Color.RED);
                        if(SELECTED_ROW==row && SELECTED_COL==col){
                            g2d.setColor(Color.WHITE);
                        }
                        g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
                        g2d.setColor(Color.RED);
                        g2d.fillOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
                        if(game.getCheckersPiece(row, col).isKing()) {
                        	Font myFont = new Font ("Courier New", 1, 40);
                        	g2d.setFont(myFont);
                    	
                        	g2d.setColor(Color.BLUE);
                        	g2d.drawString("K",x1+20,y1+45);
                    	}
                    }
                }
            }
        }

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			Object src=evt.getSource();
        	if(src==toss) {
        		toss();
        		toss.setEnabled(false);
        	}
        	
        	if(src==botplay) {
        		game.setBot(!game.isBot());
        		
        		if(game.isBot())
        			JOptionPane.showMessageDialog(null,new String("You switched to Human to Computer Mode"));
        		else
        			JOptionPane.showMessageDialog(null,new String("You switched to Human to Human Mode"));
        	}
		}
		
		//Code for adding Toss Functionality to the Game
		
		public void toss()
        {        	
        	JPanel panel=new JPanel();
        	panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            panel.add(name1);
            panel.add(name2);
            
            int result = JOptionPane.showConfirmDialog(null, panel, "Enter Your Names!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            tossWinner = name1.getText();
            if (result == JOptionPane.OK_OPTION) { //if players give names, names are assigned
            	tossWinner=playForToss();
            	
            	if(tossWinner==name1.getText())
                {
                	Player1 = tossWinner;
                    Player2 = name2.getText();
                }
                else
                {
                	Player1 = tossWinner;
                    Player2 = name1.getText();
                }
            }
            
    		JOptionPane.showMessageDialog(null,new String(tossWinner+" Won the Toss"));
    		jl.setText(tossWinner+"'s turn(Black)");

            game.setGameState(AmericanCheckersGame.GameState.PLAYING);
            contentPane.remove(gameBoardCanvas.toss);
        }
        
		//Code for Generating a random number for toss from 1-100
		
        public String playForToss()
        {
        	if((int)(Math.random()*100+1)%2==0)
        	{
        		tossWinner=name1.getText();
        	}
        	else
        	{
        		tossWinner=name2.getText();
        	}
        	return tossWinner;
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AmericanCheckersGUI();
            }
        });
    }
}

