import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ConcedeButton extends JButton 
{

  private Game _m;
    
	/**
	* Constructor creates general button in button panel labeled "Concede"
	* @param m-Game that this button exists in
	*/
  public ConcedeButton(Game m) 
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
