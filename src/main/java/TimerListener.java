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
	public boolean enabled = true;

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
		// decrement the countdown
		countdown--;
		if(!enabled)
		{
			return;
		}
		// increment the time for this move
		moveTime++;
		// if the countdown is below zero... 
		if (countdown == -1)
		{
			enabled = false;
			JOptionPane.showMessageDialog(null, "You've run out of time. You lost!");
			// then the player has run out of time and lost. 	
			// need to disable the board
		}
		else
		{
			// if not
			// convert the countdown to minutes and seconds. 
			int minutes = countdown / 60;
			int seconds = countdown % 60;
			// and update the label
			label.setText(String.format("%d:%02d", minutes, seconds));
		}
		
		
    }

	/*
	* This method takes no arguments, but returns the number of seconds it took the player to end his turn
	*/
	public int getMoveTime()
	{
		return moveTime;
	}
	
	/*
	* This method resets the time it took to make the move
	*/
	public void resetMoveTime()
	{
		moveTime = 0;
	}
	
	/**
	*  Returns countdown value
	*/
	public int getCountdown()
	{
		return countdown;
	}
	
	/* 
	* This method adds three seconds to the countdown
	*/ 
	public void addToCountdown()
	{
		countdown = countdown + 3;
	} 
}
