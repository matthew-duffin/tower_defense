/**Tower Defense -- this object will control the jframe, jpanel, and calling the game state class.
 * 
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class GameView extends JPanel implements MouseListener
{
	// Fields
	
	private GameState state;
	private GameControl control;
	private BufferedImage image;
	
	// Constructor 
	
	/**Constructs the game view object.
	 * 
	 * @param s - a game state object
	 * @param control 
	 */
	
	public GameView (GameState s, GameControl control)
	{
		
		this.addMouseListener(this);
		
		// Keep track of the game's state object.
		
		state = s;
		this.control = control;
		
		// Build the frame and the panel, then put 'this' object in it.
		// First, use inherited methods to set a new pixel size for this object.
		
		this.setMinimumSize(new Dimension(829, 400));
		this.setMaximumSize(new Dimension(829, 400));
		this.setPreferredSize(new Dimension(829, 400));
		
		// Make the JFrame, ask it to exit the application when closed.
		
		JFrame frame = new JFrame ("Defend Your Notebook");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Put 'this' JPanel into the frame.
		
		frame.setContentPane(this);
		
		// Reallocate frame space (called packing), then show it.
		
		frame.pack(); 
		frame.setVisible(true);
	}
	
	/**A paint function that calls the draw all function on our game state variable.
	 * 
	 */
	public void paint (Graphics g)
	{
		state.drawAll(g, this);  // We'll do all the work in drawAll in this checkpoint
	}
	/**centers an image on the path.
	 * 
	 * @param g
	 * @param s
	 * @param location
	 */
	public void drawCenteredImage(Graphics g, String s, Point location)
	{
		int x = 0;
		int y = 0;
		image = control.loadImage(s); // loads our image and stores reference.
		
		// if we are loading background the image wont need to be centered so this is not run.
		
		if(!s.equals("resources\\path_02.jpg"))
		{
			x = ((int) location.getX()) - (image.getHeight() / 2); //our x and y coordinates
			y = ((int) location.getY())- (image.getHeight() / 2); // these values come from subtracting half of the width
		}

		g.drawImage(image, x, y, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		
		System.out.println(x + " " + y);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
