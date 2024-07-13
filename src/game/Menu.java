/**
 * Tower Defense -- creates menu objects
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Graphics;
import  java.awt.Color;

public class Menu implements Animatable 
{

	private GameControl control;
	private GameState state;
	
	/**constructs our menu
	 * 
	 * @param control
	 */
	public Menu (GameControl control, GameState state)
	{
		this.control = control;
		this.state = state;
	}
	
	@Override
	public void update(double elapsedTime) 
	{
		state.addAnimatable(new PencilMenu(740, 200, state, control));
		state.addAnimatable(new PenMenu(755, 300, state, control));
		state.addAnimatable(new GlueMenu(705, 300, state, control));
	}

	@Override
	public void draw(Graphics g, GameView view) {
		// draws our menu.
		
		String word;
		
		g.setColor(Color.WHITE);
		g.fillRect(630, 0, 600, 700);
		g.setColor(Color.RED);
		g.drawString("DEFEND YOUR NOTEBOOK", 655, 50);
		
		word = String.valueOf(state.getLives()); // converts # of lives to string.
		g.drawString("LIVES: " + word, 705, 100);
		
		word = String.valueOf(state.getCredits()); // converts # of credits to string.
		g.drawString("CREDITS: " + word, 695, 125);
		
		word = String.valueOf(state.getKillCount()); // converts # of credits to string.
		g.drawString("KILLS: " + word, 710, 150);
	}

}
