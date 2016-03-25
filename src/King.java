/***********************************************************************
 * King checkers piece. The king can move one space in any direction; 
 * however, it cannot move into check or adjacent to the other King. 
 * 
 * @author Jennifer Moon
 * @version V2
 **********************************************************************/
public class King extends CheckersPiece {
	
	//READ ME: The king can now capture! 
	
	/* Name of Piece*/
	private String name; 
	
	/* Color of Piece */
	private int color; 
	
	//Boolean for whether or not it is in check? 
	
	//Boolean for whether or not checkmate? 
	
	/******************************************************************
	 * Creates the King piece with the name "King" and the given color
	 * 
	 * @param the color of the piece 
	 ******************************************************************/
	public King(int color) {
		name = "King";
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
	public boolean[][] getMoves(int row, int col, 
			CheckersPiece[][] board) {
		
		boolean[][] moves = new boolean[8][8]; 
		
		//For each spot on the board... 
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				
				//...check to see if it is next to the King and empty
				if (row + 1 == i && col + 1 == j 
						&& (board[i][j] == null || 
						board[i][j].getColor() == -color)) 
					moves[i][j] = true; 
				
				else if (row + 1 == i && col - 1 == j 
						&& (board[i][j] == null || 
						board[i][j].getColor() == -color)) 
					moves[i][j] = true; 
				
				else if (row - 1 == i && col + 1 == j 
						&& (board[i][j] == null || 
						board[i][j].getColor() == -color)) 
					moves[i][j] = true; 
				
				else if (row - 1 == i && col - 1 == j 
						&& (board[i][j] == null || 
						board[i][j].getColor() == -color)) 
					moves[i][j] = true; 
				
				else if (row - 1 == i && col == j 
						&& (board[i][j] == null || 
						board[i][j].getColor() == -color)) 
					moves[i][j] = true; 
				
				else if (row + 1 == i && col == j 
						&& (board[i][j] == null || 
						board[i][j].getColor() == -color)) 
					moves[i][j] = true; 
				
				else if (row == i && col + 1 == j 
						&& (board[i][j] == null || 
						board[i][j].getColor() == -color)) 
					moves[i][j] = true; 
				
				else if (row == i && col - 1 == j 
						&& (board[i][j] == null || 
						board[i][j].getColor() == -color)) 
					moves[i][j] = true; 
				
				//if it's not, you can't move there 
				//this line isn't actually need, sooooooooo
				else 
					moves[i][j] = false; 
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
	public int getColor() {
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
