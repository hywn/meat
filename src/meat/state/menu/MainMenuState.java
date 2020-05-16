package meat.state.menu;

import meat.Game;
import meat.UserInput;
import meat.entity.TestBallEntity;
import meat.gfx.DrawableTools;
import meat.state.GameState;
import meat.state.State;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainMenuState extends State
{
	private final UserInput input;

	MenuButton startBut;

	public MainMenuState(Game game)
	{
		super(game);

		input = game.getInput();

		startBut = new MenuButton("START GAME", 10, 10);

		test = new TestBallEntity(this, 50, 50);

		getGame().addUpdatable(test);
	}

	TestBallEntity test;

	@Override
	public void draw(DrawableTools tools)
	{
		// draw bouncy ball
		test.draw(tools);

		// update and draw menu button
		startBut.updateHover((int) input.getBX(), (int) input.getBY());
		startBut.draw(tools);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// if user clicked start button, start new GameState
		// and remove/clean up this MainMenuState
		if (startBut.isHovered()) {
			Game g = getGame();

			g.removeUpdatable(test);
			g.getRenderer().removeDrawable(this);

			GameState gs = new GameState(g);

			g.setCurrState(gs);
			g.getRenderer().addDrawable(gs);
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// if user presses ESC key, restart state (idk why i added this)
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Game g = getGame();

			g.removeUpdatable(test);
			g.getRenderer().removeDrawable(this);

			var menu = new MainMenuState(g);

			g.getRenderer().addDrawable(menu);
			g.setCurrState(menu);
		}
	}
}
