import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EndButtonListener implements ActionListener{

    private int countdown = 300;
	
	private JLabel label;
	private Timer timer, other;


    public EndButtonListener(Timer timer, Timer other){
		super();
		this.timer = timer;
		this.other = other;
    }

    public void actionPerformed(ActionEvent e) {
		System.out.println("I am here");
		timer.stop();
		other.start();
		switchTimers();
    }
	
	private void switchTimers()
	{
		Timer temp = timer;
		timer = other;
		other = temp;
	}
	
}