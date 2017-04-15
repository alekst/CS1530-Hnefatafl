import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class GenericColor extends JRadioButtonMenuItem 
{
	
	private Board _b;
	
	/**
	* Constructor
	* @param b-Board that this GenericColor will be on
	* 			need this to change the colors of the board
	*/
	public GenericColor(Board b) 
	{
		super("Generic");
		_b = b;
		addActionListener(new GenericColorListener());
	}

	class GenericColorListener implements ActionListener 
	{
		/**
		* Resets the frame and begins a new game
		* @param e-The action event
		*/
		public void actionPerformed(ActionEvent e) 
		{
			_b.setTileColors("generic");
		}
	}
}
