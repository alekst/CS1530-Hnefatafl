// this class encapsulates the player information panel for the game info panel. The information in the player info panel contains
// player's name, and the integer in seconds for timer's initial value. 
import java.awt.*;
import javax.swing.*;

/* The requirements state that the timer should be implemented as a separate thread, but it doesn't specify how
* this thread should be implemented. The Java API javax.swing.Timer object operates in a separate thread implicitly. 
* All Timer objects share the same thread. 
*
* From the API docs "Although all Timers perform their waiting using a single, shared thread (created by the first 
* Timer object that executes), the action event handlers for Timers execute on another thread -- the event-dispatching 
* thread. This means that the action handlers for Timers can safely perform operations on Swing components. However, 
* it also means that the handlers must execute quickly to keep the GUI responsive." (https://docs.oracle.com/javase/8/docs/api/javax/swing/Timer.html)
*
* To add to this point, more from Java tutorials, "In general, we recommend using Swing timers rather than general-purpose 
* timers for GUI-related tasks because  Swing timers all share the same, pre-existing timer thread and the GUI-related 
* task automatically executes on the event-dispatch thread. " (https://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html)
*/

public class PlayerInfoPanel extends JPanel
{
	
	private JLabel timerLabel;
	private JLabel nameLabel;
	private TimerListener listener;
	private Timer timer;
	private int time, minutes, seconds;
	
	public PlayerInfoPanel(String name, int t)
	{	
		time = t;
		minutes  = convertMinutes();
		seconds = convertSeconds();
		timerLabel = new JLabel(String.format("%d:%02d", minutes, seconds));
		add(timerLabel);
		listener = new TimerListener(timerLabel);
		timer = new Timer(1000, listener); // creates actionEvents every second on a separate thread. 
		
		nameLabel = new JLabel(name);
		setPreferredSize(new Dimension(100, 150));
		setLayout(new FlowLayout());
		add(nameLabel);
		add(timerLabel);
	}
	
	/* 
	* This method starts a timer on the PlayerInfoPanel 
	*/
	public void startTimer()
	{
		timer.start();
	}
	
	/* 
	* This method stops a timer on a PlayerInfoPanel 
	*/
	public void stopTimer()
	{
		time = time - listener.getMoveTime(); // new time value is the old time value minus however long it took to make a move
		listener.resetMoveTime(); // resets the time it took to make the move for the next turn
		timer.stop(); // stop the timer
	}
	
	/* 
	* This method adds three seconds to the clock at the end of the turn
	*/
	public void addTime()
	{
		listener.addToCountdown(); // adds three seconds for the running display in TimerListener
		time = time + 3; // plus three seconds to the time
		minutes = convertMinutes();
		seconds = convertSeconds();
		timerLabel.setText(String.format("%d:%02d", minutes, seconds)); // update the label
	}
	
	private int convertMinutes()
	{
		return time / 60;
	}
	
	private int convertSeconds()
	{
		return time % 60;
	}
	
			
	
}