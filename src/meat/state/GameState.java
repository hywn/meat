package meat.state;

import meat.Game;
import meat.gfx.DrawableTools;
import meat.state.menu.MainMenuState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameState extends State
{
	public GameState(Game game)
	{
		super(game);
	}

	private double t = 0;

	@Override
	public void draw(DrawableTools tools)
	{
		t += 0.1;

		double x = 20 + Math.sin(t) * 10;
		double y = 20 + Math.cos(t) * 10;

		var g2d = tools.getGraphics();

		g2d.setColor(Color.BLACK);

		g2d.fillOval((int) x, (int) y, 10, 10);
	}

	@Override
	public void update()
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
			Game g = getGame();
			MainMenuState m = new MainMenuState(g);

			g.setCurrState(m);
			g.getRenderer().addDrawable(m);
			g.getRenderer().removeDrawable(this);
		}
	}
}
