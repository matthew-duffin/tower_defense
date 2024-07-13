/**
 *an effects class that draws our smoke effect used on enemy death.
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class FXsmoke extends Effect implements Animatable {
	// fields
	private double age;

	public FXsmoke(GameState state, int x, int y) {
		super(state, x, y);

	}

	@Override
	public void update(double elapsedTime) {
		// makes sure our smoke only exists for 1 second.

		age += elapsedTime;

		if (age > 1.0) 
		{
			state.removeAnimatable(this);
			return;
		}

	}

	/**
	 * draws our smoke picture
	 * 
	 */
	public void draw(Graphics g, GameView view) 
	{
		Point p = new Point(x, y);
		view.drawCenteredImage(g, "resources\\smokeFX.png", p);
	}

}
