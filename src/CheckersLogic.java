import java.awt.*;
import java.util.Stack;

public class CheckersLogic {

	/**
	 * Checkers Board
	 **/
	private Chip[][] board;

	/**
	 * Row of Selecte Piece
	 **/
	int row;

	/**
	 * Column of Selected Piece
	 **/
	int col;

	/**
	 * Current Possible Moves
	 **/
	boolean[][] possible;

	/**
	 * Turn Count
	 **/
	public int turnCount;

	/**
	 * Stack for piece information
	 **/
	Stack<Chip> pieceList;

	/**
	 * Stack for coordinates
	 **/
	Stack<Point> coordsList;

	/*******************************************************************
	 * Constructor used to create a new game of Checkers with the pieces
	 * in the starting position
	 ******************************************************************/
	public CheckersLogic() {

		turnCount = 0;
		board = new Chip[8][8];

		pieceList = new Stack<>();
		coordsList = new Stack<>();

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
	 * Returns an array of possible moves given a specific checkers
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
	 * Removes the checkers piece at the given position
	 *
	 * @param row, the row of the piece
	 * @param col, the column of the piece
	 ******************************************************************/
	public void remove(int row, int col) {
		board[row][col] = null;
	}

	/*******************************************************************
	 * Adds a new checkers piece with the given color and king status at
	 * the given position
	 *
	 * @param row,   the row of the piece
	 * @param col,   the column of the piece
	 * @param color, the color of the piece
	 * @param k,     whether or not the piece is a king
	 ******************************************************************/
	public void add(int row, int col, int color, boolean k) {
		if (k) {
			board[row][col] = new Chip(color, true);
		} else {
			board[row][col] = new Chip(color, false);
		}
	}

	/*******************************************************************
	 * Returns whether or not red has won the game
	 *
	 * @return boolean representing if red has won
	 ******************************************************************/
	public boolean redWin() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null && board[i][j].getColor() == 1)
					return false;
			}
		}

		return true;
	}

	/*******************************************************************
	 * Returns whether or not black has won the game
	 *
	 * @return boolean representing if black has won
	 ******************************************************************/
	public boolean blackWin() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null && board[i][j].getColor() == -1)
					return false;
			}
		}

		return true;
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

		//stores current information of both pieces and coords onto stacks

		/* if there isn't a chip in the space (moving to an empty space),
		*  it creates a "null chip" to take up a space on the stack. */

		if (board[row2][col2] == null) {
			pieceList.push(new Chip());
		} else {
			pieceList.push(board[row2][col2]);
		}

		if (board[row1][col1] == null) {
			pieceList.push(new Chip());
		} else {
			pieceList.push(board[row1][col1]);
		}

		coordsList.push(new Point(row2, col2));
		coordsList.push(new Point(row1, col1));

		//changes the piece to a king if appropriate
		if (board[row1][col1].getColor() == -1 && row2 == 7)
			(board[row1][col1]).setKing(true);
//			board[row1][col1].setKingCount(board[row1][col1].getMoveCount());

		if (board[row1][col1].getColor() == 1 && row2 == 0)
			(board[row1][col1]).setKing(true);

		//move to the new position
		board[row2][col2] = board[row1][col1];
//		board[row2][col2].setMoveCount(board[row1][col1].getMoveCount());

//		//increments the pieces move count
//		board[row1][col1].setMoveCount(board[row1][col1].getMoveCount() + 1);
//		System.out.print(board[row1][col1].getMoveCount());

		//remove from the old position
		remove(row1, col1);

		//Removes a piece if jumped
		if (Math.abs(row1 - row2) == 2)

			if (row1 < row2 && col1 > col2) {
				remove(row1 + 1, col1 - 1);

			} else if (row1 < row2 && col1 < col2) {
				remove(row1 + 1, col1 + 1);

			} else if (row1 > row2 && col1 > col2) {
				remove(row1 - 1, col1 - 1);

			} else if (row1 > row2 && col1 < col2) {
				remove(row1 - 1, col1 + 1);
			}

		//increments the turn count
		turnCount++;
	}

	/*******************************************************************
	 * Undoes the previous move.
	 ******************************************************************/

	public void undo() {
		//Does nothing if the stacks are empty
		if (!pieceList.isEmpty()) {

			/*Takes two things off of each stack. The piece data and coords of
			 the tile you moved from AND to.
			 */
			Chip tile1 = pieceList.pop();
			Chip tile2 = pieceList.pop();
			Point coords1 = coordsList.pop();
			Point coords2 = coordsList.pop();

//			/* If the pieces move count decrements to below where it was set to
//			be a king, it's reverted back to a regular chip.
//			 */
//
//			tile1.setMoveCount(tile1.getMoveCount() - 1);
//			if (tile1.getMoveCount() > tile1.getKingCount()){
//				tile1.setKing(false);
//			}

			/* If the chip is a "null chip", it adds the chip back to that space,
			else it removes the piece at that location.
			 */

			if (tile2.getColor() != 0) {
				add(coords2.x, coords2.y, tile2.getColor(), tile2.getKing());
			} else {
				remove(coords2.x, coords2.y);
			}
			if (tile1.getColor() != 0) {
				add(coords1.x, coords1.y, tile1.getColor(), tile1.getKing());
			} else {
				remove(coords1.x, coords1.y);
			}

			/* This part simply figures out which tile to add the missing piece to
			if the previous move was a capture.
			 */

			if (Math.abs(coords1.x - coords2.x) == 2) {
				if (coords1.x < coords2.x && coords1.y > coords2.y) {
					add(coords1.x + 1, coords1.y - 1, -tile1.getColor(), tile1.getKing());

				} else if (coords1.x < coords2.x && coords1.y < coords2.y) {
					add(coords1.x + 1, coords1.y + 1, -tile1.getColor(), tile1.getKing());

				} else if (coords1.x > coords2.x && coords1.y > coords2.y) {
					add(coords1.x - 1, coords1.y - 1, -tile1.getColor(), tile1.getKing());

				} else if (coords1.x > coords2.x && coords1.y < coords2.y) {
					add(coords1.x - 1, coords1.y + 1, -tile1.getColor(), tile1.getKing());
				}


			}
			turnCount--;
		}
	}
}