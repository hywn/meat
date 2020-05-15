package meat;

import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/// the all-knowing input listener that
/// keeps track of mouse position
/// keeps track of downed keys
/// gives input over to the given meat.Game
/// NOTE: should be placed on the main Panel, not Frame (to get game-relevant mouse data)
public class UserInput implements MouseListener, MouseMotionListener, KeyListener
{
	// mouse data
	private int mX, mY;    // the actual Panel x, y coords
	private double bX, bY; // the buffer x, y coords

	// downed keys
	Map<Integer, Boolean> keys;

	// the StateManager that this meat.UserInput gives its input to
	private Game game;

	public UserInput(Game game)
	{
		this.game = game;

		keys = new HashMap();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		game.getCurrState().mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		game.getCurrState().mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		game.getCurrState().mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		game.getCurrState().mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		game.getCurrState().mouseExited(e);
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		game.getCurrState().mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		mX = e.getX();
		mY = e.getY();
		game.getCurrState().mouseMoved(e);
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		game.getCurrState().keyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		keys.put(e.getKeyCode(), true);
		game.getCurrState().keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		keys.put(e.getKeyCode(), false);
		game.getCurrState().keyReleased(e);
	}

	public int getMX()
	{
		return mX;
	}

	public int getMY()
	{
		return mY;
	}

	public double getBX()
	{
		return (double) mX / game.getPanel().getWidth() * Game.BUFFER_WIDTH; // questionable coding practice
	}

	public double getBY()
	{
		return (double) mY / game.getPanel().getHeight() * Game.BUFFER_HEIGHT; // questionable coding practice
	}

	public boolean isDepressed(int keycode)
	{
		return keys.containsKey(keycode) && keys.get(keycode);
	}
}
