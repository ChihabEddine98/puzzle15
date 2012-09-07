package org.puzzle.ui;

import android.graphics.Canvas;

/**
 * 
 * @author Hannes Dorfmann
 *
 */
public interface Drawable {
	
	/**
	 * This method is called by the 
	 * @param c
	 */
	public void draw(Canvas c);
	
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
	
	/**
	 * Determines, if some screen coordinates belongs to this Drawable or not
	 * @param x
	 * @param y
	 */
	public boolean hasCoordinates(int x, int y);

}
