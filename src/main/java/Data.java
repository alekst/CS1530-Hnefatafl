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
	
	/**
	* @param origin-Coordinate of origin location
	* @param destination-Coordinate of destination location
	* @return true-if origin and destination location are both occupied
	* @return false-if both are not occupied
	*/
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
	* Determines if there are pieces in the path of an Up to Down move
	* @param start_y-the y coordinate of the original location
	* @param end_y-the y coordinate of the end location
	* @param x-the x value of both coordinates, since it's a vertical move the x will remain the same in the origin and destination
	* @return false-if there are no pieces in the path
	* @return true-if there is NOT a piece in its path
	*/
	private boolean validUpDownMove(int start_y, int end_y, int x)
	{
		//create temp coord obj and give it x,y pairs between start spot and end spot (exclusive), if any of the temp coord objs is found in array, then invalid move
		for(int y=start_y; y<end_y; y++)
		{
			Coordinate temp_obj=new Coordinate(x,y); //temp obj used to see if a piece is in movement path
			if(isMember(temp_obj))
			{
				return false; //piece in movement path
			}
		}
		return true;
	}

	/**
	* Determines if there are pieces in the path of a Down to UP move
	* @param start_y-the y coordinate of the original location
	* @param end_y-the y coordinate of the end location
	* @param x-the x value of both coordinates, since it's a vertical move the x will remain the same in the origin and destination
	* @return false-if there are no pieces in the path
	* @return true-if there is NOT a piece in its path
	*/
	private boolean validDowUpMove(int start_y, int end_y, int x)
	{
		//create temp coord obj and give it x,y pairs between start spot and end spot (exclusive), if any of the temp coord objs is found in array, then invalid move
		for(int y=start_y; y>end_y; y--)
		{
			Coordinate temp_obj=new Coordinate(x,y); //temp obj used to see if a piece is in movement path
			if(isMember(temp_obj))
			{
				return false;//piece in movement path
			}
		}
		return true;
	}

	/**
	* Determines if there are pieces in the path of a Left to Right move
	* @param start_x-the x coordiante of the original location
	* @param end_x-the x cooridnate of the end location
	* @param y- the y value of both coordinates, since it's a horizontal move the y will remain the same in the origin and destination 
	* @return false-if there are no pieces in the path
	* @return true-if there is NOT a piece in its path
	*/
	private boolean validLeftRightMove(int start_x, int end_x, int y)
	{
		//create temp coord obj and give it x,y pairs between start spot and end spot (exclusive), if any of the temp coord objs is found in array, then invalid move
		for(int x=start_x; x<end_x; x++)
		{
			Coordinate temp_obj=new Coordinate(x,y);//temp obj used to see if a piece is in movement path
			if(isMember(temp_obj))
			{
				return false;//piece in movement path
			}
		}
		return true;
	}

	/**
	* Determines if there are pieces in the path of a Right to Left move
	* @param start_x-the x coordiante of the original location
	* @param end_x-the x cooridnate of the end location
	* @param y- the y value of both coordinates, since it's a horizontal move the y will remain the same in the origin and destination 
	* @return false-if there are no pieces in the path
	* @return true-if there is NOT a piece in its path
	*/
	private boolean validRightLeftMove(int start_x, int end_x, int y)
	{
		//create temp coord obj and give it x,y pairs between start spot and end spot (exclusive), if any of the temp coord objs is found in array, then invalid move
		for(int x=start_x; x>end_x; x--)
		{
			Coordinate temp_obj=new Coordinate(x,y);//temp obj used to see if a piece is in movement path
			if(isMember(temp_obj))
			{
				return false;//piece in movement path
			}
		}
		return true;
	}

	/**
	* Determines if a move is valid or not
	* @param origin-coordinate of the origin location
	* @param destination-coordinate of the destination location
	* @return true if the move valid
	* @return false if the move is invalid
	*/
	public boolean isValid(Coordinate origin, Coordinate destination) 
	{	
		System.out.println(origin.getX()+ " "+origin.getY() + " ____ "+ destination.getX()+ " "+ destination.getY());
		if(inSameSpot(origin, destination)) //both coordinates are occupied
		{
			return false;
		}
		else if (origin.getX() == destination.getX()) //vertical move
		{
			int start_y=origin.getY()+1; //start looking at squares one up from start square
			int end_y=destination.getY();
			int x=destination.getX(); //this y coord will not change throughout this method
			if(origin.getY()-destination.getY()<=1) //to handle up-> down movement
			{
				return validUpDownMove(start_y, end_y, x);
			}
			else //to handle down-> up movement
			{	
				start_y=origin.getY()-1; //start looking at square one dwon from the start square
				return validDowUpMove(start_y, end_y, x);
			}
		}
		else if (origin.getY() == destination.getY()) //horizontal move
		{
			int start_x=origin.getX()+1; //start looking at squares one away from start square
			int end_x=destination.getX();
			int y=destination.getY(); //this y coord will not change throughout this method
			if(origin.getX()-destination.getX()<=1) //to handle left-> right movement
			{
				return validLeftRightMove(start_x, end_x, y);
			}
			else //to handle right-> left movement
			{
				start_x=origin.getX()-1;
				return validRightLeftMove(start_x, end_x, y);
			}
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
