/* This is the interface for the backend data manipulation. 
The assumption is that the data structure that will maintain the coordinates on the board
is an array of ints. 

*/

public interface DataInterface
{
	/* This method would encode the coordinates into the value for the array. The formula is
		for conversion is value = y * 11 + x. */ 
		
	int encode (Coordinate data);
	
	Coordinate decode (int value); 
	
	int getIndex(Coordinate data); // returns index matching to the data coordinates. It returns -1 if it's not found.
	
	/* This method would return true if the value is in the array. Otherwise it would return false. If the value 
		is in the array, it means that there is a piece in that square. */
	boolean isMember (Coordinate data); 
	
	void reset(); // resets the array, i.e. the board. 
	
	void updateLocation(Coordinate newdata, Coordinate olddata); // updates the location of a piece
	
	boolean isWhite(Coordinate data); // returns true if the piece is white. white means its index is between 0 and 12.
	
	//boolean isPathClear(Coordinate origin, Coordinate destination); // returns true if the path between the source and the destination of the piece is clear. 
	
	//void deleteLocation(Coordinate data); // deletes a piece from its location. 
}


