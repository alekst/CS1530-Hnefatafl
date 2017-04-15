import java.lang.*;
import java.util.*;
import java.util.stream.*;

public class Data
{
 
	public Integer[] boardData; // Should be public so that the Board object can actually access the coordinates
	public Integer[] specialSquares; 
	
	private ArrayList<Integer> seenPieces = new ArrayList<Integer>();
	
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
  * The Data object constructor. Initializes the boardData array along with the specialSquares array. 
  */
  public Data(Integer[] boardStatus)
  {
    Integer[] initialData = boardStatus;
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
	
	/**
	* "Prints" the current board status by returning a comma deliniated string for use in saving to a file 
	*/
	public String print()
	{
    String csv_out = new String();
    for (int i=0; i < boardData.length; i++)
    {
      csv_out = csv_out + Integer.toString(boardData[i]);
      if (i != (boardData.length - 1))
      {
        csv_out = csv_out + ',';
      }
    }
    return csv_out;
  }

	/**
	* determines if a piece should be removed
	* @param temp_value-the location of where a teammate should be for a capture
	* @param turn-whose turn it is, true=white, false=black
	* @return true if a piece should be captured
	* @return false if a piece should not be captured
	*/
	private boolean isPieceRemoved(int temp_value, boolean turn)
	{ //tested indirectly via pieceLost()
		if(turn)
		{
			boolean teammate=isMember(temp_value)&&isWhite(temp_value);
			return teammate||isSpecialSquare(temp_value);
		}
		else
		{
			boolean teammate=isMember(temp_value)&&!isWhite(temp_value);
			return teammate||temp_value==61;
		}
	}

	//get enemy neigbors method
	/**
	* Gets the neighboring enemy pieces of a location
	* @param value- an encoded value of the Coordinate object
	* @return an arrayList of nieghboring Integers containing locations of enemy pieces
	*/
	private ArrayList<Integer> getEnemyNeighbors(int value)
	{//tested indirectly via pieceLost
		Integer[] neighbors = getNeighbors(value); 	//gets neighbors of the coordinate
		ArrayList<Integer> enemyNeighbors = new ArrayList<Integer>(); //array of enemy neighbors
		for(int x=0; x<neighbors.length;x++) 
		{
			if(isWhite(value)) //turn is white
			{
				if((!isWhite(neighbors[x]) && isMember(neighbors[x]))) //if piece is black and on the board
				{
					enemyNeighbors.add(neighbors[x]); //add enemy neighbor to array of enemies
				}
			}
			else //turn is black
			{	
				if((isWhite(neighbors[x]))&&isMember(neighbors[x])) //if piece is white and on the board
				{
					enemyNeighbors.add(neighbors[x]);
				}
			}
		}
		return enemyNeighbors;
	}

	//going to return an array of pieces captured
	/**
	* Determines which pieces are captured during a move
	* @param value-the value of the location of piece
	* @return an arraylist of coordinates to remove
	*/
	public ArrayList<Coordinate> pieceLost(int value)  
	{ 
		ArrayList<Integer> enemyNeighbors =getEnemyNeighbors(value); //array of enemy neighbors
		ArrayList<Coordinate>coordsToRemove=new ArrayList<Coordinate>(); //array of coordinates to remove
		if(enemyNeighbors.size()==0) //no  enemy neighbors
		{
			//do nothing
		}
		else //time to check to see if piece will be captured
		{
			//will need to loop through array of enemies and check the direction of where they are in relation to value
			//once direction is determined, see if there is a friendly piece 2 spaces away in that direction	
			for(int i=0; i<enemyNeighbors.size();i++) 
			{
				int direction=0;
				direction=enemyNeighbors.get(i).intValue()-value; //direction of enemy piece
				if(direction==-11) //up
				{	
					if(isPieceRemoved(value-22,isWhite(value)))
					{
						if(getIndex(enemyNeighbors.get(i))!=0)//king can not be captured via sandwich capture
							coordsToRemove.add(decode(enemyNeighbors.get(i)));
					}
				}
				else if (direction==11) //down
				{	
					if(isPieceRemoved(value+22,isWhite(value)))
					{
						if(getIndex(enemyNeighbors.get(i))!=0)//king can not be captured via sandwich capture
							coordsToRemove.add(decode(enemyNeighbors.get(i)));
					}
				}
				else if(direction==1) //right
				{
					if(isPieceRemoved(value+2,isWhite(value)))
					{
						if(getIndex(enemyNeighbors.get(i))!=0)//king can not be captured via sandwich capture
							coordsToRemove.add(decode(enemyNeighbors.get(i)));
					}
				}
				else if(direction==-1)//left
				{	
					if(isPieceRemoved(value-2,isWhite(value)))
					{
						if(getIndex(enemyNeighbors.get(i))!=0) //king can not be captured via sandwich capture
							coordsToRemove.add(decode(enemyNeighbors.get(i)));
					}
				}
			}
		}
		return coordsToRemove;
	}
	
	/**
	* Returns the array of coordinates of those pieces that must be removed. 
	* Looks to see if the value is on one of the walls
	* 	If this is true then depending on the wall we search through all opposite
	*	player pieces that are next to the piece along that wall until we hit
	*	a piece of the player who is doing the capturing.
	* As we traverse through the opponent pieces on the wall
	* 	we gather up the pieces into an arraylist that we will eventually return 
	* @param value-tile of the last moved piece
	* @return ArrayList<Coordinate> - Each piece that is meant to be removed
	*/
	public ArrayList<Coordinate> shieldWallCapture(int value)
	{
		// Check if piece landed on a wall and 
		// have a unique case for each wall
		ArrayList<Coordinate> return_list = new ArrayList<Coordinate>();
		Coordinate coord = decode(value);
		int streak_count = 0;
		int offset = 1;
		if((coord.getX() - 1 < 0) && (coord.getY() - 1 >= 0)) // west wall
		{
			while(isMember(value - 11 * offset) && (isWhite(value - 11 * offset) != isWhite(value))
				&& isMember(value - 11 * offset + 1) && (isWhite(value - 11 * offset + 1) == isWhite(value)))
				{
					if(getIndex(value - 11 * offset) != 0)
					{	
						return_list.add(decode(value - 11 * offset));
					}
					else
					{
						// It's a king...don't add to removal list
					}
					streak_count++;
					if(((isMember(value - 11 * (offset + 1)) && (isWhite(value - 11 * (offset + 1)) == (isWhite(value)))) 
						|| (isSpecialSquare(value - 11 * (offset + 1)))) && streak_count > 1)
					{
						return return_list;
					}
					offset++;
				}
			offset = 1;
			while(isMember(value + 11 * offset) && (isWhite(value + 11 * offset) != isWhite(value))
				&& isMember(value + 11 * offset + 1) && (isWhite(value + 11 * offset + 1) == isWhite(value)))
				{
					streak_count++;
					if(getIndex(value + 11 * offset) != 0)
					{	
						return_list.add(decode(value + 11 * offset));
					}
					else
					{
						// It's a king...don't add to removal list
					}
					if(((isMember(value + 11 * (offset + 1)) && (isWhite(value + 11 * (offset + 1)) == (isWhite(value))))
						|| (isSpecialSquare(value + 11 * (offset + 1)))) && streak_count > 1)
					{
						return return_list;
					}
					offset++;
				}
		}	
		else if((coord.getX() + 1 > 10) && (coord.getY() + 1 <= 10)) // east wall
		{
			while(isMember(value - 11 * offset) && (isWhite(value - 11 * offset) != isWhite(value))
				&& isMember(value - 11 * offset - 1) && (isWhite(value - 11 * offset - 1) == isWhite(value)))
				{
					streak_count++;
					if(getIndex(value - 11 * offset) != 0)
					{	
						return_list.add(decode(value - 11 * offset));
					}
					else
					{
						// It's a king...don't add to removal list
					}
					if(((isMember(value - 11 * (offset + 1)) && (isWhite(value - 11 * (offset + 1)) == (isWhite(value))))
						|| isSpecialSquare(value - 11 * (offset + 1))) && streak_count > 1)
					{
						return return_list;
					}
					offset++;
				}
			offset = 1;
			while(isMember(value + 11 * offset) && (isWhite(value + 11 * offset) != isWhite(value))
				&& isMember(value + 11 * offset - 1) && (isWhite(value + 11 * offset - 1) == isWhite(value)))
				{
					streak_count++;
					if(getIndex(value + 11 * offset) != 0)
					{	
						return_list.add(decode(value + 11 * offset));
					}
					else
					{
						// It's a king...don't add to removal list
					}
					if(((isMember(value + 11 * (offset + 1)) && (isWhite(value + 11 * (offset + 1)) == (isWhite(value))))
						|| isSpecialSquare(value + 11 * (offset + 1))) && streak_count > 1)
					{
						return return_list;
					}
					offset++;
				}
		}
		else if((coord.getX() - 1 >= 0) && (coord.getY() + 1 > 10)) // south wall
		{
			while(isMember(value - offset) && (isWhite(value - offset) != isWhite(value))
				&& isMember(value - offset - 11) && (isWhite(value - offset - 11) == isWhite(value)))
				{
					streak_count++;
					if(getIndex(value - offset) != 0)
					{	
						return_list.add(decode(value - offset));
					}
					else
					{
						// It's a king...don't add to removal list
					}
					if(((isMember(value - (offset + 1)) && (isWhite(value - (offset + 1)) == (isWhite(value))))
						|| isSpecialSquare(value - (offset + 1))) && streak_count > 1)
					{
						return return_list;
					}
					offset++;
				}
			offset = 1;
			while(isMember(value + offset) && (isWhite(value + offset) != isWhite(value))
				&& isMember(value + offset - 11) && (isWhite(value + offset - 11) == isWhite(value)))
				{
					streak_count++;
					if(getIndex(value + offset) != 0)
					{	
						return_list.add(decode(value + offset));
					}
					else
					{
						// It's a king...don't add to removal list
					}
					if(((isMember(value + (offset + 1)) && (isWhite(value + (offset + 1)) == (isWhite(value))))
						|| isSpecialSquare(value + (offset + 1))) && streak_count > 1)
					{
						return return_list;
					}
					offset++;
				}
		}
		else if((coord.getX() + 1 <= 10) && (coord.getY() - 1 < 0)) // north wall
		{
			while(isMember(value - offset) && (isWhite(value - offset) != isWhite(value))
				&& isMember(value - offset + 11) && (isWhite(value - offset + 11) == isWhite(value)))
				{
					streak_count++;
					if(getIndex(value - offset) != 0)
					{	
						return_list.add(decode(value - offset));
					}
					else
					{
						// It's a king...don't add to removal list
					}
					if(((isMember(value - (offset + 1)) && (isWhite(value - (offset + 1)) == (isWhite(value))))
						|| isSpecialSquare(value - (offset + 1))) && streak_count > 1)
					{
						return return_list;
					}
					offset++;
				}
			offset = 1;
			while(isMember(value + offset) && (isWhite(value + offset) != isWhite(value))
				&& isMember(value + offset + 11) && (isWhite(value + offset + 11) == isWhite(value)))
				{
					streak_count++;
					if(getIndex(value + offset) != 0)
					{	
						return_list.add(decode(value + offset));
					}
					else
					{
						// It's a king...don't add to removal list
					}
					if(((isMember(value + (offset + 1)) && (isWhite(value + (offset + 1)) == (isWhite(value))))
						|| isSpecialSquare(value + (offset + 1))) && streak_count > 1)
					{
						return return_list;
					}
					offset++;
				}
		}
		else
		{
			// Not on an edge or is a corner so there is no shield 
			// wall capture
			return return_list;
		}
		return_list.clear();
		return return_list;
	}
	
	/*
	* checks to see if there is encircling and therefore a win
	* @param value - location of last moved piece
	* @return true - encircling is happening
	* @return false - there is no encircling
	*/
	public boolean isEncircled(int value)
	{
		seenPieces.clear();
		
		if(isWhite(value)) // no need to check if the last move was a white move
		{
			return false;
		}
		else if(isLooped(value))
		{
			return isEncapsulated();
		}
		else
		{
			return false;
		}
		
	}
	
	/*
	* returns whether or not there is a loop of black pieces connected by the last moved piece
	* @param value - location of last moved piece
	* @return true - there is a loop
	* @return false - there is no loop
	*/
	
	private boolean isLooped(int value)
	{
		seenPieces.add(value);
		
		Integer[] neighbor_values;
		
		if(value == 1)
		{
			neighbor_values = new Integer[] {value + 1, value + 11, value + 12};
		}
		else if(value == 11)
		{
			neighbor_values = new Integer[] {value - 1, value + 10, value + 11};
		}
		else if(value < 12)
		{
			neighbor_values = new Integer[] {value + 1, value - 1, value + 10, value + 11, value + 12};
		}
		else if(value == 111)
		{
			neighbor_values = new Integer[] {value + 1, value - 10, value - 11};
		}
		else if(value == 121)
		{
			neighbor_values = new Integer[] {value - 11, value - 12, value - 1};
		}
		else if(value % 11 == 1)
		{
			neighbor_values = new Integer[] {value + 1, value - 10, value - 11, value + 11, value + 12};
		}
		else if(value % 11 == 0)
		{
			neighbor_values = new Integer[] {value - 11, value - 12, value - 1, value + 10, value + 11};
		}
		else if(value > 111)
		{
			neighbor_values = new Integer[] {value + 1, value - 10, value - 11, value - 12, value - 1};
		}
		else 
		{
			neighbor_values = new Integer[] {value + 1, value - 10, value - 11, value - 12, value - 1, value + 10, value + 11, value + 12};
		}
		
		if(seenPieces.size() > 3 && (seenPieces.get(seenPieces.size() - 1) == seenPieces.get(0)))
		{
			return true;
		}
		for(int i = 0 ; i < neighbor_values.length ; i++)
		{
			if(seenPieces.size() == 1)
			{
				if(isMember(neighbor_values[i]) && (! isWhite(neighbor_values[i])))
				{
					if(isLooped(neighbor_values[i]))
					{
						return true;
					}
				}
			}
			if(isMember(neighbor_values[i]) && (! isWhite(neighbor_values[i]) && (! seenPieces.subList(1, seenPieces.size()).contains(neighbor_values[i]))))
			{
				if(isLooped(neighbor_values[i]))
				{
					return true;
				}
			}
		}
		seenPieces.remove(seenPieces.size() - 1);
		return false;
	}
	
	/*
	* checks to see if all white pieces are within two black pieces on the board
	* acts as half of the isencircled method
	* @return - returns whether or not there is encapsulation
	*/
	private boolean isEncapsulated()
	{
		boolean first_seen = false;
		boolean second_seen = true;
		
		boolean nothing = true;
		
		for(int i = 1 ; i < 12 ; i++)
		{
			for(int j = 0 ; j < 11 ; j++)
			{
				if((! first_seen) && isWhite(i + (11 * j)))
				{
					return false;
				}
				else if((! first_seen) && isMember(i + (11 * j)) && (! isWhite(i + (11 * j))))
				{
					nothing = false;
					first_seen = true;
				}
				else if((first_seen) && isWhite(i + (11 * j)))
				{
					nothing = false;
					second_seen = false;
				}
				else if((first_seen) && isMember(i + (11 * j)) && (! isWhite(i + (11 * j))))
				{
					nothing = false;
					second_seen = true;
				}
			}
			if((!(first_seen && second_seen)) && (! nothing))
			{
				return false;
			}
			else
			{
				nothing = true;
				first_seen = false;
				second_seen = true;
			}
		}
		
		first_seen = false;
		second_seen = true;
		for(int j = 1 ; j < 12 ; j++)
		{
			for(int i = 0 ; i < 11 ; i++)
			{
				if((! first_seen) && isWhite(i + (11 * j)))
				{
					return false;
				}
				else if((! first_seen) && isMember(i + (11 * j)) && (! isWhite(i + (11 * j))))
				{
					first_seen = true;
				}
				else if((first_seen) && isWhite(i + (11 * j)))
				{
					second_seen = false;
				}
				else if((first_seen) && isMember(i + (11 * j)) && (! isWhite(i + (11 * j))))
				{
					second_seen = true;
				}
			}
			if((!(first_seen && second_seen)) && (! nothing))
			{
				return false;
			}
			else
			{
				nothing = true;
				first_seen = false;
				second_seen = true;
			}
		}
		return true;
		
	}
}




































