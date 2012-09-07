package org.puzzle.ui;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 
 * @author Hannes Dorfmann
 *
 */
public interface Drawable {

	
	public float getX();
	public float getY();
	public void setX(float x);
	public void setY(float y);

	
	/**
	 * This method is called by the {@link PuzzleCanvas} to draw this component on screen 
	 * @param c
	 */

	void draw(Canvas c, Paint paint);

}
