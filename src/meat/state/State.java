package meat.state;

import meat.Game;
import meat.gfx.Drawable;

import java.awt.event.*;

/// represents a state of execution that takes some user input
/// and draws something to the buffer accordingly
public abstract class State implements Drawable, MouseMotionListener, MouseListener, KeyListener
{
	private Game game;

	public State(Game game)
	{
		this.game = game;
	}

	public Game getGame()
	{
		return game;
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mouseDragged(MouseEvent e) { }

	@Override
	public void mouseMoved(MouseEvent e) { }
}
