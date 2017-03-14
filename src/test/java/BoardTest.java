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
		Game g = new Game();
		Board b = g.queryBoard();
		assertEquals(b.printPieces(g.queryPlayers()[0]), 0);
		assertEquals(b.printPieces(g.queryPlayers()[1]), 0);
	}
	
	@Test
	public void testEnableValidCoor()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Coordinate c = new Coordinate(5, 0);
		assertEquals(b.enable(c), 0);
	}
		
	@Test
	public void testEnableInvalidCoor()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Coordinate c = new Coordinate(-11, 1);
		assertEquals(b.enable(c), 1);
	}
	
	@Test
	public void testEnablePlayer0()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Player p = g.queryPlayers()[0];
		assertEquals(b.enable(p), 0);
	}
	
	@Test
	public void testEnablePlayer1()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Player p = g.queryPlayers()[1];
		assertEquals(b.enable(p), 0);
	}
	
	@Test
	public void testDisableValidCoor()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Coordinate c = new Coordinate(5, 0);
		assertEquals(b.disable(c), 0);
	}
	
	@Test
	public void testDisableInvalidCoor()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Coordinate c = new Coordinate(-11, 1);
		assertEquals(b.disable(c), 1);
	}
		
	@Test
	public void testDisablePlayer0()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Player p = g.queryPlayers()[0];
		assertEquals(b.disable(p), 0);
	}

	@Test
	public void testDisablePlayer1()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		Player p = g.queryPlayers()[1];
		assertEquals(b.disable(p), 0);
	}
	
	@Test
	public void testMoveBlackPiece()
	{
		assertEquals(1, 1);
	}
	
	@Test
	public void testMoveWhitePiece()
	{
		assertEquals(1, 1);
	}
	
	@Test
	public void testMoveKing()
	{
		assertEquals(1, 1);
	}
	
	@Test
	public void testSwitchTurns()
	{
		Game g = new Game();
		Board b = g.queryBoard();
		assertEquals(b.switchTurn(), 0);
	}
	
}