import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class NewGameButton extends JButton 
{
	
	private Board _m;
	private MainFrame _f;
	
	/**
	* Constructor
	* @param m-Board that this button exists in
	* @param f-MainFrame that this button exists in
	*/
	public NewGameButton(Board m, MainFrame f) 
	{
		super("New Game");
		_m = m;
		_f = f;
		addActionListener(new NewGameButtonListener());
	}

	class NewGameButtonListener implements ActionListener 
	{
		/**
		* Resets the frame and begins a new game
		* @param e-The action event
		*/
		public void actionPerformed(ActionEvent e) 
		{
			_f.resetFrame();
			new Game();
		}
	}
}
