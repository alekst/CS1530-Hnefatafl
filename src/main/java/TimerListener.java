import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.*;

public class TimerListener implements ActionListener
{
    private int countdown = 300;
	private JLabel label;

    public TimerListener(JLabel label)
    {
        this.label = label;
        
    }

    public void actionPerformed(ActionEvent e)
    {
		countdown--;
		if (countdown == 0)
		{
			JOptionPane.showMessageDialog(null, "You've run out of time. You lost!");
		}
		int minutes = countdown / 60;
		int seconds = countdown % 60;
        label.setText(String.format("%d:%02d", minutes, seconds));
    }
}
