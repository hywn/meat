package meat.state.menu;

import meat.Game;
import meat.UserInput;
import meat.gfx.DrawableTools;
import meat.state.GameState;
import meat.state.State;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainMenuState extends State
{
	private final UserInput input;

	MenuButton startBut;

	public MainMenuState(Game game)
	{
		super(game);

		input = game.getInput();

		startBut = new MenuButton("START GAME", 10, 10);
	}

	@Override
	public void draw(DrawableTools tools)
	{
		startBut.updateHover((int) input.getBX(), (int) input.getBY());
		startBut.draw(tools);

		double x = 50 + Math.sin(time) * 25;
		double y = 50 + Math.cos(time) * 25;

		int color = new Color(255, 0, 255).getRGB();

		var g = tools.getGraphics();
		g.setColor(new Color(255, 0, 255));
		g.drawOval((int) x + 10 - 5, (int) y + 10 - 5, 10, 10);
		tools.getBuffer().setRGB((int) input.getBX(), (int) input.getBY(), color);

		double st = (System.nanoTime() / 1000000.0) * 0.005; // ms * 0.1

		double sx = 50 + Math.sin(st) * 25;
		double sy = 50 + Math.cos(st) * 25;

		int scolor = new Color(100, 255, 100).getRGB();
		tools.getBuffer().setRGB((int) sx + 10, (int) sy + 10, scolor);
	}

	double time = 0;

	@Override
	public void update()
	{
		time = (System.nanoTime() / 1000000.0) * 0.005;
	}

	class Point
	{
		int x, y;
	}

	List<Point> points = new ArrayList<Point>();

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (startBut.isHovered()) {
			Game g = getGame();
			GameState gs = new GameState(g);

			g.setCurrState(gs);
			g.getRenderer().addDrawable(gs);
			g.getRenderer().removeDrawable(this);
		}
	}

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
