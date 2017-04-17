import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class NewGameButton extends JButton 
{
	
	private Game _m;
	private MainFrame _f;
	
	/**
	* Constructor
	* @param m-Game that this button exists in
	* @param f-MainFrame that this button exists in
	*/
	public NewGameButton(Game m, MainFrame f) 
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
			_m.stopTimers();
			_f.resetFrame();
			Game newGame = new Game();
		}
	}
}
