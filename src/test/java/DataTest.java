import java.lang.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.stream.*;

public class DataTest
{
	@Test
	public void testEncodingCoordinates11()
	{
		Manager m = new Manager();
		Coordinate c = new Coordinate(1, 1);
		assertEquals(m.encode(c), 13);
	}

	@Test 
	public void testEncodingCoordinates510()
	{
		Manager m = new Manager();
		Coordinate c = new Coordinate(5, 10);
		assertEquals(m.encode(c), 116);
	}

	@Test
	public void testEncodingCoordinates55()
	{
		Manager m = new Manager();
		Coordinate c = new Coordinate(5, 5);
		assertEquals(m.encode(c), 61);
	}

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
	
	
	@Test
	public void testIfCoordinatesAreValid()
	{
		Coordinate c = new Coordinate(1, 4);
		Coordinate b = new Coordinate(4, 4);
		Manager m = new Manager();
		
		assertTrue(m.isValid(c, b));	
	}
	
	@Test
	public void testIfCoordinatesAreInvalid()
	{
		Coordinate c = new Coordinate(5, 5);
		Coordinate b = new Coordinate(10, 4);
		Manager m = new Manager();
		
		assertFalse(m.isValid(c, b));	
	}

	@Test 
	public void testIfUpDownMoveIsValid()
	{
       	Manager m = new Manager();
        Coordinate x=new Coordinate(1,5);
        Coordinate y=new Coordinate(1,10);
        boolean value=m.isValid(x,y);
        assertTrue(value);
	}

	@Test
	public void testIfUpDownMoveIsInvalid()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(6,4);
		Coordinate y=new Coordinate(6,7);
		boolean value=m.isValid(x,y);
		assertFalse(value);
	}

	@Test
	public void testIfDownUpMoveIsValid()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(3,10);
		Coordinate y=new Coordinate(3,6);
		boolean value=m.isValid(x,y);
		assertTrue(value);
	}

	@Test 
	public void testIfDownUpMoveIsInvalid()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(5,7);
		Coordinate y=new Coordinate(5,2);
		boolean value=m.isValid(x,y);
		assertFalse(value);
	}

	@Test
	public void testIfLeftRightMoveIsValid()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(0,3);
		Coordinate y=new Coordinate(4,3);
		boolean value=m.isValid(x,y);
		assertTrue(value);
	}

	@Test
	public void testIfLeftRightMoveIsInvalid()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(3,5);
		Coordinate y=new Coordinate(8,5);
		boolean value=m.isValid(x,y);
		assertFalse(value);
	}

	@Test
	public void testIfRighLeftMoveIsValid()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(10,7);
		Coordinate y=new Coordinate(6,7);
		boolean value=m.isValid(x,y);
		assertTrue(value);
	}

	@Test
	public void testIfRighLeftMoveIsInvalid()
	{
		Manager m = new Manager();
		Coordinate x=new Coordinate(7,5);
		Coordinate y=new Coordinate(2,5);
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



