/**
 * Tower Defense -- creates our pencil tower objects
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class Pencil extends Towers
{

	private double timeSinceLastShot;
	/**our constructor
	 * 
	 * @param x
	 * @param y
	 * @param state
	 */
	public Pencil(int x, int y, GameState state) 
	{
		super(x, y, state);
		timeSinceLastShot = 0;

	}

	@Override
	public void update(double elapsedTime) {
		// keeps track of how much time has gone by
		
		timeSinceLastShot += elapsedTime; //accumulate more time
		
		if(timeSinceLastShot < 1.5) // bail out if too soon
			return;
		// fire the fire duh! Find an enemy within range
		
		Enemy e = state.findNearEnemy(x, y);
		Point p = new Point(x, y);
		
		// calculate direction of enemy from tower
		
		if(e.getLocation().distance(p) < 75)
		{
			int deltaX = e.getLocation().x - x;
			int deltaY = e.getLocation().y - y;
			
			state.addAnimatable(new FXfire(state, x, y, deltaX, deltaY));	// make fire object and add it
			timeSinceLastShot = 0; // resets time after each shot
		}
	}

	/** will draw our tower
	 * 
	 */
	public void draw(Graphics g, GameView view) 
	{	
		Point p = new Point( x, y); // creates the point our tower will be centered at.
		view.drawCenteredImage(g, "resources\\pencil.png", p);
		
	}
	
}
