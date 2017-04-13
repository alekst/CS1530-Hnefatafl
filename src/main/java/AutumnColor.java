import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class AutumnColor extends JRadioButtonMenuItem 
{
	
	private Board _b;
	
	/**
	* Constructor
	* @param b-Board that this AutumnColor will be on
	* 			need this to change the colors of the board
	*/
	public AutumnColor(Board b) 
	{
		super("Autumn");
		_b = b;
		addActionListener(new AutumnColorListener());
	}

	class AutumnColorListener implements ActionListener 
	{
		/**
		* Resets the frame and begins a new game
		* @param e-The action event
		*/
		public void actionPerformed(ActionEvent e) 
		{
			_b.setTileColors("autumn");
		}
	}
}
