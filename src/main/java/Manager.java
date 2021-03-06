import java.lang.*;
import java.util.*;
import java.util.stream.*;

public class Manager
{
	private Data _data;
	private Coordinate[] _whites;
	private Coordinate[] _blacks;
	private Coordinate[] _board;
	

	private static final int numWhites = 13;
	private static final int numBlacks = 24;
	private static final int totalPieces = 37;
	
	/**
	* initialize a data object with initial board configuration
	*/
	public Manager()
	{
		_data = new Data();
	}
	
	/**
	* initialize a data object with specified board configuration
	*/
	public Manager(Data data)
	{
		_data = data;
	}
	
	/**
	* This setter is used by tests only. 
	*/
	public void setData (Data data)
	{
		_data = data;
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
	* Returns an index based on the coordinates. It would return -1 if the index is not found. 
	* @param data-coordinates of a piece
	* @return returns an index value
	*/
	public int getIndex(Coordinate data)
	{
		int value = encode(data);
		int index = _data.getIndex(value);
		return index;	
	}
	
	/**
	* @retrun the coordinates of the king
	*/
	public Coordinate getKing()
	{
		return _data.getCoordinate(0);
	}
	
	/**
	* @return whites-returns an array containing the coordiantes of all the whtie pieces
	*/
	public Coordinate[] getWhites()
	{
		Coordinate[] whites = new Coordinate[numWhites];
		for (int i = 0; i < numWhites; i++)
		{
			whites[i] = _data.getCoordinate(i);
		}
		return whites;
	}
	/**
	* @return blacks-returns an array containing the coordiantes of all the black pieces
	*/
	public Coordinate[] getBlacks()
	{
		Coordinate[]blacks = new Coordinate[numBlacks];
		for (int i = 0; i < numBlacks; i++)
		{
			blacks[i] = _data.getCoordinate(i + 13);
		}
		return blacks;
	}
	
	/** 
	* @Return a current board status as an array of Coordinate objects
	*
	*/ 
	public Coordinate[] getBoardStatus()
	{
		_board = new Coordinate[37];
		for (int i = 0; i < 37; i++)
		{
			Coordinate c =  _data.getCoordinate(i);
			_board[i] = c;
			
		}
		return _board;
	}
	
	/** 
	* @return a current board status as a Data object
	*
	*/ 
	public Data getBoardData()
	{
		return _data;
	}
		
	/**
	* @param coord-The coordinate object of a locatio
	* @return true-if the coordinate contains a white piece
	* @return false-if the coordinate does not contain a white piece
	*/
	public boolean isWhite(Coordinate coord)
	{
		int value = encode(coord);
		return _data.isWhite(value);
	}
	
	/**
	* Resets the data
	*/
	public void reset()
	{
		_data = new Data();
	}
	
	/**
	* @param Coordinate objects for new coordinates, and old coordinates
	* Takes the source coordinates and the destination coordinates and updates the array
	*/
	public void updateLocation(Coordinate newdata, Coordinate olddata)
	{
		int newValue = encode(newdata);
		int index = getIndex(olddata);
		_data.set(index, newValue);
	}
	
	/**
	* Returns true if there is another piece on the square. Otherwise, returns false. 
	* @param Coordinate objects for new coordinates, and old coordinates
	* @return true if there is a piece in the square
	* @return false if there is not. 
	*/
	public boolean someoneThere(Coordinate data)
	{
		int value = encode(data);
		return _data.isMember(value);
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
	
	/**
	* @param origin-Coordinate of origin location
	* @param destination-Coordinate of destination location
	* @return true-if origin and destination location are both occupied
	* @return false-if both are not occupied
	*/
	private boolean inSameSpot(Coordinate origin, Coordinate destination)
	{
		if(someoneThere(origin) && someoneThere(destination)) //both coordinates are occupied
		{
			return true;
		}
		else
			return false;
	}

	/**
	* if a player cannot move they lose the game
	* @param location-location of the piece
	* @return true-if piece cannot move
	* @return false if piece can move
	*/
	public boolean rule_9(Coordinate location)
	{
		int loc=encode(location);
		//see if its sandwiched on edge
		if(loc%11==0) //east wall
		{
			//get up, down and left neighbors
			return _data.rule_9(loc,2);
		}
		else if(loc>1 && loc<11) //north wall
		{
			//get left, right, down neighbors
			return _data.rule_9(loc, 1);
		}
		else if(loc>111 && loc<121) //south wall
		{
			return _data.rule_9(loc, 3);
			//get up, left, right neighbors
		}
		else if(loc==1 || loc==12 || loc==23 || loc==34 || loc==47 || loc==56 || loc==67 || loc==78 || loc==89 ||loc==100) //west wall
		{
			return _data.rule_9(loc, 4);
			//get up, down, right neighbors
		}

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
		for(int y=start_y; y < end_y; y++)
		{
			Coordinate temp_obj=new Coordinate(x,y); //temp obj used to see if a piece is in movement path
			if(someoneThere(temp_obj))
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
			if(someoneThere(temp_obj))
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
			if(someoneThere(temp_obj))
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
			if(someoneThere(temp_obj))
			{
				return false;//piece in movement path
			}
		}
		return true;
	}
	/**
	* Determines if a piece is trying to move to a special square
	* @param x-the x coordinate of the destination
	* @param y-the y coordinate of the destination
	* @return false-if the destination is not one of the special squares
	* @return true-if the destination is one of the special squares
	*/
	public boolean inSpecialSquare(int x, int y)
	{
		if(x==0 && y==0)
		{
			return true;
		}
		else if(x==10 && y==0)
		{
			return true;
		}
		else if(x==0 && y==10)
		{
			return true;
		}
		else if(x==10 && y==10)
		{
			return true;
		}
		else if(x==5 && y==5)
		{
			return true;
		}
		else
		{
			return false;
		}
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
 		if(inSameSpot(origin, destination)) //both coordinates are occupied
 		{
 			return false;
 		}
 		if(inSpecialSquare(destination.getX(), destination.getY()))
 		{
 			if(isKing(origin)) //king is allowed to go to center and corner squares
 				return true;
 			else
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
	* Returns true if the King is on a special square. Otherwise, returns false. 
	* @param data-coordinate of a location
	* @return true if the location is a special one
	* @return false if it does not
	*/
	public boolean hasKingEscaped(Coordinate coord)
	{
		int value = encode(coord);
		return _data.isKingOnSpecialSquare(value);
	} 
	
	
	/**
	* Returns true if the king is surrounded by black pieces in four directions. 
	* @param data-coordinate of a king's location
	* @return true if the king is surrounded by black pieces
	* @return false if the king is not
	*/
	public boolean isKingSurrounded(Coordinate coord)
	{
		int value = encode(coord);
		return _data.kingLost(value);
	}

	/**
	* @param coord-coordinate of king's location
	* @return true-if exit fort has occured
	* @return false-if exit fort has not occured
	*/
	public boolean exitFort(Coordinate coord)
	{//need to test
		int value=encode(coord);
		return _data.exitFort(value);
	}
	
	/**
	* determines which pieces are captured
	* @param coord the coordinate of the piece that was just moved
	* @return an arraylist of coordinates to remove
	*/
	public ArrayList<Coordinate> isPieceSurrounded(Coordinate coord)
	{//NEED TO TEST, will probs be tested via testing data.pieceLost()
		int value=encode(coord);
		ArrayList<Coordinate>piecesToRemove=_data.pieceLost(value);
		piecesToRemove.addAll(_data.shieldWallCapture(value));
		//will probs needed to return an array of pieces to remove
		return piecesToRemove;
	}
	
	/**
	* determines if the game is in a state of encirclement
	* @param coord - the coordinaet of the piece that was just moved
	* @return boolean for if the white pieces are encircled or not
	*/
	public boolean isEncircled(Coordinate coord)
	{
		int value = encode(coord);
		return _data.isEncircled(value);
	}

	/**
	* removes a piece from the board utilizing the data.java
	* @param coord-The piece to remove
	*/
	public void removePiece(Coordinate coord) 
	{//tested via data method it calls 
		_data.set(getIndex(coord),-1);
		
	}
	
	/**
	* Loads board array values into a new data object
	* @param board : Integer array representing board configuration
	* @return 0 if no error
	*/
	public int loadData(Integer[] board) 
	{
		for (int i = 0; i < 37; i++)
		{
			_data.set(i, board[i]);
		}
		return 0;
	}

}