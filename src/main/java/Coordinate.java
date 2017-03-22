import java.awt.*;

public class Coordinate 
{
	private int x, y;
	
	public Coordinate()
	{
		// an empty constructor
	}
	/**
	* Simple constructor for Coordinate object
	* @param x-the x coordinate of the location
	* @param y-the y coordinate of the location
	*/
	 
	public Coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	/**
	* Returns the x coordinate
	*/
	public int getX()
	{
		return x;
	}
	
	/**
	* Returns the y coordinate of the location
	*/
	public int getY()
	{
		return y;
	}
	
	/**
	* Compares two Coordinate objects. 
	* @param toCompare: the Coordinate object to compare to. 
	* @param second: Second Coordinate object
	* @return true, if the objects contain equal x and y. Otherwise, returns false. 
	* TODO: WRITE SOME TESTS
	*/ 
	public boolean equal(Coordinate toCompare)
	{
		if (x == toCompare.getX())
		{
			if (y == toCompare.getY())
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	* Because Board.java uses a Coordinate object,
	* 
	*/
	public boolean isMinusOne()
	{
		if (x == -1 && y == -1)
		{
			return true;
		}
		return false;
	}
	

		 
		
	
}