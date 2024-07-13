/**
 *an effects class that draws our puddle effect used with glue towers.
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class FXpuddle extends Effect implements Animatable 
{
	// fields
	private double age;
	

	public FXpuddle(GameState state, int x, int y)
	{
		super(state, x, y);
		
	}

	@Override
	public void update(double elapsedTime) 
	{
		// makes sure our puddle only exists for 3 seconds.
		
		age += elapsedTime;
		
		if(age > 3.0)
		{
			state.removeAnimatable(this);
			return;
		}
		
		// updates our coordinates
		
		Point p = new Point(x, y);
		Enemy e = state.findNearEnemy(x, y); // find nearest enemy to this object
		
		// determines if we have hit an enemy
		
		if(e.getLocation().distance(p) < 40)
		{
			state.removeAnimatable(e); // removes our enemy when hit
			state.addAnimatable(new FXsmoke(state, e.getLocation().x, e.getLocation().y));// adds smoke death animation
			state.addCreditsKills(); // adds credits to user.
			state.increaseKillCount(); //ups kill count
		}
		
	}

	/**draws our puddle picture
	 * 
	 */
	public void draw(Graphics g, GameView view) 
	{
		Point p = new Point(x, y);
		view.drawCenteredImage(g, "resources\\glueSplat.png", p);
	}

}
