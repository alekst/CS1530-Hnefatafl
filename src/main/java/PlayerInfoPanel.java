// this class encapsulates the player information panel for the game info panel.
import java.awt.*;
import javax.swing.*;

public class PlayerInfoPanel extends JPanel
{

    private JLabel _name;
    private JLabel _timer;
    private Player _player;
    private TimerListener _listener;

    public PlayerInfoPanel(Player player)
    {
        _player = player;
        int time = _player.getTimer();
        _timer = new JLabel(Integer.toString(time));
        _name = new JLabel(_player.getName());
        _listener = new TimerListener(_player,_timer);
        setPreferredSize(new Dimension(100, 250));
        setLayout(new FlowLayout());
        add(_name);
        add(_timer);
        Timer t = new Timer(1000, _listener);

        if(_player.myTurn())
            {
                //_listener = new TimerListener(_player, _timer);
                //Timer t = new Timer(1000, _listener);
                t.start();
            }
        else
            {
                t.stop();
            }

        //Timer t = new Timer(1000, _listener);
        //t.start();

    }
}
