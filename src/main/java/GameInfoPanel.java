// adapted from Bill Laboon's ButtonPanel.java from Game of Life: https://github.com/laboon/GameOfLife/blob/master/ButtonPanel.java
// TO DO: add button panel to main frame
import java.awt.*;
import javax.swing.*;

public class GameInfoPanel extends JPanel 
{
	
	private Player _player;
	private Player _other;
	
/*
*	Create the game info panel for the main frame of the game. 
*/ 

    
	public GameInfoPanel(Board b) 
	{
		// gets the players from the board
		Player[] players = b.getPlayers(); // assumes there are only two players to this game. 
		_player = players[0];
		_other = players[1];

		JLabel playerLabel, otherLabel, playerTimer, otherTimer;
		
		playerLabel = new JLabel(_player.getName());
		playerLabel.setPreferredSize(new Dimension(20, 150));
		playerTimer = new JLabel("00:00");
		
		otherLabel = new JLabel(_other.getName());
		otherTimer = new JLabel("00:00");
		
		
		
		setPreferredSize(new Dimension(200, 250));
		setFont(new Font(null, Font.BOLD, 200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		add(Box.createRigidArea(new Dimension(5, 100)));	
		add(playerLabel);
		add(Box.createRigidArea(new Dimension(5, 10)));
		add(playerTimer);
		add(Box.createRigidArea(new Dimension(5, 250)));
		add(otherLabel);
		add(Box.createRigidArea(new Dimension(5, 10)));
		add(otherTimer);
		
	
	}
}