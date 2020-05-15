package meat;

import meat.gfx.GamePanel;
import meat.gfx.Renderer;
import meat.state.menu.MainMenuState;
import meat.state.State;

import java.awt.*;

/// the game in the highest sense
/// hooks all the pieces together to make them run
/// (handles game loop)
/// (manages the game window)
public class Game implements Runnable
{
	public static final int // questionable coding practice
		BUFFER_WIDTH = 160,
		BUFFER_HEIGHT = 90;

	private Frame frame;
	private Panel panel;

	private Renderer renderer;
	private UserInput input;
	private State currState;

	public Game()
	{
		renderer = new Renderer(BUFFER_WIDTH, BUFFER_HEIGHT);
		input = new UserInput(this);
		currState = new MainMenuState(this);

		panel = new GamePanel(renderer);
		frame = new Frame();

		panel.addMouseMotionListener(input);
		panel.addMouseListener(input);
		panel.addKeyListener(input); // NOTE: need to first click in window to get Panel's focus... not very cool!

		renderer.addDrawable(currState);

		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new GameWindowListener());
		frame.setSize(1280, 720); // NOTE: this includes the title bar... not cool! remove in future.
		frame.add(panel);
		frame.setVisible(true);
	}

	private final int
		TARGET_FPS = 120,
		TARGET_TPS = 120;

	/**
	 * the game loop that controls everything
	 * attempts to run current state at a constant ticks-per-second rate (given above)
	 */
	@Override
	public void run()
	{
		double NS_PER_TICK = 1000000000 / TARGET_TPS;
		long MS_PER_TICK = 1000 / TARGET_TPS;

		double missed_ticks = 0;
		long lastTime = System.nanoTime();

		while (true) {

			long now = System.nanoTime();

			missed_ticks += (now - lastTime) / NS_PER_TICK;

			while (missed_ticks >= 1) {
				currState.update();
				missed_ticks -= 1;
			}

			lastTime = now;

			try {
				Thread.sleep(MS_PER_TICK);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void setCurrState(State currState)
	{
		this.currState = currState;
	}

	public Panel getPanel()
	{
		return panel;
	}

	public Renderer getRenderer()
	{
		return renderer;
	}

	public State getCurrState()
	{
		return currState;
	}

	public UserInput getInput()
	{
		return input;
	}
}
