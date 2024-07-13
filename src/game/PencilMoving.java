/**
 * Tower Defense -- creates our pencil moving tower object
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class PencilMoving extends Towers
{
	private GameControl control;

	/**our constructor
	 * 
	 * @param x
	 * @param y
	 * @param state
	 */
	public PencilMoving(int x, int y, GameState state, GameControl control) 
	{
		super(x, y, state);
		this.control = control;

	}

	/**
	 *
	 */
	@Override
	public void update(double elapsedTime) 
	{
		// get our x and y coordinates
		
		x = state.getMouseX();
		y = state.getMouseY();
		
		// checks to see if mouse has been clicked
		
		if(state.getMouseClicked() && x < 630)
		{
			state.consumeClick();
			
			// will check the pixel color of our mask to see if the tower can be placed
			
			if(control.loadImage("resources\\path_02Mask.png").getRGB(x,y) == -1 || control.loadImage("resources\\path_02Mask.png").getRGB(x,y) == -12629812)
				return;
			
			state.addAnimatable(new Pencil(x, y, state)); // creates our newly placed tower.
			state.removeAnimatable(this); // removes the current tower following the mouse.
		}
		
		// removes eye tower if it is placed in menu and credits are reimbursed 
		
		else if(state.getMouseClicked())
		{
			state.consumeClick();
			state.addCreditsPencil();
			state.removeAnimatable(this);
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
