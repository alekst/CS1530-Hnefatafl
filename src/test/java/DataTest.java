import java.lang.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.stream.*;

public class DataTest
{
	

	/* 
	*	These three tests test to see if 
	* 	the conversion from a positive integer value 
	* 	and the Coordinate object is correct 
	*/
	@Test
	public void testDecodingCoordinates55()
	{
		Data d = new Data();
		Coordinate c = d.decode(55);
		assertEquals(c.getX(), 10);
		assertEquals(c.getY(), 4);
	}

	@Test 
	public void testDecodingCoordinates12()
	{
		Data d = new Data();
		Coordinate c = d.decode(12);
		assertEquals(c.getX(), 0);
		assertEquals(c.getY(), 1);
	}

	@Test
	public void testDecodingCoordinates1()
	{
		Data d = new Data();
		Coordinate c = d.decode(1);
		assertEquals(c.getX(), 0);
		assertEquals(c.getY(), 0);
	}
	
	/* 
	*	This test determines if a coodrinate object contains -1, -1 for x and y
	* 	if the argument value is negative 
	*/
	@Test
	public void testDecodingNegativeNumber()
	{
		Data d = new Data();
		Coordinate c = d.decode(-2);
		assertEquals(c.getX(), -1);
		assertEquals(c.getY(), -1);
	}
	
	
	/* 
	*	This tests if in the initial board set up, the king is located at the square number 65
	* i.e. it is in the 6th row and the 6th column 
	*/
	@Test
	public void testGettingIndexOfKing()
	{
		// arrange
		Data d = new Data();
		// act
		int index = d.getIndex(61);

		// assert
		assertEquals(index, 0); // the king lives in the index 0
	}
	
	/* 
	*	This tests if in the initial board set up, a white piece is in the square 50
	 */
	@Test
	public void testGettingIndexOfAPiece()
	{
		// arrange
		Data d = new Data();
		// act
		int index = d.getIndex(50);

		// assert
		assertTrue(index < 13); // the piece should be white, i.e. its index is below 13
	}
	
	/* 
	*	This tests if the method getIndex returns -1 when an integer value of the square
	* 	is not in the boardData array. 
	*/
	@Test
	public void testGettingIndexEmptySquare()
	{
		// arrange
		Data d = new Data();
	
		// act
		int index = d.getIndex(1);	 // shouldn't be in the array, as it is an empty square

		// assert
		assertEquals(index, -1); //-1 means not found
	}
	
	/* 	
	*	This tests if the value in the array is set correctly if 
	*	the index is not more than the number
	*	of pieces, i.e. the array size 
	*/
	@Test
	public void testSettingTheValue()
	{
		Data d = new Data();
		
		d.set(0, 10); // sets the king to square 10. (x = 10, y = 0)
		
		assertEquals(d.getIndex(10), 0); //the value of index 0 should be 10
	}
	
	/* 	
	* 	This tests if the index is more than the number
	*	an exception is thrown 
	*/
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSettingTheLargeValue()
	{
		Data d = new Data();
		
		d.set(100, 10); //the index is 100, way more than the number of pieces
	}
	
	/* 	
	*	This will test if, given an index in the array, 
	* 	the correct Coordinate object is returned. 
	*/
	@Test
	public void testIfCoordinatesAreValid()
	{
		Data d = new Data();
		Coordinate c = d.getCoordinate(0); // at index 0 is the king's default location (5, 5)
		assertEquals(c.getX(), 5);
		assertEquals(c.getY(), 5);	
	}
	
	/* 	
	* 	This will test if, given an index in the array,
	* 	to the piece that is no longer in the game, 
	* 	the correct Coordinate object is returned, i.e. null. 
	*/
	@Test
	public void testCoordinatesOfAPieceNoLongerInGame()
	{
		Data d = new Data();
		d.set(1, -1); // set the first white piece to be no longer in play 
		Coordinate c = d.getCoordinate(1);
		
		assertNull(c);	
	}
	
	/* 	
	*	This tests if the index is more than the number
	*	an exception is thrown 
	*/
	@Test(expected=IndexOutOfBoundsException.class)
	public void testCoordinatesWithTheLargeIndex()
	{
		Data d = new Data();
		
		Coordinate c = d.getCoordinate(1000);//the index is 1000, way more than the number of pieces
	}
	/* 	This tests if the value is in the array */
	@Test 
	public void testIfNumberisInArray()
	{
       	Data d = new Data();
		assertTrue(d.isMember(61)); // 61 is the value of the 0th index of the array. 
	}
	
	/* 	This tests if the value is not in the array */
	@Test 
	public void testIfNumberisNotInArray()
	{
       	Data d = new Data();
		assertFalse(d.isMember(1)); // 1 is the value not in the array. 
	}
	
	/* 	This tests if the value is for a white piece */
	@Test 
	public void testIfValueisWhitePiece()
	{
       	Data d = new Data();
		assertTrue(d.isWhite(59)); // 59 is the value of the 1st index of the array. 
	}
	
	/* 	This tests if the value is for a black piece */
	@Test 
	public void testIfValueisBlackPiece()
	{
       	Data d = new Data();
		assertFalse(d.isWhite(4)); // 4 is the value of one of the black pieces.  
	}
	
	/*
	* 	This will test if the King is on a special square
	*/
	@Test
	public void testIfKingIsOnSpecialSquare()
	{
		Data d = new Data();
		assertTrue(d.isKingOnSpecialSquare(11));
		assertTrue(d.isKingOnSpecialSquare(1));
		assertTrue(d.isKingOnSpecialSquare(111));
		assertTrue(d.isKingOnSpecialSquare(121));
	}
	
	/* 
	* 	This will test if the King is not on a special square
	*/
	@Test
	public void testIfKingIsNotOnSpecialSquare()
	{
		Data d = new Data();
		assertFalse(d.isKingOnSpecialSquare(61));
		assertFalse(d.isKingOnSpecialSquare(2));
	}
	
	
	/* 
	*	This will test if the King is surrounded by four black pieces
	*/
	@Test
	public void testIfKingHasLost()
	{
		Data d = new Data(); 
		d.set(14, 34); // set a black piece to square 34
		d.set(15, 32); // set a black piece to square 32
		d.set(16, 22); //set a black piece to square 22, i.e. one above the king
		d.set(17, 44); // set a black piece to square 44, i.e. one below the king
		
		assertTrue(d.kingLost(33));
	}
	
	/* 
	*	This will test if the King is not surrounded by four black pieces
	*/
	@Test
	public void testIfKingHasNotLost()
	{
		Data d = new Data();
		d.set(14, 34); // set a black piece to square 34
		d.set(15, 32); // set a black piece to square 32
		d.set(16, 22); //set a black piece to square 22, i.e. one above the king
		d.set(17, 44); // set a black piece to square 44, i.e. one below the king
		
		assertFalse(d.kingLost(100)); 
	}
	
	/* 
	*	This will test if the King is surrounded by three black pieces and the throne
	*/
	@Test
	public void testIfKingHasLostNearThrone()
	{
		Data d = new Data();
		d.set(9, 10); // move 9th white piece to the 10th square
		d.set(10, 9); // more 10th white piece to the 9th square
		d.set(11, 12); // more 11th white piece to the 12th square
		d.set(12, 14); // more 12th white piece to the 14th square
		d.set(14, 71); // set a black piece to square 71
		d.set(15, 73); // set a black piece to square 73
		d.set(16, 83); //set a black piece to square 83, i.e. one below the king
	
		
		assertTrue(d.kingLost(72)); // a square below the throne is 72
	}
	//will need to do lots and lots of sets to handle edge cases

	//left righ sanwich caputre for white
	//up down sandwich catpure for white
	// top left square horiz capture for white
	//top left square vert cap for white                        all these tests shoudl return true, do tests for king instead of standard issue white piece too!
	//bottom left square horiz cap for white
	//bottom left square vert cap for white
	//top right square horiz cap for white
	//top right square vert cap for white
	//bottom right square horiz cap for white
	//bottom right square vert cap for white

	//left righ sanwich caputre for black, returns true
	//up down sandwich catpure for black, returns true


	// top left square horiz capture for black
	//top left square vert cap for black                        all these tests shoudl return false
	//bottom left square horiz cap for black
	//bottom left square vert cap for black
	//top right square horiz cap for black
	//top right square vert cap for black
	//bottom right square horiz cap for black
	//bottom right square vert cap for black

	//throne captures for black and white, when king is in throne and when king is not in throne

	//king is on throne for these
	//black piece to right, sanwiched by white
	//black piece to left, sanwiched by white         //all should return true
	//black piece to up, sandwiched by white
	//black piece to down, sanwiched by white

	//king is NOT on throne for these
	//black piece to right, sanwiched by white
	//black piece to left, sanwiched by white         //all should return true
	//black piece to up, sandwiched by white
	//black piece to down, sanwiched by white


	//king is on throne for these
	//white piece to right, sanwiched by black
	//white piece to left, sanwiched by black         //all should return true
	//white piece to up, sandwiched by black
	//white piece to down, sanwiched by black

	//king is NOT on throne for these
	//white piece to right, sanwiched by black
	//white piece to left, sanwiched by black         //all should return true
	//white piece to up, sandwiched by black
	//white piece to down, sanwiched by black

	//cases for multiple piece captures for both black and white and king
	//also need to test removal from backend
	//need to ensure that when a piece moves to a sandwich position, i.e. between two enemy pieces it is not removed


	
}



