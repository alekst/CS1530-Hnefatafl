import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
 
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 

public abstract class GameListener implements ActionListener
{

    private static boolean active = true;
	
	/**
	 * @param boolean true or false. 
	 * Sets the GameListener active or passive. 
	 */
    public static void setActive(boolean active){
        GameListener.active = active;
    }
	
	/**
	 * This method is a getter method for active variable and is used for testing only. 
	 */
	public static boolean getActive()
	{
		return active;
	}

    protected abstract void doPerformAction(ActionEvent e);

	/**
	 * @param ActionEvent object. 
	 * This method performs action only when it is set to active. 
	 */
    @Override
    public final void actionPerformed(ActionEvent e){
        if(active){
            doPerformAction(e);
        }
    }
}

