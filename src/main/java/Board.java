import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class Board extends JPanel
{
	
	private Square[][] boardSquares = new Square[11][11];
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
	
	
	private Coordinate first_clicked = new Coordinate(-1, -1);
	private Coordinate second_clicked = new Coordinate(-1, -1);
	private Color selected_color;
	
	//private Data pieces = new Data();
	private Manager _manager;
	private Player _player;
	private Player _other;
	
	
	/**
	 *	Empty constructor for game initialization purposes. Overridden by constructor below when instantiated.
	 */
	public Board()
	{
		//empty constructor
	}
		
	/**
	 *	Constructor method for Board class. Initializes board, game squares, and draws appropriate pieces to the squares.
	 *	@param manager: The manager of the board data for the game 
	 *	@param player: Player currently moving (pieces enabled)
	 *	@param other: Player currently idle (pieces disabled)
	 */
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
	
	/**
	 *	Accessor for Board object
	 *	@return this instance of the board
	 */
	public final JComponent getBoard()
	{
		return board;
	}
	
	/**
	 *	Initiliazes board panel in the main panel, draws the border and color, and instantiates a panel for the grid layout. 
	 */
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
	
	
	/**
	 *	Creates JButton in all grid squares 
	 * 	Sets corners and thrown to yellow to designate special squares
	 *	Sets value of boardSquares array to the button at that index 
	 */
	private void createSquares()
	{
		
		// create the board squares
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int i = 0; i < boardSquares.length; i++)
		{
			for (int j = 0; j < boardSquares[i].length; j++)
			{
				
				Square square = new Square(new Coordinate(j, i));
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
				square.setMargin(buttonMargin);
				square.setMaximumSize(new Dimension(j * 1000, i * 1000));
				boardSquares[j][i] = square;
			}
		}
	}
	
	/**
	 *	Sets labels along axes for grid locations
	 *		- A-K letters to mark the columns in the first row
	 *		- 1-11 for row numbers
	 */
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
		switchTurn(false); // this switch turn does not count. This is done so that the Black player would go first. 
	}	
	
	/**
	* Prints appropriate piece image (black, white, king) on location designated by the Coordinate argument
	* @param coor: Coordinate object designating the location where the piece should be printed
	* @param type: String desginating type of piece to print, either whiteKing, whitePiece, or blackPiece
	* @return int, 1 if coor does not have a valid location on the board
	* 0 if the piece is printed
	*/
	public int printPiece(Coordinate coor, String type)
	{
		
		if(coor.getX() < 0 || coor.getY() < 0)
		{
			return 1;
		}
		Square square = boardSquares[coor.getX()][coor.getY()];
		
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
	
	/**
	* Removes appropriate piece image (black, white, king) to a blank space on location designated by the Coordinate argument
	* @param coor: Coordinate object designating the location where the piece should be removed
	* @return int, 1 if coor does not have a valid location on the board
	* 0 if the piece is removed
	*/
	public int removePiece(Coordinate coor)
	{
		if(coor.getX() < 0 || coor.getY() < 0)
		{
			return 1;
		}
		Square square = boardSquares[coor.getX()][coor.getY()];
		square.setText("");
		return 0;
	}
	
	/**
	* Calls printPieces to print the pieces for each player on the board
	* @return 0 on successful completion
	*/
	public int printAll()
	{
		printPieces(_player);
		printPieces(_other);
		return 0;
	}
	
	/**
	* Prints the pieces for a particular player
	* @param player: Player object indicating for which player to print
	* @return int, 0 on successful completion
	*/
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
	
	/**

	* Enables pieces related to designated player 
	*/
	public void enable(Player player)
	{
		Coordinate [] pieces = player.getPieces();
		
		for(int i = 0 ; i < pieces.length; i++)
		{
			enable(pieces[i]);
		}
	}
	
	/**
	* Disables pieces related to designated player 
	* @param player: Player object designating which player should be disabled
	*/
	public void disable(Player player)
	{
		Coordinate [] pieces = player.getPieces();
		
		for(int i = 0 ; i < pieces.length; i++)
		{
			
			disable(pieces[i]);
		
		}
	}
	
	/**
	* Action listener for user actions clicking board pieces for moves.
	* Gets coordiante from click location for first click to change to coordinate related to second click 
	* Calls manager to access/update data 
	* Switches player turns on end of move
	* @param actionEvent: ActionEvent object passed in from frame 
	*/
	ActionListener actionListener = new GameListener()
	{
		 public void doPerformAction(ActionEvent actionEvent)
 		{			
 			Square b = (Square)actionEvent.getSource();
 			Coordinate coor = b.getCoord();
			selected_color = boardSquares[coor.getX()][coor.getY()].getBackground();
				
			if((first_clicked.isMinusOne()) && (_manager.getIndex(coor) != -1))
			{
				first_clicked = coor;	
				boardSquares[coor.getX()][coor.getY()].setBackground(Color.darkGray);
			}
 			else if(first_clicked.equal(coor))
 			{
// 				// Unselect it
 				boardSquares[coor.getX()][coor.getY()].setBackground(selected_color);
 			} 
			else if(_manager.getIndex(coor) == -1)
 			{
 				second_clicked = coor;

 				if(_manager.isValid(first_clicked, second_clicked))
				{
					move(first_clicked, second_clicked);

					boardSquares[first_clicked.getX()][first_clicked.getY()].setBackground(selected_color);

					_manager.updateLocation(second_clicked, first_clicked);
					
					ArrayList<Coordinate>piecesToRemove=_manager.isPieceSurrounded(second_clicked); //sees if a piece(s) is captured
					for(int i=0; i<piecesToRemove.size(); i++) //removes pieces from board
					{
						enable(piecesToRemove.get(i)); //enable the spot where the piece used to reside
						removePiece(piecesToRemove.get(i)); //remove it from the front end
						_manager.removePiece(piecesToRemove.get(i)); //remove it from the backedn
					}
					
					if (_player.hasWon())
					{
						JOptionPane.showMessageDialog(null, "Congratulations! You have won!");
					}
					_player.doneWithTurn(); //disable whole board
					_other.newTurn();
					resetClicks();
					switchTurn(true); // this is an actual turn that has been made, so the flag is set to true
				}
			}
			else
			{
				// User has clicked occupied space...select this piece as
				// 		the piece to move
				boardSquares[first_clicked.getX()][first_clicked.getY()].setBackground(selected_color);

				first_clicked = coor;
				selected_color = boardSquares[coor.getX()][coor.getY()].getBackground();
				boardSquares[coor.getX()][coor.getY()].setBackground(Color.darkGray);
			}
		}
	};
	
	
	/**
	* Disables a button at designated Coordinate, for use in switching turns
	* @param coor: Coordinate object designating the location where the piece should be disabled
	* @return int, 0 on successful completion
	*/
	private int disable(Coordinate coor)
	{
		if(coor==null)
					return 0;
		if(coor.getX() < 0 || coor.getY() < 0)
		{
			return 1;
		}
		Square square = boardSquares[coor.getX()][coor.getY()];
		square.setEnabled(false);
		return 0;
	}

	/**
	* Enables button at designated coordinate, for use in switching turns
	* @param coor: Coordinate object designating the location where the piece should be enabled
	* @return int, 0 on successful completion
	*/
	private int enable(Coordinate coor)
	{
		if(coor==null)
					return 0;
		if(coor.getX() < 0 || coor.getY() < 0)
		{
			return 1;
		}
		Square square = boardSquares[coor.getX()][coor.getY()];
		square.setEnabled(true);
		return 0;
	}

	
	/**
	* Changes current player to "other" and "other" player to now be the current player to move
	* Calls enable and disable methods for the respective players' pieces 
	*/
	private void switchTurn(boolean turn)
	{
		
		PlayerInfoPanel playerInfo = _player.getInfo();

		// stop the active player timer
		playerInfo.stopTimer();
		// if this is the end of an actual turn and not done in preparation for the game
		if (turn)
		{
			// add three seconds to this player's time
			playerInfo.addTime();
		}		
		// start the passive player timer
		PlayerInfoPanel otherInfo = _other.getInfo();
		otherInfo.startTimer();
		
		// switch players
		Player temp = _player;
		_player = _other;
		_other = temp;
		
		// enable the active player
		enable(_player);
		
		// disable the passive player. 
		disable(_other);
	}
		
	/**
	* "Moves" the piece by deleting piece at old location and printing piece at new location 
	* @param oldData: Coordinate object indicating old location of the moving piece
	* @param newData: Coordinate object indicating new location to where the piece should move
	*/
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
	
	/**
	* Sets clicks to non-board locations to clear clicks 
	*/
	private void resetClicks()
	{
		first_clicked = new Coordinate(-1, -1);
		second_clicked = new Coordinate(-1, -1);
	}
	
}



	
