import java.lang.*;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 *	This class holds all tests for the Board class. 
 *	Internal "private" methods are inherently tested via these methods.
 *	Playing actions from user clicks are tested by hand
 */
public class BoardTest
{

	/*
	 *	This test tests that a piece can be printed on a valid square of the board.
	 */
	@Test
	public void testPrintPieceInBounds()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Coordinate c = new Coordinate(1, 1);
		assertEquals(b.printPiece(c, " "), 0);
	}
	
	/*
	 *	This test tests that a piece cannot be printed on an invalid square (out of bounds of the board).
	 */
	@Test
	public void testPrintPieceOutBounds()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Coordinate c = new Coordinate(-11, 1);
		assertEquals(b.printPiece(c, " "), 1);
	}
	
	/*
	 *	This test tests that a piece can be removed on a valid square of the board.
	 */
	@Test
	public void testRemovePieceInBounds()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Coordinate c = new Coordinate(3, 0);
		assertEquals(b.removePiece(c), 0);
	}
	
	/*
	  *	This test tests that a piece cannot be removed from an invalid square (out of bounds of the board).
	 */
	@Test
	public void testRemovePieceOutBounds()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Coordinate c = new Coordinate(-11, 1);
		assertEquals(b.removePiece(c), 1);
	}
	
	/*
	 *	This test tests that all of the pieces of the board (for each player) can be printed without error.
	 */
	@Test
	public void testPrintPieces()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		assertEquals(b.printPieces(g.queryPlayers()[0]), 0);
		assertEquals(b.printPieces(g.queryPlayers()[1]), 0);
	}
		
}
