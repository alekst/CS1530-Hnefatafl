import java.lang.*;
import java.io.*;
import java.nio.*;

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
		
		// player 2
		_blacks = new Player(_manager);
		_blacks.setBlack();
		
		_board = new Board(_manager, _whites, _blacks);
		
		MainFrame _mf = new MainFrame(this);
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
	* Opens a new file "saved_games/saved_hnefetafl.txt" and writes the current board array to a csv file
	* Throws IO exception on error in file opening or writing
	*/
	public void save() throws java.io.IOException
	{
		FileOutputStream out = null;
		try {
			File dir = new File("saved_games");
			dir.mkdirs();
			File f = new File(dir, "saved_hnefatafl.csv");
			f.createNewFile();
      out = new FileOutputStream("saved_hnefatafl.csv");
        
			String b = new String();
			b = _board.printBoard();
			byte[] boardBytes = b.getBytes("UTF-8");
      out.write(boardBytes);
		} catch(IOException e) {
  		e.printStackTrace();
		} finally {
			if (out != null)
			{
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	* Currently unused...will be used to load game
	*/
	public void load()
	{
		throw new UnsupportedOperationException();
	}
	
	
}