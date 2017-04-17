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
    this.setEnabled(true);
  }

  class ConcedeButtonListener implements ActionListener 
  {
    /**
	* Player who clicked the concede button concedes the game
	* @param e-The action event
	*/
    public void actionPerformed(ActionEvent e) 
    {
      String s;
      _m.stopTimers();
      if (_m.getTurn() == 0)
      {
        s = "Game conceded by white. Black wins!";
      } else 
      {
        s = "Game conceded by black. White wins!";
      }
      _m.queryBoard().showMsg(s);
      _m.queryBoard().end();
	  }
    
  }      
}
