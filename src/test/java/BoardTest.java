import java.lang.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest
{
	@Test
	public void testPrintBlackInBounds()
	{
		Board b = new Board();
		Coordinate c = new Coordinate(1, 1);
		assertEquals(b.printBlack(c), 0);
	}
	
	@Test
	public void testPrintBlackOutBounds()
	{
		Board b = new Board();
		Coordinate c = new Coordinate(-11, 1);
		assertEquals(b.printBlack(c), 1);
	}
	
	@Test
	public void testPrintWhiteInBounds()
	{
		Board b = new Board();
		Coordinate c = new Coordinate(1, 1);
		assertEquals(b.printWhite(c), 0);
	}
	
	@Test
	public void testPrintWhiteOutBounds()
	{
		Board b = new Board();
		Coordinate c = new Coordinate(-11, 1);
		assertEquals(b.printWhite(c), 1);
	}
	
	@Test
	public void testPrintKingInBounds()
	{
		Board b = new Board();
		Coordinate c = new Coordinate(1, 1);
		assertEquals(b.printKing(c), 0);
	}
	
	@Test
	public void testPrintKingOutBounds()
	{
		Board b = new Board();
		Coordinate c = new Coordinate(-11, 1);
		assertEquals(b.printKing(c), 1);
	}
	
	@Test
	public void testRemovePieceInBounds()
	{
		Board b = new Board();
		Coordinate c = new Coordinate(1, 1);
		assertEquals(b.removePiece(c), 0);
	}
	
	@Test
	public void testRemovePieceOutBounds()
	{
		Board b = new Board();
		Coordinate c = new Coordinate(-11, 1);
		assertEquals(b.removePiece(c), 1);
	}
	
	@Test
	public void testPrintAllPieces()
	{
		Board b = new Board();
		assertEquals(b.printAllPieces(), 0);
	}
}