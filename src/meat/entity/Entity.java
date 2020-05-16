package meat.entity;

import meat.gfx.BufferPoint;
import meat.gfx.Drawable;
import meat.gfx.DrawableTools;
import meat.gfx.PhysicalGraphic;
import meat.state.State;
import meat.state.Updatable;

import java.awt.image.BufferedImage;
import java.util.function.Function;

/// a buffer that moves around on the screen
/// TODO: continuous collision
public abstract class Entity extends PhysicalGraphic implements Drawable, Updatable
{
	// the state this Entity belongs to
	private State state;

	// when this Entity was created, in system nanoseconds
	private long timeCreated;

	// a function representing the movement of this Entity
	// time alive (nanoseconds) -> point on buffer
	protected Function<Long, BufferPoint> movement;

	public Entity(BufferedImage myBuffer, State state, Function<Long, BufferPoint> movement)
	{
		super(myBuffer, movement.apply(0L).x, movement.apply(0L).y);

		this.state = state;
		this.movement = movement;

		timeCreated = System.nanoTime();
		update();
	}

	/**
	 * updates position of Entity based on its movement function
	 * (that maps time alive [in nanoseconds] to a point on the buffer [represented as BufferPoint])
	 */
	@Override
	public void update()
	{
		BufferPoint pos = movement.apply(getTimeAlive());

		this.x = pos.x;
		this.y = pos.y;
	}

	/**
	 * get how many nanoseconds this Entity has been alive for
	 *
	 * @return nanoseconds
	 */
	public long getTimeAlive()
	{
		return System.nanoTime() - timeCreated;
	}

	@Override
	public void draw(DrawableTools tools)
	{
		BufferPoint pos = movement.apply(getTimeAlive());

		tools.drawImage(getTools().getBuffer(), pos.x, pos.y);
	}

	public State getState()
	{
		return state;
	}
}