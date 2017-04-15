import java.lang.*;
import javax.swing.*;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 *	This class holds all tests for the Game class. 
 *	Internal "private" methods are inherently tested via these methods.
 *	Playing actions from user clicks are tested by hand
 */
public class GameTest
{
	/*
	 *	This test tests that getTurn gets the player turn
	 */
	@Test
	public void testGetTurn()
	{
		Game g = new Game();
		assertEquals(1, g.getTurn());
	}
}