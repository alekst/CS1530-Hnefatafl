import java.lang.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.stream.*;

// These tests are related to PlayerInfoPanel class
public class PlayerInfoTest
{
	// This test verifies that the number of pieces is set and gotten correctly. 
	@Test
	public void testSetNumberOfPieces()
	{
		PlayerInfoPanel pi = new PlayerInfoPanel();
		int pieces = 20;
		pi.setNumPieces(pieces);
		assertEquals(pi.getNumPiece(), pieces);	
	}
	
	// This test verifies that the number of pieces is set and gotten correctly. 
	@Test
	public void testSetWrongNumberOfPieces()
	{
		PlayerInfoPanel pi = new PlayerInfoPanel();
		int pieces = 15;
		pi.setNumPieces(pieces);
		assertNotEquals(pi.getNumPiece(), 10);	
	}
	
	// This test verifies that the decrementing the number of pieces works as expected. 
	@Test
	public void testTakeTwoPieces()
	{
		PlayerInfoPanel pi = new PlayerInfoPanel();
		int pieces = 20;
		pi.setNumPieces(pieces);
		pi.takeAPiece();
		pi.takeAPiece();
		assertEquals(pi.getNumPiece(), pieces - 2);	
	}
	
	// This test verifies that the setting of the pieces works as expected. 
	@Test
	public void testSetAPiece()
	{
		PlayerInfoPanel pi = new PlayerInfoPanel();
		int pieces = 20;
		pi.setNumPieces(pieces);
		int morePieces = 200;
		pi.setNumPieces(morePieces);
		assertEquals(pi.getNumPiece(), morePieces);	
	}
	
}