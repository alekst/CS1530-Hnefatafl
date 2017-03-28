// adapted from Bill Laboon's ButtonPanel.java from Game of Life: https://github.com/laboon/GameOfLife/blob/master/ButtonPanel.java
import java.awt.*;
import javax.swing.*;

public class MainFrame
{
	private final int HEIGHT = 800;
	private final int WIDTH = 1000;

	private JFrame _frame = new JFrame("Hnefatafl ");

	private ButtonPanel _buttonPanel; //button panel
	
	private GameInfoPanel _gameInfo; //game info

  public MainFrame(Board board, GameInfoPanel gameInfo) 
	{
		_frame.setSize(WIDTH, HEIGHT);
		// Close program when window is closed
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Add Main Panel and Button Panel
		_buttonPanel = new ButtonPanel(board, this);
		
		//add the board	to the main frame
		_frame.add(board.getBoard(), BorderLayout.CENTER);
		
		// add the button panel to the main frame
		_frame.add(_buttonPanel, BorderLayout.SOUTH);
		
		// add the game info panel to the main frame
		_frame.add(gameInfo, BorderLayout.EAST);
		
	
		_frame.setVisible(true);	
	}
	
	public void resetFrame()
	{
		this._frame.dispose();
	}
}
