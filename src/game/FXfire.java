/**
 *an effects class that draws our fire effect used with pencil towers.
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class FXfire extends Effect implements Animatable 
{
	// fields
	private int deltaX;
	private int deltaY;
	private double age;
	

	public FXfire(GameState state, int x, int y, int deltaX, int deltaY)
	{
		super(state, x, y);
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		
	}

	@Override
	public void update(double elapsedTime) 
	{
		// makes sure our fire only exists for 1 second.
		
		age += elapsedTime;
		
		if(age > 1.0)
		{
			state.removeAnimatable(this);
			return;
		}
		
		// updates our coordinates
		
		x += deltaX * 3 * elapsedTime;
		y += deltaY * 3 * elapsedTime;
		
		Point p = new Point(x, y);
		Enemy e = state.findNearEnemy(x, y); // find nearest enemy to this object
		
		// determines if we have hit an enemy
		
		if(e.getLocation().distance(p) < 30)
		{
			state.removeAnimatable(this); // removes this object
			state.removeAnimatable(e); // removes our enemy when hit
			state.addAnimatable(new FXsmoke(state, e.getLocation().x, e.getLocation().y)); // adds smoke death animation
			state.addCreditsKills(); // adds credits to user.
			state.increaseKillCount(); //ups kill count
		}
		
	}

	/**draws our fire picture
	 * 
	 */
	public void draw(Graphics g, GameView view) 
	{
		Point p = new Point(x, y);
		view.drawCenteredImage(g, "resources\\fire.png", p);
	}

}
