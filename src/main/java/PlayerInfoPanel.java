// this class encapsulates the player information panel for the game info panel.
import java.awt.*;
import javax.swing.*;



public class PlayerInfoPanel extends JPanel
{
	
	public Player player;
	public JLabel timerLabel;
	public JLabel name;
	
	public PlayerInfoPanel()
	{
		//empty
	}
	
	public PlayerInfoPanel(Player player)
	{
		player = player;
		int time = player.getTimer();
		timerLabel = new JLabel(Integer.toString(time));
		name = new JLabel(player.getName());
		setPreferredSize(new Dimension(100, 250));
		setLayout(new FlowLayout());
		add(name);
		add(timerLabel);
	}
			
	
}