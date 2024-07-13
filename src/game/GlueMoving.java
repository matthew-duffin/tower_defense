/**
 * Tower Defense -- creates our glue moving tower object
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class GlueMoving extends Towers {

	private GameControl control;

	/**
	 * our constructor
	 * 
	 * @param x
	 * @param y
	 * @param state
	 */
	public GlueMoving(int x, int y, GameState state, GameControl control) {
		super(x, y, state);
		this.control = control;
	}

	/**
	 *
	 */
	@Override
	public void update(double elapsedTime) {
		// get our x and y coordinates

		x = state.getMouseX();
		y = state.getMouseY();

		// checks to see if mouse has been clicked

		if (state.getMouseClicked() && x < 630)
		{
			state.consumeClick();
			
			// will check the pixel color of our mask to see if the tower can be placed. This Tower can be placed in the lake so it has only one value that needs to be checked.

			if (control.loadImage("resources\\path_02Mask.png").getRGB(x, y) == -1)
				return;
			state.addAnimatable(new Glue(x, y, state)); // creates our newly placed tower.
			state.removeAnimatable(this); // removes the current tower following the mouse.
		}

		// removes eye tower if it is placed in menu and credits are reimbursed

		else if (state.getMouseClicked()) {
			state.consumeClick();
			state.addCreditsPencil(); // costs the same as a pencil.
			state.removeAnimatable(this);
		}

	}

	/**
	 * will draw our tower
	 * 
	 */
	public void draw(Graphics g, GameView view) {
		Point p = new Point(x, y); // creates the point our tower will be centered at.
		view.drawCenteredImage(g, "resources\\glue.png", p);

	}

}
