import java.awt.event.*;
import javax.swing.JLabel;

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
        label.setText(countdown + "");
    }
}
