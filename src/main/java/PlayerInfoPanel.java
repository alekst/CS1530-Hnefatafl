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
	private JLabel textLabel, piecesLabel;
	private TimerListener listener;
	private Timer timer;
	private int time, minutes, seconds;
	private int pieces;
	
	/**
	 * An empty constructor used for testing purposes only. 
	 */

	public PlayerInfoPanel()
	{
		// an empty constructor
	}
	
	/**
	 * A constructor for the info panel. 
	 * @param name - a Player's handle. 
	 * @param t - the initial time on the clock in seconds
	 * @param pieces - number of pieces for the player
	 *
	 */
	public PlayerInfoPanel(String name, int t, int pieces)
	{	
		time = t;
		this.pieces = pieces;
		minutes  = convertMinutes();
		seconds = convertSeconds();
		timerLabel = new JLabel(String.format("%d:%02d", minutes, seconds));
		timerLabel.setFont(new Font("Serif", Font.PLAIN, 90));
		add(timerLabel);
		listener = new TimerListener(timerLabel, time);
		timer = new Timer(1000, listener); // creates actionEvents every second on a separate thread. 
		nameLabel = new JLabel(name);
		nameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		
		textLabel = new JLabel("Pieces: ");
		textLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		
		piecesLabel = new JLabel(Integer.toString(pieces));
		piecesLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		
		setPreferredSize(new Dimension(100, 150));
		setLayout(new FlowLayout());
		setOpaque(true);
		add(nameLabel);
		add(timerLabel);
		add(textLabel);
		add(piecesLabel);
	}
	
	/* 
	* This method starts a timer on the PlayerInfoPanel 
	*/
	public void startTimer()
	{
		timer.start();
	}
	
	/*
	 * This method returns true if the countdown is equal or below zero. Otherwise, true. 
	 */
	public boolean timerDone()
	{
		System.out.println(listener.enabled);
		return !listener.enabled;
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
		if (listener.getCountdown() == 0) // if the time has run out
		{
			//JOptionPane.showMessageDialog(null, "You've run out of time. You lost!");
		}
		else
		{
			listener.addToCountdown(); // adds three seconds for the running display in TimerListener
			time = time + 3; // plus three seconds to the time
			minutes = convertMinutes();
			seconds = convertSeconds();
			timerLabel.setText(String.format("%d:%02d", minutes, seconds)); // update the label
		}
		
	}
	/**
	 * Converts time in seconds into complete minutes
	 */ 
	
	/*
	* This method takes the number of pieces as an argument and sets it to the info panel
	*/
	public void setNumPieces(int pieces)
	{
		this.pieces = pieces;
	}
	/*
	* This method is useful for testing to see if the number of pieces was set correctly
	*/
	
	public int getNumPiece()
	{
		return pieces;
	}
	
	/*
	 * This method decrements the number of pieces the player has left. It can only go down. 
	 * 
	 */
	public void takeAPiece()
	{
		pieces--;
	}
	
	/*
	 * This method updates the label with the number of pieces.
	 * 
	 */
	public void updatePieces()
	{
		piecesLabel.setText(Integer.toString(pieces));
	}
	
	/**
	 * A helper method to convert the time in seconds to show just whole minutes. 
	 *
	 */
	private int convertMinutes()
	{
		return time / 60;
	}
	
	/**

	 * A helper method to convert the time in seconds to show just remainder of seconds to the complete minute. 
	 *
	 */
	private int convertSeconds()
	{
		return time % 60;
	}
	
	/**
	 * Accessor method to get the player's current time
	 *
	 */
	public int getTime()
	{
		int saveTime = listener.getCountdown();
		return saveTime;
	}
			
	
}