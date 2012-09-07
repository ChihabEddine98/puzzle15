package org.puzzle.ui;

import org.puzzle.game.FreeSpace;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

/**
 * This drawable draws the {@link FreeSpace} on the screen
 * @author Hannes Dorfmann
 *
 */
public class FreeSpaceDrawable implements Drawable{

	private float x, y, width;
	private FreeSpace freeSpace;
	
	public FreeSpaceDrawable(FreeSpace freeSpace, float x, float y, float width){
		this.x = x;
		this.y = y;
		this.width = width;
		this.freeSpace = freeSpace;
	}
	
	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public void draw(Canvas c, Paint paint) {
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL); 
		c.drawRect(x, y, x+width, y+width, paint);
	}
	
	public FreeSpace getFreeSpace(){
		return freeSpace;
	}

}
