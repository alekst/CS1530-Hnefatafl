/* This is the interface for the backend data manipulation. 
The assumption is that the data structure that will maintain the coordinates on the board
is an array of ints. 

*/

public interface DataInterface
{
	/**
	* This method would encode the coordinates into the value for the array. 
	*
	* The formula is for conversion is value = y * 11 + x. 
	* @param data-the Coordinate object of the location
	* @return the int value of the location
	*/ 
	int encode (Coordinate data);
	

	/**
	* This method decodes an int location into a coordinate object
	* @param value-the int value of the location
	* @return Coordinate object of the location
	*/
	Coordinate decode (int value); 


	/**
	* Gets the matching index of a coordinate, if it exists
	* @param data-Coordinates of Location
	* @return if found, the index matching to the data coordinates
	* @return if not found, -1
	*/
	int getIndex(Coordinate data); 


	/**
	* Determines if value is in array
	* @param data-coordinates of a location
	* @return if the value is in the array, true (means there is a piece in that square)
	* @return if the value is not in the array, false
	*/
	boolean isMember (Coordinate data); 
	
	void reset(); // resets the array, i.e. the board. 
	
	/**
	* updates the location of a piece
	* @param newdata-the coordinates of where the piece is moving to
	* @param olddata-the coordinates of where the piece was moving from
	*/
	void updateLocation(Coordinate newdata, Coordinate olddata); // updates the location of a piece
	

	/** 
	* Determines if the piece is white (index is between 0 and 12)
	* @param data-the coordinates of a piece
	* @return if the piece is white, true
	* @return if the piece is not white, false
	*/
	boolean isWhite(Coordinate data); 
	
	//boolean isPathClear(Coordinate origin, Coordinate destination); // returns true if the path between the source and the destination of the piece is clear. 
	
	//void deleteLocation(Coordinate data); // deletes a piece from its location. 
}


