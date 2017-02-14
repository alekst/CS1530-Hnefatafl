import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class NewGameButton extends JButton 
{
	
	private Board _m;
	private MainFrame _f;
	
	public NewGameButton(Board m, MainFrame f) 
	{
		super("New Game");
		_m = m;
		_f = f;
		addActionListener(new NewGameButtonListener());
	}

	class NewGameButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			_f.resetFrame();
			new MainFrame();
		}
	}
}
