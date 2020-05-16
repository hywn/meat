package meat.entity;

import meat.UserInput;
import meat.gfx.BufferPoint;
import meat.gfx.Util;
import meat.state.State;

import java.awt.event.KeyEvent;

public class TestBallPlayer extends Entity
{
	public TestBallPlayer(State state, int x, int y)
	{
		super(Util.loadImage("/testball.png"), state, t -> new BufferPoint(x, y));
	}

	double posX, posY;
	double vX, vY;

	@Override
	public void update()
	{
		super.update();

		UserInput input = getState().getGame().getInput();

		// set constant velocity if user presses a movement key
		if (input.isDepressed(KeyEvent.VK_A))
			vX = -2.5;
		if (input.isDepressed(KeyEvent.VK_D))
			vX = 2.5;
		if (input.isDepressed(KeyEvent.VK_W))
			vY = -2.5;
		if (input.isDepressed(KeyEvent.VK_S))
			vY = 2.5;

		// stop if velocity is low enough
		if (Math.abs(vX) < 1)
			vX = 0;
		if (Math.abs(vY) < 1)
			vY = 0;

		// always try to slow down (push velocity towards 0)
		if (vX != 0)
			vX -= Math.abs(vX) / vX;
		if (vY != 0)
			vY -= Math.abs(vY) / vY;

		// apply velocities
		posX += vX;
		posY += vY;

		// communicate how the ball moves

		final long last = getTimeAlive();

		movement = t -> {
			double secsElapsed = (t - last) / 1.0E9;
			return new BufferPoint(posX + secsElapsed * vX, posY + secsElapsed * vY);
		};
	}
}
