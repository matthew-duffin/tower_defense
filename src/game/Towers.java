/**
 * Tower Defense -- our tower super class
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

public abstract class Towers implements Animatable
{
	// Fields
	 
	protected int x, y;
	protected GameState state;
	
	/**our constructor
	 * 
	 * @param x
	 * @param y
	 * @param state
	 */
	public Towers(int x, int y, GameState state)
	{
		this.x = x; // creates an x coordinate
		this.y = y; // creates a y coordinate
		this.state = state; // saves a reference to the gameState object
	}
	
}
