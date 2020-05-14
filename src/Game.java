import gfx.GamePanel;
import gfx.Renderer;

import java.awt.*;

/// the game in the highest sense
/// hooks all the pieces together to make them run
/// (handles game loop)
/// (manages the game window)
public class Game implements Runnable
{
	private Frame frame;
	private Panel panel;

	public Game()
	{
		Renderer renderer = new Renderer(128, 64);
		UserInput input = new UserInput();

		panel = new GamePanel(renderer);
		frame = new Frame();

		panel.addMouseMotionListener(input);

		renderer.addDrawable(tools -> {

			Graphics2D g2d = tools.getGraphics();

			int mX = (int) ((double) input.getMX() / panel.getWidth() * tools.getBufferWidth());
			int mY = (int) ((double) input.getMY() / panel.getHeight() * tools.getBufferHeight());

			var dull = Color.RED;
			var bright = new Color(255, 0, 255);

			if (10 <= mX && mX < 20 && 10 <= mY && mY < 15) {
				g2d.setColor(bright);
				g2d.fillRect(9, 9, 12, 7);
			} else {
				g2d.setColor(dull);
				g2d.fillRect(10, 10, 10, 5);
			}

		});

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
}
