package gfx;

import java.awt.*;

/// a special Panel that only draws a given gfx.Renderer's buffer
public class GamePanel extends Panel
{
	Renderer renderer;

	public GamePanel(Renderer renderer)
	{
		this.renderer = renderer;
	}

	@Override
	public void update(Graphics g)
	{
		paint(g); // non-overridden update() calls some fancy stuff that we don't want
	}

	@Override
	public void paint(Graphics g)
	{
		renderer.draw((Graphics2D) g, 0, 0, this.getWidth(), this.getHeight());
	}
}
