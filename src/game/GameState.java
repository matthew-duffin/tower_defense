/**
 * Tower Defense -- An object that will control the state of the game.
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.awt.event.*;

public class GameState implements MouseListener, MouseMotionListener
{
	// Fields
	
	private Path path;
	private GameControl control;
	private List<Animatable> gameObjects;
	private List<Animatable> toAdd; // animatables that need to be added to animatable list
	private List<Animatable> toRemove; // animatable that need to be removed from animatable list.
	private int mouseX, mouseY;
	private boolean mouseClicked; // saves a reference to a mouse click 
	private boolean gameOver;
	private int lives;
	private int credits;
	private int updateCount;// keeps track of how many updates have been called.
	private int tacCount; // counts how many tacs have been made.
	private double pTraveled; // the percent traveled;
	private int killCount;
	
	/* Contracts needed! */
	
	/**GameState Constructor that is in charge of all gui information.
	 * @throws FileNotFoundException 
	 * 
	 */
	public GameState(GameControl control) 
	{
		
		// creates our list objects
		
		gameObjects = new ArrayList<Animatable>();
		toAdd = new ArrayList<Animatable>();
		toRemove = new ArrayList<Animatable>();
		
		this.control = control;
		
		// intialize values to some of our fields
		
		lives = 10;
		gameOver = true;
		credits = 300;
		updateCount = 1;
		tacCount = 0;
		pTraveled = 0;
		
		// build our path
		try 
		{
			path = new Path(new Scanner(new File("src\\resources\\path02.txt")));
		} 
		catch (FileNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/** will return x coordinate of mouse
	 * 
	 * @return
	 */
	public int getMouseX()
	{
		return mouseX;
	}
	
	/** will return y coordinate of mouse
	 * 
	 * @return
	 */
	public int getMouseY()
	{
		return mouseY;
	}
	
	/** will return value of mouseClicked
	 * 
	 * @return
	 */
	public boolean getMouseClicked()
	{
		return mouseClicked;
	}
	/**will consume a mouse click and turn it false.
	 * 
	 */
	public void consumeClick()
	{
		mouseClicked = false;
	}
	/**will unconsume a mouse click and turn it false.
	 * 
	 */
	public void unconsumeClick()
	{
		mouseClicked = true;
	}

	/**gets the path 
	 * 
	 * @return
	 */
	public Path getPath ()
	{
		return path;
	}
	
	/**A function that draws everything
	 * 
	 * @param - a graphics object.
	 * @param - a GameView object
	 */
	public void drawAll(Graphics g, GameView view)
	{
		for(Animatable a : gameObjects)
		{
			a.draw(g, view);
			if(this.isGameOver() == true)
			{
				g.setColor(Color.RED);
				g.drawString("GAME OVER", 300, 200);
			}
		}
			
		
	}
	/**A function that updates 60 times a second. This controls every game Object and how it will update.
	 * 
	 * @param elapsed time - the amount of time elapsed in a double.
	 */
	public void updateAll(double elapsedTime)
	{
		if(this.isGameOver() == false)
		{
			for(Animatable a : gameObjects)
				a.update(elapsedTime);
			
			// add and remove pending items
			 gameObjects.removeAll(toRemove);
			 gameObjects.addAll(toAdd);
	
			 toAdd.clear();
			 toRemove.clear();
			 
			 // If there is an unconsumed mouse click, consume it
			 
			 mouseClicked = false;
			 
			 // generates enemies
			 // every few updates a tac will spawn but will only spawn a max of 60 tacs.
			 
			if((updateCount % 40) == 0 && tacCount <= 60)
			{
				addAnimatable(new Tac(control, this, pTraveled));
				tacCount++;
				
				// this causes every for 5 tacs an eraser to spawn.
				
				if((tacCount % 5) == 0)
				{
					addAnimatable(new Eraser(control, this, pTraveled));
				}
			}
			
			 updateCount++; // updates our total number of updates
			 
			 // ends our game when the game over condition is true
			 
			 if(lives <= 0 || killCount >= 60)
				this.GameOver();
		}
		 
	}
	/** adds animatable objects to our list
	 * 
	 * @param a - an animatable object
	 */
	public void addAnimatable(Animatable a)
	{
		toAdd.add(a);
	}
	/**removes animatable objects from our list.
	 * 
	 * @param a - an animatable object
	 */
	public void removeAnimatable(Animatable a)
	{
		toRemove.add(a);
	}
	/**Returns the players number of lives remaining
	 * 
	 * @return - lives
	 */
	public int getLives()
	{
		return lives;
	}
	
	/** subtracts lives
	 * 
	 * @return - lives
	 */
	public int subLives()
	{
		lives = lives - 1;
		return lives;
	}
	
	/**Returns the players number of credits remaining
	 * 
	 * @return - credits
	 */
	public int getCredits()
	{
		return credits;
	}
	
	/**subtracts credits from total for pencils
	 * 
	 * @return - credits
	 */
	public int subCreditsPencil()
	{
		credits = credits - 100;
		return credits;
	}
	/**subtracts credits from total for pens
	 * 
	 * @return - credits
	 */
	public int subCreditsPen()
	{
		credits = credits - 200;
		return credits;
	}
	/**access kill count
	 * 
	 * @return - credits
	 */
	public int getKillCount()
	{
		return killCount;
	}
	
	/**increases number of kills
	 * @return - credits
	 */
	public int increaseKillCount()
	{
		killCount++;
		return killCount;
	}
	
	/**adds credits to total from pencil towers
	 * 
	 * @return - credits
	 */
	public int addCreditsPencil()
	{
		credits = credits + 100;
		return credits;
	}
	/**adds credits from kills
	 * 
	 * @return - credits
	 */
	public int addCreditsKills()
	{
		credits = credits + 25;
		return credits;
	}
	
	/**adds credits to total from pen towers
	 * 
	 * @return - credits
	 */
	public int addCreditsPen()
	{
		credits = credits + 200;
		return credits;
	}
	
	/**Returns if game is over
	 * 
	 * @return - gameOver
	 */
	public boolean isGameOver()
	{
		return gameOver;
	}
	/**sets game to over
	 * 
	 * @return - gameOver
	 */
	public void GameOver()
	{
		gameOver = true;
		updateCount = 1;
	}
	
	/**returns the nearest enemy object reference to the caller.
	 * needs to be the enemy closest to the x, y coordinates
	 * null will be returned if no enemy is close by
	 * @param x
	 * @param y
	 * @return
	 */
	public Enemy findNearEnemy(int x, int y) 
	{
		Point p = new Point(x, y);
		Enemy closest = null; // if no enemy is closest null is returned
		
		// optimization loop 
		for(Animatable a : gameObjects )
		{
			if(a instanceof Enemy)
			{
				Enemy e = (Enemy) a; // casts our animatable to an enemy
				
				if(closest == null) // no closest enemy found yet
					closest = e;
				else if(p.distance(e.getLocation()) < p.distance(closest.getLocation())) // when closest enemy is found, it replaces our closest variable
					closest = e;
						
			}
		}
		
		return closest;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		//auto generated
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseClicked = true;
		gameOver = false; // makes it so that when you click the game starts again.
		
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
