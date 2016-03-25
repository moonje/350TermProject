
public class CheckersLogic {

	/** Checkers Board **/
	private CheckersPiece[][] board; 
	
	/** Row of Selecte Piece **/
	int row; 
	
	/** Column of Selected Piece **/
	int col; 
	
	/** Current POssible Moves **/
	boolean[][] possible; 
	
	/** Turn Count **/
	int turnCount; 
	
	/*******************************************************************
	 * Constructor used to create a new game of Checkers with the pieces 
	 * in the starting position 
	 ******************************************************************/
	public CheckersLogic(){
		
		turnCount = 0; 
		board = new CheckersPiece[8][8]; 
		
		board[0][1] = new Chip(-1); 
		board[0][3] = new Chip(-1);
		board[0][5] = new Chip(-1);
		board[0][7] = new Chip(-1);
		
		board[1][0] = new Chip(-1);
		board[1][2] = new Chip(-1);
		board[1][4] = new Chip(-1);
		board[1][6] = new Chip(-1);
		
		board[2][1] = new Chip(-1);
		board[2][3] = new Chip(-1);
		board[2][5] = new Chip(-1);
		board[2][7] = new Chip(-1);
		
		board[5][0] = new Chip(1); 
		board[5][2] = new Chip(1);
		board[5][4] = new Chip(1);
		board[5][6] = new Chip(1);
		
		board[6][1] = new Chip(1);
		board[6][3] = new Chip(1);
		board[6][5] = new Chip(1);
		board[6][7] = new Chip(1);
		
		board[7][0] = new Chip(1);
		board[7][2] = new Chip(1);
		board[7][4] = new Chip(1);
		board[7][6] = new Chip(1);
	}
	
	/*******************************************************************
	 * Returns the board 
	 * 
	 * @return the chess board
	 ******************************************************************/
	public CheckersPiece[][] getBoard(){
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
	 * @return an integer representing the color of the player at a 
	 *         given position 
	 * @param row, the row the piece is in 
	 * @param col, the column the piece is in 
	 ******************************************************************/
	public int getColorAt(int r, int c) {
		if (board[r][c] == null)
			return 0; 
		
		return board[r][c].getColor();
	}
	
	/*******************************************************************
	 * Returns an array of possible moves given a specific checkers 
	 * piece 
	 * 
	 * @param row, the row of the piece
	 * @param col, the column of the piece 
	 * @return boolean array of possible places a piece can move 
	 ******************************************************************/
	public boolean[][] getMoves(int row, int col){
		
		//Checks to see if the current player can move the given piece 
		if (board[row][col] != null)
			if (board[row][col].getColor() != getPlayer()){
				possible = new boolean[8][8];  
				return possible; 
			}
		
		CheckersPiece temp = board[row][col];
		
		if (temp == null){
			boolean[][] b = new boolean[8][8];
			return b;
		}
		
		this.row = row; 
		this.col = col; 
		possible = temp.getMoves(row, col, board);
		
		return possible;
	}
	
	/*******************************************************************
	 * Removes the checkers piece at the given position 
	 * 
	 * @param row, the row of the piece 
	 * @param col, the column of the piece 
	 ******************************************************************/
	public void remove(int row, int col){
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
	public void move(int row1, int col1, int row2, int col2){
		
		//changes the piece to a king if appropriate 
		if (board[row1][col1].getColor() == -1 && row2 == 7)
			((Chip) board[row1][col1]).setKing(true); 
		
		if (board[row1][col1].getColor() == 1 && row2 == 0)
			((Chip) board[row1][col1]).setKing(true); 
		
		//move to the new position
		board[row2][col2] = board[row1][col1];
		
		//remove from the old position 
		remove(row1, col1);
		
		//Removes a piece if jumped 
		if (Math.abs(row1 - row2) == 2)
			remove(row1 + 1, col1 + 1);
		
		//increments the turn count
		turnCount++;
	}
}
