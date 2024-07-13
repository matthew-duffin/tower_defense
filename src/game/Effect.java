/**
 * Tower Defense -- An effects super class
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

public abstract class Effect implements Animatable
{
	// fields
	protected GameState state;
	protected int x;
	protected int y;
	
	/**Constructor for our effects classes
	 * 
	 * @param state
	 * @param x
	 * @param y
	 * @param deltaX
	 * @param deltaY
	 */
	public Effect(GameState state, int x, int y)
	{
		// creates references to all our fields
		this.state = state;
		this.x = x;
		this.y = y;
		
	}

}
