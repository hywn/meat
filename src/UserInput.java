import java.awt.event.*;

/// the all-knowing X-Listener that
/// keeps track of mouse position
/// keeps track of downed keys
/// handing input over to the StateManager
/// NOTE: should be placed on the main Panel, not Frame (to get game-relevant mouse data)
public class UserInput implements MouseListener, MouseMotionListener, KeyListener
{
	// mouse data
	int mX, mY;

	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	@Override
	public void mousePressed(MouseEvent e)
	{

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	@Override
	public void mouseDragged(MouseEvent e)
	{

	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		mX = e.getX();
		mY = e.getY();
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{

	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}

	public int getMX()
	{
		return mX;
	}

	public int getMY()
	{
		return mY;
	}
}
