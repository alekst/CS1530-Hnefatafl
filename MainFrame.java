// adapted from Bill Laboon's ButtonPanel.java from Game of Life: https://github.com/laboon/GameOfLife/blob/master/ButtonPanel.java
import java.awt.*;
import javax.swing.*;

public class MainFrame 
{
    private final int HEIGHT = 800;
    private final int WIDTH = 1000;
    
    private JFrame _frame = new JFrame("Hnefatafl ");

    private MainPanel _mainPanel; //actual board game

    private ButtonPanel _buttonPanel; //button panel
    
    public MainFrame() 
    {
		_frame.setSize(WIDTH, HEIGHT);
		// Close program when window is closed
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Add Main Panel and Button Panel
		 
		_mainPanel = new MainPanel();// adds cells to the main frame

		_buttonPanel = new ButtonPanel(_mainPanel);
		
		_frame.add(_mainPanel, BorderLayout.NORTH); 
		_frame.add(_buttonPanel, BorderLayout.SOUTH);
		
		_frame.setVisible(true);	
    }
}
