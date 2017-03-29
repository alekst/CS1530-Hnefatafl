// TO DO: 
// Ask if we can use SafeSave and other functionality from Laboon's GoL FileAccess obj

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class SaveButton extends JButton 
{

	private Game _m;

	/**
	* Constructor creates general button in button panel labeled "Save"
	* @param m-Game that this button exists in
	*/
	public SaveButton(Game m) 
	{
		super("Save");
		_m = m;
		addActionListener(new SaveButtonListener());
		//this.setEnabled(false);
	}

	
	class SaveButtonListener implements ActionListener 
	{
		/**
		* Saves an existing board data to a csv file by calling the game's void save method
		* @param e-The action event
		*/
		public void actionPerformed(ActionEvent e)
		{
			try {
				_m.save();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Error: There was a problem saving this game.");
			}
		}
	}
}
