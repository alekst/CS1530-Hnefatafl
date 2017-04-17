import java.lang.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.stream.*;

public class SquareTest
{
	// This class will test Square class methods.
	
	@Test
	public void testGetCoord()
	{
		Coordinate c = new Coordinate(5, 5);
		Square square = new Square(c);
		Coordinate returned = square.getCoord();
		assertEquals(returned.getX(), c.getX());
		assertEquals(returned.getY(), c.getY());
	}
	
}