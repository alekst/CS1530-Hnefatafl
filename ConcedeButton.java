import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ConcedeButton extends JButton 
{

  private Board _m;
    
  public ConcedeButton(Board m) 
  {
    super("Concede");
	  _m = m;
	  addActionListener(new ConcedeButtonListener());
  }

  class ConcedeButtonListener implements ActionListener 
  {
    
    public void actionPerformed(ActionEvent e) 
    {
      
	  }
    
  }      
}
