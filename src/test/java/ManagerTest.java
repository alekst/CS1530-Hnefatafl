import java.lang.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.stream.*;

public class ManagerTest
{
	@Test
	public void testGettingIndexOfKing()
	{
		// arrange
		Manager m = new Manager();
		Coordinate c = new Coordinate(5, 5); // where the king should be

		// act
		int index = m.getIndex(c);

		// assert
		assertEquals(index, 0); // the king lives in the index 0
	}

	@Test
	public void testGettingIndexEmptySquare()
	{
		// arrange
		Manager m = new Manager();
		Coordinate c = new Coordinate(0, 1); // shouldn't be in the array

		// act
		int index = m.getIndex(c);

		// assert
		assertEquals(index, -1); //-1 means not found
	}

	@Test
	public void testIfNumberIsAMember()
	{
		// arrange
		Manager m = new Manager();
		Coordinate c = new Coordinate(5, 5); // the king
	
		// act
		boolean b = m.someoneThere(c);
	
		// assert
		assertTrue(b);
	
	}

	@Test
	public void testIfNumberIsNotAMember()
	{
		// arrange
		Manager m = new Manager();
		Coordinate c = new Coordinate(0, 0); // the first square is empty
	
		// act
		boolean b = m.someoneThere(c);
	
		// assert
		assertFalse(b);
	
	}
	
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
	
	@Test 
	public void testKingShouldNotBeCornered()
	{
		// arrange 
		
		Manager m = new Manager();

		Coordinate king = new Coordinate(5, 5);
		
		// assert
		assertFalse(m.isKingSurrounded(king));	
	}
	
	@Test
	public void testIfKingHasEscaped()
	{
		// arrange
		Manager m = new Manager();
		
		
		Coordinate king = new Coordinate(10, 10);
		
		// assert
		assertTrue(m.hasKingEscaped(king));
	}
	
	@Test
	public void testIfKingHasNotEscaped()
	{
		// arrange
		Manager m = new Manager();

		
		Coordinate king = new Coordinate(2, 10);
		
		//assert
		assertFalse(m.hasKingEscaped(king));
	}
}
