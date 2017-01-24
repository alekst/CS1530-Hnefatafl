import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Board extends JFrame
{
	
	private JButton[][] boardSquares = new JButton[11][11];
	private JPanel board;
	private static final String COLS = "ABCDEFGHIJK";
	
	
	public Board()
	{
		setUpBoard();
		createSquares();
		fillSquares();
	}
	
	
	public final JComponent getBoard()
	{
		return board;
	}
	
	private void setUpBoard()
	{
		board = new JPanel(new GridLayout(0, 12));
		board.setBorder(new CompoundBorder (new EmptyBorder(12, 12, 12, 12), new LineBorder(Color.BLACK)));
		// todo: allow user to change board colors. 
		Color ochre = new Color(204, 119, 34);
		JPanel boardConstrain = new JPanel(new GridBagLayout());
		boardConstrain.setBackground(ochre);
		boardConstrain.add(board);	
	}
	

	
	private void createSquares()
	{
		// create the board squares
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int i = 0; i < boardSquares.length; i++)
		{
			for (int j = 0; j < boardSquares[i].length; j++)
			{
				
				JButton square = new JButton();
				if (( j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0))
				{
					square.setBackground(Color.lightGray);
				}
				else
				{
					square.setBackground(Color.darkGray);
				}
				square.setMargin(buttonMargin);
			    // the square needs to be opaque to use the setBackground method. 
				//https://docs.oracle.com/javase/7/docs/api/javax/swing/JComponent.html#setBackground(java.awt.Color)
				square.setOpaque(true); 
				square.setBorderPainted(false);
				boardSquares[j][i] = square;
			}
		}
	}
	
	private void fillSquares()
	{
		board.add(new JLabel(""));
		// let's fill the rest of the top row with letters
		for (int i = 0; i < 11; i++)
		{
			board.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
		}
		// lets'fill out the rest of the rows 
		for (int i = 0; i < 11; i++)
		{
			for (int j = 0; j < 11; j++)
			{
				switch(j){
					case 0: // add numbers vertically
					board.add(new JLabel("" + (12 - (i + 1)), 
						SwingConstants.CENTER));
					default:
						board.add(boardSquares[j][i]);
				}
		
			}
		}
	}	
	
	// used for testing purposes only 
	// public static void main(String[] args)
// 	{
// 		Runnable r = new Runnable()
// 		{
// 			@Override
// 			public void run()
// 			{
// 				// creating a fake gui container for teating purposes
// 				JPanel gui = new JPanel(new BorderLayout(3, 3));
// 				gui.setBorder(new EmptyBorder(5, 5, 5, 5));
// 				Board b = new Board();
// 				gui.add(b.getBoard());
// 				JFrame f = new JFrame("Testing");
// 				f.add(gui);
// 				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
// 				f.setLocationByPlatform(true);
//
// 				f.pack();
// 				f.setVisible(true);
//
// 			}
// 		};
// 		// Swing GUIs should be created and updated on the EDT
// 		// http://docs.oracle.com/javase/tutorial/uiswing/concurrency
// 		SwingUtilities.invokeLater(r);
// 	}
	
}



	
