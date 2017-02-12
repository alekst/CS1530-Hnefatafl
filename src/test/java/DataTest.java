import java.lang.*;
import org.junit.Test;
import static org.junit.Assert.*;

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
		d. initialize();
		 
		// act
		Coordinate[] board = d.getBoardStatus();
		
		// assert
		assertEquals(board.length, 36);
		
	}
	
	@Test
	public void testIfCoordinatesAreValid()
	{
		Coordinate c = new Coordinate(5, 5);
		Coordinate b = new Coordinate(1, 5);
		Data d = new Data();
		
		assertTrue(d.isValid(c, b));	
	}
	
	@Test
	public void testIfCoordinatesAreInvalid()
	{
		Coordinate c = new Coordinate(5, 5);
		Coordinate b = new Coordinate(10, 4);
		Data d = new Data();
		
		assertFalse(d.isValid(c, b));	
	}
	
}



