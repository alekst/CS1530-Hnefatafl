import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class LoadButton extends JButton 
{

	private Board _m;
	
	public LoadButton(Board m) 
	{
		super("Load");
		_m = m;
		addActionListener(new LoadButtonListener());
		this.setEnabled(false);
	}

	class LoadButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			
		}
	}
}
