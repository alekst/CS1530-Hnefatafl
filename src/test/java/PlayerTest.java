import java.lang.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.stream.*;

public class PlayerTest
{
	// Check if isWhite accurately detects white players
	// and that setWhite accurately sets white players
	@Test
	public void testSetWhiteIsWhite()
	{
		Player p = new Player();
		p.setWhite();
		assertEquals(p.isWhite(), true);
	}

	// Checks if isWhite accurately detects white players
	// and that setBlack accurately sets black players
	@Test
	public void testSetBlackIsWhite()
	{
		Player p = new Player();
		p.setBlack();
		assertEquals(p.isWhite(), false);
	}
	
	// checks if myTurn accurately detects players not on their turns
	@Test
	public void testSetWhiteMyTurn()
	{
		Player p = new Player();
		p.setWhite();
		assertEquals(p.myTurn(), false);
	}
	
	// Checks if myTurn accurately detects players on their turn
	@Test
	public void testSetBlackMyTurn()
	{
		Player p = new Player();
		p.setBlack();
		assertEquals(p.myTurn(), true);
	}
	
	// Checks if doneWithTurn accurately ends turns
	@Test
	public void testdoneWithTurn()
	{
		Player p = new Player();
		p.setBlack();
		p.doneWithTurn();
		assertEquals(p.myTurn(), false);
	}
	
	
	// Checks if newTurn accurately begins a new turn
	@Test
	public void testNewTurn()
	{
		Player p = new Player();
		p.setWhite();
		p.newTurn();
		assertEquals(p.myTurn(), true);
	}
	
	/* TEST WRITING IN PROGRESS
	// Checks if getPieces with a black player returns
	// 	correct black pieces	
	@Test
	public void testGetBlackPieces()
	{
		Manager m = new Manager();
		Data d = new Data();
		
		Player p = new Player(m);
		p.setBlack();
		
		
		boolean test_var = p.getPieces().equals(m.getBlacks());
		assertEquals(test_var, true);
	}
	
	// Checks if getPieces with a white player returns
	// 	correct white pieces
	@Test
	public void testGetWhitePieces()
	{
		Manager m = new Manager();
		Data d = new Data();
		
		Player p = new Player(m);
		p.setWhite();
		
		boolean test_var = p.getPieces().equals(m.getWhites());
		assertEquals(test_var, true);
	}
	
	*/
	
	// Checks if hasWon works when somebody has won
	@Test
	public void testHasWonTrue()
	{
		Data d = new Data();
		d.set(0, 1);
		
		Manager m = new Manager();
		m.setData(d);
		
		Player p = new Player(m);
		p.setWhite();
		
		assertEquals(p.hasWon(), true);
	}
	
	// Checks if hasWon works when nobody has won
	@Test
	public void testHasWonFalse()
	{
		Manager m = new Manager();
		
		Player p = new Player(m);
		p.setWhite();
		
		assertEquals(p.hasWon(), false);
	}
	
	// Checks if the PlayerInfoPanel has been added
	@Test
	public void testPanelAdded()
	{
		Manager m = new Manager();
		Player p = new Player();
		PlayerInfoPanel panel = new PlayerInfoPanel("dworb", 300);
		p.addInfo(panel);
		PlayerInfoPanel actual = p.getInfo();
		assertEquals(panel, actual);
	}
	

	//tests exitFort
	@Test
	public void testExitFort()
	{
		Manager m=new Manager();
		Data d = new Data();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(6,59,16,62,63,4,8,18,-1,71,17,73,83,3,37,14,43,10,22,34,44,45,55,56,57,65,66,67,77,78,88,105,114,115,116,117,118));
		d.boardData = list.toArray(d.boardData);
		m.setData(d);
		Player p=new Player(m);
		assertTrue(p.exitFort());
	}
	
}
		


