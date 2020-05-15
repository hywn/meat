package meat.state;

import meat.Game;
import meat.gfx.PhysicalGraphic;
import meat.UserInput;
import meat.gfx.DrawableFont;
import meat.gfx.DrawableTools;
import meat.gfx.Util;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MainMenuState extends State
{
	private final UserInput input;
	PhysicalGraphic button;

	public MainMenuState(Game game)
	{
		super(game);

		input = game.getInput();

		var butText = DrawableFont.CAPS_57.renderString("START GAME");
		var butGraphics = new BufferedImage(butText.getWidth() + 4, butText.getHeight() + 4, BufferedImage.TYPE_INT_ARGB);

		butGraphics.setRGB(0, 0, Color.BLACK.getRGB());

		button = new PhysicalGraphic(butGraphics, 10, 10);

	}

	@Override
	public void draw(DrawableTools tools)
	{
		var bt = button.getTools();
		var bg = bt.getGraphics();

		var butText = DrawableFont.CAPS_57.renderString("START GAME");
		//var butGraphics = new BufferedImage(butText.getWidth() + 4, butText.getHeight() + 4, BufferedImage.TYPE_INT_ARGB);

		tools.drawImage(butText, 0, 0);

		if (button.intersects((int) input.getBX(), (int) input.getBY())) {
			bg.setColor(Color.YELLOW);
			bg.fillRect(0, 0, bt.getBufferWidth(), bt.getBufferHeight());
		} else {
			bg.setBackground(Util.COLOR_CLEAR);
			bg.clearRect(0, 0, bt.getBufferWidth(), bt.getBufferHeight());
			bg.setColor(Color.RED);
			bg.fillRect(1, 1, bt.getBufferWidth() - 2, bt.getBufferHeight() - 2);
		}

		button.draw(tools);

		int color = new Color(255, 0, 255).getRGB();
		tools.getBuffer().setRGB((int) input.getBX(), (int) input.getBY(), color);

		for (Point p : points)
			tools.getBuffer().setRGB((int) p.x, (int) p.y, Color.BLACK.getRGB());
	}

	class Point
	{
		int x, y;
	}

	List<Point> points = new ArrayList<Point>();

	@Override
	public void mouseMoved(MouseEvent e)
	{
		if (input.isDepressed(KeyEvent.VK_X)) {
			Point p = new Point();
			p.x = (int) input.getBX();
			p.y = (int) input.getBY();
			points.add(p);
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (input.isDepressed(KeyEvent.VK_X)) {
			Point p = new Point();
			p.x = (int) input.getBX();
			p.y = (int) input.getBY();
			points.add(p);
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Game g = getGame();
			g.getRenderer().removeDrawable(this);
			var menu = new MainMenuState(g);
			g.getRenderer().addDrawable(menu);
			g.setCurrState(menu);
		}
	}
}
