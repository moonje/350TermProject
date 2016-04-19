import java.util.ArrayList;

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
	
	public boolean isMoved;

	/******************************************************************
	 * Creates the King piece with the name "King" and the given color
	 *
	 * @param //the color of the piece
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
			
					int x = 0;
		if(board[row][0] != null && board[row][0].getName().equals("Rook")){
			if(!board[row][0].hasMoved()) {
				x = 1;
				while (x <= col && x > 0) {
					if (board[row][x] == null){
						x++;
						continue;
					}
					else if(board[row][x].getName().equals("King")) {
						if (board[row][x].hasMoved()) {
							break;
						} else {
							moves[row][x - 2] = true;
							break;
						}

					}
					else{
						break;
					}
				}
			}
		}
		if(board[row][7] != null && board[row][7].getName().equals("Rook")) {
			if (!board[row][7].hasMoved()) {
				x = 6;
				while (x >= col) {
					if (board[row][x] == null){
						x--;
						continue;
					}
					else if(board[row][x].getName().equals("King")) {
						if (board[row][x].hasMoved()) {
							break;
						} else {
							moves[row][x + 2] = true;
							break;
						}

					}else{
						break;
					}
				}
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
	public String getName() {
		return name;
	
	}
		public void moved(){
		this.isMoved = true;
	}

	public boolean hasMoved(){
		return this.isMoved;
	}

}
