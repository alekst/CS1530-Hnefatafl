// adapted from Bill Laboon's ButtonPanel.java from Game of Life: https://github.com/laboon/GameOfLife/blob/master/ButtonPanel.java
// TO DO: add button panel to main frame
import java.awt.*;
import javax.swing.*;

public class ButtonPanel extends JPanel 
{
  
  private SaveButton _save;

  private LoadButton _load;
  
  private NewGameButton _newGame;

  private ConcedeButton _concede;
    
  /**
  * Constructor - add all of the buttons to
  * the ButtonPanel.
  */
    
  public ButtonPanel(Board m) 
  {

    // Send a reference to the Main Panel
    // to all of the buttons.
	
	_save = new SaveButton(m);
	_load = new LoadButton(m);
	_newGame = new NewGameButton(m);
	_concede = new ConcedeButton(m);
	setLayout(new FlowLayout());

	  // Add all of the buttons
	  add(_save);
	  add(_load);
	  add(_newGame);
	  add(_concede);
  }
    
}
