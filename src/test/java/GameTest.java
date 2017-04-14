import java.lang.*;
import java.util.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;



public class GameTest
{
	
	//Tests if queryManager returns a Manager object
	@Test
	public void testQueryInstanceOfManager()
	{
		Game g = new Game();
		assertThat(g.queryManager(), instanceOf(Manager.class));
	}
	
}