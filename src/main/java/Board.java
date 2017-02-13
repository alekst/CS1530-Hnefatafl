import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Board extends JPanel
{
	
	private JButton[][] boardSquares = new JButton[11][11];
	private JPanel board;
	private static final String COLS = "ABCDEFGHIJK";
	private static final int height = 2000;
	private static final int width = 1080;
	private String blackPiece = new String("\u265F");
	private String whitePiece = new String("\u2659");
	private String whiteKing = new String("\u2655");
	private static final int kingIndex = 0;
	private static final int whiteIndex = 1;
	private static final int blackIndex = 13;
	private static final int numWhites = 12;
	private static final int numBlacks = 24;
	
	private int whose_move = 0; // If 0 then white's move, if 1 then black's move
	
	private Coordinate first_clicked = new Coordinate(-1,-1);
	private Coordinate second_clicked = new Coordinate(-1,-1);
	
	private Data pieces = new Data();
	
	public Board()
	{
		super();
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
		// the background color if the board is smaller than the panel where it is contained. 
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
				square.addActionListener(actionListener);
				// todo: allow user to change board colors? 
				if (( j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0))
				{
					square.setBackground(Color.lightGray);
				}
				else
				{
					square.setBackground(Color.gray);
				}
					//square.setFont(new Font("Serif", Font.PLAIN, 20));
				square.setMargin(buttonMargin);
				square.setPreferredSize(new Dimension(50, 50));
				// the square needs to be opaque to use the setBackground method. 
				//https://docs.oracle.com/javase/7/docs/api/javax/swing/JComponent.html#setBackground(java.awt.Color)
				square.setOpaque(true); 
				square.setBorderPainted(true);
				square.setFont(new Font("UNI-8", Font.PLAIN, 36));
				square.setMaximumSize(new Dimension(j * 1000, i * 1000));
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
		pieces.initialize();
		printAllPieces();
	}	
	
	public int printBlack(Coordinate coor)
	{
		if(coor.getX() < 0 || coor.getY() < 0)
		{
			return 1;
		}
		JButton square = boardSquares[coor.getX()][coor.getY()];
		square.setText(blackPiece);
		return 0;
	}
	
	public int printWhite(Coordinate coor)
	{
		if(coor.getX() < 0 || coor.getY() < 0)
		{
			return 1;
		}
		JButton square = boardSquares[coor.getX()][coor.getY()];
		square.setText(whitePiece);
		return 0;
	}
	
	public int printKing(Coordinate coor)
	{
		if(coor.getX() < 0 || coor.getY() < 0)
		{
			return 1;
		}
		JButton square = boardSquares[coor.getX()][coor.getY()];
		square.setText(whiteKing);
		return 0;
	}
	
	public int removePiece(Coordinate coor)
	{
		if(coor.getX() < 0 || coor.getY() < 0)
		{
			return 1;
		}
		JButton square = boardSquares[coor.getX()][coor.getY()];
		square.setText("");
		return 0;
	}
	
	public int printAllPieces()
	{
		Coordinate[] coor_array = new Coordinate[36];
		coor_array = pieces.getBoardStatus();
		
		printKing(coor_array[kingIndex]);
		for(int i = 0 ; i < numWhites ; i++)
		{
			printWhite(coor_array[whiteIndex + i]);
		}
		for(int i = 0 ; i < numBlacks ; i++)
		{
			printBlack(coor_array[blackIndex + i]);
		}
		return 0;
	} 
	
	ActionListener actionListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			JButton b = (JButton)actionEvent.getSource();
			Coordinate coor = getButtonCoordinate(b);
			if(first_clicked.getX() == -1 && first_clicked.getY() == -1)
			{
				first_clicked = coor;
			}
			else if(first_clicked.getX() == coor.getX() && first_clicked.getY() == coor.getY())
			{
				// Do nothing
			}
			else
			{
				second_clicked = coor;
				System.out.println("second clicked = :" + second_clicked);
				if(pieces.isValid(first_clicked, second_clicked))
				{
					removePiece(first_clicked);
					if(pieces.isWhite(first_clicked))
					{
						printWhite(second_clicked);
					}
					else
					{
						printBlack(second_clicked);
					}
					pieces.updateLocation(second_clicked, first_clicked);
					first_clicked = new Coordinate(-1, -1);
					whose_move = (whose_move + 1) % 2;
				}
				second_clicked = new Coordinate(-1, -1);
				
			}
		}
	};
	
	public Coordinate getButtonCoordinate(JButton b)
	{
		return new Coordinate((int)(b.getMaximumSize().getWidth()/1000), (int)(b.getMaximumSize().getHeight()/1000));
	}
	
	// used for testing purposes only 
	/* public static void main(String[] args)
 	{
 		Runnable r = new Runnable()
 		{
 			@Override
 			public void run()
 			{
 				// creating a fake gui container for teating purposes
 				JPanel gui = new JPanel(new BorderLayout(3, 3));
 				gui.setBorder(new EmptyBorder(5, 5, 5, 5));
 				Board b = new Board();
 				gui.add(b.getBoard());
 				JFrame f = new JFrame("Testing");
 				f.add(gui);
 				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 				f.setLocationByPlatform(true);

 				f.pack();
 				f.setVisible(true);

 			}
 		};
// 		// Swing GUIs should be created and updated on the EDT
// 		// http://docs.oracle.com/javase/tutorial/uiswing/concurrency
		SwingUtilities.invokeLater(r);
 	}*/
	
}



	
