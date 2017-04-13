import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class SummerColor extends JRadioButtonMenuItem 
{
	
	private Board _b;
	
	/**
	* Constructor
	* @param b-Board that this SummerColor will be on
	* 			need this to change the colors of the board
	*/
	public SummerColor(Board b) 
	{
		super("Summer");
		_b = b;
		addActionListener(new SummerColorListener());
	}

	class SummerColorListener implements ActionListener 
	{
		/**
		* Resets the frame and begins a new game
		* @param e-The action event
		*/
		public void actionPerformed(ActionEvent e) 
		{
			_b.setTileColors("summer");
		}
	}
}
