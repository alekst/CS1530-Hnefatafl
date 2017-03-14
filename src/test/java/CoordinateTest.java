import java.lang.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.stream.*;

public class CoordinateTest
{
	@Test
	public void testGetX()
	{
		int x = 5;
		int y = 10;
		Coordinate c = new Coordinate(x,y);
		assertEquals(c.getX(), 5);
	}
	
	@Test
	public void testGetY()
	{
		int x = 5;
		int y = 10;
		Coordinate c = new Coordinate(x,y);
		assertEquals(c.getY(), 10);
	}
}