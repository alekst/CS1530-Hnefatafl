import java.lang.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest
{

	@Test
	public void testPrintPieceInBounds()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Coordinate c = new Coordinate(1, 1);
		assertEquals(b.printPiece(c, " "), 0);
	}
	
	@Test
	public void testPrintPieceOutBounds()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Coordinate c = new Coordinate(-11, 1);
		assertEquals(b.printPiece(c, " "), 1);
	}
	
	
	@Test
	public void testRemovePieceInBounds()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Coordinate c = new Coordinate(3, 0);
		assertEquals(b.removePiece(c), 0);
	}
	
	@Test
	public void testRemovePieceOutBounds()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Coordinate c = new Coordinate(-11, 1);
		assertEquals(b.removePiece(c), 1);
	}
	
	@Test
	public void testPrintPieces()
	{
		Board b = new Board();
		Player p = new Player();
		Manager m = new Manager();
		
		assertEquals(b.printPieces(p), 0);
	}
}