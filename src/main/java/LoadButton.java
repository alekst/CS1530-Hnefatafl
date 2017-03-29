import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class LoadButton extends JButton 
{

	private Game _m;
	
	/**
	* Constructor creates general button in button panel labeled "Load"
	* @param m-Game that this button exists in
	*/
	public LoadButton(Game m) 
	{
		super("Load");
		_m = m;
		addActionListener(new LoadButtonListener());
	}

	class LoadButtonListener implements ActionListener 
	{
		/**
		* Loads an existing game from the designated csv file onto the game board
		* @param e-The action event
		*/
		public void actionPerformed(ActionEvent e)
		{
			try {
				_m.load();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Error: There was a problem loading this game.");
			}
		}
	}
}
