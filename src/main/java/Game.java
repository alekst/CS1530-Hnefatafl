import java.lang.*;

public class Game
{
	
	private Board _board; 
	private Player _whites;
	private Player _blacks;
	private Manager _manager;
	
	public Game()
	{
		init();	
	}
	
	// * initializes the game state
	private void init()
	{
		
		_manager = new Manager();	
		
		// player 1
		_whites = new Player(_manager);
		_whites.setWhite();
		
		// player 2
		_blacks = new Player(_manager);
		_blacks.setBlack();
		
		_board = new Board(_manager, _whites, _blacks);
		
		MainFrame _mf = new MainFrame(_board);
	}
	
	public Manager queryManager()
	{
		return _manager;
	}
	
	public Board queryBoard()
	{
		return _board;
	}
	
	// to save the game
	public void save()
	{
		throw new UnsupportedOperationException();
	}
	
	// to load the game
	public void load()
	{
		throw new UnsupportedOperationException();
	}
	
	
}