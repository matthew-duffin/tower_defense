/**
 * Tower Defense -- creates our pen objects. This tower fire more quickly than pencils but costs 100 more
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class Pen extends Towers
{
	private double timeSinceLastShot;
	/**our constructor
	 * 
	 * @param x
	 * @param y
	 * @param state
	 */
	public Pen(int x, int y, GameState state) 
	{
		super(x, y, state);
		timeSinceLastShot = 0;

	}

	@Override
	public void update(double elapsedTime) 
	{
	// keeps track of how much time has gone by
		
		timeSinceLastShot += elapsedTime; //accumulate more time
		
		if(timeSinceLastShot < .5) // bail out if too soon
			return;
		// fire the fire duh! Find an enemy within range
		
		Enemy e = state.findNearEnemy(x, y);
		Point p = new Point(x, y);
		
		// calculate direction of enemy from tower
		
		if(e.getLocation().distance(p) < 75)
		{
			int deltaX = e.getLocation().x - x;
			int deltaY = e.getLocation().y - y;
			
			state.addAnimatable(new FXlightning(state, x, y, deltaX, deltaY));	// make fire object and add it
			timeSinceLastShot = 0; // resets time after each shot
		}
		
	}

	/** will draw our tower
	 * 
	 */
	public void draw(Graphics g, GameView view) 
	{	
		Point p = new Point( x, y); // creates the point our tower will be centered at.
		view.drawCenteredImage(g, "resources\\pen.png", p);
		
	}
	
}
