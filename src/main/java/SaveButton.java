// TO DO: 
// Ask if we can use SafeSave and other functionality from Laboon's GoL FileAccess obj

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class SaveButton extends JButton 
{

	private Board _m;

	/**
	* Constructor...Button is currently set to disabled
	* @param m-Board that this button exists in
	*/
	public SaveButton(Board m) 
	{
		super("Save");
		_m = m;
		addActionListener(new SaveButtonListener());
		this.setEnabled(false);
	}

	class SaveButtonListener implements ActionListener 
	{
		/**
		* Loads an existing game
		* @param e-The action event
		*/
		public void actionPerformed(ActionEvent e) 
		{

		}
	}
}
