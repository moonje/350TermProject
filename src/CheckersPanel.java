import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CheckersPanel extends JPanel {
	/** The Ch Board **/
	public JButton[][] board;
	
	/** The Checkers Game **/
	public CheckersLogic checkers; 
	
	/** The Last Row Selected **/
	public int lrow; 
	
	/** The Last Column Selected **/
	public int lcolumn; 
	
	/** Checkers Piece Images **/
	ImageIcon blackchip; 
	ImageIcon redchip; 
	ImageIcon blackking;
	ImageIcon redking; 
	
	/** The Panel with Buttons **/
	JPanel panel; 

	/*******************************************************************
	 * Constructor to create the board 
	 ******************************************************************/
	public CheckersPanel() {
		
		Image bchip = null; 
		Image bkchip = null; 
		Image rchip = null; 
		Image rkchip = null; 
		
		try {
			bchip = ImageIO.read(
					getClass().getResource("BlackChip.png"));
			bkchip = ImageIO.read(
					getClass().getResource("BlackChipKing.png"));
			
			rchip = ImageIO.read(
					getClass().getResource("RedChip.png"));
			rkchip = ImageIO.read(
					getClass().getResource("RedChipKing.png"));
			
		} catch (Exception e) {
			System.out.print("One or more picture not loaded");
		}
		
		bchip = bchip.getScaledInstance(80, 80, java.awt.Image.
				SCALE_SMOOTH);
		blackchip = new ImageIcon(bchip);
		
		bkchip = bkchip.getScaledInstance(80, 80, java.awt.Image.
				SCALE_SMOOTH);
		blackking = new ImageIcon(bkchip);
		
		rchip = rchip.getScaledInstance(80, 80, java.awt.Image.
				SCALE_SMOOTH);
		redchip = new ImageIcon(rchip);
		
		rkchip = rkchip.getScaledInstance(80, 80, java.awt.Image.
				SCALE_SMOOTH);
		redking = new ImageIcon(rkchip);
		
		//create the board 
		/*JPanel*/ panel = new JPanel();
		panel.setLayout(new GridLayout(8,8,5,5));
		
		checkers = new CheckersLogic();
		board = new JButton[8][8];
		ButtonListener listener = new ButtonListener();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new JButton("");
				board[i][j].setPreferredSize(new Dimension(90, 90));
				board[i][j].addActionListener(listener);
				panel.add(board[i][j]);

			}
		}
		
		add(panel, BorderLayout.CENTER);
		displayBoard();
		setRegColor();
		setBackgroundColor();
	}
	
	/*******************************************************************
	 * Displays the location of the pieces 
	 ******************************************************************/
	public void displayBoard() {
		CheckersPiece[][] temp = checkers.getBoard();
		
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				
				if (temp[i][j] != null){
					
					if (temp[i][j].getColor() == -1 && 
							!((Chip) temp[i][j]).getKing()){
						board[i][j].setIcon(redchip);
					} else if (temp[i][j].getColor() == -1 &&
							((Chip) temp[i][j]).getKing()){
						board[i][j].setIcon(redking);
					} else if (temp[i][j].getColor() == 1 &&
							((Chip) temp[i][j]).getKing()){
						board[i][j].setIcon(blackking);
					} else if (temp[i][j].getColor() == 1 &&
							!((Chip) temp[i][j]).getKing()){
						board[i][j].setIcon(blackchip);
					}
					
					
				} else 
					board[i][j].setIcon(null);
	}
	
	/*******************************************************************
	 * Displays the possible moves of the selected piece 
	 * 
	 * @param spots, the possible spots to move to 
	 ******************************************************************/
	private void setAvailable(boolean[][] spots) {
		
		setRegColor();
		for (int i = 0; i < 8; i ++)
			for (int j = 0; j < 8; j++) 
				if (spots [i][j] == true) 
					board[i][j].setBackground(Color.GREEN);
	}
	
	/*******************************************************************
	 * Disables or enables all pieces of the board 
	 * 
	 * @param b whether or not the buttons should be enabled or not 
	 ******************************************************************/
	public void disableBoard(boolean b){
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j ++)
				board[i][j].setEnabled(b);
					
	}
	
	/*******************************************************************
	 * Displays the board's regular colors (black and white) 
	 ******************************************************************/
	private void setRegColor(){
		
		for (int j = 0; j < 8; j = j + 2)
			for (int i = 0; i < 8; i ++){
				if (i % 2 == 0)
					board[i][j].setBackground(Color.DARK_GRAY);
				else 
					board[i][j].setBackground(Color.WHITE);
			}
		
		for (int j = 1; j < 8; j = j + 2)
			for (int i = 0; i < 8; i ++){
				if (i % 2 == 1)
					board[i][j].setBackground(Color.DARK_GRAY);
				else 
					board[i][j].setBackground(Color.WHITE);
			}
	}

	/*******************************************************************
	 * Sets the background color to correspond to whose turn it is.
	 ******************************************************************/
	public void setBackgroundColor(){
		if (checkers.turnCount % 2 == 0)
			panel.setBackground(Color.RED);
		else
			panel.setBackground(Color.BLACK);
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
						
						if (board[i][j].getBackground() != Color.GREEN){
							setAvailable(checkers.getMoves(i, j));
							lrow = i; 
							lcolumn = j;
							
						} else {
							checkers.move(lrow, lcolumn, i, j);
							setRegColor();
						}	
					}
				}

			if (checkers.redWin() == true){
				//displayBoard();
				disableBoard(false);
				JOptionPane.showMessageDialog(null, "RED WINS!");
			} else if (checkers.blackWin() == true){
				//displayBoard();
				disableBoard(false);
				JOptionPane.showMessageDialog(null, "BLACK WINS!");
			}
			setBackgroundColor();
			displayBoard();
		}
	}
}