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
	* @param value-encoded value of king's location
	* @return true-if exit fort has occured
	* @return false-if exit fort has not occured
	*/
	public boolean exitFort(int value)
	{
		int edge=isKingOnEdge(value); //get edge
		if(edge!=0)//&&canKingMove(value,edge))//king on edge
		{//fix canKingMove()
			//check that pieces in the kings row/column
			if(edge==1 || edge==2) //east/west wall check up and down first, +11 and -11
			{
				int friendly_1=0;
				int friendly_2=0;
				//loop through spots north and south from kings location
				System.out.println("starting at"+" "+value);
				int curr_spot=value-11;
				while(!isSpecialSquare(curr_spot)) //need to find a white piece north
				{
					System.out.println(" in loop going up:"+ " "+ curr_spot);
					if(!isWhite(curr_spot)&&isMember(curr_spot))
					{
						System.out.println("stop:"+" "+curr_spot);
						break;
					}
					else if(isWhite(curr_spot) && isMember(curr_spot))
					{
						System.out.println("friendly @:"+ " "+curr_spot);
						friendly_1=curr_spot; //setting a friendly_1 var
						//when white piece  is found start checking in the spot directly perpendicular to it
						//then check for a piece a diagonal it (more than 1 possible diagonal)
					}
					curr_spot=curr_spot-11;
				}
				if(friendly_1==0) //if 1 friendly is not found yet exit cannot occur
				{
					System.out.println("no friendly_1");
					return false;
				}
				//at this point we need to have a white piece on the same column
				curr_spot=value+11;
				while(!isSpecialSquare(curr_spot)) //need to find a white piece south
				{
					System.out.println(" in loop going down:"+ " "+ curr_spot);
					if(!isWhite(curr_spot)&&isMember(curr_spot))
					{
						System.out.println("stop:"+" "+curr_spot);
						break;
					}
					else if(isWhite(curr_spot) && isMember(curr_spot))
					{
						System.out.println("friendly @:"+ " "+curr_spot);
						friendly_2=curr_spot; //setting friendly_2 var
					}
					curr_spot=curr_spot+11;
				}
				if(friendly_2==0)
				{
					System.out.println("no friendly_2");
					return false;
				}
				else
				{
					System.out.println("**start finding loop");
					ArrayList<Integer> prev_pieces = new ArrayList<Integer>(); 
					ArrayList<Integer> loop_pieces = new ArrayList<Integer>(); 
					System.out.println("loop?"+" "+loop_(friendly_1, friendly_2, prev_pieces, value,loop_pieces));
				}
			}
			else if(edge==3 || edge==4)
			{
				int friendly_1=0;
				int friendly_2=0;
				System.out.println("starting at"+" "+value);
				int curr_spot=value-1;
				while(!isSpecialSquare(curr_spot)) //special squares at end of row/column always stop there
				{//need to find a white piece west
					System.out.println(" in loop going left:"+ " "+ curr_spot);
					if(!isWhite(curr_spot)&&isMember(curr_spot))
					{
						System.out.println("stop:"+" "+curr_spot);
						break;
					}
					else if(isWhite(curr_spot) && isMember(curr_spot))
					{
						System.out.println("friendly @:"+ " "+curr_spot);
						friendly_1=curr_spot; //setting a friendly_1 var
					}
					curr_spot=curr_spot-1;
				}
				if(friendly_1==0) //if 1 friendly is not found yet exit cannot occur
				{
					System.out.println("no friendly_1");
					return false;
				}
				curr_spot=value+1;
				while(!isSpecialSquare(curr_spot))
				{//need to find a white piece east
					System.out.println(" in loop going right:"+ " "+ curr_spot);
					if(!isWhite(curr_spot)&&isMember(curr_spot))
					{
						System.out.println("stop:"+" "+curr_spot);
						break;
					}
					else if(isWhite(curr_spot) && isMember(curr_spot))
					{
						System.out.println("friendly @:"+ " "+curr_spot);
						friendly_2=curr_spot;
					}
					curr_spot=curr_spot+1;
				}
				if(friendly_2==0)
				{
					System.out.println("no friendly_2");
					return false;
				}
				else
				{
					System.out.println("**start finding loop");
					ArrayList<Integer> prev_pieces = new ArrayList<Integer>(); 
					ArrayList<Integer> loop_pieces = new ArrayList<Integer>();
					System.out.println("loop?"+" "+loop_(friendly_1, friendly_2, prev_pieces, value, loop_pieces));


				}
			}
		}
		else
			System.out.print("no");
		return false;
	}

	private boolean canKingMove(int value, int edge)
	{
		boolean ret_val=false; //initially king cannot move
		Integer [] neighb=getNeighbors(value);
		if(edge==1)//west
		{
			boolean b1=isMember(neighb[0]);
			boolean b2=isMember(neighb[1]);
			boolean b3=isMember(neighb[2]);
			ret_val=b1||b2||b3;
		}
		else if(edge==2)
		{
			boolean b1=isMember(neighb[0]);
			boolean b2=isMember(neighb[1]);
			boolean b3=isMember(neighb[3]);
			ret_val=b1||b2||b3;
		}
		else if(edge==3)
		{
			boolean b1=isMember(neighb[1]);
			boolean b2=isMember(neighb[2]);
			boolean b3=isMember(neighb[3]);
			ret_val=b1||b2||b3;
		}
		else if(edge==4)
		{
			boolean b1=isMember(neighb[0]);
			boolean b2=isMember(neighb[2]);
			boolean b3=isMember(neighb[3]);
			ret_val=b1||b2||b3;
		}
		return ret_val;
	}


	//needs work, once a loop is found need to ensure that no captures can occur in it(no black pieces is in it)
	//and need to make sure none of the pieces making the fort can be cap'd
	private boolean loop_(int start, int stop, ArrayList<Integer> prev_pieces, int kingVal, ArrayList<Integer> loop_pieces)
	{
		//base case
		if(start==stop)//king cannot be a "friendly" piece as well
		{
			loop_pieces.add(stop);
			System.out.println("KING:"+" "+kingVal);
			System.out.println("loop: ");
			for(int i=0; i<loop_pieces.size(); i++)
			{//check for vulnerable pieces here
				System.out.println(loop_pieces.get(i));
			}
			ArrayList<Integer> safe_spots=getSafeRegion(loop_pieces, kingVal);
			System.out.println("safe space:");
			for(int i=0;i<safe_spots.size();i++)
			{
				System.out.println(safe_spots.get(i));
			}
			return true; //made loop
		}
		else
		{
			//add start to prev pieces
			prev_pieces.add(start);
			System.out.println("___starting with"+" "+start);
			//check north
			if(isWhite(start-11)&&isMember(start-11) && !prev_pieces.contains(start-11) && (start-11)!=kingVal)
			{
				System.out.println("checking north");
				loop_pieces.add(start);
				if(loop_(start-11, stop, prev_pieces,kingVal, loop_pieces))
				{
					//loop_pieces.add(start-11);
					return true;
				}
				else 
					return false;
			}
			//check south
			else if(isWhite(start+11)&&isMember(start+11) && !prev_pieces.contains(start+11) && (start+11)!=kingVal)
			{
				System.out.println("checking south");
				loop_pieces.add(start);
				if(loop_(start+11, stop, prev_pieces,kingVal, loop_pieces))
				{
					//loop_pieces.add(start+11);
					return true;
				}
				else 
					return false;
			}
			//check west
			else if(isWhite(start-1)&&isMember(start-1) && !prev_pieces.contains(start-1) && (start-1)!=kingVal)
			{
				System.out.println("checking west");
				loop_pieces.add(start);
				if(loop_(start-1, stop, prev_pieces,kingVal, loop_pieces))
				{
					//loop_pieces.add(start-1);
					return true;
				}
				else 
					return false;
			}
			//check east
			else if(isWhite(start+1)&&isMember(start+1) && !prev_pieces.contains(start+1) && (start+1)!=kingVal)
			{
				System.out.println("checking east");
				loop_pieces.add(start);
				if(loop_(start+1, stop, prev_pieces,kingVal, loop_pieces))
				{
					//loop_pieces.add(start+1);
					return true;
				}
				else 
					return false;
			}
			//check northWest
			else if(isWhite(start-12)&&isMember(start-12) && !prev_pieces.contains(start-12) && (start-12)!=kingVal)
			{
				System.out.println("Checking northWest");
				loop_pieces.add(start);
				if(loop_(start-12, stop, prev_pieces,kingVal, loop_pieces))
				{
					//loop_pieces.add(start-12);
					return true;
				}
				else 
					return false;
			}
			//check northEast
			else if(isWhite(start+10)&&isMember(start+10) && !prev_pieces.contains(start+10) && (start+10)!=kingVal)
			{
				System.out.println("Checking northEast");
				loop_pieces.add(start);
				if(loop_(start+10, stop, prev_pieces,kingVal, loop_pieces))
				{
					//loop_pieces.add(start+10);
					return true;
				}
				else 
					return false;
			}
			//check southWest
			else if(isWhite(start-10)&&isMember(start-10) && !prev_pieces.contains(start-10) && (start-10)!=kingVal)
			{
				System.out.println("Checking southWest");
				loop_pieces.add(start);
				if(loop_(start-10, stop, prev_pieces,kingVal, loop_pieces))
				{
					//loop_pieces.add(start-10);
					return true;
				}
				else 
					return false;
			}
			//check southEast
			else if(isWhite(start+12)&&isMember(start+12) && !prev_pieces.contains(start+12) && (start+12)!=kingVal)
			{
				System.out.println("Checking southEast");
				loop_pieces.add(start);
				if(loop_(start+12, stop, prev_pieces,kingVal, loop_pieces))
				{
					//loop_pieces.add(start+12);
					return true;
				}
				else 
					return false;
			}
			else
				return false;
		}	
	}

	private ArrayList<Integer> getSafeRegion(ArrayList<Integer> loop_pieces,int kingVal)
	{
		ArrayList<Integer> safe_spots = new ArrayList<Integer>();
		for(int i=0; i<loop_pieces.size(); i++)
		{//check for vulnerable pieces here
			if((loop_pieces.get(i).intValue()/10)==kingVal/10)
			{
				System.out.println("same row");
				int temp=loop_pieces.get(i).intValue();
				if(temp>kingVal)
				{
					while(temp!=kingVal)
					{
						temp=temp-1;
						System.out.println("____going left"+" "+temp);
						//if temp is not a piece, not king and not in the array
						//add it
						if(!(safe_spots.contains(temp)) && !(loop_pieces.contains(temp))&& temp!=kingVal)
							safe_spots.add(temp);
					}
				}
				else if(temp<kingVal)
				{
					while(temp!=kingVal)
					{
						temp=temp+1;
						System.out.println("____going right"+" "+temp);
						if(!(safe_spots.contains(temp)) && !(loop_pieces.contains(temp))&& temp!=kingVal)
							safe_spots.add(temp);
					}
				}
			}
			else if(loop_pieces.get(i).intValue()>kingVal)
			{
				System.out.println("go north");
				//int temp=loop_pieces.get(i).intValue()-10;
				int temp=loop_pieces.get(i).intValue();
				while((temp/10)!=kingVal/10)
				{
					temp=temp-11;
					System.out.println("going up"+" "+temp);
					if(!(safe_spots.contains(temp)) && !(loop_pieces.contains(temp))&& temp!=kingVal)
							safe_spots.add(temp);
				}
				if(temp>kingVal)
				{
					while(temp!=kingVal)
					{
						temp=temp-1;
						System.out.println("____going left"+" "+temp);
						if(!(safe_spots.contains(temp)) && !(loop_pieces.contains(temp))&& temp!=kingVal)
							safe_spots.add(temp);
					}
				}
				else if(temp<kingVal)
				{
					while(temp!=kingVal)
					{
						temp=temp+1;
						System.out.println("____going right"+" "+temp);
						if(!(safe_spots.contains(temp)) && !(loop_pieces.contains(temp))&& temp!=kingVal)
							safe_spots.add(temp);
					}
				}
			}
			else if(loop_pieces.get(i).intValue()<kingVal)
			{
				System.out.println("go south");
				int temp=loop_pieces.get(i).intValue();
				while((temp/10)!=kingVal/10)
				{
					temp=temp+11;
					System.out.println("going down"+" "+temp);
					if(!(safe_spots.contains(temp)) && !(loop_pieces.contains(temp))&& temp!=kingVal)
						safe_spots.add(temp);
				}
				if(temp>kingVal)
				{
					while(temp!=kingVal)
					{
						temp=temp-1;
						System.out.println("___going left"+" "+temp);
						if(!(safe_spots.contains(temp)) && !(loop_pieces.contains(temp))&& temp!=kingVal)
							safe_spots.add(temp);
					}
				}
				else if(temp<kingVal)
				{
					while(temp!=kingVal)
					{
						temp=temp+1;
						System.out.println("___going right"+" "+temp);
						if(!(safe_spots.contains(temp)) && !(loop_pieces.contains(temp))&& temp!=kingVal)
							safe_spots.add(temp);
					}
				}
			}
		}

		return safe_spots;
	}

	/**
	* @param value-encoded value of king's location
	* @return 1-if king is on west wall
	* @return 2-if king is on east wall
	* @return 3-if king is on south wall
	* @return 4-if king is on north wall
	* @rerurn 0-if king is NOT on edge
	*/
	private int isKingOnEdge(int value)
	{
		Coordinate coord=decode(value);
		if((coord.getX() - 1 < 0) && (coord.getY() - 1 >= 0)) // west wall
		{
			System.out.println("West Wall");
			return 1;
		}
		else if((coord.getX() + 1 > 10) && (coord.getY() + 1 <= 10)) //east wall
		{
			System.out.println("East wall");
			return 2;
		}
		else if((coord.getX() - 1 >= 0) && (coord.getY() + 1 > 10)) // south wall
		{
			System.out.println("South wall");
			return 3;
		}
		else if((coord.getX() + 1 <= 10) && (coord.getY() - 1 < 0)) // north wall
		{
			System.out.println("North wall");
			return 4;
		}
		else
			return 0;
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
					return_list.add(decode(value - 11 * offset));
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
					return_list.add(decode(value + 11 * offset));
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
					return_list.add(decode(value - 11 * offset));
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
					return_list.add(decode(value + 11 * offset));
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
					return_list.add(decode(value - offset));
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
					return_list.add(decode(value + offset));
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
					return_list.add(decode(value - offset));
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
					return_list.add(decode(value + offset));
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
	
}
