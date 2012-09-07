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

	private int x, y, width;
	private FreeSpace freeSpace;
	
	public FreeSpaceDrawable(FreeSpace freeSpace, int x, int y, int width){
		this.x = x;
		this.y = y;
		this.width = width;
		this.freeSpace = freeSpace;
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void draw(Canvas c, Paint paint) {
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL); 
		c.drawRect(x, y, width, width, paint);
	}
	
	public FreeSpace getFreeSpace(){
		return freeSpace;
	}

}
