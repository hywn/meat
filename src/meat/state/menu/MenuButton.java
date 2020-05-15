package meat.state.menu;

import meat.gfx.DrawableFont;
import meat.gfx.PhysicalGraphic;
import meat.gfx.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton extends PhysicalGraphic
{
	private final Color
		COLOR_HOVERED = new Color(80, 240, 80),
		COLOR_UNHOVERED = new Color(60, 150, 60);

	/**
	 * generate a suitable buffer that will accommodate
	 * all MenuButton actions around the given textBuffer
	 *
	 * @param textBuffer a buffer containing text
	 * @return the created buffer
	 */
	private static BufferedImage getBlankBuffer(BufferedImage textBuffer)
	{
		return new BufferedImage(textBuffer.getWidth() + 4, textBuffer.getHeight() + 4, BufferedImage.TYPE_INT_ARGB);
	}

	private boolean isHovered = false;
	private final BufferedImage TEXT_BUFFER;

	public void updateHover(int bX, int bY)
	{
		boolean isHovered = intersects(bX, bY);

		if (this.isHovered == isHovered)
			return;

		this.isHovered = isHovered;

		if (isHovered)
			hover();
		else
			unhover();
	}

	private void hover()
	{
		var t = getTools();
		var g = t.getGraphics();

		g.setColor(COLOR_HOVERED);
		g.fillRect(0, 0, t.getBufferWidth(), t.getBufferHeight());
		t.drawImage(TEXT_BUFFER, 2, 2);
	}

	private void unhover()
	{
		var t = getTools();
		var g = t.getGraphics();

		g.setBackground(Util.COLOR_CLEAR);
		g.clearRect(0, 0, t.getBufferWidth(), t.getBufferHeight());

		g.setColor(COLOR_UNHOVERED);
		g.fillRect(1, 1, t.getBufferWidth() - 2, t.getBufferHeight() - 2);
		t.drawImage(TEXT_BUFFER, 2, 2);
	}

	public MenuButton(String text, int x, int y)
	{
		super(getBlankBuffer(DrawableFont.CAPS_57.renderString(text)), x, y);

		TEXT_BUFFER = DrawableFont.CAPS_57.renderString(text);

		unhover();
	}

	public boolean isHovered()
	{
		return isHovered;
	}
}
