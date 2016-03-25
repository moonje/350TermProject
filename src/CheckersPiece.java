/***********************************************************************
 * Abstract class with methods that each piece will need
 * 
 * @author Jennifer Moon
 * @version V2
 **********************************************************************/
abstract class CheckersPiece {
	
	abstract boolean[][] getMoves(int row, int col, 
			CheckersPiece[][] board);
	
	abstract int getColor();
	
	abstract String getName();
}
