package meat;

import meat.gfx.GamePanel;
import meat.gfx.Renderer;
import meat.state.MainMenuState;
import meat.state.State;

import java.awt.*;

/// the game in the highest sense
/// hooks all the pieces together to make them run
/// (handles game loop)
/// (manages the game window)
public class Game implements Runnable
{
	public static final int // questionable coding practice
		BUFFER_WIDTH = 128,
		BUFFER_HEIGHT = 64;

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
		frame.setSize(800, 500);
		frame.add(panel);
		frame.setVisible(true);
	}

	/**
	 * the game loop that controls everything
	 */
	@Override
	public void run()
	{
		while (true) {

			panel.repaint();

			try {
				Thread.sleep(17);
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
