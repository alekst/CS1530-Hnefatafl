import java.lang.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.stream.*;

public class CoordinateTest
{
	// Setup a Coordinate object and retrieve the x coordinate
	@Test
	public void testGetX()
	{
		int x = 5;
		int y = 10;
		Coordinate c = new Coordinate(x,y);
		assertEquals(c.getX(), 5);
	}
	
	// Setup a Coordinate object and retrieve the y coordinate
	@Test
	public void testGetY()
	{
		int x = 5;
		int y = 10;
		Coordinate c = new Coordinate(x,y);
		assertEquals(c.getY(), 10);
	}
	
	//Tests comparison of two Coordinate objects if two objects contain the same values
	@Test
	public void testEqual()
	{
		Coordinate c = new Coordinate(3, 4);
		Coordinate d = new Coordinate(3, 4);
		assertTrue(c.equal(d));
	}
	
	// Test comparison of two Coordinate objects if two objects do not contain the same values
	@Test
	public void testNotEqual()
	{
		Coordinate c = new Coordinate(1, 2);
		Coordinate d = new Coordinate(3, 4);
		assertFalse(c.equal(d));
	}
	
	// Tests to determine if the Coordinate object contains (-1, -1)
	@Test
	public void testMinusOne()
	{
		Coordinate c = new Coordinate(-1, -1);
		assertTrue(c.isMinusOne());
	}
	
	// Tests to determine if the Coordinate object does not contain (-1, -1)
	@Test
	public void testNotMinusOneNegative()
	{
		Coordinate c = new Coordinate(-3, -4);
		assertFalse(c.isMinusOne());
	}
	
	// Tests to determine if the Coordinate object does not contain (-1, -1)
	@Test
	public void testNotMinusOnePositive()
	{
		Coordinate c = new Coordinate(1, 1);
		assertFalse(c.isMinusOne());
	}
}