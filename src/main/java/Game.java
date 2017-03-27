import java.lang.*;
import java.io.*;
import java.nio.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	* Initializes the game state with existing manager (related data) and players
	* @param manager : Manager object with related Data
	* @param whites : white player
	* @param blacks : black player
	*/
	private void load_init(Manager manager, Player whites, Player blacks)
	{
		// manager
		_manager = manager;	
		// player 1
		_whites = whites;
		// player 2
		_blacks = blacks;
			
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
	* Opens a new file "saved_hnefetafl.csv" and writes the current board array to a csv file
	* Throws IO exception on error in file opening or writing
	*/
	public void save() throws java.io.IOException
	{
		FileOutputStream out = null;
		
		try {
      out = openOutStream();
      printBoard(out);
		} catch(IOException e) 
		{
  		System.err.format("IOException: %s%n", e);
		} finally {
			closeOutStream(out);
		}
	}
	
	/**
	* Reads in the bytes of the "saved_hnefetafl.csv" file and converts it to a string
	* Passes the string representation of the file to the board to load
	* Throws IO exception on error in file opening or reading
	*/
	public void load() throws java.io.IOException
	{
		Path path = Paths.get("saved_hnefatafl.csv");
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) 
		{
			if (reader != null) {
    		String line = null;
				line = reader.readLine();
				String[] result = line.split(",");
				Integer[] locs = new Integer[37];
				for (int i = 0; i < 37; i++)
				{
					locs[i] = Integer.valueOf(result[i]);
				}
				_manager.loadData(locs);
				load_init(_manager, _whites, _blacks);
			} else 
			{
				System.out.println("Sorry, there are no saved games to load!");
			}

		} catch (IOException e) 
		{
    	System.err.format("IOException: %s%n", e);
		}
	}
	
	/**
	* Gets turn value from black player 
	* @return byte 1 if it is black's turn to move
	* @return byte 0 if it is white's turn to move
	*/
	private byte getTurn()
	{
		byte turnByte;
		if(_blacks.myTurn())
		{
			turnByte = (byte) 1;
		} else 
		{
			turnByte = (byte) 1;
		}
		return turnByte;
	}
	
	/**
	* Opens file output stream for specified game saving file "saved_hnefatafl.csv"
	* @return FileOutputStream out 
	*/
	private FileOutputStream openOutStream() throws java.io.IOException
	{
		FileOutputStream out = null;
		try 
		{
			File f = new File("saved_hnefatafl.csv");
			f.createNewFile();
      out = new FileOutputStream("saved_hnefatafl.csv");
		} catch(IOException e) 
		{
  		System.err.format("IOException: %s%n", e);
		}
		return out;
	}
	
	/**
	* Closes file output stream for game saving file
	* @return 0 upon no error
	* @return 1 if error, also prints error code to system error
	*/
	private int closeOutStream(FileOutputStream out) throws java.io.IOException
	{
		try 
		{
			if (out != null)
			{
				out.flush();
				out.close();
			}
		} catch(IOException e) 
		{
	  	System.err.format("IOException: %s%n", e);
			return 1;
		}
		return 0;
	}
	
	/**
	* Prints board array to file output stream
	* @return 0 upon no error
	* @return 1 if error, also prints error code to system error
	*/
	private int printBoard(FileOutputStream out) throws java.io.IOException
	{
		try 
		{
			String b = new String();
			b = _board.printBoard();
			byte[] boardBytes = b.getBytes("UTF-8");
			int len = boardBytes.length;

			// appends turn to array
			byte[] finalBytes = new byte[len + 1]; 
			for (int i = 0; i < len; i++)
			{
				finalBytes[i] = boardBytes[i];
			}
			finalBytes[len] = getTurn();
			
			// append timer data to array 
			out.write(finalBytes);
		} catch(IOException e) 
		{
			System.err.format("IOException: %s%n", e);
			return 1;
		}
		return 0;
	}
	
}