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
	private JMenuItem newCheckGame; 
	private JMenuItem resignCheck; 
	private ModelBoard model; 
	private CheckersPanel check; 
	private JPanel panel; 

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
        JTabbedPane tabbedPane = new JTabbedPane();

        //READ ME: Took out the border layout stuff and separated the two UI's into tabs -JZ

		//panel.setLayout(new BorderLayout());
		
		//panel.add(model, BorderLayout.WEST);
		//panel.add(check, BorderLayout.EAST);

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
		
		chessoptionMenu.add(resignChess);
		chessoptionMenu.add(newChessGame);
		
		checkoptionMenu.add(resignCheck);
		checkoptionMenu.add(newCheckGame);
		
		menus = new JMenuBar();
		menus.add(chessoptionMenu);
		menus.add(checkoptionMenu);
		setJMenuBar(menus);
	}

	/*******************************************************************
	 * Action Listener that handles what button has been pressed, 
	 * specifically, what menu item has been chosen
	 * 
	 * @param what has been pressed 
	 ******************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Chess
		
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
				this.remove(model);
				model = new ModelBoard();
				this.getContentPane().add(model);
				this.setVisible(true);
			}
		}
		
		//Checkers
		
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
				this.remove(check);
				check = new CheckersPanel();
				this.getContentPane().add(check);
				this.setVisible(true);
			}
		}
	}
}

