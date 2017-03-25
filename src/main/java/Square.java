import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/*
* This class encapsulates the board square object. It contains the Coordinate object for that square. 
*/
public class Square extends JButton 
{
	
	private Coordinate coord;
	
	public Square(Coordinate coord)
	{
		this.coord = coord;
		setPreferredSize(new Dimension(50, 50));
		// the square needs to be opaque to use the setBackground method. 
		//https://docs.oracle.com/javase/7/docs/api/javax/swing/JComponent.html#setBackground(java.awt.Color)
		setOpaque(true); 
		setBorderPainted(true);
		setFont(new Font("UNI-8", Font.PLAIN, 36));
	}
		
	public Coordinate getCoord()
	{
		return coord;
	}
		

}

	