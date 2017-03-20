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
		else if(index==0)
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
	* returns true if the value is a special square
	* @param integer value of a location
	* @return true if the value is a special square
	* @return false if value is not a special square
	*/
	public boolean specialSquare(int value)	
	{
		if(value==11 || value==1 || value==111 || value==121)
			return true;
		else
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

	//going to return an array of pieces captured
	public ArrayList<Coordinate> pieceLost(int value)
	{
		Integer[] neighbors = getNeighbors(value); 	//gets neighbors of the coordinate
		ArrayList<Integer> enemyNeighbors = new ArrayList<Integer>();
		ArrayList<Integer>piecesToRemove=new ArrayList<Integer>();
		System.out.println("start here "+value + "*****");  //the value for which we will check neighbors
		for(int x=0; x<neighbors.length;x++) //directions -11 is up, +11 is down, +1 is right, -1 is left
		{									// get neighbors returns +11, -11, +1, -1
			if(isWhite(value))
			{
				if((!isWhite(neighbors[x]) && isMember(neighbors[x])))
				{
					enemyNeighbors.add(neighbors[x]); //add enemy neighbor to array of enemies
				}
			}
			else //turn is black
			{	
				if((isWhite(neighbors[x]))&&isMember(neighbors[x]))
				{
					enemyNeighbors.add(neighbors[x]);
				}
			}
		}
		if(enemyNeighbors.size()==0) //no  enemy neighbors
		{
			System.out.println("no enemyNeighbors");
		}
		else //time to check to see if piece will be captured
		{
			//will need to loop through array of enemies and check the direction of where they are relation to value
			//once directior is determined, see if there is a friendly piece 2 spaces away in that direction
			if(isWhite(value))//white turn, utilize special square captures here
			{	
				for(int i=0; i<enemyNeighbors.size();i++)
				{
					System.out.println("enemy @ "+enemyNeighbors.get(i).intValue());
					int direction=0;
					int temp_value=value; //to check for friendly pieces
					direction=enemyNeighbors.get(i).intValue()-value;
					System.out.println("enemy direction :"+direction);
					if(direction==-11) //up
					{
						temp_value=temp_value-22; //check 2 spaces away
						boolean teammate=isMember(temp_value)&&isWhite(temp_value);
						//check for special square, check four squares
						System.out.println("teammate to the north :"+teammate);
						if(teammate || specialSquare(temp_value))
						{
							//System.out.println("remove piece @ "+enemyNeighbors.get(i).intValue());
							piecesToRemove.add(enemyNeighbors.get(i));
						}
					}
					else if (direction==11) //down
					{
						temp_value=temp_value+22; //check 2 spaces away
						boolean teammate=isMember(temp_value)&&isWhite(temp_value);
						System.out.println("teammate to the south :"+teammate);
						if(teammate|| specialSquare(temp_value))
						{
							//System.out.println("remove piece @ "+enemyNeighbors.get(i).intValue());
							piecesToRemove.add(enemyNeighbors.get(i));
						}
					}
					else if(direction==1) //right
					{
						temp_value=temp_value+2; //check 2 spaces away
						boolean teammate=isMember(temp_value)&&isWhite(temp_value);
						System.out.println("teammate to the west :"+teammate);
						if(teammate|| specialSquare(temp_value))
						{
							//System.out.println("remove piece @ "+enemyNeighbors.get(i).intValue());
							piecesToRemove.add(enemyNeighbors.get(i));
						}
					}
					else if(direction==-1)//left
					{	
						temp_value=temp_value-2; //check 2 spaces away
						boolean teammate=isMember(temp_value)&&isWhite(temp_value);
						System.out.println("teammate to the east :"+teammate);
						if(teammate|| specialSquare(temp_value))
						{
							//System.out.println("remove piece @ "+enemyNeighbors.get(i).intValue());
							piecesToRemove.add(enemyNeighbors.get(i));
						}
					}
				}
			}
			else if(!isWhite(value))//black turn
			{
				for(int i=0; i<enemyNeighbors.size();i++)
				{
					System.out.println("enemy @ "+enemyNeighbors.get(i).intValue());
					int direction=0;
					int temp_value=value; //to check for friendly pieces
					direction=enemyNeighbors.get(i).intValue()-value;
					System.out.println("enemy direction :"+direction);
					if(direction==-11) //up
					{
						temp_value=temp_value-22; //check 2 spaces away
						boolean teammate=(isMember(temp_value)&&!isWhite(temp_value));
						System.out.println("teammate to the north :"+teammate);
						if(teammate)
						{
							//System.out.println("remove piece @ "+enemyNeighbors.get(i).intValue());
							piecesToRemove.add(enemyNeighbors.get(i));
						}
					}
					else if (direction==11) //down
					{
						temp_value=temp_value+22; //check 2 spaces away
						boolean teammate=(isMember(temp_value)&&!isWhite(temp_value));
						System.out.println("teammate to the south :"+teammate);
						if(teammate)
						{
							//System.out.println("remove piece @ "+enemyNeighbors.get(i).intValue());
							piecesToRemove.add(enemyNeighbors.get(i));
						}
					}
					else if(direction==1) //right
					{
						temp_value=temp_value+2; //check 2 spaces away
						boolean teammate=(isMember(temp_value)&&!isWhite(temp_value));
						System.out.println("teammate to the west :"+teammate);
						if(teammate)
						{
							//System.out.println("remove piece @ "+enemyNeighbors.get(i).intValue());
							piecesToRemove.add(enemyNeighbors.get(i));
						}
					}
					else if(direction==-1)//left
					{	
						temp_value=temp_value-2; //check 2 spaces away
						boolean teammate=(isMember(temp_value)&&!isWhite(temp_value));
						System.out.println("teammate to the east :"+teammate);
						if(teammate)
						{
							//System.out.println("remove piece @ "+enemyNeighbors.get(i).intValue());
							piecesToRemove.add(enemyNeighbors.get(i));
						}
					}
				}
			}
		}
		//display remove array
		ArrayList<Coordinate>coordsToRemove=new ArrayList<Coordinate>();
		for(int i=0; i<piecesToRemove.size();i++)
		{
			Coordinate temp=decode(piecesToRemove.get(i));
			coordsToRemove.add(temp);
			System.out.println("remove piece @ "+piecesToRemove.get(i));
		}
		return coordsToRemove;
	}
}
