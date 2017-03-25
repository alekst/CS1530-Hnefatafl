import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.*;
/* This listener is responsible for listening to the Timer object and decrementing the countdown
* every second. */ 

public class TimerListener implements ActionListener
{
    private int countdown = 300;
	private JLabel label;
	private int moveTime = 0;

	/* 
	* A basic constructor that takes a JLabel as an argument
	*/   
    public TimerListener(JLabel label)
    {
        this.label = label; 
    }
	
	/* 
	* This action is performed every time the Timer objects sends an Event (every second)
	*/
    public void actionPerformed(ActionEvent e)
    {
		// increment the time for this move
		moveTime++;
		// decrement the countdown
		countdown--;
		// if the countdown is zero... 
		if (countdown == 0)
		{
			// then the player has run out of time and lost. 
			JOptionPane.showMessageDialog(null, "You've run out of time. You lost!");
			// need to disable the board
		}
		// if not
		// convert the countdown to minutes and seconds. 
		int minutes = countdown / 60;
		int seconds = countdown % 60;
		// and update the label
        label.setText(String.format("%d:%02d", minutes, seconds));
    }
	
	/*
	* This method takes no arguments, but returns the number of seconds it took the player to end his turn
	*/
	public int getMoveTime()
	{
		System.out.println("This is moveTime " + moveTime);
		return moveTime;
	}
	
	/*
	* This method resets the time it took to make the move
	*/
	public void resetMoveTime()
	{
		moveTime = 0;
	}
	
	/* 
	* This method adds three seconds to the countdown
	*/ 
	public void addToCountdown()
	{
		countdown = countdown + 3;
	} 
}
