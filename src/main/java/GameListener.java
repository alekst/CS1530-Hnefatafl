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
    public static void setActive(boolean active){
        GameListener.active = active;
    }

    protected abstract void doPerformAction(ActionEvent e);

    @Override
    public final void actionPerformed(ActionEvent e){
        if(active){
            doPerformAction(e);
        }
    }
}

