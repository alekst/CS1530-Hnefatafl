import java.awt.*;
import javax.swing.*;

public class PassivePlayerInfoPanel extends PlayerInfoPanel
{

	private Player _player;
	private Timer _timer;
	
    public PassivePlayerInfoPanel(Player player)
    {
		_player = player;
		
    }
	
	public void stopTimer(Timer timer)
	{
		_timer = timer;
		_timer.stop();
	}
}