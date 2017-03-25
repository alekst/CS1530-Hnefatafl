import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.*;
/* This listener is responsible for listening to the Timer object and decrementing the countdown
* every second. */ 

public class TimerListener implements ActionListener
{
    private int countdown = 300;
	private JLabel label;

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
		// if the countdown is zero... 
		if (countdown == 0)
		{
			// then the player has run out of time and lost. 
			JOptionPane.showMessageDialog(null, "You've run out of time. You lost!");
		}
		// if not, convert the countdown to minutes and seconds. 
		int minutes = countdown / 60;
		int seconds = countdown % 60;
		// and update the label
        label.setText(String.format("%d:%02d", minutes, seconds));
    }
}
