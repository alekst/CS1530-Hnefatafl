import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class LoadButton extends JButton 
{

	private Board _m;
	
	/**
	* Constructor...Button is currently set to disabled
	* @param m-Board that this button exists in
	*/
	public LoadButton(Board m) 
	{
		super("Load");
		_m = m;
		addActionListener(new LoadButtonListener());
		this.setEnabled(false);
	}

	class LoadButtonListener implements ActionListener 
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
