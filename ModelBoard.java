import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/***********************************************************************
 * Chess board panel 
 * 
 * @author Jennifer Moon
 * @version V2
 **********************************************************************/
public class ModelBoard extends JPanel{

	/**
	 * The Chess Board
	 **/
	public JButton[][] board;

	/**
	 * The Chess Game
	 **/
	public Chess chess;

	/**
	 * The Last Row Selected
	 **/
	public int lrow;

	/**
	 * The Last Column Selected
	 **/
	public int lcolumn;




	ImageIcon white_Bishop = new ImageIcon(((new ImageIcon("ChessWhiteBishop.jpg").getImage()).
		getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH)));

	ImageIcon white_Knight = new ImageIcon(((new ImageIcon("ChessWhiteKnight.jpg").getImage()).
			getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH)));

	ImageIcon white_Pawn = new ImageIcon(((new ImageIcon("ChessWhitePawn.png").getImage()).
			getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH)));

	ImageIcon white_King = new ImageIcon(((new ImageIcon("ChessWhiteKing.jpg").getImage()).
			getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH)));

	ImageIcon white_Queen = new ImageIcon(((new ImageIcon("ChessWhiteQueen.jpg").getImage()).
			getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH)));

	ImageIcon white_Rook = new ImageIcon(((new ImageIcon("ChessWhiteRook.jpg").getImage()).
			getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH)));

	ImageIcon black_Pawn = new ImageIcon(((new ImageIcon("ChessPawnBlack.png").getImage()).
			getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH)));

	ImageIcon black_Bishop = new ImageIcon((new ImageIcon("ChessBlackBishop.jpg").
			getImage().getScaledInstance(90,90, java.awt.Image.SCALE_SMOOTH)));

	ImageIcon black_King = new ImageIcon(((new ImageIcon("ChessBlackKing.jpg").getImage()).
			getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH)));

	ImageIcon black_Queen = new ImageIcon(((new ImageIcon("ChessBlackQueen.jpg").getImage()).
			getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH)));

	ImageIcon black_Knight = new ImageIcon(((new ImageIcon("ChessBlackKnight.jpg").getImage()).
			getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH)));

	ImageIcon black_Rook = new ImageIcon(((new ImageIcon("ChessBlackRook.jpg").getImage()).
			getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH)));


	/*******************************************************************
	 * Constructor to create the board
	 ******************************************************************/
	public ModelBoard() {

		//create the board 
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 8, 5, 5));

		chess = new Chess();
		board = new JButton[8][8];
		ButtonListener listener = new ButtonListener();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new JButton("");
				//board[i][j].setIcon(new ImageIcon("ChessBlackKing.jpg"));
				board[i][j].setPreferredSize(new Dimension(90, 90));
				board[i][j].addActionListener(listener);
				panel.add(board[i][j]);

			}
		}

		displayBoard();
		add(panel, BorderLayout.CENTER);

		//loadPieces();
		setRegColor();

	}

	/*******************************************************************
	 * Displays the location of the pieces
	 ******************************************************************/
	private void displayBoard() {
		CheckersPiece[][] temp = chess.getBoard();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (temp[i][j] != null) {
					if (temp[i][j].getName().equals("King")) {
						if (temp[i][j].getColor() == -1) {
							board[i][j].setIcon(black_King);
						} else
							board[i][j].setIcon(white_King);
					}
					if (temp[i][j].getName().equals("Queen")) {
						if (temp[i][j].getColor() == -1) {
							board[i][j].setIcon(black_Queen);
						} else
							board[i][j].setIcon(white_Queen);
					}
					if (temp[i][j].getName().equals("Pawn")) {
						if (temp[i][j].getColor() == -1) {
							board[i][j].setIcon(black_Pawn);
						} else
							board[i][j].setIcon(white_Pawn);
					}
						if (temp[i][j].getName().equals("Rook")) {
							if (temp[i][j].getColor() == -1) {
								board[i][j].setIcon(black_Rook);
							} else
								board[i][j].setIcon(white_Rook);
						}
							if (temp[i][j].getName().equals("Bishop")) {
								if (temp[i][j].getColor() == -1) {
									board[i][j].setIcon(black_Bishop);
								} else
									board[i][j].setIcon(white_Bishop);
							}
								if (temp[i][j].getName().equals("Knight")) {
									if (temp[i][j].getColor() == -1) {
										board[i][j].setIcon(black_Knight);
									} else
										board[i][j].setIcon(white_Knight);
								}

									//board[i][j].setText(temp[i][j].getName());

								} else {
									board[i][j].setIcon(null);
								}
				System.out.println(board[i][j]);
							}

						}
					}








	/*******************************************************************
	 * Displays the possible moves of the selected piece
	 *
	 * @param spots, the possible spots to move to
	 ******************************************************************/
	private void setAvailable(boolean[][] spots) {
		setRegColor();
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (spots[i][j] == true)
					board[i][j].setBackground(Color.GREEN);
	}

	/*******************************************************************
	 * Disables or enables all pieces of the board
	 *
	 * @param b whether or not the buttons should be enabled or not
	 ******************************************************************/
	public void disableBoard(boolean b) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				board[i][j].setEnabled(b);

	}

	/*******************************************************************
	 * Displays the board's regular colors (black and white)
	 ******************************************************************/
	private void setRegColor() {
		for (int j = 0; j < 8; j = j + 2)
			for (int i = 0; i < 8; i++) {
				if (i % 2 == 0)
					board[i][j].setBackground(Color.GRAY);
				else
					board[i][j].setBackground(Color.WHITE);
			}

		for (int j = 1; j < 8; j = j + 2)
			for (int i = 0; i < 8; i++) {
				if (i % 2 == 1)
					board[i][j].setBackground(Color.GRAY);
				else
					board[i][j].setBackground(Color.WHITE);
			}
	}

	/*******************************************************************
	 * Action Listener that handles what button has been pressed
	 ******************************************************************/
	private class ButtonListener implements ActionListener {

		/***************************************************************
		 * Processes what button has been pressed
		 *
		 * @param e, the ActionEvent performed
		 **************************************************************/
		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < 8; i++)

				for (int j = 0; j < 8; j++) {

					if (board[i][j] == e.getSource()) {

						if (board[i][j].getBackground() != Color.GREEN) {
							setAvailable(chess.getMoves(i, j));
							lrow = i;
							lcolumn = j;

						} else {
							chess.move(lrow, lcolumn, i, j);

							setRegColor();
						}
					}
				}

			displayBoard();
		}
	}

	/***********
	 * LoadPieces loads images into each button that is created, allows for easier tracking of what player is what, and
	 * where the pieces are.
	 ***********/
	public void loadPieces() {

		CheckersPiece[][] temp = chess.getBoard();
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {

				if (temp[i][j] != null) {
					if (temp[i][j].getName().equals("King")) {
						if (temp[i][j].getColor() == -1) {
							board[i][j].setIcon(black_King);
						} else
							board[i][j].setIcon(white_King);
					}
					if (temp[i][j].getName().equals("Queen")) {
						if (temp[i][j].getColor() == -1) {
							board[i][j].setIcon(black_Queen);
						} else
							board[i][j].setIcon(white_Queen);
					}
					if (temp[i][j].getName().equals("Pawn")) {
						if (temp[i][j].getColor() == -1) {
							board[i][j].setIcon(black_Pawn);
						} else
							board[i][j].setIcon(white_Pawn);
					if (temp[i][j].getName().equals("Rook")) {
							if (temp[i][j].getColor() == -1) {
								board[i][j].setIcon(black_Rook);
							} else
								board[i][j].setIcon(white_Rook);
					if (temp[i][j].getName().equals("Bishop")) {
								if (temp[i][j].getColor() == -1) {
									board[i][j].setIcon(black_Bishop);
								} else
									board[i][j].setIcon(white_Bishop);
					if (temp[i][j].getName().equals("Knight")) {
									if (temp[i][j].getColor() == -1) {
										board[i][j].setIcon(black_Knight);
									} else
										board[i][j].setIcon(white_Knight);


								}

							}
						}
//						board[0][0].setIcon(white_Rook);
//						board[0][1].setIcon(white_Knight);
//						board[0][2].setIcon(white_Bishop);
//						board[0][3].setIcon(white_Queen);
//						board[0][4].setIcon(white_King);
//						board[0][5].setIcon(white_Bishop);
//						board[0][6].setIcon(white_Knight);
//						board[0][7].setIcon(white_Rook);
//
//						board[1][0].setIcon(white_Pawn);
//						board[1][1].setIcon(white_Pawn);
//						board[1][2].setIcon(white_Pawn);
//						board[1][3].setIcon(white_Pawn);
//						board[1][4].setIcon(white_Pawn);
//						board[1][5].setIcon(white_Pawn);
//						board[1][6].setIcon(white_Pawn);
//						board[1][7].setIcon(white_Pawn);
//
//						board[6][0].setIcon(black_Pawn);
//						board[6][1].setIcon(black_Pawn);
//						board[6][2].setIcon(black_Pawn);
//						board[6][3].setIcon(black_Pawn);
//						board[6][4].setIcon(black_Pawn);
//						board[6][5].setIcon(black_Pawn);
//						board[6][6].setIcon(black_Pawn);
//						board[6][7].setIcon(black_Pawn);
//
//						board[7][0].setIcon(black_Rook);
//						board[7][1].setIcon(black_Knight);
//						board[7][2].setIcon(black_Bishop);
//						board[7][3].setIcon(black_Queen);
//						board[7][4].setIcon(black_King);
//						board[7][5].setIcon(black_Bishop);
//						board[7][6].setIcon(black_Knight);
//						board[7][7].setIcon(black_Rook);
					}
				}
			}
		}
	}
}
