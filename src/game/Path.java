/**Tower Defense -- this object will create our path.
 * 
 * 
 * @author Matthew Duffin
 * @version Spring 2022
 */
package game;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Point;
import java.awt.Color;

public class Path {
	/* Contracts needed */

	// Fields to store the path and its length

	private int[] xArr, yArr;
	private int xCoor = 0; // our final x and y coordinates.
	private int yCoor = 0;
	private double totalPath = 0;
	private Point finalP;
	private int length = 0;
	private double[] disArr;
	private double[] totalLength;

	/**
	 * This constructor does the following: - It reads a number of coordinates, n,
	 * from the scanner. - It creates new array(s) (or an ArrayList) to hold the
	 * path coordinates, and stores it in the field in 'this' object. - It loops n
	 * times, each time scanning a coordinate x,y pair, creating an object to
	 * represent the coordinate (if needed), and stores the coordinate in the
	 * array(s) or ArrayList.
	 * 
	 * @param s a Scanner set up by the caller to provide a list of coordinates
	 * @throws FileNotFoundException
	 */
	public Path(Scanner in) throws FileNotFoundException 
	{
		length = in.nextInt();
		xArr = new int[length]; // creates array for x values
		yArr = new int[length]; // creates array for y values.
		int count = 0;
		disArr = new double[length - 1];
		totalLength = new double[length - 1];

		while (in.hasNext()) // fills parallel arrays.
		{
			xArr[count] = in.nextInt();
			yArr[count] = in.nextInt();
			count++;
		}

		// loop that will perform distance formula and add these values to our distance
		// array.
		// then it will calculate total path distance by adding up all values in the
		// array.
		for (count = 0; count < xArr.length - 1; count++) {
			double xDis = Math.pow((xArr[count + 1] - xArr[count]), 2); // subtracts x2 coordinate from x1 coordinate and squares it
			double yDis = Math.pow((yArr[count + 1] - yArr[count]), 2); // subtracts y2 coordinate from y1 coordinate and squares it.

			double pointDis = Math.sqrt(xDis + yDis); // takes square root of both values.
			disArr[count] = pointDis; // saves lengths of each individual segment in this array.

			totalPath = totalPath + disArr[count]; // calculates total path
			totalLength[count] = totalPath; // saves the path total as we go through each segment.

		}
	}

	/**
	 * Draws the current path to the screen (to wherever the graphics object points)
	 * using a highly-visible color.
	 * 
	 * @param g a graphics object
	 */
	public void drawPath(Graphics g) 
	{
		g.setColor(Color.RED);
		
		for (int count = 0; count < xArr.length - 1; count++) // draws a red line between each point in path.
		{
			g.drawLine(xArr[count], yArr[count], xArr[count + 1], yArr[count + 1]);
		}
	}

	/**
	 * Given a percentage between 0% and 100%, this method calculates the location
	 * along the path that is exactly this percentage along the path. The location
	 * is returned in a Point object (integer x and y), and the location is a screen
	 * coordinate.
	 * 
	 * If the percentage is less than 0%, the starting position is returned. If the
	 * percentage is greater than 100%, the final position is returned.
	 * 
	 * If students don't want to use Point objects, they may write or use another
	 * object to represent coordinates.
	 *
	 * Caution: Students should never directly return a Point object from a path
	 * list. It could be changed by the outside caller. Instead, always create and
	 * return a new point objects as the result from this method.
	 * 
	 * @param percentTraveled a distance along the path
	 * @return the screen coordinate of this position along the path
	 */
	public Point locatePosition(double percentTraveled) {
		double percent = percentTraveled;
		double pointDis = 0; // total distance between each point.
		double remainingPer = 0; // percent across each individual segment.

		// this loop will tell us which segment we are actually in.
		double p = totalPath * percent; // will tell us how many pixels we have traveled.

		for (int count = 0; count < totalLength.length; count++) // loops through our length totals.
		{
			if (p <= totalLength[count]) // if p is <= to the total path length of that segment, we know that is the
											// segment we are in.
			{
				if (count >= 1) 
				{
					p = p - totalLength[count - 1]; // p is then changed to show what is left to be traveled in segment.
				}
				remainingPer = p / disArr[count]; // this value is converted to a percent.

				xCoor = (int) ((1 - remainingPer) * xArr[count] + (remainingPer) * xArr[count + 1]); // calculate
																										// weighted
																										// average of x.
				yCoor = (int) ((1 - remainingPer) * yArr[count] + (remainingPer) * yArr[count + 1]); // calculate
																										// weighted
																										// average of y.
				break;
			}

		}
		finalP = new Point(xCoor, yCoor);
		return finalP;

	}

}
