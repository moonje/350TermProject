import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
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
	private ModelBoard model; 

	public static void main (String[] args){
		ModelBoard2 brd = new ModelBoard2();
		brd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//ModelBoard model = new ModelBoard();
		//frame.getContentPane().add(model);
		//frame.setVisible(true);
		//frame.setSize(780, 805);

	}
	
	ModelBoard2(){
		this.setTitle("Checkers and Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		model = new ModelBoard();
		this.getContentPane().add(model);
		this.setVisible(true);
		this.setSize(780, 825);
		this.setupMenus();
	}
	
	private void setupMenus(){
		optionMenu = new JMenu("Options");
		resign = new JMenuItem("Resign");
		
		resign.addActionListener(this);
		optionMenu.add(resign);
		menus = new JMenuBar();
		menus.add(optionMenu);
		setJMenuBar(menus);
	}

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
		
	}



}
