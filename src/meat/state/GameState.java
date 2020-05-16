package meat.state;

import meat.Game;
import meat.entity.TestBallPlayer;
import meat.gfx.DrawableTools;
import meat.state.menu.MainMenuState;

import java.awt.event.KeyEvent;

public class GameState extends State implements Updatable
{
	TestBallPlayer test;

	public GameState(Game game)
	{
		super(game);

		test = new TestBallPlayer(this, 50, 50);
		game.addUpdatable(this);
	}

	@Override
	public void draw(DrawableTools tools)
	{
		test.draw(tools);
	}

	@Override
	public void update()
	{
		test.update();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
			Game g = getGame();
			MainMenuState m = new MainMenuState(g);

			g.setCurrState(m);
			g.getRenderer().addDrawable(m);

			g.removeUpdatable(this);
			g.getRenderer().removeDrawable(this);
		}
	}
}
