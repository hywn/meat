package meat.gfx;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/// draws stuff to screen
public class Renderer
{
	// pieces of code to run each time the buffer is redrawn
	private List<Drawable> drawables;

	// tools to draw to the buffer
	private DrawableTools tools;

	public Renderer(int baseWidth, int baseHeight)
	{
		BufferedImage buffer = new BufferedImage(baseWidth, baseHeight, BufferedImage.TYPE_INT_ARGB);
		drawables = new ArrayList();
		tools = new DrawableTools(buffer);

		// clear the buffer with a white background each time it is redrawn
		addDrawable(tools -> {
			Graphics2D g2d = tools.getGraphics();

			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, tools.getBufferWidth(), tools.getBufferHeight());
		});
	}

	/**
	 * redraw the buffer and draw it to the provided Graphics2D object
	 * the buffer is drawn at (x, y)
	 * the buffer is resized to the provided width and height.
	 *
	 * @param g2d    any Graphics2D object
	 * @param x      x-coord to draw buffer at
	 * @param y      y-coord to draw buffer at
	 * @param width  width to resize buffer to
	 * @param height height to resize buffer to
	 */
	public void draw(Graphics2D g2d, int x, int y, int width, int height)
	{
		// draw buffer
		for (Drawable d : drawables)
			d.draw(tools);

		// display buffer (stretches to given width, height)

		BufferedImage buffer = tools.getBuffer();

		double scaleX = (double) width / buffer.getWidth();
		double scaleY = (double) height / buffer.getHeight();

		AffineTransform transform = new AffineTransform();
		transform.scale(scaleX, scaleY);

		g2d.drawImage(
			buffer,
			new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR),
			0,
			0
		);
	}

	/**
	 * add a buffer-editing piece of code to be executed before the buffer is
	 * drawn to the screen
	 *
	 * @param drawable the code to be executed
	 */
	public void addDrawable(Drawable drawable)
	{
		drawables.add(drawable);
	}

	public void removeDrawable(Drawable drawable)
	{
		drawables.remove(drawable);
	}

	public DrawableTools getTools()
	{
		return tools;
	}
}
