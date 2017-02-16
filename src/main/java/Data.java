import java.lang.*;
import java.util.*;
import java.util.stream.*;

public class Data implements DataInterface
{
 
	public Integer[] boardData; // Should be public so that the Board object can actually access the coordinates
	
	public Data()
	{
		// an empty constructor
	}
    
    /**
     * Initializes the initial values for the data array, which
     * will contain the encoded coordinates for the pieces on
     * the board. The 0th index of the array is the kind, followed by
     * the white piece, followed by the black pieces. 
     */
    public void initialize()
    {
	    Integer[] initialData = {61, 59, 60, 62, 63, 49, 50, 51, 39, 71, 72, 73, 83, 4, 5, 6, 7, 8, 17, 34, 44, 45, 55, 56, 57, 65, 66, 67, 77, 78, 88, 105, 114, 115, 116, 117, 118
	    };
        boardData = initialData.clone();
    }
	/**
	* Encodes the Coordinate object for storage
	* @param data-the coordinate object of the piece
	* @return returns the int value of that location
	*/
    public int encode(Coordinate data)
    {
        return (data.getY() * 11 + data.getX() + 1);
    }
	
	/**
	* Decodes the Coordinate object for storage
	* @param-value the int value of the location
	* @return returns a coordinate object for that location
	*/
	public Coordinate decode(int value)
	{
		int x = value % 11 - 1;
		if (x < 0) x = 10;
		
		int y = value / 11;
		if (value % 11 == 0) y = y - 1;
		
		return new Coordinate(x, y);
	}
	
	/**
	* Returns an index based on the coordinates. It would return -1 if the index is not found. 
	* @param data-coordinates of a piece
	* @return returns an index value
	*/
	public int getIndex(Coordinate data)
	{
		int value = encode(data);
		int index = Arrays.asList(boardData).indexOf(value);
		return index;	
	}
	/**
	* Returns the Coordinate object, based on the index (0-36) of the array. If there is no piece in
	* the game, it will return 0. 
	* @param index-index value from array
	* @return 0 if no piece in the game
	* @return the decoded value of the index if the piece is in the game
	*/
	public Coordinate getCoordinate(int index)
	{
		int value = boardData[index];
		if (value == 0)
			return null;
		else
			return decode(value);		
	}
	
	/** 
	* @Return a current board status as an array of Coordinate objects
	*
	*/ 
	public Coordinate[] getBoardStatus()
	{
		Coordinate[] board = new Coordinate[37];
		for (int i = 0; i < 37; i++)
		{
			Coordinate c = getCoordinate(i);
			board[i] = c;
		}
		return board;
	}
	/**
	* Returns true if the coordinates are in the array. Otherwise, it returns false. 
	* @param data-coordinate of a location
	* @return true if the coordinates are in the array
	* @return false if the coordinats are not in the array
	*/	
	public boolean isMember (Coordinate data)
	{
		int value = encode(data);
		return Arrays.asList(boardData).contains(value);
	}
	
	/**
	* Resets the array to its initial state
	*/
	public void reset()
	{
		initialize();
	}
	/** 
	* Takes the source coordinates and the destination coordinates and updates the array
	*/
	public void updateLocation(Coordinate newdata, Coordinate olddata)
	{
		int newValue = encode(newdata);
		int index = getIndex(olddata);
		boardData[index] = newValue;
	}
	/**
	* Returns true if the piece in the given coordinate is white. Otherwise, returns false
	* @param data-coordinate of a location
	* @return true if the piece is white
	* @return false if the piece is not white
	*/
	public boolean isWhite(Coordinate data)
	{
		// returns true if the piece is white. white means its index is between 0 and 12. The method assumes that the coordinate contains a piece. 
		int index = getIndex(data);
		if (index < 13 && index > 0)
			return true;
		else 
			return false;
	}
	
	private boolean inSameSpot(Coordinate origin, Coordinate destination) 
	{
		if(isMember(origin) && isMember(destination)) //both coordinates are occupied
		{
			System.out.println("Both are occupied");
			return true;
		}
		else 
			return false;
	}

	/**
	* Returns true if the destination is up/down or left/right. Otherwise, returns false. 
	* @param data-coordinate of an origin location and a destination location
	* @return true if the move is up/down or left/right
	* @return false if the move is not horizontal or vertical
	*/
	public boolean isValid(Coordinate origin, Coordinate destination) 
	{	
		//TODO: implement rules of the game for a move that encounters another piece on the way.  need to figure out issue with negatives
		if(inSameSpot(origin, destination)) //both coordinates are occupied
		{
			return false;
		}
		else if (origin.getX() == destination.getX())
		{
			// that means the path is up/down
			int start_y=origin.getY()+1; //start looking at squares one away from start square
			int end_y=destination.getY();
			int x=destination.getX(); //this y coord will not change throughout this method
			if(origin.getY()-destination.getY()<=1) //to handle up-> down movement
			{
				for(int y=start_y; y<end_y; y++)
				{
					Coordinate temp_obj=new Coordinate(x,y); //temp obj used to see if a piece is in movement path
					if(isMember(temp_obj))
					{
						System.out.println("up/down "+temp_obj.getX()+ " "+temp_obj.getY());
						return false; //piece in movement path
					}
				}
			}
			else //to handle down-> up movement
			{	
				start_y=origin.getY()-1;
				for(int y=start_y; y>end_y; y--)
				{
					Coordinate temp_obj=new Coordinate(x,y); //temp obj used to see if a piece is in movement path
					if(isMember(temp_obj))
					{
						System.out.println("down/up "+temp_obj.getX()+ " "+temp_obj.getY());
						return false;//piece in movement path
					}
				}
			}
			return true; //no pieces in movement path
		}
		else if (origin.getY() == destination.getY())
		{
			// that means the path is left/right
			//create temp coord obj and give it x,y pairs between start spot and end spot (exclusive), if any of the temp coord objs is found in array, then invalid move
			//use isMember(temp coor obj)
			int start_x=origin.getX()+1; //start looking at squares one away from start square
			int end_x=destination.getX();
			int y=destination.getY(); //this y coord will not change throughout this method
			if(origin.getX()-destination.getX()<=1) //to handle left-> right movement
			{
				for(int x=start_x; x<end_x; x++)
				{
					Coordinate temp_obj=new Coordinate(x,y);//temp obj used to see if a piece is in movement path
					if(isMember(temp_obj))
					{
						System.out.println("left/right "+temp_obj.getX()+ " "+temp_obj.getY());
						return false;//piece in movement path
					}
				}
			}
			else //to handle right-> left movement
			{
				start_x=origin.getX()-1;
				for(int x=start_x; x>end_x; x--)
				{
					Coordinate temp_obj=new Coordinate(x,y);//temp obj used to see if a piece is in movement path
					if(isMember(temp_obj))
					{
						System.out.println("right/left "+temp_obj.getX()+ " "+temp_obj.getY());
						return false;//piece in movement path
					}
				}
			}
			return true; //no pieces in movement path
		}
		else
		{
			//not a valid move
			return false;
		}
	}
	
	/**
	* Returns true if the coordinate contain the King piece. Otherwise, returns false. 
	* @param data-coordinate of a location
	* @return true if the location contain the king
	* @return false if it does not
	*/
	public boolean isKing(Coordinate coord)
	{	
		return getIndex(coord) == 0;
	}

	

	
	
	
}
