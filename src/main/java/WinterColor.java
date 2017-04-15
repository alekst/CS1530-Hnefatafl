import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class WinterColor extends JRadioButtonMenuItem 
{
	
	private Board _b;
	
	/**
	* Constructor
	* @param b-Board that this GenericColor will be on
	* 			need this to change the colors of the board
	*/
	public WinterColor(Board b) 
	{
		super("Winter");
		_b = b;
		addActionListener(new WinterColorListener());
	}

	class WinterColorListener implements ActionListener 
	{
		/**
		* Resets the frame and begins a new game
		* @param e-The action event
		*/
		public void actionPerformed(ActionEvent e) 
		{
			_b.setTileColors("winter");
		}
	}
}
