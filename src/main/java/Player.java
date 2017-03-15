public class Player
{
	private Manager _manager;
	private boolean _white;
	private boolean _turn;
	
	/**
	* Constructor
	*/
	public Player()
	{
		// an empty constructor
	}
	
	/**
	* Constructor 
	* @param manager-Manager object for Player object 
	*/
	public Player(Manager manager)
	{
		_manager = manager;
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
	
}