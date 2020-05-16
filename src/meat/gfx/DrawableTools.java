package meat.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

/// these are the tools given to the programmer
/// that wishes to draw stuff to the screen
/// (draw to a buffer, which is drawn to the screen)
/// provides commonly-used functions that modify the buffer
public class DrawableTools
{
	// the buffer that is drawn to the screen after the programmer messes with it
	private BufferedImage buffer;

	public DrawableTools(BufferedImage buffer)
	{
		this.buffer = buffer;
	}

	/**
	 * renders given string with given font and puts it on buffer
	 * with given x, y coords.
	 *
	 * @param string the string to render
	 * @param x      x coord
	 * @param y      y coord
	 * @param font   the font to use to render the string
	 * @return the rendered string that was put on the buffer
	 */
	public BufferedImage drawString(String string, int x, int y, DrawableFont font)
	{
		BufferedImage rendered = font.renderString(string);

		getGraphics().drawImage(rendered, Util.IDENTITY_TRANSFORM, x, y);

		return rendered;
	}

	/**
	 * draws given image onto buffer without any transformation
	 *
	 * @param image image to draw
	 * @param x     x-coord of where to draw onto buffer
	 * @param y     y-coord of where to draw onto buffer
	 */
	public void drawImage(BufferedImage image, int x, int y)
	{
		getGraphics().drawImage(image, Util.IDENTITY_TRANSFORM, x, y);
	}

	public Graphics2D getGraphics()
	{
		return (Graphics2D) buffer.getGraphics();
	}

	public BufferedImage getBuffer()
	{
		return buffer;
	}

	public int getBufferWidth()
	{
		return buffer.getWidth();
	}

	public int getBufferHeight()
	{
		return buffer.getHeight();
	}
}
