import java.lang.*;
import java.util.*;
import java.util.stream.*;

public class Data
{
 
	public Integer[] boardData; // Should be public so that the Board object can actually access the coordinates
	public Integer[] specialSquares; 
	
	
	/**
	* The Data object constructor. Initializes the boardData array along with the specialSquares array. 
	*/
	public Data()
	{
	    Integer[] initialData = {61, 59, 60, 62, 63, 49, 50, 51, 39, 71, 72, 73, 83, 4, 5, 6, 7, 8, 17, 34, 44, 45, 55, 56, 57, 65, 66, 67, 77, 78, 88, 105, 114, 115, 116, 117, 118
	    };
		Integer[] special = {1, 11, 61, 111, 121};
        boardData = initialData.clone();
		specialSquares = special.clone();
	}
    
	
	/**
	* Decodes the Coordinate object for storage
	* @param-value the int value of the location
	* @return returns a coordinate object for that location
	*/
	public Coordinate decode(int value)
	{
		int x, y;
		if (value < 0)
		{
			x = -1;
			y = -1;
		}
		else
		{
			x = value % 11 - 1;
			if (x < 0) x = 10;

			y = value / 11;
			if (value % 11 == 0) y = y - 1;
		}
		
		return new Coordinate(x, y);
	}
	
	/**
	* Returns an index based on the coordinates. It would return -1 if the index is not found. 
	* @param data-coordinates of a piece
	* @return returns an index value
	*/
	public int getIndex(int value)
	{
		return Arrays.asList(boardData).indexOf(value);
	}
	
	/**
	* Takes a value and an index and sets the value to the index in the boardData array.  
	* @param index
	* @param value
	*/
	public void set(int index, int value)
	{
		boardData[index] = value;	
	}
	
	
	/**
	* Returns the Coordinate object, based on the index (0-36) of the array. If there is no piece in
	* the game, it will return 0. 
	* @param index-index value from array
	* @return -1 if no piece in the game
	* @return the decoded value of the index if the piece is in the game
	*/
	public Coordinate getCoordinate(int index)
	{
		int value = boardData[index];
		if (value == -1)
			return null;
		else
			return decode(value);
	}
	
	/**
	* Returns true if the coordinates are in the array. Otherwise, it returns false. 
	* @param data-coordinate of a location
	* @return true if the coordinates are in the array
	* @return false if the coordinates are not in the array
	*/	
	public boolean isMember (int value)
	{
		return Arrays.asList(boardData).contains(value);
	}
	
	
	/**
	* Returns true if the piece in the given value is white. Otherwise, returns false
	* @param data-coordinate of a location
	* @return true if the piece is white
	* @return false if the piece is not white
	*/
	public boolean isWhite(int value)
	{
		// returns true if the piece is white. white means its index is between 0 and 12. The method assumes that the coordinate contains a piece. 
		int index = Arrays.asList(boardData).indexOf(value);
		if (index < 13 && index > 0)
			return true;
		else 
			return false;
	}
	/**
	* Returns true if the king in the given value is on the special corner squares. Otherwise, returns false
	* @param integer value of a location
	* @return true if the king is on a special square
	* @return false if the king is not on a special square
	*/
	public boolean isKingOnSpecialSquare(int value)
	{
		if (value != 61) // if the square isn't the throne
		{
			return Arrays.asList(specialSquares).contains(value);
		}
		return false;	
	}	
	
	/**
	* Returns true if the king is surrounded by black pieces in four directions. 
	* @param an encoded value of the Coordinate object
	* @return true if the king is surrounded by black pieces
	* @return false if the king is not
	*/
	public boolean kingLost(int value)
	{
		Integer[] neighbors = getNeighbors(value); // contains values to check against
		ArrayList<Integer> checked = new ArrayList<Integer>();
		for(int i = 0; i < neighbors.length; i++)
		{
			if (isMember(neighbors[i]) && (!isWhite(neighbors[i]))) // condition 1: the square must contain a black piece
			{
				checked.add(neighbors[i]);
			}
			else if (neighbors[i] == 61) // or condition 2: if the throne (61) is a neighbor
			{
				checked.add(neighbors[i]);
			}
		}
		if (checked.size() >= 4) // black pieces/ special squares are more than 4, 
		{ 
			return true; // which means the king is surrounded. 
		}
		return false;	
	}
	
	/**
	* Returns an array of integers of squares to check if they are taken
	* @param an encoded value of the Coordinate object
	* @return an array of Integers containing values of neighboring squares.
	*/
	private Integer[] getNeighbors(int value)
	{
		Integer[] arr = {value + 11, value - 11, value + 1, value - 1};
		return arr;
	}	
	
	/**
	* Returns true if the value is in the special squares array. Otherwise returns false. 
	* @param an encoded value of the Coordinate object
	* @return true if the value is a special square
	* @return false if the value is not a special square
	*/	
	private boolean isSpecialSquare(int value)
	{
		return Arrays.asList(specialSquares).contains(value);
	}

	public boolean pieceLost(int value)
	{
		Integer[] neighbors = getNeighbors(value); 	//gets neighbors of the coordinate

		System.out.println(isWhite(value)); //will be used to determine which pieces are friendly or foe
		//!isWhite(value) will determine which pieces are foes
		//will need to use isMember method to see if the pieces are actually there

		System.out.println(value);  //the value for which we will check neighbors
		/*
		for(int x=0; x<neighbors.length;x++)
		{
			System.out.println(neighbors[x]);
		}
		*/
		for(int x=0; x<neighbors.length;x++)
		{
			if(isWhite(value))
			{
				if((!isWhite(neighbors[x]) && isMember(neighbors[x])))
				{
					//get direction of enemy neighbors
					System.out.println(neighbors[x]);
				}
			}
			else 
			{	
				if((isWhite(neighbors[x]))&&isMember(neighbors[x]))
				{
					//get direction of enemy neighbors
					System.out.println(neighbors[x]);
				}
			}
			
		}
		return true;
	}
	
	
	
	
}
