// adapted from Bill Laboon's ButtonPanel.java from Game of Life: https://github.com/laboon/GameOfLife/blob/master/ButtonPanel.java
// TO DO: add button panel to main frame
import java.awt.*;
import javax.swing.*;

public class GameInfoPanel extends JPanel 
{
	private PlayerInfoPanel _playerInfo;
	private PlayerInfoPanel _otherInfo;		
/*
*	Create the game info panel for the main frame of the game. The panel contains individual Player information panels.
*/
  public GameInfoPanel(PlayerInfoPanel playerInfo, PlayerInfoPanel otherInfo) 
	{
		_playerInfo = playerInfo;
		_otherInfo = otherInfo;
		setPreferredSize(new Dimension(200, 250));
		setFont(new Font(null, Font.BOLD, 200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(playerInfo);
		add(otherInfo);
	}


}

