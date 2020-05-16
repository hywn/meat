package meat.entity;

import meat.gfx.BufferPoint;
import meat.gfx.Util;
import meat.state.State;

public class TestBallEntity extends Entity
{
	public TestBallEntity(State state, int x, int y)
	{
		super(Util.loadImage("/testball.png"), state, t -> {
			return new BufferPoint(x, y);
		});
	}

	double velX, velY;

	@Override
	public void update()
	{
		super.update();

		var input = getState().getGame().getInput();
		double targetX = input.getBX();
		double targetY = input.getBY();

		velX += (targetX - this.x - getTools().getBufferWidth()) / 2;
		velY += (targetY - this.y - getTools().getBufferHeight()) / 2 + 15;

		velX -= (Math.abs(velX) / velX) * 4;
		velY -= (Math.abs(velY) / velY) * 3;

		long now = getTimeAlive();

		movement = t -> {
			double sinceLast = (t - now) / 1.0E9;
			return new BufferPoint((int) (velX * sinceLast) + this.x, (int) (velY * sinceLast) + this.y);
		};
	}
}
