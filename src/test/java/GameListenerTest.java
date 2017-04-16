import java.lang.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class GameListenerTest
{
	//Tests if the listener is set active true
	public void testListenerActive()
	{
		GameListener listener = new GameListener()
			{
				public void doPerformAction(ActionEvent actionEvent)
				{
					// nothing here, since we are testing one variable. 
					// This is needed to satisfy the abstract class requirements.
				}	
			};
		assertTrue(listener.getActive());
	}
	
	//Tests if the listener is set active false
	public void testListenerActiveFalse()
	{
		GameListener listener = new GameListener()
		{
			public void doPerformAction(ActionEvent actionEvent)
			{
				// nothing here, since we are testing one variable. 
				// This is needed to satisfy the abstract class requirements.
			}	
		};
		listener.setActive(false);
		assertTrue(listener.getActive());
	}
	
}