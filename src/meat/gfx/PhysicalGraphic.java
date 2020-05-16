package meat.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

/// a buffer that 'takes up space'
/// based on its transparent pixels
/// TODO: intersects(PhysicalGraphic otherGraphic)
public class PhysicalGraphic implements Drawable
{
	// the X and Y coordinates of this PhysicalGraphic in terms of some larger buffer of existence
	protected int x, y;

	DrawableTools myTools;

	public PhysicalGraphic(BufferedImage myBuffer, int x, int y)
	{
		this.x = x;
		this.y = y;

		myTools = new DrawableTools(myBuffer);
	}

	@Override
	public void draw(DrawableTools tools)
	{
		tools.drawImage(myTools.getBuffer(), x, y);
	}

	/**
	 * does this PhysicalGraphic take up space in the larger buffer of existence
	 * at the given X, Y coords?
	 *
	 * @param bX buffer X coord
	 * @param bY buffer Y coord
	 * @return if it takes up space at the given coords
	 */
	public boolean intersects(int bX, int bY)
	{
		int myX = bX - x;
		int myY = bY - y;

		if (0 > myX || myX >= myTools.getBufferWidth() || 0 > myY || myY >= myTools.getBufferHeight())
			return false;

		Color myColor = new Color(myTools.getBuffer().getRGB(myX, myY), true);

		return myColor.getAlpha() != 0;
	}

	public DrawableTools getTools()
	{
		return myTools;
	}
}
