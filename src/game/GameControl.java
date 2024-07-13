/**
 * Tower defense 'control' class.  This class encapsulates all the logic and setup
 * for the game, including creation of state and view classes, listener creation,
 * timers, game start and game over logic, etc.
 * 
 * @author Peter Jensen, Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.*;

public class GameControl implements Runnable, ActionListener
{
	// Fields 
	
	private GameView view;
	private GameState state;
	private Timer timer;
	private BufferedImage image;
	private Map<String, BufferedImage> map = new HashMap<>();
	private long previousTime;
	
	// Constructor
	
	/**
	 * Starts the game initialization process.
	 */
	public GameControl() 
	{
		System.out.println("Starting the game...");
		
		// The constructor is called from main, so it is executing in the main thread.
		// All GUI work needs to be done by the GUI thread.  Make a call to ask the
		// GUI thread to run this object, then simply return to main.  The remainder
		// of the game setup will take place when the 'run' method is called by
		// the GUI thread (later).
		
		SwingUtilities.invokeLater(this);
		
		// intialize previousTime
		
		previousTime = System.currentTimeMillis();
	}
	

	/**
	 * This is the first function invoked (called) by the GUI thread.
	 * Set up the game.
	 */
	public void run ()
	{
		
		// Set up game objects.
		
		state = new GameState(this);
		view = new GameView(state, this);
		
		// adds all of our game objects to our animatables list
		
		state.addAnimatable(new Background());
		state.addAnimatable(new Menu(this, state));
		
		// add mouseListeners
		
		view.addMouseListener(state);
		view.addMouseMotionListener(state);
		
		// a timer that triggers an event 60 times a second.
		
		timer = new Timer(16, this); // delay in miliseconds.
		timer.start();
	}
	
	/** called whenever the game performs an action event happens
	 * This object listens for it and receives the event.
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e)
	{
		long currentTime = System.currentTimeMillis();
		double elapsedTime = (currentTime - previousTime) / 1000.0;
		previousTime = currentTime;
		
		state.updateAll(elapsedTime);
		view.repaint();
	}
	/**puts a buffered image into a map object. Will load image if it has not already been loaded.
	 * 
	 * @param s - the key used to locate in a map.
	 * @return a buffered image 
	 */
	public BufferedImage loadImage(String s)
	{
		if(!map.containsKey(s))
		{
			try
			{
				ClassLoader loader = this.getClass().getClassLoader();
				InputStream is = loader.getResourceAsStream(s);
				image = javax.imageio.ImageIO.read(is);
				map.put(s, image);
			}
			catch (IOException e)
			{
				System.out.println("Unable to load image.");
			}
		}

		return  map.get(s);
	}

}
