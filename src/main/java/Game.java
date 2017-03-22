import java.lang.*;
import java.awt.event.*;
import java.awt.*;

public class Game
{
	
	private Board _board; 
	private Player _whites;
	private Player _blacks;
	private Manager _manager;
	private PlayerInfoPanel _whiteInfo, _blackInfo;
	
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
		
		// player info panel
		_whiteInfo = new PlayerInfoPanel("Whites", 300); // 300 seconds

		
		_whites.addInfo(_whiteInfo);
		
		// player 2
		_blacks = new Player(_manager);
		_blacks.setBlack();
		
		// player info
		_blackInfo = new PlayerInfoPanel("Blacks", 300);
		_blacks.addInfo(_blackInfo);

		
		
		_board = new Board(_manager, _whites, _blacks);
		GameInfoPanel _gi = new GameInfoPanel(_whiteInfo, _blackInfo);
		
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
