import java.lang.*;
import javax.swing.*;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

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
	 *	This test tests that all of the pieces of the board can be printed without error.
	 */
	@Test
	public void testPrintAllPieces()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		assertEquals(b.printAll(), 0);
	}
	
	/*
	 *	This test tests that printBoard returns a string
	 */
	@Test
	public void testprintBoardIsString()
	{
		Manager m = new Manager();
		Player p1 = new Player(m);
		PlayerInfoPanel in = new PlayerInfoPanel("weezy", 1, 12);
		p1.addInfo(in);
		Player p2 = new Player(m);
		p2.addInfo(in);
		Board b = new Board(m, p1, p2);
		assertThat(b.printBoard(), instanceOf(String.class));
	}
	
	
}
