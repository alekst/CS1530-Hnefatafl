/* This is the interface for the backend data manipulation. 
The assumption is that the data structure that will maintain the coordinates on the board
is an array of ints. 

*/

public interface backendInterface
{
	/* This method would encode the coordinates into the value for the array. The formula is
		for conversion is value = y * 11 + x. */ 
		
	int encode (int x, int y); // alternative is the Coordinate object with x and y coordinates
	
	int getIndex(Coordinate data); // returns index matching to the data coordinates. It returns -1 if it's not found.
	
	Coordinate decode (int value); //this would return the Coordinate object with x and y coordinates
	
	/* This method would return true if the value is in the array. Otherwise it would return false. If the value 
		is in the array, it means that there is a piece in that square. */
	boolean isMember (int value);  // alternative is isMember(Coordinate data)
	
	void reset(); // resets the array, i.e. the board. 
	
	void updateLocation(int x, int y, int index); // updates the location by index of the array. 
	// Alternative arguments to the above are (Coordinate data, int index)
	
	boolean isWhite(int index); // returns true if the piece is white. white means its index is between 1 and xx.
	
	boolean isBlack(int index); // returns true is the piece is black. black means its index is in the higher end of the array. 
	
	boolean isKing(int index); // returns true is the index is 0, i.e. the piece is King. Otherwise, false. 
	
	boolean isPathClear(Coordinate data, int index); // returns true if the path between the source and the destination of the piece is clear. 
	
	void deleteLocation(Coordinate data); // deletes a piece from its location. 
		
}