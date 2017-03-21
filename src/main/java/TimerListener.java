import java.awt.event.*;
import javax.swing.JLabel;

public class TimerListener implements ActionListener
{
    private Player _player;
    private JLabel _label;

    public TimerListener(Player player, JLabel label)
    {
        _player = player;
        _label = label;
    }

    public void actionPerformed(ActionEvent e)
    {
        _player.decrimentTimer();
        String text = Integer.toString(_player.getTimer());
        _label.setText(text);
    }
}
