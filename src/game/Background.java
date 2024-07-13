/**
 * Tower Defense -- creates background objects
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class Background implements Animatable 
{
	private GameControl control;
	
	/**constructor of our background
	 * 
	 * @param control
	 */
	public Background ()
	{

	}
	
	@Override
	public void update(double elapsedTime) 
	{
		// Nothing to move, therefore nothing to do.
	}

	@Override
	public void draw(Graphics g, GameView view) 
	{
		// Draw the background
		Point p = new Point(0, 0);
		
		view.drawCenteredImage(g,  "resources\\path_02.jpg", p);
	}

}
