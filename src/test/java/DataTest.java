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
		d.set(10, 9); // move 10th white piece to the 9th square
		d.set(11, 12); // move 11th white piece to the 12th square
		d.set(12, 14); // move 12th white piece to the 14th square
		d.set(14, 71); // set a black piece to square 71
		d.set(15, 73); // set a black piece to square 73
		d.set(16, 83); //set a black piece to square 83, i.e. one below the king
	
		
		assertTrue(d.kingLost(72)); // a square below the throne is 72
	}
	//will need to do lots and lots of sets to handle edge cases

	//left right sanwich caputre for white
	@Test
	public void leftRightWhiteCapture()
	{
		Data d=new Data();
		d.set(24,58);
		d.set(9,68);
		d.set(9,57);
		ArrayList<Coordinate> test_arr=d.pieceLost(57);
		assertTrue(2==test_arr.get(0).getX() && 5==test_arr.get(0).getY()); //should test to see which coord is in the array rather than the size
	}

	//up down sandwich catpure for white
	@Test
	public void upDownWhiteCapture()
	{
		Data d=new Data();
		d.set(21,48);
		d.set(8,37);
		ArrayList<Coordinate> test_arr=d.pieceLost(37);
		assertTrue(3==test_arr.get(0).getX() && 4==test_arr.get(0).getY());
	}


	// top left square horiz capture for white
	@Test
	public void topLeftHorizWhiteCapture()
	{
		Data d=new Data();
		d.set(24,2);
		d.set(1,3);
		ArrayList<Coordinate> test_arr=d.pieceLost(3);
		assertTrue(1==test_arr.get(0).getX() && 0==test_arr.get(0).getY());
	}

	//top left square vert cap for white                       
	public void topLeftVertWhiteCapture()
	{
		Data d=new Data();
		d.set(19,12);
		d.set(1,23);
		ArrayList<Coordinate> test_arr=d.pieceLost(23);
		assertTrue(1==test_arr.get(0).getX() && 1==test_arr.get(0).getY());

	}

	//bottom left square horiz cap for white
	
	@Test
	public void bottomLeftHorizWhiteCapture()
	{
		Data d=new Data();
		d.set(32,112);
		d.set(1,113);
		ArrayList<Coordinate> test_arr=d.pieceLost(113);
		assertTrue(1==test_arr.get(0).getX() && 10==test_arr.get(0).getY());

	}

	//bottom left square vert cap for white
	@Test
	public void bottomLeftVertWhiteCapture()
	{
		Data d=new Data();
		d.set(31,100);
		d.set(1,89);
		ArrayList<Coordinate> test_arr=d.pieceLost(89);
		assertTrue(0==test_arr.get(0).getX() && 9==test_arr.get(0).getY());

	}

	//top right square horiz cap for white
	@Test
	public void topRightHorizWhiteCapture()
	{
		Data d=new Data();
		d.set(17,10);
		d.set(4,9);
		ArrayList<Coordinate> test_arr=d.pieceLost(9);
		assertTrue(9==test_arr.get(0).getX() && 0==test_arr.get(0).getY());

	}

	//top right square vert cap for white
	@Test
	public void topRightVertWhiteCapture()
	{
		Data d=new Data();
		d.set(20,22);
		d.set(8,33);
		ArrayList<Coordinate> test_arr=d.pieceLost(33);
		assertTrue(10==test_arr.get(0).getX() && 1==test_arr.get(0).getY());

	}
	//bottom right square horiz cap for white
	@Test
	public void bottomRightHorizWhiteCapture()
	{
		Data d=new Data();
		d.set(36,120);
		d.set(4,119);
		ArrayList<Coordinate> test_arr=d.pieceLost(119);
		assertTrue(9==test_arr.get(0).getX() && 10==test_arr.get(0).getY());

	}
	
	//bottom right square vert cap for white
	@Test
	public void bottomRightVertWhiteCapture()
	{
		Data d=new Data();
		d.set(30,110);
		d.set(12,99);
		ArrayList<Coordinate> test_arr=d.pieceLost(99);
		assertTrue(10==test_arr.get(0).getX() && 9==test_arr.get(0).getY());

	}

	//up down sanwich caputre for black, returns true
	@Test
	public void upDownBlackCapture()
	{
		Data d=new Data();
		d.set(21,48);
		d.set(27,70);
		ArrayList<Coordinate> test_arr=d.pieceLost(70);
		assertTrue(3==test_arr.get(0).getX() && 5==test_arr.get(0).getY());

	}
	//left right sandwich catpure for black, returns true
	@Test
	public void leftRighBlackCapture()
	{
		Data d=new Data();
		d.set(19,38);
		d.set(20,40);
		ArrayList<Coordinate> test_arr=d.pieceLost(40);
		assertTrue(5==test_arr.get(0).getX() && 3==test_arr.get(0).getY());

	}


	// top right square horiz capture for black, nothing should be capped
	@Test
	public void topRightHorizBlackCapture()
	{
		Data d=new Data();
		d.set(8,10);
		d.set(17,9);
		ArrayList<Coordinate> test_arr=d.pieceLost(9);
		assertEquals(test_arr.size(),0);

	}

	//top right square vert cap for black
	@Test
	public void topRightVertBlackCapture()
	{
		Data d=new Data();
		d.set(8,22);
		d.set(20,33);
		ArrayList<Coordinate> test_arr=d.pieceLost(33);
		assertEquals(test_arr.size(),0);

	}
	//top left square vert cap for black
	@Test
	public void topLeftVertBlackCapture()
	{
		Data d=new Data();
		d.set(8,12);
		d.set(19,23);
		ArrayList<Coordinate> test_arr=d.pieceLost(23);
		assertEquals(test_arr.size(),0);
	}                        
	
	//top left square horiz cap for black
	@Test
	public void topLeftHorizBlackCapture()
	{
		Data d=new Data();
		d.set(1,2);
		d.set(13,3);
		ArrayList<Coordinate> test_arr=d.pieceLost(3);
		assertEquals(test_arr.size(),0);

	}
	
	//bottom left square horiz cap for black
	@Test 
	public void bottomleftHorizBlackCapture()
	{
		Data d=new Data();
		d.set(9,112);
		d.set(32,113);
		ArrayList<Coordinate> test_arr=d.pieceLost(113);
		assertEquals(test_arr.size(),0);
	}

	//bottomleft square vert cap for black
	@Test
	public void bottomLeftVertBlackCapture()
	{
		Data d=new Data();
		d.set(12,100);
		d.set(29,89);
		ArrayList<Coordinate> test_arr=d.pieceLost(89);
		assertEquals(test_arr.size(),0);
	}
	//bottom right square horiz cap for black
	@Test
	public void bottomRightHorizBlackCapture()
	{
		Data d=new Data();
		d.set(11,120);
		d.set(36,119);
		ArrayList<Coordinate> test_arr=d.pieceLost(119);
		assertEquals(test_arr.size(),0);
	}
	//bottom right square vert cap for black
	@Test
	public void bottomRightVertBlackCapture()
	{
		Data d=new Data();
		d.set(12,110);
		d.set(30,99);
		ArrayList<Coordinate> test_arr=d.pieceLost(99);
		assertEquals(test_arr.size(),0);
	}

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

	//cases for multiple piece captures for both black and white and king (2 and 3 piece caps) (2 piece ex:  XO-OX, x goes where dash is )
	//also need to test removal from backend
	//need to ensure that when a piece moves to a sandwich position, i.e. between two enemy pieces it is not removed

	//do tests for when no pieces are captured

	
}



