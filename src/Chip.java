/***********************************************************************
 * Checkers piece. It can move forward diagonally unless a king, 
 * then it can move diagonally in any direction. 
 * 
 * @author Jennifer Moon
 * @version V2
 **********************************************************************/
public class Chip extends CheckersPiece {

	/** The Color of the Chip **/
	private int color; 
	
	/** Whether or not the chip is a king **/
	private boolean king;

	/** How many times the chip has moved (for use with undo) **/
	private int moveCount;

	/** When the chip turned into a king (if at all) **/
	private int kingCount;
	/*******************************************************************
	 * Creates a "null chip" for use in the undo function
	 ******************************************************************/
	public Chip(){
		color = 0;
		king = false;
	}
	/*******************************************************************
	 * Creates a new chip with the given color 
	 * 
	 * @param color, the color of the chip 
	 ******************************************************************/
	public Chip(int color){
		this.color = color; 
		king = false;
		moveCount = 0;
	}

	/*******************************************************************
	 * Creates a new chip with the given color and king status
	 *
	 * @param color, the color of the chip
	 * @param k, whether or not chip is a king
	 ******************************************************************/

	public Chip(int color, boolean k){
		this.color = color;
		king = k;
		moveCount = 0;
	}
	
	/*******************************************************************
	 * Returns an array of the possible moves in which a given piece 
	 * can move 
	 * 
	 * @param row, the row the piece is in 
	 * @param col, the column the piece is in 
	 * @param board, the locations of all the other pieces 
	 * @return an array of the possible places the chip may move
	 ******************************************************************/
	@Override
	public boolean[][] getMoves(int row, int col, 
			CheckersPiece[][] board) {
		
		boolean[][] possible = new boolean[8][8]; 
		
		if (king == true){
			
			if (row + 1 < 8 && col + 1 < 8){
				if (board[row + 1][col + 1] == null){
					possible[row + 1][col + 1] = true; 
				
				} else if (board[row + 1][col + 1].getColor() == -color)
					if (row + 2 < 8 && col + 2 < 8)
						if (board[row + 2][col + 2] == null)
							possible[row + 2][col + 2] = true; 
			}
			
			if (row + 1 < 8 && col - 1 > -1){
				if (board[row + 1][col - 1] == null){
					possible[row + 1][col - 1] = true; 
				
				} else if (board[row + 1][col - 1].getColor() == -color)
					if (row + 2 < 8 && col - 2 > -1)
						if(board[row + 2][col - 2] == null)
							possible[row + 2][col - 2] = true; 
			}
			
			if (row - 1 > -1 && col + 1 < 8){
				if (board[row - 1][col + 1] == null) {
					possible[row - 1][col + 1] = true; 
				
				} else if (board[row - 1][col + 1].getColor() == -color)
					if (row - 2 > -1 && col + 2 < 8)
						if(board[row - 2][col + 2] == null)
							possible[row - 2][col + 2] = true; 
			}
			
			if (row - 1 > -1 && col - 1 > -1){
				if (board[row - 1][col - 1] == null) {
					possible[row - 1][col - 1] = true; 
				
				} else if (board[row - 1][col - 1].getColor() == -color) 
					if (row - 2 > -1 && col - 2 > -1)
						if(board[row - 2][col - 2] == null)
							possible[row - 2][col - 2] = true; 
			}
			
			
		} else if (color == -1){
			//moves top down 
			
			if (row + 1 < 8 && col + 1 < 8){
				if (board[row + 1][col + 1] == null)
					possible[row + 1][col + 1] = true; 
				
				else if (board[row + 1][col + 1].getColor() == -color)
					if (row + 2 < 8 && col + 2 < 8)
						if (board[row + 2][col + 2] == null)
							possible[row + 2][col + 2] = true; 
			}
			
			if (row + 1 < 8 && col - 1 > -1){
				if (board[row + 1][col - 1] == null) {
					possible[row + 1][col - 1] = true; 
				
				} else if (board[row + 1][col - 1].getColor() == -color)
					if (row + 2 < 8 && col - 2 > -1)
						if(board[row + 2][col - 2] == null)
							possible[row + 2][col - 2] = true; 
			}
					
			
		} else if (color == 1){
			//moves bottom up 
			
			if (row - 1 > -1 && col + 1 < 8){
				if (board[row - 1][col + 1] == null)
					possible[row - 1][col + 1] = true; 
				
				else if (board[row - 1][col + 1].getColor() == -color)
					if (row - 2 > -1 && col + 2 < 8)
						if(board[row - 2][col + 2] == null)
							possible[row - 2][col + 2] = true; 
			}
			
			if (row - 1 > -1 && col - 1 > -1){
				if (board[row - 1][col - 1] == null)
					possible[row - 1][col - 1] = true; 
				
				else if (board[row - 1][col - 1].getColor() == -color)
					if (row - 2 > -1 && col - 2 > -1)
						if(board[row - 2][col - 2] == null)
							possible[row - 2][col - 2] = true; 
			}
		}
		
		return possible;
	}

	/*******************************************************************
	 * Returns the color of the piece 
	 * 
	 * @return color, the color of the piece 
	 ******************************************************************/
	@Override
	public int getColor() {
		return color; 
	}
	
	/*******************************************************************
	 * Returns the name of the piece 
	 * 
	 * @return the name of the piece 
	 ******************************************************************/
	@Override
	public String getName() {
		return "Chip";
	}

	/*******************************************************************
	 * Returns how many times the piece has moved
	 *
	 * @return how many times the piece has moved
	 ******************************************************************/

	public int getMoveCount() { return moveCount; }

	/*******************************************************************
	 * Sets how many times the piece has moved
	 *
	 * @param //how many times the piece has moved
	 ******************************************************************/

	public void setMoveCount(int m){ this.moveCount = m; }

	/*******************************************************************
	 * Returns how many times the piece has moved
	 *
	 * @return how many times the piece has moved
	 ******************************************************************/

	public int getKingCount() { return kingCount; }

	/*******************************************************************
	 * Sets how many moves it took until the piece turned into a king
	 *
	 * @param //how many moves it took until the piece turned into a king
	 ******************************************************************/

	public void setKingCount(int k) {this.kingCount = k; }

	/*******************************************************************
	 * Returns whether or not a piece is a king
	 * 
	 * @return whether or not the piece is a king
	 ******************************************************************/

	public boolean getKing(){
		return king; 
	}
	
	/*******************************************************************
	 * Changes the Chip to a king
	 * 
	 * @param king whether or not the piece is a king
	 ******************************************************************/
	public void setKing(boolean king){
		this.king = king; 
	}
}