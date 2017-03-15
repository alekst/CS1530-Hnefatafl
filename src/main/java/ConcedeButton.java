import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ConcedeButton extends JButton 
{

  private Board _m;
    
	/**
	* Constructor
	* @param m-Board that this button exists in
	*/
  public ConcedeButton(Board m) 
  {
    super("Concede");
	  _m = m;
	  addActionListener(new ConcedeButtonListener());
    this.setEnabled(false);
  }

  class ConcedeButtonListener implements ActionListener 
  {
    /**
	* Player who clicked the concede button concedes the game
	* @param e-The action event
	*/
    public void actionPerformed(ActionEvent e) 
    {
      
	  }
    
  }      
}
