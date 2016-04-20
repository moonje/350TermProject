import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;

/***********************************************************************
 * Chess board panel 
 * 
 * @author Jennifer Moon
 * @version V2
 **********************************************************************/
public class ModelBoard extends JPanel {

	/** The Chess Board **/
	public JButton[][] board;
	
	/** The Chess Game **/
	public Chess chess; 
	
	/** The Last Row Selected **/
	public int lrow; 
	
	/** The Last Column Selected **/
	public int lcolumn; 
	
	/** The Board of Buttons **/
	private JPanel panel; 
	
	//private JMenuItem save;
	
	//public PrintWriter out;
	
	/** The Chess Piece Images **/
	ImageIcon white_Bishop; 
	ImageIcon white_Knight;
	ImageIcon white_Pawn;
	ImageIcon white_King;
	ImageIcon white_Queen;
	ImageIcon white_Rook;
	ImageIcon black_Pawn;
	ImageIcon black_Bishop;
	ImageIcon black_King;
	ImageIcon black_Queen;
	ImageIcon black_Knight;
	ImageIcon black_Rook;

	
	/*******************************************************************
	 * Constructor to create the board 
	 ******************************************************************/
	public ModelBoard() {
		
		//ptionMenu = new JMenu("Options");
		//save = new JMenuITem("Save Game");
		Image wbishop = null; 
		Image wking = null;
		Image wqueen = null; 
		Image wrook = null; 
		Image wknight = null; 
		Image wpawn = null; 
		
		Image bbishop = null; 
		Image bking = null;
		Image bqueen = null; 
		Image brook = null; 
		Image bknight = null; 
		Image bpawn = null;
		
		try {
			wbishop = ImageIO.read(
					getClass().getResource("WhiteBishop.png"));
			wrook = ImageIO.read(
					getClass().getResource("WhiteRook.png"));
			wqueen = ImageIO.read(
					getClass().getResource("WhiteQueen.png"));
			wknight = ImageIO.read(
					getClass().getResource("WhiteKnight.png"));
			wpawn = ImageIO.read(
					getClass().getResource("WhitePawn.png"));
			wking = ImageIO.read(
					getClass().getResource("WhiteKing.png"));
			
			bbishop = ImageIO.read(
					getClass().getResource("BlackBishop.png"));
			brook = ImageIO.read(
					getClass().getResource("BlackRook.png"));
			bqueen = ImageIO.read(
					getClass().getResource("BlackQueen.png"));
			bknight = ImageIO.read(
					getClass().getResource("BlackKnight.png"));
			bpawn = ImageIO.read(
					getClass().getResource("BlackPawn.png"));
			bking = ImageIO.read(
					getClass().getResource("BlackKing.png"));
			
		} catch (Exception e) {
			System.out.print("One or more images not loaded");
		}
		
		//white pieces 
		
		wbishop = wbishop.getScaledInstance(60, 85, 
				java.awt.Image.SCALE_SMOOTH);
		white_Bishop = new ImageIcon(wbishop);
		
		wrook = wrook.getScaledInstance(60, 80,
				java.awt.Image.SCALE_SMOOTH);
		white_Rook = new ImageIcon(wrook);
		
		wqueen = wqueen.getScaledInstance(55, 90,
				java.awt.Image.SCALE_SMOOTH);
		white_Queen = new ImageIcon(wqueen);
		
		wknight = wknight.getScaledInstance(50, 80, 
				java.awt.Image.SCALE_SMOOTH);
		white_Knight = new ImageIcon(wknight);
		
		wpawn = wpawn.getScaledInstance(50, 80, 
				java.awt.Image.SCALE_SMOOTH);
		white_Pawn = new ImageIcon(wpawn);
		
		wking = wking.getScaledInstance(50, 80, 
				java.awt.Image.SCALE_SMOOTH);
		white_King = new ImageIcon(wking);
		
		//black pieces 
		
		bbishop = bbishop.getScaledInstance(60, 85, 
				java.awt.Image.SCALE_SMOOTH);
		black_Bishop = new ImageIcon(bbishop);
		
		brook = brook.getScaledInstance(60, 80,
				java.awt.Image.SCALE_SMOOTH);
		black_Rook = new ImageIcon(brook);
		
		bqueen = bqueen.getScaledInstance(55, 90,
				java.awt.Image.SCALE_SMOOTH);
		black_Queen = new ImageIcon(bqueen);
		
		bknight = bknight.getScaledInstance(60, 80, 
				java.awt.Image.SCALE_SMOOTH);
		black_Knight = new ImageIcon(bknight);
		
		bpawn = bpawn.getScaledInstance(50, 80, 
				java.awt.Image.SCALE_SMOOTH);
		black_Pawn = new ImageIcon(bpawn);
		
		bking = bking.getScaledInstance(50, 80, 
				java.awt.Image.SCALE_SMOOTH);
		black_King = new ImageIcon(bking);
		
		
		//create the board
		panel = new JPanel();
		panel.setLayout(new GridLayout(8,8,5,5));
		
		chess = new Chess();
		board = new JButton[8][8];
		ButtonListener listener = new ButtonListener();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new JButton("");
				board[i][j].setPreferredSize(new Dimension(90, 90));
				board[i][j].addActionListener(listener);
				panel.add(board[i][j]);

			}
		}
		
		add(panel, BorderLayout.CENTER);
		displayBoard();
		setRegColor();
		setBackgroundColor();
	}
	
	/*******************************************************************
	 * Displays the location of the pieces 
	 ******************************************************************/
	public void displayBoard() {
		
		CheckersPiece[][] temp = chess.getBoard();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (temp[i][j] != null) {
					if (temp[i][j].getName().equals("King")) {
						if (temp[i][j].getColor() == -1) {
							board[i][j].setIcon(black_King);
						} else
							board[i][j].setIcon(white_King);
					}
					if (temp[i][j].getName().equals("Queen")) {
						if (temp[i][j].getColor() == -1) {
							board[i][j].setIcon(black_Queen);
						} else
							board[i][j].setIcon(white_Queen);
					}
					if (temp[i][j].getName().equals("Pawn")) {
						if (temp[i][j].getColor() == -1) {
							board[i][j].setIcon(black_Pawn);
						} else
							board[i][j].setIcon(white_Pawn);
					}
					if (temp[i][j].getName().equals("Rook")) {
							if (temp[i][j].getColor() == -1) {
								board[i][j].setIcon(black_Rook);
							} else
								board[i][j].setIcon(white_Rook);
						}
					if (temp[i][j].getName().equals("Bishop")) {
							if (temp[i][j].getColor() == -1) {
								board[i][j].setIcon(black_Bishop);
							} else
								board[i][j].setIcon(white_Bishop);
							}
					if (temp[i][j].getName().equals("Knight")) {
							if (temp[i][j].getColor() == -1) {
									board[i][j].setIcon(black_Knight);
							} else
									board[i][j].setIcon(white_Knight);
							}

					} else {
						board[i][j].setIcon(null);
					}
				}
			}
		}
	
	/*******************************************************************
	 * Displays the possible moves of the selected piece 
	 * 
	 * @param spots, the possible spots to move to 
	 ******************************************************************/
	private void setAvailable(boolean[][] spots) {
		
		setRegColor();
		for (int i = 0; i < 8; i ++)
			for (int j = 0; j < 8; j++) 
				if (spots [i][j] == true) 
					board[i][j].setBackground(Color.GREEN);
	}
	
	/*******************************************************************
	 * Disables or enables all pieces of the board 
	 * 
	 * @param b whether or not the buttons should be enabled or not 
	 ******************************************************************/
	public void disableBoard(boolean b){
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j ++)
				board[i][j].setEnabled(b);
					
	}
	
	/*******************************************************************
	 * Displays the board's regular colors (black and white) 
	 ******************************************************************/
	private void setRegColor(){
		
		for (int j = 0; j < 8; j = j + 2)
			for (int i = 0; i < 8; i ++){
				if (i % 2 == 0)
					board[i][j].setBackground(Color.DARK_GRAY);
				else 
					board[i][j].setBackground(Color.WHITE);
			}
		
		for (int j = 1; j < 8; j = j + 2)
			for (int i = 0; i < 8; i ++){
				if (i % 2 == 1)
					board[i][j].setBackground(Color.DARK_GRAY);
				else 
					board[i][j].setBackground(Color.WHITE);
			}
	}

	/*******************************************************************
	 * Sets the background color based on whose turn it is.
	 ******************************************************************/
	public void setBackgroundColor(){
		if (chess.turnCount % 2 == 0)
			panel.setBackground(Color.BLACK);
		else
			panel.setBackground(Color.WHITE);
	}
// public void actionPerformed(ActionEvent e){	
// 	if (e.getSource() == save) {

// 			String PieceColor;
// 			String Delim;

// 			try {
// 				out = new PrintWriter("ChessSave.txt");
// 			}
// 				catch(Exception IoException){
// 				//add error message
// 					System.out.println("GAME OVER");

// 			}
// 			for (int x = 0; x < 8; x++) {
// 				for (int y = 0; y < 8; y++) {

// 					if(x == 8){
// 						Delim = "|";
// 					}
// 					else{
// 						Delim = ",";
// 					}


// 					if (board[x][y] != null) {
// 						if (chess.getColorAt(x, y) == 1) {
// 							PieceColor = "White";
// 						}
// 						else {
// 							PieceColor = "Black";
// 						}

// 						//String out = PieceColor + Delim + board[x][y].getName() + Delim;
// 						out.print(PieceColor + Delim + board[x][y].getName() + Delim);
// 					}
// 					else{
// 						out.print("x" + Delim);
// 					}
// 					out.println();
// 				}
// 			}

// 			out.close();

// 		}
// 	}
	


	/*******************************************************************
	 * Action Listener that handles what button has been pressed 
	 ******************************************************************/
	private class ButtonListener implements ActionListener {

		/***************************************************************
		 * Processes what button has been pressed 
		 * 
		 * @param e, the ActionEvent performed 
		 **************************************************************/
		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < 8; i++) 
				
				for (int j = 0; j < 8; j++) {
					
					if (board[i][j] == e.getSource()) {
						
						if (board[i][j].getBackground() != Color.GREEN){
							setAvailable(chess.getMoves(i, j));
							lrow = i; 
							lcolumn = j;
							
						} else {
							chess.move(lrow, lcolumn, i, j);
							setRegColor();
							setBackgroundColor();
						}	
					}
				}

			displayBoard();
		}
	}
}

