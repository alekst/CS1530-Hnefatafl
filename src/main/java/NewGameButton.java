import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class NewGameButton extends JButton 
{
	
	private Board _m;
	
	public NewGameButton(Board m) 
	{
		super("New Game");
		_m = m;
		addActionListener(new NewGameButtonListener());
	}

	class NewGameButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{

		}
	}
}
