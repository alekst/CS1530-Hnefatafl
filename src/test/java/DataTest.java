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
		Data d = new Data();
		Coordinate c = new Coordinate(1, 1);
		assertEquals(d.encode(c), 13);
	}

	@Test 
	public void testEncodingCoordinates510()
	{
		Data d = new Data();
		Coordinate c = new Coordinate(5, 10);
		assertEquals(d.encode(c), 116);
	}

	@Test
	public void testEncodingCoordinates55()
	{
		Data d = new Data();
		Coordinate c = new Coordinate(5, 5);
		assertEquals(d.encode(c), 61);
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
		Data d = new Data();
		d.initialize();
		Coordinate c = new Coordinate(5, 5); // where the king should be

		// act
		int index = d.getIndex(c);

		// assert
		assertEquals(index, 0); // the king lives in the index 0
	}

	@Test
	public void testGettingIndexEmptySquare()
	{
		// arrange
		Data d = new Data();
		d.initialize();
		Coordinate c = new Coordinate(0, 1); // shouldn't be in the array

		// act
		int index = d.getIndex(c);

		// assert
		assertEquals(index, -1); //-1 means not found
	}

	@Test
	public void testIfNumberIsAMember()
	{
		// arrange
		Data d = new Data();
		d.initialize();
		Coordinate c = new Coordinate(5, 5); // the king
	
		// act
		boolean b = d.isMember(c);
	
		// assert
		assertTrue(b);
	
	}

	@Test
	public void testIfNumberIsNotAMember()
	{
		// arrange
		Data d = new Data();
		d.initialize();
		Coordinate c = new Coordinate(0, 0); // the first square is empty
	
		// act
		boolean b = d.isMember(c);
	
		// assert
		assertFalse(b);
	
	}
	
	@Test
	public void testIfBoardStatusReturnsBoard()
	{
		// arrange
		Data d = new Data();
		d.initialize();
		 
		// act
		Coordinate[] board = d.getBoardStatus();
		
		// assert
		assertEquals(board.length, 37);
		
	}
	
	@Test
	public void testIfCoordinatesAreValid()
	{
		Coordinate c = new Coordinate(1, 4);
		Coordinate b = new Coordinate(4, 4);
		Data d = new Data();
		d.initialize();
		
		assertTrue(d.isValid(c, b));	
	}
	
	@Test
	public void testIfCoordinatesAreInvalid()
	{
		Coordinate c = new Coordinate(5, 5);
		Coordinate b = new Coordinate(10, 4);
		Data d = new Data();
		d.initialize();
		
		assertFalse(d.isValid(c, b));	
	}

	@Test 
	public void testIfUpDownMoveIsValid()
	{
        Data d=new Data();
        d.initialize();
        Coordinate x=new Coordinate(1,5);
        Coordinate y=new Coordinate(1,10);
        boolean value=d.isValid(x,y);
        assertTrue(value);
	}

	@Test
	public void testIfUpDownMoveIsInvalid()
	{
		Data d=new Data();
		d.initialize();
		Coordinate x=new Coordinate(6,4);
		Coordinate y=new Coordinate(6,7);
		boolean value=d.isValid(x,y);
		assertFalse(value);
	}

	@Test
	public void testIfDownUpMoveIsValid()
	{
		Data d=new Data();
		d.initialize();
		Coordinate x=new Coordinate(3,10);
		Coordinate y=new Coordinate(3,6);
		boolean value=d.isValid(x,y);
		assertTrue(value);
	}

	@Test 
	public void testIfDownUpMoveIsInvalid()
	{
		Data d=new Data();
		d.initialize();
		Coordinate x=new Coordinate(5,7);
		Coordinate y=new Coordinate(5,2);
		boolean value=d.isValid(x,y);
		assertFalse(value);
	}

	@Test
	public void testIfLeftRightMoveIsValid()
	{
		Data d=new Data();
		d.initialize();
		Coordinate x=new Coordinate(0,3);
		Coordinate y=new Coordinate(4,3);
		boolean value=d.isValid(x,y);
		assertTrue(value);
	}

	@Test
	public void testIfLeftRightMoveIsInvalid()
	{
		Data d=new Data();
		d.initialize();
		Coordinate x=new Coordinate(3,5);
		Coordinate y=new Coordinate(8,5);
		boolean value=d.isValid(x,y);
		assertFalse(value);
	}

	@Test
	public void testIfRighLeftMoveIsValid()
	{
		Data d=new Data();
		d.initialize();
		Coordinate x=new Coordinate(10,7);
		Coordinate y=new Coordinate(6,7);
		boolean value=d.isValid(x,y);
		assertTrue(value);
	}

	@Test
	public void testIfRighLeftMoveIsInvalid()
	{
		Data d=new Data();
		d.initialize();
		Coordinate x=new Coordinate(7,5);
		Coordinate y=new Coordinate(2,5);
		boolean value=d.isValid(x,y);
		assertFalse(value);
	}


	
	@Test
	public void testKingShouldBeCornered()
	{
		// arrange 
		
		Data d = new Data();
		d.initialize();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(93, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 82, 92, 94, 104));
		d.boardData = list.toArray(d.boardData);
		Coordinate king = new Coordinate(4, 8);
		
		// assert
		assertTrue(d.isSurrounded(king));	
		
	}
	
	@Test 
	public void testKingShouldNotBeCornered()
	{
		// arrange 
		
		Data d = new Data();
		d.initialize();
		Coordinate king = new Coordinate(5, 5);
		
		// assert
		assertFalse(d.isSurrounded(king));	
	}
	
	@Test
	public void testIfKingHasEscaped()
	{
		// arrange
		Data d = new Data();
		d.initialize();
		
		Coordinate king = new Coordinate(10, 10);
		
		// assert
		assertTrue(d.hasEscaped(king));
	}
	
	@Test
	public void testIfKingHasNotEscaped()
	{
		// arrange
		Data d = new Data();
		d.initialize();
		
		Coordinate king = new Coordinate(2, 10);
		
		//assert
		assertFalse(d.hasEscaped(king));
	}
	
}



