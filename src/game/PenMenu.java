/**
 * Tower Defense -- creates our pen menu tower object
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class PenMenu extends Towers
{
	private GameControl control;
	/**our constructor
	 * 
	 * @param x
	 * @param y
	 * @param state
	 */
	public PenMenu(int x, int y, GameState state, GameControl control) 
	{
		super(x, y, state);
		this.control = control;
	}

	@Override
	public void update(double elapsedTime)
	{
		if(state.getMouseClicked() && state.getCredits() >= 500) // if mouse has been clicked and this value returns true, run block.
		{
			int deltaX = Math.abs(x - state.getMouseX());
			int deltaY = Math.abs(y - state.getMouseY());
			 
			// determines whether the tower has been clicked on.
			
			if(deltaX < 40 && deltaY < 40)
			{
				state.consumeClick(); 
				
				// will add a new moving eye tower to our list of animatable objects
				
				state.addAnimatable(new PenMoving(state.getMouseX(), state.getMouseY(), state, control));
				state.subCreditsPen(); // removes the credits we spent from our balance 
			}
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
