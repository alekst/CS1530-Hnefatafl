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
	
	
	private Coordinate first_clicked = new Coordinate(-1,-1);
	private Coordinate second_clicked = new Coordinate(-1,-1);
	
	//private Data pieces = new Data();
	private Manager _manager;
	private Player _player;
	private Player _other;
	
	public Board()
	{
		//empty constructor
	}
		
	public Board(Manager manager, Player player, Player other)
	{
		super();
		_manager = manager;
		_player = player;
		_other = other;
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
				if (( j == 0 && i == 0 ) || ( j == 10 && i == 10 ) || ( j == 0 && i == 10 ) || ( j == 10 && i == 0 ) || ( j == 5 && i == 5 ))
				{
					square.setBackground(Color.yellow);
				}
				else if (( j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0))
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
		// let's fill out the rest of the rows 
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
		printAll();
		switchTurn();
	}	
	
	public int printPiece(Coordinate coor, String type)
	{
		
		if(coor.getX() < 0 || coor.getY() < 0)
		{
			return 1;
		}
		JButton square = boardSquares[coor.getX()][coor.getY()];
		
		if (type.equals(whiteKing))
		{
			square.setText(whiteKing);
		}
		else if (type.equals(whitePiece))
		{
			square.setText(whitePiece);
		}
		else if (type.equals(blackPiece))
		{
			square.setText(blackPiece);
		}	
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
	
	public int printAll()
	{
		printPieces(_player);
		printPieces(_other);
		return 0;
	}
	
	public int printPieces(Player player)
	{	
		Coordinate[] pieces = player.getPieces();
		for(int i = 0 ; i < pieces.length; i++)
		{
			if(player.isWhite())
			{
				if (i == 0)
				{
					printPiece(pieces[i], whiteKing);
				}
				else
				{
					printPiece(pieces[i], whitePiece);
				}	
			}	
			else
			{
				printPiece(pieces[i], blackPiece);
			}	
		}		
		return 0;
	} 
	
	
	private int enable(Coordinate coor)
	{
		JButton square = boardSquares[coor.getX()][coor.getY()];
		square.setEnabled(true);
		return 0;
	}
	
	private int disable(Coordinate coor)
	{
		JButton square = boardSquares[coor.getX()][coor.getY()];
		square.setEnabled(false);
		return 0;
	}
	
	ActionListener actionListener = new GameListener()
	{
		public void doPerformAction(ActionEvent actionEvent)
		{
			
			JButton b = (JButton)actionEvent.getSource();
			Coordinate coor = getButtonCoordinate(b);
			if((first_clicked.getX() == -1 && first_clicked.getY() == -1) && (_manager.getIndex(coor) != -1))
			{
				first_clicked = coor;
			}
			else if(first_clicked.getX() == coor.getX() && first_clicked.getY() == coor.getY())
			{
				// Unselect it
			}
			else if(_manager.getIndex(coor) == -1)
			{
				second_clicked = coor;
				
				if(_manager.isValid(first_clicked, second_clicked))
				{
					move(first_clicked, second_clicked);
					_manager.updateLocation(second_clicked, first_clicked);
					_player.doneWithTurn();
					if (_player.hasWon())
					{
						JOptionPane.showMessageDialog(null, "Congratulations! You have won!");
					}	
					else if (_player.hasLost())
					{
						JOptionPane.showMessageDialog(null, "Sorry, you've lost!");
					}
					else
					{
						_other.newTurn();
						resetClicks();
						switchTurn();
					}	
					
				}
			}
			else
			{
				// Do nothing...can't go to spot thats already occupied
			}
		}
	};
	
	
	public Coordinate getButtonCoordinate(JButton b)
	{
		return new Coordinate((int)(b.getMaximumSize().getWidth()/1000), (int)(b.getMaximumSize().getHeight()/1000));
	}
	
	private void switchTurn()
	{
		Player temp = _player;
		_player = _other;
		_other = temp;
		enable(_player);
		disable(_other);
		
	}
	
	private void enable(Player player)
	{
		Coordinate [] pieces = player.getPieces();
		
		for(int i = 0 ; i < pieces.length; i++)
		{
				enable(pieces[i]);
		}
	}
	
	private void disable(Player player)
	{
		Coordinate [] pieces = player.getPieces();
		
		for(int i = 0 ; i < pieces.length; i++)
		{
			
				disable(pieces[i]);
		
		}
	}
		
	
	private void move(Coordinate oldData, Coordinate newData)
	{
		removePiece(oldData);
			
		if (_manager.isKing(oldData))
		{
			printPiece(newData, whiteKing);
		}
		else if(_player.isWhite())
		{
			printPiece(newData, whitePiece);
		}
		else
		{
			printPiece(newData, blackPiece);
		}
	}
	
	private void resetClicks()
	{
		first_clicked = new Coordinate(-1, -1);
		second_clicked = new Coordinate(-1, -1);
	}
}



	
