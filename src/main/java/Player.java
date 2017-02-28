public class Player
{
	private Manager _manager;
	private boolean _white;
	private boolean _turn;
	
	public Player()
	{
		// an empty constructor
	}
	
	public Player(Manager manager)
	{
		_manager = manager;
	}
	
	public void setWhite()
	{
		_white = true;
		_turn = false;
		
	}
	
	public void setBlack()
	{
		_white = false;
		_turn = true;
	}
	
	public void doneWithTurn()
	{
		_turn = false; 
	}
	
	public void newTurn()
	{
		_turn = true; 
	}
	
	public boolean myTurn()
	{
		return _turn;
	}
	
	
	public Coordinate[] getPieces()
	{
		if (_white)
		{
			return _manager.getWhites();
		}
		else
			return _manager.getBlacks();
	}
		
	
	public boolean isWhite()
	{
		return _white;
	}
	
	public boolean hasWon()
	{
		Coordinate king = _manager.getKing();
		if (_manager.hasKingEscaped(king))
		{
			return true;
		}
		return false;
	}
	
	public boolean hasLost()
	{
		Coordinate king = _manager.getKing();
		if (_manager.isKingSurrounded(king) && _white == true)
		{
			return true;
		}
		return false;
	}
	
}