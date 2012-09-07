package org.puzzle.ui;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 
 * @author Hannes Dorfmann
 *
 */
public interface Drawable {

	
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);

	
	/**
	 * This method is called by the {@link PuzzleCanvas} to draw this component on screen 
	 * @param c
	 */

	void draw(Canvas c, Paint paint);

}
