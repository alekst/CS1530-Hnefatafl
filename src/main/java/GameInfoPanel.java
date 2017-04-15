// adapted from Bill Laboon's ButtonPanel.java from Game of Life: https://github.com/laboon/GameOfLife/blob/master/ButtonPanel.java
// TO DO: add button panel to main frame
import java.awt.*;
import javax.swing.*;

public class GameInfoPanel extends JPanel 
{	

GenericColor generic;
AutumnColor autumn;
WinterColor winter;
SpringColor spring;
SummerColor summer;
private PlayerInfoPanel _playerInfo;
private PlayerInfoPanel _otherInfo;		
	

/*
*	Create the game info panel for the main frame of the game. The panel contains individual Player information panels.
*/
  public GameInfoPanel(PlayerInfoPanel playerInfo, PlayerInfoPanel otherInfo, Board b) 
	{
		generic = new GenericColor(b);
		generic.setSelected(true);
		autumn = new AutumnColor(b);
		winter = new WinterColor(b);
		spring = new SpringColor(b);
		summer = new SummerColor(b);
		
		ButtonGroup g = new ButtonGroup();
		g.add(generic);
		g.add(autumn);
		g.add(winter);
		g.add(spring);
		g.add(summer);
		
		JMenu tileColorMenu = new JMenu("Tile Color Options");
		JMenuBar tileColorMenuBar = new JMenuBar();
		
		tileColorMenu.add(generic);
		tileColorMenu.add(autumn);
		tileColorMenu.add(winter);
		tileColorMenu.add(spring);
		tileColorMenu.add(summer);
		
		tileColorMenuBar.add(tileColorMenu);
		
		_playerInfo = playerInfo;
		_otherInfo = otherInfo;
		
		setPreferredSize(new Dimension(200, 250));
		setFont(new Font(null, Font.BOLD, 200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(playerInfo);
		add(otherInfo);
		add(tileColorMenuBar);
	}
	
	public JRadioButtonMenuItem getRadioButton(String s)
	{
		if(s.equals("Autumn"))
		{
			return autumn;
		}
		else
		{
			return generic;
		}
	}

}

