/***********************************************************************
 * Knight checkers piece. The knight can move in an L shape in any
 * direction so long as it is not obstructed by another piece. 
 * 
 * @author Jennifer Moon
 * @version V2
 **********************************************************************/
public class Knight extends CheckersPiece{
	
	/* Piece Type */
	private String name; 
	
	/* Color of Piece */
	private int color; 
	
	/******************************************************************
	 * Creates the knight piece with the name "Knight" and the 
	 * given color
	 * 
	 * @param the color of the piece 
	 ******************************************************************/
	public Knight(int color){
		name = "Knight"; 
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
		
		//For each spot on the board... 
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				
				if (i == row + 1 && j == col + 2 
						&& (board[i][j] == null
						|| board[i][j].getColor() == -color))
					moves[i][j] = true; 
				
				if (i == row + 1 && j == col - 2
						&& (board[i][j] == null
						|| board[i][j].getColor() == -color))
					moves[i][j] = true; 
				
				if (i == row + 2 && j == col + 1
						&& (board[i][j] == null
						|| board[i][j].getColor() == -color))
					moves[i][j] = true; 
				
				if (i == row + 2 && j == col - 1
						&& (board[i][j] == null
						|| board[i][j].getColor() == -color))
					moves[i][j] = true; 
				
				if(i == row - 1 && j == col + 2
						&& (board[i][j] == null
						|| board[i][j].getColor() == -color))
					moves[i][j] = true; 
				
				if(i == row - 1 && j == col - 2
						&& (board[i][j] == null
						|| board[i][j].getColor() == -color))
					moves[i][j] = true; 
				
				if (i == row - 2 && j == col - 1
						&& (board[i][j] == null
						|| board[i][j].getColor() == -color))
					moves[i][j] = true; 
				
				if (i == row - 2 && j == col + 1
						&& (board[i][j] == null
						|| board[i][j].getColor() == -color))
					moves[i][j] = true; 
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