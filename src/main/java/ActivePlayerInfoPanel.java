// this class encapsulates the player information panel for the game info panel.
import java.awt.*;
import javax.swing.*;

public class ActivePlayerInfoPanel extends PlayerInfoPanel
{
	private TimerListener _listener;
	
    public ActivePlayerInfoPanel(Player player)
    {
		_listener = new TimerListener(player);
	}
	
	public void addActiveTimer()
	{
		Timer t = new Timer(1000, _listener);
		t.start();
	}
}
