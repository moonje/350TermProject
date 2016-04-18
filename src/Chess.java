import javax.swing.JOptionPane;

/***********************************************************************
 * Chess controller class
 * 
 * @author Jennifer Moon
 * @version V2
 **********************************************************************/
public class Chess {

	/** Chess Board **/
	private CheckersPiece[][] board;

	/** Row of Selected Piece **/
	int row;

	/** Column of Selected Piece **/
	int col;

	/** Current Possible Moves**/
	boolean[][] possible;

	boolean blackCheck;

	boolean whiteCheck;

	/** Turn Count **/
	public int turnCount;

	//White = 1, top
	//Black = -1, bottom

	/*******************************************************************
	 * Constructor used to create a new game of Chess with the pieces
	 * in the starting position
	 ******************************************************************/
	public Chess() {
		blackCheck = false;
		whiteCheck = false;
		turnCount = 0;
		board = new CheckersPiece[8][8];

		board[0][0] = new Rook(1);
		board[0][1] = new Knight(1);
		board[0][2] = new Bishop(1);
		board[0][3] = new King(1);
		board[0][4] = new Queen(1);
		board[0][5] = new Bishop(1);
		board[0][6] = new Knight(1);
		board[0][7] = new Rook(1);

		board[1][0] = new Pawn(1);
		board[1][1] = new Pawn(1);
		board[1][2] = new Pawn(1);
		board[1][3] = new Pawn(1);
		board[1][4] = new Pawn(1);
		board[1][5] = new Pawn(1);
		board[1][6] = new Pawn(1);
		board[1][7] = new Pawn(1);

		board[6][0] = new Pawn(-1);
		board[6][1] = new Pawn(-1);
		board[6][2] = new Pawn(-1);
		board[6][3] = new Pawn(-1);
		board[6][4] = new Pawn(-1);
		board[6][5] = new Pawn(-1);
		board[6][6] = new Pawn(-1);
		board[6][7] = new Pawn(-1);

		board[7][0] = new Rook(-1);
		board[7][1] = new Knight(-1);
		board[7][2] = new Bishop(-1);
		board[7][3] = new Queen(-1);
		board[7][4] = new King(-1);
		board[7][5] = new Bishop(-1);
		board[7][6] = new Knight(-1);
		board[7][7] = new Rook(-1);
	}

	/*******************************************************************
	 * Returns the board
	 *
	 * @return the chess board
	 ******************************************************************/
	public CheckersPiece[][] getBoard() {
		return board;
	}

	/*******************************************************************
	 * Returns the current player
	 *
	 * @return an integer representing the current player
	 ******************************************************************/
	public int getPlayer() {
		if (turnCount % 2 == 0)
			return -1;
		else
			return 1;
	}

	/*******************************************************************
	 * Returns the color of the piece at a given location, returns 0
	 * if there is not a piece at the given location
	 *
	 * @param //row, the row the piece is in
	 * @param //col, the column the piece is in
	 * @return an integer representing the color of the player at a
	 * given position
	 ******************************************************************/
	public int getColorAt(int r, int c) {
		if (board[r][c] == null)
			return 0;

		return board[r][c].getColor();
	}

	/*******************************************************************
	 * Returns an array of possible moves given a specific chess
	 * piece
	 *
	 * @param row, the row of the piece
	 * @param col, the column of the piece
	 * @return boolean array of possible places a piece can move
	 ******************************************************************/
	public boolean[][] getMoves(int row, int col) {

		//Checks to see if the current player can move the given piece 
		if (board[row][col] != null)
			if (board[row][col].getColor() != getPlayer()) {
				possible = new boolean[8][8];
				return possible;
			}

		CheckersPiece temp = board[row][col];

		if (temp == null) {
			boolean[][] b = new boolean[8][8];
			return b;
		}

		this.row = row;
		this.col = col;
		possible = temp.getMoves(row, col, board);

		return possible;
	}

	/*******************************************************************
	 * Removes the chess piece at the given position
	 *
	 * @param row, the row of the piece
	 * @param col, the column of the piece
	 ******************************************************************/
	public void remove(int row, int col) {
		board[row][col] = null;
	}

	/*******************************************************************
	 * Moves the piece at the given position to the other given
	 * position. It is assumed that the given move is legal.
	 *
	 * @param row1, the row to be moved from
	 * @param col1, the column to be moved from
	 * @param row2, the row to be moved to
	 * @param col2, the column to be moved to
	 ******************************************************************/
	public void move(int row1, int col1, int row2, int col2) {

		//move to the new position
		board[row2][col2] = board[row1][col1];

		//remove from the old position 
		remove(row1, col1);

		//checks to see if either king is in check
		kingCheck(1);
		kingCheck(-1);

		//increments the turn count
		turnCount++;
	}

	public void kingCheck(int color) {
		boolean found = false;
		//finds the coords of the king of the given color
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null) {
					if (board[i][j].getName().equals("King") && 
							color == getColorAt(i, j)) {
						for (int k = 0; k < 8; k++) {
							for (int l = 0; l < 8; l++) {
								/*finds every piece of opposite color 
								 * and checks their moveset to see if 
								 * it hits the kings space*/
								if (getMoves(k, l)[i][j] && 
										getColorAt(k, l) == (-color)) {
									found = true;
									if (color == 1) {
										whiteCheck = true;
									} else blackCheck = true;
								}
							}
						}
					}
				}
			}
		}
		if (found && whiteCheck && color == 1){ 
			//System.out.print("White king is in check! \n"); 
			JOptionPane.showMessageDialog(null, 
					"White King is in Check!");
		} else { 
			whiteCheck = false; 
		}
		System.out.print(whiteCheck);
		
		if (found && blackCheck && color == -1){ 
			//System.out.print("Black king is in check! \n"); 
			JOptionPane.showMessageDialog(null, 
					"Black King is in Check!");
		} else { 
			blackCheck = false; 
		}
		System.out.print(blackCheck);
	}
}