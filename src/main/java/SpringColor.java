import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class SpringColor extends JRadioButtonMenuItem 
{
	
	private Board _b;
	
	/**
	* Constructor
	* @param b-Board that this SpringColor will be on
	* 			need this to change the colors of the board
	*/
	public SpringColor(Board b) 
	{
		super("Spring");
		_b = b;
		addActionListener(new SpringColorListener());
	}

	class SpringColorListener implements ActionListener 
	{
		/**
		* Resets the frame and begins a new game
		* @param e-The action event
		*/
		public void actionPerformed(ActionEvent e) 
		{
			_b.setTileColors("spring");
		}
	}
}
