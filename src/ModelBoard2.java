import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/***********************************************************************
 * Main GUI for the Checker's Game
 * 
 * @author Jennifer Moon
 * @version V2
 **********************************************************************/
public class ModelBoard2 extends JFrame implements ActionListener{
	
	private JMenuBar menus;
	
	private JMenu chessoptionMenu;
	private JMenu checkoptionMenu;
	
	private JMenuItem resignChess; 
	private JMenuItem newChessGame;
	private JMenuItem resignCheck;
	private JMenuItem newCheckGame;
	private JMenuItem undoCheckers;
	private JMenuItem undoChess; 
	
	private ModelBoard model; 
	
	private CheckersPanel check;
	
	private JTabbedPane tabbedPane;
	
	private JMenu options; 

	/*******************************************************************
	 * Main method used to create the Chess GUI
	 ******************************************************************/
	public static void main (String[] args){
		ModelBoard2 brd = new ModelBoard2();
	}
	
	/*******************************************************************
	 * Creates a new ModelBoard2
	 ******************************************************************/
	public ModelBoard2(){

		this.setTitle("Checkers and Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		model = new ModelBoard();
		check = new CheckersPanel();
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Checkers", null, check, 
        		"Current Checkers game");
		tabbedPane.addTab("Chess", null, model, "Current Chess game");

		this.getContentPane().add(tabbedPane);
		
		this.setSize(780, 850);
		this.setupMenus();
		this.setVisible(true);
	}
	
	/*******************************************************************
	 * Creates the Menus
	 ******************************************************************/
	private void setupMenus(){

		chessoptionMenu = new JMenu("Chess");
		checkoptionMenu = new JMenu("Checkers");

		resignChess = new JMenuItem("Resign Chess");
		newChessGame = new JMenuItem("New Chess Game");
		
		resignChess.addActionListener(this);
		newChessGame.addActionListener(this);

		options = new JMenu("Options");
		
		undoCheckers = new JMenuItem("Undo Last Move");
		undoChess = new JMenuItem("Undo Last Move");
		
		
		chessoptionMenu = new JMenu("Chess");
		checkoptionMenu = new JMenu("Checkers");
		resignChess = new JMenuItem("Resign Chess");
		newChessGame = new JMenuItem("New Chess Game");
		resignCheck = new JMenuItem("Resign Checkers");
		newCheckGame = new JMenuItem("New Checkers Game");
		
		resignChess.addActionListener(this);
		newChessGame.addActionListener(this);
		resignCheck.addActionListener(this);
		newCheckGame.addActionListener(this);
		undoCheckers.addActionListener(this);
		undoChess.addActionListener(this);

		chessoptionMenu.add(resignChess);
		chessoptionMenu.add(newChessGame);
		chessoptionMenu.add(undoChess);
		
		checkoptionMenu.add(resignCheck);
		checkoptionMenu.add(newCheckGame);
		checkoptionMenu.add(undoCheckers);
		
		options.add(chessoptionMenu);
		options.add(checkoptionMenu);
		
		menus = new JMenuBar();
		menus.add(options);
		setJMenuBar(menus);
	}

	/*******************************************************************
	 * Action Listener that handles what button has been pressed, 
	 * specifically, what menu item has been chosen
	 * 
	 * @param //what has been pressed
	 ******************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Chess
		
		if (e.getSource() == undoChess){
			model.chess.undo();
			model.setBackgroundColor();
			model.displayBoard();
		}
		
		if (e.getSource() == resignChess){
			int response = JOptionPane.showConfirmDialog(null, 
				    "Are you sure you would like to resign Chess?",
				    null,
				    JOptionPane.YES_NO_OPTION);
			
			if (response == JOptionPane.YES_OPTION)
				model.disableBoard(false);
		}
		
		if (e.getSource() == newChessGame){
			int response = JOptionPane.showConfirmDialog(null, 
				    "Are you sure you would like to start a new game"
				    + "of Chess?",
				    null,
				    JOptionPane.YES_NO_OPTION);
			
			if (response == JOptionPane.YES_OPTION){
				this.tabbedPane.remove(model);
				this.remove(model);
				model = new ModelBoard();
				this.tabbedPane.insertTab("Chess", null, model, 
						"Current Chess game", 1);
				this.tabbedPane.setSelectedIndex(1);
				this.setVisible(true);
			}
		}
		
		//Checkers
		
		if (e.getSource() == undoCheckers){
			check.checkers.undo();
			check.setBackgroundColor();
			check.displayBoard();
		}
		
		if (e.getSource() == resignCheck){
			int response = JOptionPane.showConfirmDialog(null, 
				    "Are you sure you would like to resign Checkers?",
				    null,
				    JOptionPane.YES_NO_OPTION);
			
			if (response == JOptionPane.YES_OPTION)
				check.disableBoard(false);
		}
		
		if (e.getSource() == newCheckGame){
			int response = JOptionPane.showConfirmDialog(null, 
				    "Are you sure you would like to start a new game"
				    + " of Checkers?",
				    null,
				    JOptionPane.YES_NO_OPTION);

			if (response == JOptionPane.YES_OPTION){
				this.tabbedPane.remove(check);
				this.remove(check);
				check = new CheckersPanel();
				this.tabbedPane.insertTab("Checkers", null, check, 
						"Current Checkers game", 0);
				this.tabbedPane.setSelectedIndex(0);
				this.setVisible(true);
			}
		}
	}
}

