import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JMenuItem resign; 
	private JMenuItem newGame;
	private ModelBoard model; 

	/*******************************************************************
	 * Main method used to create the Chess GUI
	 ******************************************************************/
	public static void main (String[] args){
		ModelBoard2 brd = new ModelBoard2();
		JOptionPane.showConfirmDialog(null, 
			    "This message is for Jacob 3/22",
			    null,
			    JOptionPane.YES_NO_OPTION);
	}
	
	/*******************************************************************
	 * Creates a new ModelBoard2
	 ******************************************************************/
	public ModelBoard2(){
		this.setTitle("Checkers and Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		model = new ModelBoard();
		this.getContentPane().add(model);
		this.setVisible(true);
		this.setSize(780, 825);
		this.setupMenus();
	}
	
	/*******************************************************************
	 * Creates the Menus
	 ******************************************************************/
	private void setupMenus(){
		optionMenu = new JMenu("Options");
		resign = new JMenuItem("Resign");
		newGame = new JMenuItem("New Game");
		
		resign.addActionListener(this);
		newGame.addActionListener(this);
		
		optionMenu.add(resign);
		optionMenu.add(newGame);
		
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
		
		if (e.getSource() == resign){
			int response = JOptionPane.showConfirmDialog(null, 
				    "Are you sure you would like to resign?",
				    null,
				    JOptionPane.YES_NO_OPTION);
			
			if (response == JOptionPane.YES_OPTION)
				model.disableBoard(false);
		}
		
		if (e.getSource() == newGame){
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
