import java.lang.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.stream.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ManagerTest
{

	/**
	* Tests to see if the destination square is unoccupied and if it is
	* the move should be valid.
	*/
	@Test
	public void testIfCoordinatesAreValid()
	{
		Coordinate c = new Coordinate(1, 4);
		Coordinate b = new Coordinate(4, 4);
		Manager m = new Manager();
		
		assertTrue(m.isValid(c, b));	
	}
	
	/**
	* Tests to see if the destination square is occupied and if it is
	* the method should return false
	*/
	@Test
	public void testIfCoordinatesAreInvalid()
	{
		Coordinate c = new Coordinate(5, 5);
		Coordinate b = new Coordinate(10, 4);
		Manager m = new Manager();
		
		assertFalse(m.isValid(c, b));	
	}

	/**
	* Tests whether an up down move is valid
	*/
	@Test 
	public void testIfUpDownMoveIsValid()
	{
       	Manager m = new Manager();
        Coordinate x=new Coordinate(1,5);
        Coordinate y=new Coordinate(1,10);
        boolean value=m.isValid(x,y);
        assertTrue(value);
	}

	/**
	* Tests whether an up down is invalid
	*/

	@Test
	public void testIfUpDownMoveIsInvalid()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(6,4);
		Coordinate y=new Coordinate(6,7);
		boolean value=m.isValid(x,y);
		assertFalse(value);
	}

	/**
	* Tests whether a down up move is valid
	*/
	@Test
	public void testIfDownUpMoveIsValid()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(3,10);
		Coordinate y=new Coordinate(3,6);
		boolean value=m.isValid(x,y);
		assertTrue(value);
	}

	/**
	* Tests whether a down up move is invalid
	*/

	@Test 
	public void testIfDownUpMoveIsInvalid()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(5,7);
		Coordinate y=new Coordinate(5,2);
		boolean value=m.isValid(x,y);
		assertFalse(value);
	}

	/**
	* Tests whether a left right move is valid
	*/
	@Test
	public void testIfLeftRightMoveIsValid()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(0,3);
		Coordinate y=new Coordinate(4,3);
		boolean value=m.isValid(x,y);
		assertTrue(value);
	}

	/**
	* Tests whether a left right move is invalid
	*/
	@Test
	public void testIfLeftRightMoveIsInvalid()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(3,5);
		Coordinate y=new Coordinate(8,5);
		boolean value=m.isValid(x,y);
		assertFalse(value);
	}

	/**
	* Tests whether a right left move is valid
	*/
	@Test
	public void testIfRighLeftMoveIsValid()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(10,7);
		Coordinate y=new Coordinate(6,7);
		boolean value=m.isValid(x,y);
		assertTrue(value);
	}

	/**
	* Tests whether a right left move is invalid
	*/
	@Test
	public void testIfRighLeftMoveIsInvalid()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(7,5);
		Coordinate y=new Coordinate(2,5);
		boolean value=m.isValid(x,y);
		assertFalse(value);
	}

	/**
	* tests to ensure non-king pieces cannot enter the top left special square
	*/
	@Test
	public void testTopLeftSpecialSquare()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(0,3);
		Coordinate y=new Coordinate(0,0);
		boolean value=m.isValid(x,y);
		assertFalse(value);
	}

	/**
	* tests to ensure non-king pieces cannot enter the bottom left special square
	*/
	@Test
	public void testBottomLeftSpecialSquare()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(0,7);
		Coordinate y=new Coordinate(0,10);
		boolean value=m.isValid(x,y);
		assertFalse(value);
	}

	/**
	* tests to ensure non-king pieces cannot enter the top right special square
	*/
	@Test
	public void testTopRightSpecialSquare()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(10,3);
		Coordinate y=new Coordinate(10,0);
		boolean value=m.isValid(x,y);
		assertFalse(value);
	}

	/**
	* tests to ensure non-king pieces cannot enter the bottom right special square
	*/
	@Test
	public void testBottomRightSpecialSquare()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(10,7);
		Coordinate y=new Coordinate(10,10);
		boolean value=m.isValid(x,y);
		assertFalse(value);
	}

	/**
	* tests a board set up in which the king should be cornered. 
	*
	*/
	
	@Test
	public void testKingShouldBeCornered()
	{
		// arrange 
		
		Manager m = new Manager();
		Data d = new Data();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(93, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 82, 92, 94, 104));
		d.boardData = list.toArray(d.boardData);
		m.setData(d);
		Coordinate king = new Coordinate(4, 8);
		
		// assert
		assertTrue(m.isKingSurrounded(king));	
		
	}
	
	/**
	* tests a board set up (it is an initial set up) in which the king should not be cornered. 
	*
	*/
	@Test 
	public void testKingShouldNotBeCornered()
	{
		// arrange 
		
		Manager m = new Manager();

		Coordinate king = new Coordinate(5, 5);
		
		// assert
		assertFalse(m.isKingSurrounded(king));	
	}
	
	/**
	* tests to see if the king has escaped. 
	*
	*/
	
	@Test
	public void testIfKingHasEscaped()
	{
		// arrange
		Manager m = new Manager();
		
		
		Coordinate king = new Coordinate(10, 10); // a special escape square
		
		// assert
		assertTrue(m.hasKingEscaped(king));
	}
	
	/**
	* tests to see if the king has not escaped. 
	*
	*/
	
	@Test
	public void testIfKingHasNotEscaped()
	{
		// arrange
		Manager m = new Manager();

		
		Coordinate king = new Coordinate(2, 10);
		
		//assert
		assertFalse(m.hasKingEscaped(king));
	}
	
	/** 
	* the following tests will check to see if inSpecialSquare
	* returns true when special squares are input and false
	* when the x,y are not the coordinates for a special square
	*/
	
	@Test
	public void upperLeftSpecialCheck()
	{
		Manager m = new Manager();
		assertTrue(m.inSpecialSquare(0,0));
	}
	
	@Test
	public void upperRightSpecialCheck()
	{
		Manager m = new Manager();
		assertTrue(m.inSpecialSquare(10,0));
	}
	
	@Test
	public void lowerLeftSpecialCheck()
	{
		Manager m = new Manager();
		assertTrue(m.inSpecialSquare(0,10));
	}
	
	@Test
	public void lowerRightSpecialCheck()
	{
		Manager m = new Manager();
		assertTrue(m.inSpecialSquare(10,10));
	}
	
	@Test
	public void centerSpecialCheck()
	{
		Manager m = new Manager();
		assertTrue(m.inSpecialSquare(5,5));
	}
	
	@Test
	public void notSpecialCheck()
	{
		Manager m = new Manager();
		assertFalse(m.inSpecialSquare(3,4));
	}
	
	/**
	* This will test if the encoding returns the right value, given a Coordinate object
	*/
	@Test
	public void testEncode()
	{
		Manager m = new Manager();
		Coordinate c = new Coordinate(0, 0);
		int expected = 1;
		assertEquals(expected, m.encode(c));
	}
	
	/**
	* This will test if the encoding returns the right value, given a Coordinate object
	*/
	@Test
	public void testEncodeWrong()
	{
		Manager m = new Manager();
		Coordinate c = new Coordinate(10, 10);
		int expected = 1;
		assertNotEquals(expected, m.encode(c));
	}
	
	/**
	* This will test if it returns a proper index
	*/
	@Test
	public void testKingIndex()
	{
		Manager m = new Manager();
		Coordinate c = new Coordinate(5, 5); // should be the initial king position
		int expected = 0; // the king's index
		assertEquals(expected, m.getIndex(c));
	}
	
	/**
	* This will test if the king's index works. 
	*/
	@Test
	public void testKingLocation()
	{
		Manager m = new Manager();
		Coordinate expected = new Coordinate(5, 5); // should be the initial king position
		assertEquals(expected.getX(), m.getKing().getX());
		assertEquals(expected.getY(), m.getKing().getY());
	}
	
	/**
	* This will test if only the Whites are returned
	*/
	@Test
	public void testGetWhites()
	{
		Manager m = new Manager();
		int expected = 13;
		assertEquals(expected, m.getWhites().length);
	}
	
	/**
	* This will test if only the Blacks are returned
	*/
	@Test
	public void testGetBlacks()
	{
		Manager m = new Manager();
		int expected = 24;
		assertEquals(expected, m.getBlacks().length);
	}
	
	/*
	 * This will test getBoardStatus returns a Coordinate array of size 37. 
	 */
	@Test
	public void testGetBoardStatus()
	{
		Manager m = new Manager();
		int expected  = 37;
		assertEquals(expected, m.getBoardStatus().length);
	}
	
	//Tests if getBoardData returns a Data object
	@Test
	public void testGetBoardData()
	{
		Manager m = new Manager();
		assertThat(m.getBoardData(), instanceOf(Data.class));
	}
	
	
	/*
	 * This will test if someoneThere returns true when there is a piece in the square 
	 */
	@Test
	public void testSomeoneThereTrue()
	{
		Manager m = new Manager();
		Coordinate c = new Coordinate(5, 5); // the king's coordinate
		assertTrue(m.someoneThere(c));
	}
	
	/*
	 * This will test if someoneThere returns false when there is no piece in the square 
	 */
	@Test
	public void testSomeoneThereFalse()
	{
		Manager m = new Manager();
		Coordinate c = new Coordinate(11, 0); // a special square
		assertFalse(m.someoneThere(c));
	}
	
	/*
	 * This will test if the coordinate contains the King
	 */
	@Test
	public void testIsKingTrue()
	{
		Manager m = new Manager();
		Coordinate c = new Coordinate(5, 5); // the king's coordinate
		assertTrue(m.isKing(c));
	}
	
	/*
	 * This will test if the coordinate does not contain the King
	 */
	@Test
	public void testIsKingFalse()
	{
		Manager m = new Manager();
		Coordinate c = new Coordinate(4, 5); // the king's neighboring coordinate
		assertFalse(m.isKing(c));
	}
	
	/*
	 * This will test if the Data is loaded, given an array of Integers
	 */
	@Test
	public void testLoadData()
	{
		Manager m = new Manager();
		Integer[] board = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36};
		assertEquals(m.loadData(board), 0);
	}

	/*
	* Tests rule 9 in manager
	*/
	@Test
	public void test1_rule_9()
	{
		
		Manager m = new Manager();
		Data d = new Data();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(114 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,15 ,5 ,6 ,7 ,8 ,17 ,37 ,40,45 ,55 ,56 ,59 ,53 ,66 ,67 ,77 ,78 ,87 ,103 ,113 ,115 ,116 ,117 ,74 ));
		d.boardData = list.toArray(d.boardData);
		m.setData(d);
		Coordinate temp=new Coordinate(3,10);
		assertTrue(m.rule_9(temp));
	}

	/*
	* Tests rule 9 in manager
	*/
	@Test
	public void test2_rule_9()
	{
		Manager m = new Manager();
		Data d = new Data();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(39 ,82 ,38 ,54 ,66 ,-1 ,-1 ,17 ,44 ,-1 ,84 ,73 ,95 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,55 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1));
		d.boardData = list.toArray(d.boardData);
		m.setData(d);
		Coordinate temp=new Coordinate(10,4);
		assertTrue(m.rule_9(temp));
	}




}
