import java.awt.*;

public class Coordinate 
{
	private int x, y;

	/**
	 * An emply constructor, used for testing purposes. 
	 */	

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
	* Checks to see if the Coordinate values are (-1, -1). These are the placeholders used
	* by the Board class. 
	* @return true, if both x and y are -1. Otherwise, false
	*/
	public boolean isMinusOne()
	{
		if (x == -1 && y == -1)
		{
			return true;
		}
		return false;
	}
		
	/**
	* Resets the coordinate to -1,-1
	*/
	public void setMinusOne()
	{
		this.x = -1;
		this.y = -1;
	}

}