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
	*/
    public int encode(Coordinate data)
    {
        return (data.getY() * 11 + data.getX() + 1);
    }
	
	/**
	* Decodes the Coordinate object for storage
	*/
	public Coordinate decode(int value)
	{
		int x = value % 11 - 1;
		if (x < 0) x = 10;
		
		int y = value / 11;
		if (value % 11 == 0) y = y - 1;
		
		return new Coordinate(x, y);
	}
	
	
	public int getIndex(Coordinate data)
	{
		int value = encode(data);
		System.out.println(Arrays.toString(boardData));
		int index = Arrays.asList(boardData).indexOf(value);
		return index;
		
	}
	
	public boolean isMember (Coordinate data)
	{
		int value = encode(data);
		return Arrays.asList(boardData).contains(value);
	}
	
	public void reset()
	{
		initialize();
	}
	
	public void updateLocation(Coordinate newdata, Coordinate olddata)
	{
		int newValue = encode(newdata);
		int index = getIndex(olddata);
		boardData[index] = newValue;
	}
	
	public boolean isWhite(Coordinate data)
	{
		// returns true if the piece is white. white means its index is between 0 and 12. The method assumes that the coordinate contains a piece. 
		int index = getIndex(data);
		if (index < 13 && index > 0)
			return true;
		else 
			return false;
	}
	
	// public boolean isPathClear(Coordinate origin, Coordinate destination)
// 	{
// 		if (origin.getX() == destination.getX())
// 		{
// 			// that means the path is up/down
// 			// to be implemented.
// 		}
// 		else if (origin.getY() == destination.getY())
// 		{
// 			// that means the path is left/right
// 		}
// 	}
	
	
	
	
}
