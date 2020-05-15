import gfx.Drawable;
import gfx.DrawableTools;
import gfx.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PhysicalGraphic implements Drawable
{
	private int x, y;

	DrawableTools bufferTools;
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
		tools.drawImage(tools.getBuffer(), x, y);
	}

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
