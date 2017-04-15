import java.lang.*;
import java.util.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

/*
 *	This class holds all tests for the Game class. 
 *	Internal "private" methods are inherently tested via these methods.
 *	Playing actions from user clicks are tested by hand
 */
public class GameTest
{
  //Tests if queryManager returns a Manager object
	@Test
	public void testQueryInstanceOfManager()
	{
		Game g = new Game();
		assertThat(g.queryManager(), instanceOf(Manager.class));
	}
  
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