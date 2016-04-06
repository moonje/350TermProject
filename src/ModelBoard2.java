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
	private JMenuItem resignChess; 
	private JMenuItem newChessGame;
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
		
		this.setSize(780, 825);
		this.setupMenus();
		this.setVisible(true);
	}
	
	/*******************************************************************
	 * Creates the Menus
	 ******************************************************************/
	private void setupMenus(){
		optionMenu = new JMenu("Options");
		resignChess = new JMenuItem("Resign Chess");
		newChessGame = new JMenuItem("New Chess Game");
		
		resignChess.addActionListener(this);
		newChessGame.addActionListener(this);
		
		optionMenu.add(resignChess);
		optionMenu.add(newChessGame);
		
		menus = new JMenuBar();
		menus.add(optionMenu);
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
				this.remove(model);
				model = new ModelBoard();
				this.getContentPane().add(model);
				this.setVisible(true);
			}
		}
	}
}

