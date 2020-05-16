package meat;

import meat.gfx.GamePanel;

import java.awt.*;

public class Main
{
	/**
	 * runs program. takes no command-line args
	 *
	 * @param args command-line args
	 */
	public static void main(String[] args)
	{
		Game game = new Game();

		new Thread(game).start();

		Panel panel = game.getPanel();

		/* render thread */
		// we don't really care about managing the game's FPS
		// since the game's TPS is already managed
		new Thread(() -> {
			while (true) {
				panel.repaint();
				try {
					Thread.sleep(5); // render very fast
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
