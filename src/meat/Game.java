package meat;

import meat.gfx.GamePanel;
import meat.gfx.Renderer;
import meat.state.Updatable;
import meat.state.menu.MainMenuState;
import meat.state.State;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
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

	private Frame frame; // frame that holds game buffer
	private Panel panel; // panel that holds game buffer

	private Renderer renderer; // logic that manages updates to the game buffer
	private UserInput input;   // logic that manages user input and passes it to the current state
	private State currState;   // currently-executed state

	// what to update every tick
	private final List<Updatable> updatables;

	public Game()
	{
		updatables = new ArrayList();

		renderer = new Renderer(BUFFER_WIDTH, BUFFER_HEIGHT);
		input = new UserInput(this);

		panel = new GamePanel(renderer);
		frame = new Frame();

		panel.addMouseMotionListener(input);
		panel.addMouseListener(input);
		panel.addKeyListener(input); // NOTE: need to first click in window to get Panel's focus... not very cool!

		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new GameWindowListener());
		frame.setSize(1280, 720); // NOTE: this includes the title bar... not cool! remove in future.
		frame.add(panel);
		frame.setVisible(true);

		currState = new MainMenuState(this);

		renderer.addDrawable(currState);
	}

	// how many updates/ticks per second to run the game at
	private final int
		TARGET_TPS = 60;

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
				for (Updatable u : updatables)
					u.update();
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

	public void addUpdatable(Updatable updatable)
	{
		updatables.add(updatable);
	}

	public void removeUpdatable(Updatable updatable)
	{
		updatables.remove(updatable);
	}
}
