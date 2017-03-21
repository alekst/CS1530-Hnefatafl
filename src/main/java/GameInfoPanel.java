// adapted from Bill Laboon's ButtonPanel.java from Game of Life: https://github.com/laboon/GameOfLife/blob/master/ButtonPanel.java
// TO DO: add button panel to main frame
import java.awt.*;
import javax.swing.*;

public class GameInfoPanel extends JPanel 
{
	
    private PlayerInfoPanel _player;
    private PlayerInfoPanel _other;
	
/*
*	Create the game info panel for the main frame of the game. 
*/

  public GameInfoPanel(Player player, Player other) 
	{
 
		_player = new PlayerInfoPanel(player);
		_other = new PlayerInfoPanel(other);

		setPreferredSize(new Dimension(200, 250));
		setFont(new Font(null, Font.BOLD, 200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(_player);
		add(_other);
	}
}

