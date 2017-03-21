import java.lang.*;

public class Game
{
	
	private Board _board; 
	private Player _whites;
	private Player _blacks;
	private Manager _manager;
	
	/**
	* Constructor
	*/
	public Game()
	{
		init();	
	}
	
	/**
	* Initializes the game state
	*/
	private void init()
	{
		
		_manager = new Manager();	
		
		// player 1
		_whites = new Player(_manager);
		_whites.setWhite();
		_whites.setName("Whites"); // TODO: Allow user to set his/her own name
    	_whites.setTimer(300); // 300 seconds is 5 minutes TODO: allow player set the general time limit
		
		// player 2
		_blacks = new Player(_manager);
		_blacks.setBlack();
		_blacks.setName("Blacks");
    	_blacks.setTimer(300); 
		
		GameInfoPanel _gi = new GameInfoPanel(_whites, _blacks);
		_board = new Board(_manager, _gi, _whites, _blacks);
    	
		MainFrame _mf = new MainFrame(_board, _gi);
	}
	
	/**
	* @return the game's Manager object
	*/
	public Manager queryManager()
	{
		return _manager;
	}
	
	/**
	* @return the game's Board Object
	*/
	public Board queryBoard()
	{
		return _board;
	}
	
	/**
	* @return the two players in the game in form of an array of players
	* 	index 0 is the white player
	* 	index 1 is the black player
	*/
	public Player[] queryPlayers()
	{
		Player[] players = new Player[2];
		players[0] = _whites;
		players[1] = _blacks;
		return players;
	}
	
	/**
	* Currently unused...will be used to save game
	*/
	public void save()
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	* Currently unused...will be used to load game
	*/
	public void load()
	{
		throw new UnsupportedOperationException();
	}
	
	
}
