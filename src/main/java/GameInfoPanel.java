// adapted from Bill Laboon's ButtonPanel.java from Game of Life: https://github.com/laboon/GameOfLife/blob/master/ButtonPanel.java
// TO DO: add button panel to main frame
import java.awt.*;
import javax.swing.*;

public class GameInfoPanel extends JPanel 
{
	
    private PlayerInfoPanel _playerInfo;
    private PlayerInfoPanel _otherInfo;
	private JLabel label1, label2;
	private TimerListener _listener, _listener2;
	
	
/*
*	Create the game info panel for the main frame of the game. 
*/

  public GameInfoPanel(PlayerInfoPanel playerInfo, PlayerInfoPanel otherInfo) 
	{

	

		
		
		//int time = player.getTimer();
		//label1 = new JLabel(Integer.toString(time));
		
		//int time2 = other.getTimer();
		//label2 = new JLabel(Integer.toString(time2));
		
		//add(label1);
		//add(label2);
		
		//_listener = new TimerListener(label1);
		//_listener2 = new TimerListener(label2);
				
 	   	//Timer t1 = new Timer(1000, _listener);
		//Timer t2 = new Timer(1000, _listener2);
		
		// _playerInfo = new PlayerInfoPanel(player);
		// _otherInfo = new PlayerInfoPanel(other);
		
		//JButton button = new JButton("End turn");
		//button.addActionListener(new EndButtonListener(_playerInfo.timer, _otherInfo.timer)); 
		
		//
		//_playerInfo.startTimer();
		//t1.start();
		
		setPreferredSize(new Dimension(200, 250));
		setFont(new Font(null, Font.BOLD, 200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(playerInfo);
		//add(button);
		add(otherInfo);
	}

}

