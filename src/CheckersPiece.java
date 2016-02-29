/***********************************************************************
 * Abstract class with methods that each piece will need
 * 
 * @author Jennifer Moon
 **********************************************************************/
abstract class CheckersPiece {
	
	abstract boolean[][] getMoves(int row, int col, 
			CheckersPiece[][] board);
	
	abstract String getPiece();
	
	abstract int getColor();
}