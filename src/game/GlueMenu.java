/**
 * Tower Defense -- creates our glue menu tower object
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class GlueMenu extends Towers
{

	private GameControl control;
	/**our constructor
	 * 
	 * @param x
	 * @param y
	 * @param state
	 */
	public GlueMenu(int x, int y, GameState state, GameControl control) 
	{
		super(x, y, state);
		this.control = control;
	}

	@Override
	public void update(double elapsedTime)
	{
		if(state.getMouseClicked() && state.getCredits() >= 100) // if mouse has been clicked and this value returns true, run block.
		{
			int deltaX = Math.abs(x - state.getMouseX());
			int deltaY = Math.abs(y - state.getMouseY());
			 
			// determines whether the tower has been clicked on.
			
			if(deltaX < 40 && deltaY < 40)
			{
				state.consumeClick(); 
				
				// will add a new moving eye tower to our list of animatable objects
				
				state.addAnimatable(new GlueMoving(state.getMouseX(), state.getMouseY(), state, control));
				state.subCreditsPencil(); // removes the credits we spent from our balance. it is worth the same as a pencil so I will use the same helper function.
			}
		}
		
	}

	/** will draw our tower
	 * 
	 */
	public void draw(Graphics g, GameView view) 
	{	
		Point p = new Point( x, y); // creates the point our tower will be centered at.
		view.drawCenteredImage(g, "resources\\glue.png", p);
		
	}
	
}
