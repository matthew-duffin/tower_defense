/**
 * Tower Defense -- An enemy super class
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Point;
import java.util.TimerTask;

public abstract class Enemy implements Animatable
{
	// Fields

	protected int x;
	protected int y;
	protected GameState state;
	protected double pTraveled;
	protected Point location;

	/**
	 * our constructor
	 * 
	 * @param x
	 * @param y
	 * @param state
	 */
	public Enemy(GameState state, double pTraveled) 
	{
		// these values will always start the same for every enemy
		
		x = 0;
		y = 0;
		this.pTraveled = pTraveled;
		
		this.state = state; // saves a reference to the gameState object
	}
	/**gets the current location of an enemy
	 * 
	 * @return - a point location where the enemy was.
	 */
	public Point getLocation()
	{
		location = state.getPath().locatePosition(pTraveled);
		return location;
	}
}
