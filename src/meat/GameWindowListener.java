package meat;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// TODO: uh
// TODO: delete and replace with in-game menu
public class GameWindowListener extends WindowAdapter
{
	@Override
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
}