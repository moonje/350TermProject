/***********************************************************************
 * Pawn checkers piece. The pawn can move only one space forward, must
 * capture diagonally, and can move one or two spaces forward on the 
 * first turn 
 * 
 * @author Jennifer Moon
 * @version V2
 **********************************************************************/
public class Pawn extends CheckersPiece{
	
	//READ ME: The pawn can now capture! 
	
	/* Piece Type */
	private String name; 
	
	/* Color of Piece */
	private int color; 
	
	/******************************************************************
	 * Creates the pawn piece with the name "Pawn" and the given color
	 * 
	 * @param the color of the piece 
	 ******************************************************************/
	public Pawn(int color){
		name = "Pawn";
		this.color = color; 
	}

	/******************************************************************
	 * Returns an array of type boolean listing the possible places 
	 * the piece may move 
	 * 
	 * @param row, the row the piece is in 
	 * @param col, the column the piece is in 
	 * @param board, the layout of the board
	 * @return an array of the possible moves 
	 ******************************************************************/
	@Override
	boolean[][] getMoves(int row, int col, CheckersPiece[][] board) {
		
		boolean[][] moves = new boolean[8][8];
		
		//Black piece (moves bottom up) 
		if (color == -1){
			
			if (row - 1 > 0){
				
				//THESE
				if (board[row][col] == board[6][col] && 
						board[row - 2][col] == null) {
					moves[row - 2][col] = true;
				}
				
				//Cannot capture
				if (board[row - 1][col] == null)
					moves[row - 1][col] = true; 
				
				//Can only move this way if capturing
				if (col - 1 >= 0)
					if (board[row-1][col-1] != null)
						if (board[row-1][col-1].getColor() == -color)
							moves[row-1][col-1] = true; 

				//Can only move this way if capturing
				if (col + 1 < 8)
					if (board[row-1][col+1] != null)
						if (board[row-1][col+1].getColor() == -color)
						moves[row-1][col+1] = true; 
			}
			
		//White piece (moves top down)
		} else if (color == 1){
			
			if (row + 1 < 8){
				
				//THIS
				if (board[row][col] == board[1][col] 
						&& board[row + 2][col] == null) {
					moves[row + 2][col] = true;
				}
				
				//Cannot capture 
				if (board[row+1][col] == null)	
					moves[row+1][col] = true; 
				
				//Can only move this way if capturing 
				if (col - 1 >= 0)
					if (board[row+1][col-1] != null)
						if (board[row+1][col-1].getColor() == -color)
							moves[row+1][col-1] = true; 

				//Can only move this way if capturing
				if (col + 1 < 8)
					if (board[row+1][col+1] != null)
						if (board[row+1][col+1].getColor() == -color)
							moves[row+1][col+1] = true; 
			}
		}
		
		return moves;
	}

	/******************************************************************
	 * Returns the color of the piece 
	 * 
	 * @return the color of the piece 
	 ******************************************************************/
	@Override
	int getColor() {
		return color; 
	}
	
	/******************************************************************
	 * Returns the name of the piece 
	 * 
	 * @return the name of the piece 
	 ******************************************************************/
	@Override
	String getName() {
		return name; 
	}

}

