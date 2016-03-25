/***********************************************************************
 * Queen checkers piece. The queen can move in any direction any number
 * of spaces so long as it is not obstructed by another piece. 
 * 
 * @author Jennifer Moon
 * @version V2
 **********************************************************************/
public class Queen extends CheckersPiece{

	/* Piece Type */
	private String name; 
	
	/* Color of Piece */
	private int color; 
	
	/******************************************************************
	 * Creates the queen piece with the name "Queen" and the given color
	 * 
	 * @param the color of the piece 
	 ******************************************************************/
	public Queen(int color){
		name = "Queen"; 
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
		boolean cont = true; 
		
		//Same column, positive direction 
		int i = row + 1; 
		
		while (i < 8 && cont){
			
			if (board[i][col] == null) {
				moves[i][col] = true; 
				i++;
				
			} else {
				if (board[i][col].getColor() == - color)
					moves[i][col] = true; 
				cont = false; 
			}
		}
		
		//Same column, negative direction 
		i = row - 1; 
		cont = true; 
		
		while (i >= 0 && cont){
			
			if (board[i][col] == null) {
				moves[i][col] = true; 
				i--;
				
			} else {
				if (board[i][col].getColor() == - color)
					moves[i][col] = true; 
				cont = false; 
			}
		}
		
		//Same row, positive direction 
		int j = col + 1; 
		cont = true; 
		
		while (j < 8 && cont){
			
			if (board[row][j] == null) {
				moves[row][j] = true; 
				j++;
				
			} else {
				if (board[row][j] != null)
					if (board[row][j].getColor() == - color)
						moves[row][j] = true; 
				cont = false; 
			}
		}
		
		//Same row, negative direction 
		j = col - 1; 
		cont = true; 
		
		while (j >= 0 && cont){
			
			if (board[row][j] == null) {
				moves[row][j] = true; 
				j--;
				
			} else {
				if (board[row][j] != null)
					if (board[row][j].getColor() == - color)
						moves[row][j] = true; 
				cont = false; 
			}
		}
		
		//Positive slope, positive direction 
		i = row + 1; 
		j = col + 1; 
		cont = true; 
		
		while (i < 8 && j < 8 && cont){
			
			if(board[i][j] == null){
				moves[i][j] = true; 
				i++;
				j++;
				
			} else {
				if (board[i][j] != null)
					if (board[i][j].getColor() == - color)
						moves[i][j] = true; 
				cont = false; 
			}
		}
		
		//Positive slope, negative direction 
		i = row - 1; 
		j = col - 1; 
		cont = true;
		
		while (i >= 0 && j >= 0 && cont){
			
			if(board[i][j] == null){
				moves[i][j] = true; 
				i--; 
				j--;
				
			} else {
				if (board[i][j] != null)
					if (board[i][j].getColor() == - color)
						moves[i][j] = true; 
				cont = false; 
			}
		}
		
		//Negative slope, positive direction 
		i = row - 1; 
		j = col + 1; 
		cont = true;
		
		while (i >= 0 && j < 8 && cont){
			
			if (board[i][j] == null){
				moves[i][j] = true; 
				i--;
				j++;
				
			} else {
				if (board[i][j] != null)
					if (board[i][j].getColor() == - color)
						moves[i][j] = true; 
				cont = false; 
			}
		}
		
		//Negative slope, negative direction 
		i = row + 1; 
		j = col - 1; 
		cont = true;
		
		while (i < 8 && j >= 0 && cont){
			
			if(board[i][j] == null){
				moves[i][j] = true; 
				i++;
				j--;
				
			} else {
				if (board[i][j] != null)
					if (board[i][j].getColor() == - color)
						moves[i][j] = true; 
				cont = false; 
			}
		}
		
		//Currently the queen can only move to empty spaces and 
		//cannot jump 
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