import gfx.Drawable;
import gfx.DrawableTools;

import java.awt.event.*;

public abstract class State implements Drawable, MouseMotionListener, MouseListener, KeyListener
{
	private Game game;

	public State(Game game)
	{
		this.game = game;
	}

	protected Game getGame()
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
