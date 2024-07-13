/**
 * Tower Defense -- creates our tac enemy object
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class Tac extends Enemy
{
	// fields
	
	private GameControl control;
	private GameView view;
	private Path path;
	
	public Tac(GameControl control, GameState state, double pTraveled) 
	{
		super(state, pTraveled);
	}

	@Override
	public void update(double elapsedTime) {
		// finds and updates our location on the path.
		path = state.getPath();
		Point point = path.locatePosition(pTraveled);
		x = (int) point.getX();
		y = (int) point.getY();

		pTraveled += 0.060 * elapsedTime; // updates how far along said path objects are.

		// if enemy reaches end run this
		
		if(pTraveled >= 1.0)
		{
			state.subLives(); // remove one life
			state.removeAnimatable(this); // remove enemy from animatables list.
		}
	}

	@Override
	public void draw(Graphics g, GameView view) 
	{
		Point p = getLocation(); // creates the point our object will be centered at. Which is current enemy location.

		view.drawCenteredImage(g, "resources\\thumbtTack.png", p);
	}

}
