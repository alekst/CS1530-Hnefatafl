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

	
}



