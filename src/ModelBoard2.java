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
	private JMenu optionMenu;
	private JMenu chessMenu;
	private JMenu checkersMenu;
	private JMenuItem resignChess;
	private JMenuItem newChessGame;
	private JMenuItem resignCheckers;
	private JMenuItem newCheckersGame;
	private ModelBoard model; 
	private CheckersPanel check; 
	private JPanel panel;
	private JTabbedPane tabbedPane;

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
		//panel = new JPanel();
        tabbedPane = new JTabbedPane();

		tabbedPane.addTab("Chess", null, model, "Current Chess game");
        tabbedPane.addTab("Checkers", null, check, "Current Checkers game");

		this.getContentPane().add(tabbedPane);
		
		this.setSize(780, 850);
		this.setupMenus();
		this.setVisible(true);
	}
	
	/*******************************************************************
	 * Creates the Menus
	 ******************************************************************/
	private void setupMenus(){
		optionMenu = new JMenu("Options");

		chessMenu = new JMenu("Chess");
		checkersMenu = new JMenu("Checkers");

		resignChess = new JMenuItem("Resign Chess");
		newChessGame = new JMenuItem("New Chess Game");
		resignCheckers = new JMenuItem("Resign Checkers");
		newCheckersGame = new JMenuItem("New Checkers Game");
		
		resignChess.addActionListener(this);
		newChessGame.addActionListener(this);
		resignCheckers.addActionListener(this);
		newCheckersGame.addActionListener(this);

		optionMenu.add(chessMenu);
		chessMenu.add(resignChess);
		chessMenu.add(newChessGame);
		optionMenu.add(checkersMenu);
		checkersMenu.add(resignCheckers);
		checkersMenu.add(newCheckersGame);
		
		menus = new JMenuBar();
		menus.add(optionMenu);
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
		
		if (e.getSource() == resignChess){
			int response = JOptionPane.showConfirmDialog(null, 
				    "Are you sure you would like to resign?",
				    null,
				    JOptionPane.YES_NO_OPTION);
			
			if (response == JOptionPane.YES_OPTION)
				model.disableBoard(false);
		}
		
		if (e.getSource() == newChessGame){
			int response = JOptionPane.showConfirmDialog(null, 
				    "Are you sure you would like to start a new game?",
				    null,
				    JOptionPane.YES_NO_OPTION);
			
			if (response == JOptionPane.YES_OPTION){
				this.tabbedPane.remove(model);
				this.remove(model);
				model = new ModelBoard();
				this.tabbedPane.insertTab("Chess", null, model, "Current Chess game", 0);
				this.tabbedPane.setSelectedIndex(0);
				this.setVisible(true);
			}
		}
		if (e.getSource() == resignCheckers){
			int response = JOptionPane.showConfirmDialog(null,
					"Are you sure you would like to resign?",
					null,
					JOptionPane.YES_NO_OPTION);

			if (response == JOptionPane.YES_OPTION)
				check.disableBoard(false);
		}
		if (e.getSource() == newCheckersGame){
			int response = JOptionPane.showConfirmDialog(null,
					"Are you sure you would like to start a new game?",
					null,
					JOptionPane.YES_NO_OPTION);

			if (response == JOptionPane.YES_OPTION){
				this.tabbedPane.remove(check);
				this.remove(check);
				check = new CheckersPanel();
				this.tabbedPane.insertTab("Checkers", null, check, "Current Checkers game", 1);
				this.tabbedPane.setSelectedIndex(1);
				this.setVisible(true);
			}
		}
	}
}

