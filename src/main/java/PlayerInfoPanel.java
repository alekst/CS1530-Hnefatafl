// this class encapsulates the player information panel for the game info panel.
import java.awt.*;
import javax.swing.*;



public class PlayerInfoPanel extends JPanel
{
	
	public JLabel timerLabel;
	public JLabel nameLabel;
	public TimerListener listener;
	public Timer timer;
	public int countdown;
	public String name;
	
	public PlayerInfoPanel()
	{
		timerLabel = new JLabel(Integer.toString(countdown));
		
		add(timerLabel);
		listener = new TimerListener(timerLabel);
		timer = new Timer(1000, listener);
		
		nameLabel = new JLabel(name);
		setPreferredSize(new Dimension(100, 250));
		setLayout(new FlowLayout());
		add(nameLabel);
		add(timerLabel);
	}
	
	public void startTimer()
	{
		timer.start();
	}
	
			
	
}