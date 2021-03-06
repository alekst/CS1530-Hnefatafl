import java.lang.*;
import java.util.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
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


	/**
	* This test will ensure that the king is not captured by a sandwich capture, capture not should occur
	*/
	@Test
	public void testIfKingIsCapturedBySandwich()
	{
		Data d=new Data();
		d.set(8,31);
		d.set(6,28);
		d.set(0,36);
		d.set(24,35);
		d.set(14,38);
		d.set(14,37);
		ArrayList<Coordinate> test_arr=d.pieceLost(37);
		assertEquals(test_arr.size(),0);
	}

	/**
	* This test will ensure that the king is not captured by a horizontal sandwich capture on a special square, capture not should occur
	*/
	@Test
	public void testIfKingIsCaptureBySpecialSquareHoriz()
	{
		Data d=new Data();
		d.set(0,10);
		d.set(17,9);
		ArrayList<Coordinate> test_arr=d.pieceLost(9);
		assertEquals(test_arr.size(),0);
	}

	/**
	* This test will ensure that the king is not captured by a vertical sandwich capture on a special square, capture not should occur
	*/
	@Test
	public void testIfKingIsCaptureBySpecialSquareVert()
	{
		Data d=new Data();
		d.set(0,100);
		d.set(29,89);
		ArrayList<Coordinate> test_arr=d.pieceLost(89);
		assertEquals(test_arr.size(),0);
	}

	/**
	* Test is white method to ensure king is a white piece, capture should occur
	*/
	@Test
	public void testIfKingIsWhite()
	{
		Data d=new Data();
		assertTrue(d.isWhite(61));
	}

	/**
	* tests captures via a simple left right sandwich capture, capture should occur
	*/
	@Test
	public void leftRightWhiteCapture()
	{
		Data d=new Data();
		d.set(24,58);
		d.set(9,68);
		d.set(9,57);
		ArrayList<Coordinate> test_arr=d.pieceLost(57);
		assertTrue(2==test_arr.get(0).getX() && 5==test_arr.get(0).getY()); 
	}

	/**
	* tests captures via a simple up down sandwich capture, capture should occur
	*/
	@Test
	public void upDownWhiteCapture()
	{
		Data d=new Data();
		d.set(21,48);
		d.set(8,37);
		ArrayList<Coordinate> test_arr=d.pieceLost(37);
		assertTrue(3==test_arr.get(0).getX() && 4==test_arr.get(0).getY());
	}


	/**
	* tests horizontal sandwich captures with top left special square for white pieces, capture should occur
	*/
	@Test
	public void topLeftHorizWhiteCapture()
	{
		Data d=new Data();
		d.set(24,2);
		d.set(1,3);
		ArrayList<Coordinate> test_arr=d.pieceLost(3);
		assertTrue(1==test_arr.get(0).getX() && 0==test_arr.get(0).getY());
	}

	/**
	* tests vertical sandwich captures with top left special square for white pieces, capture should occur
	*/                     
	public void topLeftVertWhiteCapture()
	{
		Data d=new Data();
		d.set(19,12);
		d.set(1,23);
		ArrayList<Coordinate> test_arr=d.pieceLost(23);
		assertTrue(1==test_arr.get(0).getX() && 1==test_arr.get(0).getY());

	}

	/**
	* tests horizontal sandwich captures with bottom left special square for white pieces, capture should occur
	*/
	@Test
	public void bottomLeftHorizWhiteCapture()
	{
		Data d=new Data();
		d.set(32,112);
		d.set(1,113);
		ArrayList<Coordinate> test_arr=d.pieceLost(113);
		assertTrue(1==test_arr.get(0).getX() && 10==test_arr.get(0).getY());

	}

	/**
	* tests vertical sandwich captures with bottom left special square for white pieces, capture should occur
	*/
	@Test
	public void bottomLeftVertWhiteCapture()
	{
		Data d=new Data();
		d.set(31,100);
		d.set(1,89);
		ArrayList<Coordinate> test_arr=d.pieceLost(89);
		assertTrue(0==test_arr.get(0).getX() && 9==test_arr.get(0).getY());

	}

	/**
	* tests horizontal sandwich captures with top right special square for white pieces, capture should occur
	*/
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
	/**
	* tests vertical sandwich captures with top right special square for white pieces, capture should occur
	*/
	@Test
	public void topRightVertWhiteCapture()
	{
		Data d=new Data();
		d.set(20,22);
		d.set(8,33);
		ArrayList<Coordinate> test_arr=d.pieceLost(33);
		assertTrue(10==test_arr.get(0).getX() && 1==test_arr.get(0).getY());

	}

	/**
	* tests horizontal sandwich captures with bottom right special square for white pieces, capture should occur
	*/
	@Test
	public void bottomRightHorizWhiteCapture()
	{
		Data d=new Data();
		d.set(36,120);
		d.set(4,119);
		ArrayList<Coordinate> test_arr=d.pieceLost(119);
		assertTrue(9==test_arr.get(0).getX() && 10==test_arr.get(0).getY());

	}
	
	/**
	* tests vertical sandwich captures with bottom right special square for white pieces, capture should occur
	*/
	@Test
	public void bottomRightVertWhiteCapture()
	{
		Data d=new Data();
		d.set(30,110);
		d.set(12,99);
		ArrayList<Coordinate> test_arr=d.pieceLost(99);
		assertTrue(10==test_arr.get(0).getX() && 9==test_arr.get(0).getY());

	}

	/**
	* tests up down sandwich capture for black pieces, capture should occur
	*/
	@Test
	public void upDownBlackCapture()
	{
		Data d=new Data();
		d.set(21,48);
		d.set(27,70);
		ArrayList<Coordinate> test_arr=d.pieceLost(70);
		assertTrue(3==test_arr.get(0).getX() && 5==test_arr.get(0).getY());

	}

	/**
	* tests left right sandwich capture for black pieces, capture should occur
	*/
	@Test
	public void leftRighBlackCapture()
	{
		Data d=new Data();
		d.set(19,38);
		d.set(20,40);
		ArrayList<Coordinate> test_arr=d.pieceLost(40);
		assertTrue(5==test_arr.get(0).getX() && 3==test_arr.get(0).getY());

	}

	/**
	* Tests whether a black piece will be captured when it is to the right of the throne
	* with the king on it. The black piece is then sandwiched by a white piece, capture should occur
	*/
	@Test
	public void kingOnThroneBlackPieceRightWhiteCapture()
	{
		Data d=new Data();
		//moving pieces out so capture can occur
		d.set(24,68);
		d.set(7,29);
		d.set(36,119);
		d.set(11,106);
		d.set(20,33);
		d.set(4,85);
		d.set(35,118);
		d.set(3,95);
		d.set(25,62);
		d.set(4,63);
		ArrayList<Coordinate> test_arr=d.pieceLost(63);
		assertTrue(6==test_arr.get(0).getX() && 5==test_arr.get(0).getY());

	}
	
	/**
	* Tests whether a black piece will be captured when it is to the left of the throne
	* with the king on it. The black piece is then sandwiched by a white piece, capture should occur
	*/
	@Test
	public void kingOnThroneBlackPieceLeftWhiteCapture()
	{
		Data d=new Data();
		d.set(29,100);
		d.set(1,103);
		d.set(27,89);
		d.set(5,46);
		d.set(24,58);
		d.set(2,16);
		d.set(24,60);
		d.set(5,27);
		d.set(32,112);
		d.set(5,49);
		ArrayList<Coordinate> test_arr=d.pieceLost(49);
		assertTrue(4==test_arr.get(0).getX() && 5==test_arr.get(0).getY());
	}

	/**
	* Tests whether a black piece will be captured when it is north of the throne
	* with the king on it. The black piece is then sandwiched by a white piece, capture should occur
	*/
	@Test
	public void kingOnThroneBlackPieceUpWhiteCapture()
	{
		Data d=new Data();
		d.set(8,35);
		d.set(6,39);
		d.set(24,79);
		d.set(6,40);
		d.set(18,50);
		d.set(6,39);
		ArrayList<Coordinate> test_arr=d.pieceLost(39);
		assertTrue(5==test_arr.get(0).getX() && 4==test_arr.get(0).getY());
	}
 
	/**
	* Tests whether a black piece will be captured when it is south of the throne
	* with the king on it. The black piece is then sandwiched by a white piece, capture should occur
	*/
	@Test
	public void kingOnThroneBlackPieceDownWhiteCapture()
	{
		Data d=new Data();
		d.set(12,86);
		d.set(9,82);
		d.set(11,75);
		d.set(10,70);
		d.set(34,72);
		d.set(9,83);
		ArrayList<Coordinate> test_arr=d.pieceLost(83);
		assertTrue(5==test_arr.get(0).getX() && 6==test_arr.get(0).getY());
	}

	/**
	* Tests whether a black piece will be captured when it is to the right of the throne
	* with the king NOT on it. The black piece is then sandwiched by a white piece, capture should occur
	*/
	@Test
	public void kingNotOnThroneBlackPieceRightWhiteCapture()
	{
		Data d=new Data();
		d.set(4,52);
		d.set(4,53);
		d.set(11,84);
		d.set(3,29);
		d.set(0,65);
		d.set(35,85);
		d.set(4,64);
		d.set(35,63);
		d.set(35,62);
		d.set(4,63);
		ArrayList<Coordinate> test_arr=d.pieceLost(63);
		assertTrue(6==test_arr.get(0).getX() && 5==test_arr.get(0).getY());

	}

	/**
	* Tests whether a black piece will be captured when it is to the left of the throne
	* with the king NOT on it. The black piece is then sandwiched by a white piece, capture should occur
	*/
	@Test 
	public void kingNotOnThroneBlackPieceLeftWhiteCapture()
	{
		Data d=new Data();
		d.set(1,15);
		d.set(5,16);
		d.set(9,104);
		d.set(2,49);
		d.set(0,91);
		d.set(24,60);
		d.set(2,59);
		ArrayList<Coordinate> test_arr=d.pieceLost(59);
		assertTrue(4==test_arr.get(0).getX() && 5==test_arr.get(0).getY());

	}

	/**
	* Tests whether a black piece will be captured when it is north of the throne
	* with the king NOT on it. The black piece is then sandwiched by a white piece, capture should occur
	*/
	@Test
	public void kingNotOnThroneBlackPieceUpWhiteCapture()
	{
		Data d=new Data();
		d.set(8,43);
		d.set(7,54);
		d.set(5,27);
		d.set(6,53);
		d.set(0,42);
		d.set(18,50);
		d.set(5,39);
		ArrayList<Coordinate> test_arr=d.pieceLost(39);
		assertTrue(5==test_arr.get(0).getX() && 4==test_arr.get(0).getY());
	}
	
	/**
	* Tests whether a black piece will be captured when it is south of the throne
	* with the king NOT on it. The black piece is then sandwiched by a white piece, capture should occur
	*/
	@Test
	public void kingNotOnThroneBlackPieceDownWhiteCapture()
	{
		Data d=new Data();
		d.set(11,76);
		d.set(9,68);
		d.set(10,69);
		d.set(0,75);
		d.set(12,84);
		d.set(31,72);
		d.set(12,83);
		ArrayList<Coordinate> test_arr=d.pieceLost(83);
		assertTrue(5==test_arr.get(0).getX() && 6==test_arr.get(0).getY());
	}

	/**
	* Tests whether a white piece will be captured when it is to the right of the throne
	* with the king NOT on it. The white piece is then sandwiched by a black piece, capture should occur
	*/
	@Test
	public void kingNotOnThroneWhitePieceRightBlackCapture()
	{
		Data d=new Data();
		d.set(11,106);
		d.set(4,96);
		d.set(3,64);
		d.set(0,85);
		d.set(7,53);
		d.set(16,62);
		d.set(3,63);
		ArrayList<Coordinate> test_arr=d.pieceLost(63);
		assertTrue(6==test_arr.get(0).getX() && 5==test_arr.get(0).getY());
	}

	/**
	* Tests whether a white piece will be captured when it is to the left of the throne
	* with the king NOT on it. The white piece is then sandwiched by a black piece, capture should occur
	*/
	@Test
	public void kingNotOnThroneWhitePieceLeftBlackCapture()
	{
		Data d=new Data();
		d.set(1,15);
		d.set(5,27);
		d.set(2,80);
		d.set(0,37);
		d.set(24,60);
		d.set(2,59);
		ArrayList<Coordinate> test_arr=d.pieceLost(59);
		assertTrue(4==test_arr.get(0).getX() && 5==test_arr.get(0).getY());
	}

	/**
	* Tests whether a white piece will be captured when it is north of the throne
	* with the king NOT on it. The white piece is then sandwiched by a black piece, capture should occur
	*/
	@Test
	public void kingNotOnThroneWhitePieceUpBlackCapture()
	{
		Data d=new Data();
		d.set(8,36);
		d.set(6,28);
		d.set(0,43);
		d.set(18,19);
		d.set(5,50);
		d.set(13,39);
		ArrayList<Coordinate> test_arr=d.pieceLost(39);
		assertTrue(5==test_arr.get(0).getX() && 4==test_arr.get(0).getY());
	}

	/**
	* Tests whether a white piece will be captured when it is south of the throne
	* with the king NOT on it. The white piece is then sandwiched by a black piece, capture should occur
	*/
	@Test
	public void kingNoOnThroneWhitePieceDownBlackCapture()
	{
		Data d=new Data();
		d.set(12,79);
		d.set(11,76);
		d.set(9,70);
		d.set(10,85);
		d.set(0,91);
		d.set(24,58);
		d.set(10,72);
		d.set(31,83);
		ArrayList<Coordinate> test_arr=d.pieceLost(83);
		assertTrue(5==test_arr.get(0).getX() && 6==test_arr.get(0).getY());
	}

	/**
	* Tests whether two black pieces can be captured at the same time, capture should occur
	*/
	@Test
	public void twoPieceWhiteCapture()
	{
		Data d=new Data();
		d.set(36,107);
		d.set(11,106);
		d.set(30,109);
		d.set(12,110);
		d.set(10,108);
		ArrayList<Coordinate> test_arr=d.pieceLost(108);
		assertTrue(9==test_arr.get(0).getX() && 9==test_arr.get(0).getY()
			&& 7==test_arr.get(1).getX() && 9==test_arr.get(1).getY());

	}

	/**
	* Tests whether three black pieces can be captured at the same time, capture should occur
	*/
	@Test
	public void threePieceWhiteCapture()
	{
		Data d=new Data();
		d.set(29,80);
		d.set(9,79);
		d.set(33,82);
		d.set(12,94);
		d.set(32,114);
		d.set(32,70);
		d.set(12,92);
		d.set(10,83);
		d.set(12,81);
		ArrayList<Coordinate> test_arr=d.pieceLost(81);
		assertTrue(3==test_arr.get(0).getX() && 6==test_arr.get(0).getY()
			&& 4==test_arr.get(1).getX() && 7==test_arr.get(1).getY()
			&& 2==test_arr.get(2).getX() && 7==test_arr.get(2).getY());
	}

	/**
	* Tests whether two white pieces can be captured at the same time, capture should occur
	*/
	@Test
	public void twoPieceBlackCapture()
	{
		Data d=new Data();
		d.set(35,95);
		d.set(11,74);
		d.set(30,99);
		d.set(11,96);
		d.set(4,98);
		d.set(36,97);
		ArrayList<Coordinate> test_arr=d.pieceLost(97);
		assertTrue(9==test_arr.get(0).getX() && 8==test_arr.get(0).getY()
			&& 7==test_arr.get(1).getX() && 8==test_arr.get(1).getY());

	}

	/**
	* Tests whether three white pieces can be captured at the same time, capture should occur
	*/
	@Test
	public void threePieceBlackCapture()
	{
		Data d=new Data();
		d.set(24,90);
		d.set(9,93);
		d.set(31,94);
		d.set(1,91);
		d.set(27,70);
		d.set(12,81);
		d.set(32,92);
		ArrayList<Coordinate> test_arr=d.pieceLost(92);
		assertTrue(3==test_arr.get(0).getX() && 7==test_arr.get(0).getY()
			&& 4==test_arr.get(1).getX() && 8==test_arr.get(1).getY()
			&& 2==test_arr.get(2).getX() && 8==test_arr.get(2).getY());

	}

	/**
	* Tests whether a piece is "removed" from the backend, setting it's location to -1 means 
	* the piece is no longer on the board
	*/
	@Test
	public void removeFromBackend()
	{
		Data d=new Data();
		d.set(24,-1);
		assertEquals(d.boardData[24].intValue(),-1);

	}

	//need to ensure that when a piece moves to a sandwich position, i.e. between two enemy pieces it is not removed

	/**
	* Tests whether when a piece moves into a sandwich position it is not capture
	* ex: a white piece moves in between two blacl pieces, the white piece should be captured
	*/
	@Test
	public void pieceMovesToSandwichPosition()
	{
		Data d=new Data();
		d.set(9,93);
		d.set(32,103);
		d.set(9,104);
		ArrayList<Coordinate> test_arr=d.pieceLost(104);
		assertEquals(test_arr.size(),0);
	}

	// Tests shield wall captures utilizing special squares
	// 	on west wall top special square
	@Test
	public void upperLeft1SWCSpecial()	
	{
		Data d = new Data();
		d.set(5, 12);
		d.set(1, 23);
		d.set(13, 13);
		d.set(14, 24);
		ArrayList<Coordinate> test_arr = d.shieldWallCapture(34);
		assertTrue(0==test_arr.get(0).getX() && 2==test_arr.get(0).getY()
			&& 0==test_arr.get(1).getX() && 1==test_arr.get(1).getY());
	}
	
	// Tests shield wall captures utilizing special squares
	// 	on north wall left special square
	@Test
	public void upperLeft2SWCSpecial()	
	{
		Data d = new Data();
		d.set(5, 2);
		d.set(1, 3);
		d.set(2, 4);
		d.set(13, 13);
		d.set(15, 14);
		d.set(16, 15);
		ArrayList<Coordinate> test_arr = d.shieldWallCapture(5);
		assertTrue(3==test_arr.get(0).getX() && 0==test_arr.get(0).getY()
			&& 2==test_arr.get(1).getX() && 0==test_arr.get(1).getY()
			&& 1==test_arr.get(2).getX() && 0==test_arr.get(2).getY());
	}
	
	// Tests shield wall captures utilizing special squares
	// 	on east wall top special square
	@Test
	public void upperRight1SWCSpecial()	
	{
		Data d = new Data();
		d.set(5, 22);
		d.set(1, 33);
		d.set(13, 21);
		d.set(14, 32);
		ArrayList<Coordinate> test_arr = d.shieldWallCapture(44);
		assertTrue(10==test_arr.get(0).getX() && 2==test_arr.get(0).getY()
			&& 10==test_arr.get(1).getX() && 1==test_arr.get(1).getY());
	}
	
	// Tests shield wall captures utilizing special squares
	// 	on north wall right special square
	@Test
	public void upperRight2SWCSpecial()	
	{
		Data d = new Data();
		d.set(5, 10);
		d.set(1, 9);
		d.set(15, 21);
		d.set(13, 20);
		d.set(17, 19);
		d.set(6, 8);
		ArrayList<Coordinate> test_arr = d.shieldWallCapture(7);
		assertTrue(7==test_arr.get(0).getX() && 0==test_arr.get(0).getY()
			&& 8==test_arr.get(1).getX() && 0==test_arr.get(1).getY()
			&& 9==test_arr.get(2).getX() && 0==test_arr.get(2).getY());
	}
	
	// Tests shield wall captures utilizing special squares
	// 	on west wall bottom special square
	@Test
	public void lowerLeft1SWCSpecial()	
	{
		Data d = new Data();
		d.set(5, 100);
		d.set(1, 89);
		d.set(29, 79);
		d.set(8, 78);
		d.set(23, 90);
		d.set(21, 101);
		ArrayList<Coordinate> test_arr = d.shieldWallCapture(67);
		assertTrue(0==test_arr.get(0).getX() && 7==test_arr.get(0).getY()
			&& 0==test_arr.get(1).getX() && 8==test_arr.get(1).getY()
			&& 0==test_arr.get(2).getX() && 9==test_arr.get(2).getY());
	}
	
	// Tests shield wall captures utilizing special squares
	// 	on south wall left special square
	@Test
	public void lowerLeft2SWCSpecial()
	{
		Data d = new Data();
		d.set(5, 112);
		d.set(1, 113);
		d.set(13, 101);
		d.set(14, 102);
		ArrayList<Coordinate> test_arr = d.shieldWallCapture(114);
		assertTrue(2==test_arr.get(0).getX() && 10==test_arr.get(0).getY()
			&& 1==test_arr.get(1).getX() && 10==test_arr.get(1).getY());
	}	
	
	// Tests shield wall captures utilizing special squares
	// 	on east wall bottom special square
	@Test
	public void lowerRight1SWCSpecial()
	{
		Data d = new Data();
		d.set(5, 99);
		d.set(1, 110);
		d.set(13, 98);
		d.set(14, 109);
		ArrayList<Coordinate> test_arr = d.shieldWallCapture(88);
		assertTrue(10==test_arr.get(0).getX() && 8==test_arr.get(0).getY()
			&& 10==test_arr.get(1).getX() && 9==test_arr.get(1).getY());
	}	
	
	// Tests shield wall captures utilizing special squares
	// 	on south wall right special square
	@Test
	public void lowerRight2SWCSpecial()	
	{
		Data d = new Data();
		d.set(5, 99);
		d.set(1, 110);
		d.set(13, 98);
		d.set(14, 109);
		ArrayList<Coordinate> test_arr = d.shieldWallCapture(88);
		assertTrue(10==test_arr.get(0).getX() && 8==test_arr.get(0).getY()
			&& 10==test_arr.get(1).getX() && 9==test_arr.get(1).getY());
	}
	
	// Tests shield wall captures without special spaces
	// 	on north wall
	@Test
	public void northSWC()	
	{
		Data d = new Data();
		d.set(13, 15);
		d.set(14, 16);
		d.set(15, 3);
		d.set(16, 18);
		d.set(17, 19);
		d.set(1, 4);
		d.set(2, 5);
		d.set(3, 6);
		d.set(4, 7);
		d.set(5, 8);
		d.set(19, 9);
		ArrayList<Coordinate> test_arr = d.shieldWallCapture(9);
		assertTrue(7==test_arr.get(0).getX() && 0==test_arr.get(0).getY()
			&& 6==test_arr.get(1).getX() && 0==test_arr.get(1).getY()
			&& 5==test_arr.get(2).getX() && 0==test_arr.get(2).getY()
			&& 4==test_arr.get(3).getX() && 0==test_arr.get(3).getY()
			&& 3==test_arr.get(4).getX() && 0==test_arr.get(4).getY());
	}
	
	// Tests shield wall captures without special spaces
	// 	on south wall
	@Test
	public void southSWC()	
	{
		Data d = new Data();
		d.set(32, 103);
		d.set(33, 104);
		d.set(34, 113);
		d.set(35, 106);
		d.set(36, 107);
		d.set(1, 114);
		d.set(2, 115);
		d.set(3, 116);
		d.set(4, 117);
		d.set(5, 118);
		d.set(19, 119);
		ArrayList<Coordinate> test_arr = d.shieldWallCapture(119);
		assertTrue(7==test_arr.get(0).getX() && 10==test_arr.get(0).getY()
			&& 6==test_arr.get(1).getX() && 10==test_arr.get(1).getY()
			&& 5==test_arr.get(2).getX() && 10==test_arr.get(2).getY()
			&& 4==test_arr.get(3).getX() && 10==test_arr.get(3).getY()
			&& 3==test_arr.get(4).getX() && 10==test_arr.get(4).getY());
	}
	
	// Tests shield wall captures without special spaces
	// 	on east wall
	@Test
	public void eastSWC()	
	{
		Data d = new Data();
		d.set(20, 43);
		d.set(22, 54);
		d.set(26, 33);
		d.set(28, 76);
		d.set(30, 87);
		d.set(1, 44);
		d.set(2, 55);
		d.set(3, 66);
		d.set(4, 77);
		d.set(5, 88);
		d.set(19, 99);
		ArrayList<Coordinate> test_arr = d.shieldWallCapture(99);
		assertTrue(10==test_arr.get(0).getX() && 7==test_arr.get(0).getY()
			&& 10==test_arr.get(1).getX() && 6==test_arr.get(1).getY()
			&& 10==test_arr.get(2).getX() && 5==test_arr.get(2).getY()
			&& 10==test_arr.get(3).getX() && 4==test_arr.get(3).getY()
			&& 10==test_arr.get(4).getX() && 3==test_arr.get(4).getY());
	}
	
	// Tests shield wall captures without special spaces
	// 	on west wall
	@Test
	public void westSWC()
	{
		
		Data d = new Data();
		d.set(19, 35);
		d.set(21, 46);
		d.set(23, 89);
		d.set(27, 68);
		d.set(29, 79);
		d.set(1, 34);
		d.set(2, 45);
		d.set(3, 56);
		d.set(4, 67);
		d.set(5, 78);
		d.set(31, 23);
		ArrayList<Coordinate> test_arr = d.shieldWallCapture(23);
		assertTrue(0==test_arr.get(0).getX() && 3==test_arr.get(0).getY()
			&& 0==test_arr.get(1).getX() && 4==test_arr.get(1).getY()
			&& 0==test_arr.get(2).getX() && 5==test_arr.get(2).getY()
			&& 0==test_arr.get(3).getX() && 6==test_arr.get(3).getY()
			&& 0==test_arr.get(4).getX() && 7==test_arr.get(4).getY());
	}
	
	// Tests shield wall captures on a king
	// 	shouldn't capture anything
	@Test
	public void kingSWC() 
	{
		Data d = new Data();
		d.set(19, 35);
		d.set(21, 46);
		d.set(23, 89);
		d.set(27, 68);
		d.set(29, 79);
		d.set(0, 34);
		d.set(2, 45);
		d.set(3, 56);
		d.set(4, 67);
		d.set(5, 78);
		d.set(31, 23);
		ArrayList<Coordinate> test_arr = d.shieldWallCapture(23);
		assertTrue(0==test_arr.get(0).getX() && 4==test_arr.get(0).getY()
			&& 0==test_arr.get(1).getX() && 5==test_arr.get(1).getY()
			&& 0==test_arr.get(2).getX() && 6==test_arr.get(2).getY()
			&& 0==test_arr.get(3).getX() && 7==test_arr.get(3).getY());
	}
	
	// Tests if print returns a string
	@Test
	public void testPrintData()
	{
		Data d = new Data();
		assertThat(d.print(), instanceOf(String.class));
	}

	/*
	* Test rule 9 in data
	*/
	@Test
	public void test_rule_9()
	{
		Manager m = new Manager();
		Data d = new Data();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(114 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,15 ,5 ,6 ,7 ,8 ,17 ,37 ,40,45 ,55 ,56 ,59 ,53 ,66 ,67 ,77 ,78 ,87 ,103 ,113 ,115 ,116 ,117 ,74 ));
		d.boardData = list.toArray(d.boardData);
		m.setData(d);
		assertTrue(d.rule_9(114,3));	
	}

	/*
	* Test rule 9 in data
	*/
	@Test
	public void test2_rule_9()
	{
		Manager m = new Manager();
		Data d = new Data();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(39 ,82 ,38 ,54 ,66 ,-1 ,-1 ,17 ,44 ,-1 ,84 ,73 ,95 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,55 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1));
		d.boardData = list.toArray(d.boardData);
		m.setData(d);
		assertTrue(d.rule_9(55,2));	
	}
	
	// Tests to see if there is encircling with loop surrounding all whites
	public void encircleTrueTest()
	{
		Data d = new Data();
		d.set(21, 47);
		d.set(19, 37);
		d.set(13, 27);
		d.set(14, 29);
		d.set(16, 29);
		d.set(17, 41);
		d.set(22, 63);
		d.set(28, 75);
		d.set(30, 85);
		d.set(33, 93);
		d.set(29, 81);
		d.set(27, 69);
		assertTrue(d.isEncircled(29));
	}
	
	// Tests to see if there is no encircling when there is a loop
	//	and all whites are surrounded, but the loop isn't surrounding
	//		all whites
	public void encircleFalseTest()
	{
		Data d = new Data();
		d.set(15, 29);
		d.set(17, 19);
		assertFalse(d.isEncircled(29));
	}

	/*
	* tests exitFort in data
	*/
	@Test
	public void testExitFort()
	{
		Manager m = new Manager();
		Data d = new Data();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(6,59,16,62,63,4,8,18,-1,71,17,73,83,3,37,14,43,10,22,34,44,45,55,56,57,65,66,67,77,78,88,105,114,115,116,117,118));
		d.boardData = list.toArray(d.boardData);
		m.setData(d);
 		assertTrue(d.exitFort(6));
	}

	/*
	* tests exitFort in data
	*/
	@Test
	public void testExitFort_2()
	{
		Manager m = new Manager();
		Data d = new Data();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(66,59,64,55,107,49,53,54,39,71,76,77,83,15,5,6,7,8,17,34,23,45,27,56,57,10,85,67,99,78,89,105,114,115,116,117,118));
		d.boardData = list.toArray(d.boardData);
		m.setData(d);
 		assertTrue(d.exitFort(66));
	}

	/*
	* tests exitFort in data
	*/
	@Test
	public void testExitFort_3()
	{
		Manager m = new Manager();
		Data d = new Data();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(116,59,60,62,118,49,105,51,39,71,115,73,106,4,5,6,7,8,17,34,44,45,55,56,57,65,66,67,77,78,88,110,114,-1,102,64,120));
		d.boardData = list.toArray(d.boardData);
		m.setData(d);
 		assertTrue(d.exitFort(116));
	}

	/*
	* tests exitFort in data
	*/
	@Test
	public void testExitFort_4()
	{
		Manager m = new Manager();
		Data d = new Data();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(56,91,78,57,63,45,50,51,39,68,72,73,83,4,5,6,7,8,17,37,44,26,55,3,2,65,66,103,77,100,88,105,114,115,116,117,118));
		d.boardData = list.toArray(d.boardData);
		m.setData(d);
 		assertTrue(d.exitFort(56));
	}
}



