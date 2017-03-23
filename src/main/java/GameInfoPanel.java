// adapted from Bill Laboon's ButtonPanel.java from Game of Life: https://github.com/laboon/GameOfLife/blob/master/ButtonPanel.java
// TO DO: add button panel to main frame
import java.awt.*;
import javax.swing.*;

public class GameInfoPanel extends JPanel 
{
	
    private PlayerInfoPanel _player;
    private PlayerInfoPanel _other;
	private TimerListener _listener;
	
	
/*
*	Create the game info panel for the main frame of the game. 
*/

  public GameInfoPanel(Player player, Player other) 
	{
		_listener = new TimerListener(player);
 	   	 //Timer t1 = new Timer(1000, _listener);
		 Timer t2 = new Timer(1000, _listener);
		_player = new ActivePlayerInfoPanel(player);
		_other = new PassivePlayerInfoPanel(other);
		
		_player.addActiveTimer();
		_other.stopTimer(t2);
		
		setPreferredSize(new Dimension(200, 250));
		setFont(new Font(null, Font.BOLD, 200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(_player);
		add(_other);
	}
	
	
	public void switchActivePlayer()
	{
		 PlayerInfoPanel temp = _player;
		_player = _other;
		_other = temp;
	}
}

