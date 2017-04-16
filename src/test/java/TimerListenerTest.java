import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class TimerListenerTest
{

	JLabel label = new JLabel("test");
	TimerListener listener = new TimerListener(label);
	
	// Checks if the moveTime is zero when the listener is created
	@Test
	public void testMoveTimeInit()
	{
		assertEquals(listener.getMoveTime(), 0);
		assertEquals(listener.getCountdown(), 300); // 300 seconds is on the clock. Will need to be changed once the time is changed. 
	}
	
	@Test
	public void testAddingToCountdown()
	{
		listener.addToCountdown();
		assertEquals(listener.getCountdown(), 303);
	}
}