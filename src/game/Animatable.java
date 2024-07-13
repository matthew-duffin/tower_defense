/**
 * Tower Defense -- An interface for animatable objects
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;
import java.awt.Graphics;

public interface Animatable 
{
	public void update(double elapsedTime); 
	public void draw (Graphics g, GameView view);
}
