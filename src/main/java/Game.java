import java.lang.*;
import java.awt.event.*;
import java.awt.*;
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
		
		int numWhites = _manager.getWhites().length;
		
		// player info panel
		_whiteInfo = new PlayerInfoPanel("Whites", 300, numWhites); // 300 seconds, 13 pieces.
		
		
		// add the info panel to the player
		_whites.addInfo(_whiteInfo);
		
		// player 2
		_blacks = new Player(_manager);
		_blacks.setBlack();
		
		int numBlacks = _manager.getBlacks().length;
		
		// player info panel
		_blackInfo = new PlayerInfoPanel("Blacks", 300, numBlacks); // 300 seconds, 24 pieces
		
		// add player info to the player
		_blacks.addInfo(_blackInfo);

		//create the board
		_board = new Board(_manager, _whites, _blacks);
		
		//create the Game Info Panel
		GameInfoPanel _gi = new GameInfoPanel(_whiteInfo, _blackInfo, _board);
		// create the main frame
		MainFrame _mf = new MainFrame(this, _gi);
	}

	/**
	* Initializes the game state with existing manager (related data) and players
	* @param manager : Manager object with related Data
	* @param whites : white player
	* @param blacks : black player
	*/
	private void load_init(Manager manager, Player whites, Player blacks, int whiteTime, int blackTime)
	{
		// manager
		_manager = manager;	
		// player 1 : right now resets player info on load
		_whites = whites;
		int numWhites = _manager.getWhites().length;
		_whiteInfo = new PlayerInfoPanel("Whites", whiteTime, numWhites); // 300 seconds
		_whites.addInfo(_whiteInfo);
		
		// player 2 : right now resets player info on load
		_blacks = blacks;
		int numBlacks = _manager.getBlacks().length;
		_blackInfo = new PlayerInfoPanel("Blacks", blackTime, numBlacks);
		
		_blacks.addInfo(_blackInfo);
		
		_board = new Board(_manager, _whites, _blacks);
		
	
		GameInfoPanel _gi = new GameInfoPanel(_whiteInfo, _blackInfo, _board);
		MainFrame _mf = new MainFrame(this, _gi);
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
			_board.showMsg("Game has been saved successfully.");
		} catch(IOException e) 
		{
			_board.showMsg("Sorry, something went wrong in saving your game. Please try again.");
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
			
				Integer[] gameInfo = parseInput(line);
				stopTimers();
				resetPlayers();
				load_init(_manager, _whites, _blacks, gameInfo[2], gameInfo[1]);	// data is set via the manager in the parseInput method
				setPlayers(gameInfo[0]); // [0]: turn flag
				setColorScheme(gameInfo[3]); // [3]: color scheme
			} 
		} catch (java.nio.file.NoSuchFileException nf) 
		{
			_board.showMsg("Sorry, there are no saved games to load! To save a game, click \"Save\" below.");
		} catch (IOException e) 
		{
    	System.err.format("IOException: %s%n", e);
		} 
	}
	
	/**
	* Gets turn value from black player 
	* @return int 1 if it is black's turn to move
	* @return int 0 if it is white's turn to move
	*/
	public int getTurn()
	{
		int turn;
		if(_blacks.myTurn())
		{
			turn = 1;
		} else 
		{
			turn = 0;
		}
		return turn;
	}
	
	/**
	* Gets the Integer array for board locations from input String
	* Gets turn flag (last value) and sets player turn 
	* @param input - String representation of board and turn flag
	* @return Integer array of piece locations on the board
	*/
	private Integer[] parseInput(String input)
	{
		String[] result = input.split(",");	// result is array of locations + turn int

		// extract and set piece locations
		Integer[] locs = new Integer[37];
		for (int i = 0; i < 37; i++)
		{
			locs[i] = Integer.valueOf(result[i]);
		}
		_manager.loadData(locs);
		
		// extract other saved game info
		// [0]: turn flag, [1]: black timer countdown, [2]: white timer countdown, [3]: color scheme
		Integer[] gameInfo = new Integer[4]; // [0]: turn flag,  
		for (int i = 0; i < 4; i++)
		{
			gameInfo[i] = Integer.parseInt(result[37 + i]); 
		}
	
		return gameInfo;
	}
	
	/**
	* Resets players by creating new players
	*/ 
	private void resetPlayers()
	{
		// player 1
		_whites = new Player(_manager);
		_whites.setWhite();
							
		// player 2
		_blacks = new Player(_manager);
		_blacks.setBlack();	
	}
	
	/**
	* Receives turn flag and sets player turn accordingly 
	* If the flag is 0, it is white's turn, changes turns
	* If the flag is 1, it is black's turn (new game default)
	*/
	private void setPlayers(int turn) 
	{
		if (turn == 0) //White's turn
		{
			_board.setTurn(true); // switches player turn as if black has moved
		}
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
			b = _board.printBoard();	// gets String of board (comma separated)
			// print game info to file
			String turn  = Integer.toString(getTurn()); //gets if it is black's turn
			String blackTimer = Integer.toString(getBlackTimer()); //black timer first
			String whiteTimer = Integer.toString(getWhiteTimer()); //white timer second
			String color = Integer.toString(getColorScheme());
			// sets last 4 ints to store game info — [0]: turn flag, [1]: black timer countdown, [2]: white timer countdown, [3]: color scheme
			b = b + "," + turn + "," + blackTimer + "," + whiteTimer + "," + color;
			byte[] boardBytes = b.getBytes("UTF-8");
			// append timer data to array 
			out.write(boardBytes);
		} catch(IOException e) 
		{
			System.err.format("IOException: %s%n", e);
			return 1;
		}
		return 0;
	}
	
	/**
	*	Gets the black player's current time
	* @return int indicating color choice
	*/
	private int getBlackTimer()
	{
		return _blackInfo.getTime();
	}
	
	/**
	*	Gets the white player's current time
	* @return int indicating color choice
	*/
	private int getWhiteTimer()
	{
		return _whiteInfo.getTime();
	}
	
	/**
	*	Returns the board's current color scheme
	* @return int indicating color choice
	*/
	private int getColorScheme()
	{
		return _board.getColorScheme();
	}
	
	/**
	*	Sets the color scheme for the game based on saved integer value. Calls method to set tiles with the related string flag.
	* @param color : int stored to saved file designating the color scheme of the board
	*/
	private void setColorScheme(int color)
	{
		String s = null;
		switch(color) {
			case 1: s = "autumn";
							break;
			case 2: s = "winter";
							break;
			case 3: s = "spring";
							break;
			case 4: s = "summer";
							break;
			default: s = "generic";
							break;
		}
		_board.setTileColors(s);	
	}
	
	/**
	*	Stops the previous running threads before creating new timer threads.
	*/
	public void stopTimers()
	{
		_whiteInfo.stopTimer(); 
		_blackInfo.stopTimer(); 
	}
	
}
