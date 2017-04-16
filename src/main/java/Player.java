public class Player
{
	private Manager _manager;
	private boolean _white;
	private boolean _turn;
	private PlayerInfoPanel _info;
	private int _pieces;
	private Coordinate last_move;
	private int repetition_count;
	
	/**
	* Constructor
	*/
	public Player()
	{
		// an empty constructor
		repetition_count = 0;
	}
	
	/**
	* Constructor 
	* @param manager-Manager object for Player object 
	*/
	public Player(Manager manager)
	{
		_manager = manager;
		repetition_count = 0;
	}
	
	/**
	* Add the info panel to player. TO BE TESTED.
	*/
	public void addInfo(PlayerInfoPanel panel)
	{
		_info = panel;
	} 
	
	/*
	* Returns the PlayerInfoPanel object
	*/
	public PlayerInfoPanel getInfo()
	{
		return _info;
	}
	
    /**
     * Player is set to be the white player
     */
    public void setWhite()
    {
        _white = true;
        _turn = false;
    }

	/**
	* Player is set to be the black player
	*/
	public void setBlack()
	{
		_white = false;
		_turn = true;
	}
	
	/**
	* Player has completed their turn
	*/
	public void doneWithTurn()
	{
		_turn = false; 
	}
	
	/**
	* Player moves to the next turn
	*/
	public void newTurn()
	{
		_turn = true; 
	}
	
	/**
	* Player is set to be the black player
	*/
	public boolean myTurn()
	{
		return _turn;
	}
	
	/**
	* Returns the coordinate of this certain Player's pieces
	* @return Returns coordinate array of manager's respective pieces
	*/
	public Coordinate[] getPieces()
	{
		if (_white)
		{
			return _manager.getWhites();
		}
		else
			return _manager.getBlacks();
	}
		
	/**
	* Returns true if this is a white player, false if not
	* @return True - white player
	* @return False if black player
	*/
	public boolean isWhite()
	{
		return _white;
	}
	
	/**
	* Checks to see if the game is over and Player has won
	* @return True - Game is won by this player
	* @return False - Game is not won by this player
	*/
	public boolean hasWon()
	{
		if (escaped() || surroundedKing())
			return true;
		return false;
	}
	
	/**
	* Checks to see if the king has escaped the board
	* @return True - king has escaped
	* @return False - king has not escaped
	*/
	public boolean escaped()
	{
		Coordinate king = _manager.getKing();
		if (_manager.hasKingEscaped(king))
		{
			return true;
		}
		return false;
	}
	
	/**
	* Checks to see if king is surrounded
	* @return True - king is surrounded
	* @return False - king is not surrounded
	*/
	public boolean surroundedKing()
	{
		Coordinate king = _manager.getKing();
		if (_manager.isKingSurrounded(king))
		{
			return true;
		}
		return false;
	}
	
	/**
	* sets the last_move variable to be used later
	*/
	public void setLastMove(Coordinate coord)
	{
		last_move = coord;
	}

	/**
	* gets the last_move variable to be used later
	*/
	public Coordinate getLastMove()
	{
		return last_move;
	}
	
	/**
	* adds repetition value if move is the same as the last_move variable
	*/
	public void addRepetition(Coordinate coord)
	{
		if(last_move == null)
		{
			// Don't do anything
			// wait so we can set last_move to something
			// occurs on the first move
		}
		else if(last_move.getX() == coord.getX() && last_move.getY() == coord.getY())
		{
			repetition_count ++;
		}
		else
		{
			repetition_count = 0;
		}
	}
	
	/**
	* returns the repetition_count
	*/
	public int getRepetition()
	{
		return repetition_count;
	}
	
}
