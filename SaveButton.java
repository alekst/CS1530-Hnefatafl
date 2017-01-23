// TO DO: 
// Ask if we can use SafeSave and other functionality from Laboon's GoL FileAccess obj

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class SaveButton extends JButton 
{

  private MainPanel _m;
    
  public SaveButton(MainPanel m) 
  {
    super("Save");
	  _m = m;
    addActionListener(new SaveButtonListener());
  }

  class SaveButtonListener implements ActionListener 
  {
    
	  public void actionPerformed(ActionEvent e) 
    {
	      
	  }
  }      
}
