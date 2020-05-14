package gfx;

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
