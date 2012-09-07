package org.puzzle.ui;

import org.puzzle.game.PuzzleTile;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;


/**
 * This class draws a {@link PuzzleTile} on screen
 * @author Hannes Dorfmann
 *
 */
public class PuzzleTileDrawable implements Drawable{

	private static int fontSize = 14;
	private static int numberSpace = 3;
	
	private Bitmap bitmap;
	
	/**
	 * The x,y coordinates are referring to the top left corner of this {@link Drawable}
	 */
	private int x, y;
	
	private PuzzleTile tile;
	
	public PuzzleTileDrawable(PuzzleTile tile, Bitmap bitmap, int x, int y){
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.tile = tile;
	}
	
	
	@Override
	public void draw(Canvas c, Paint paint) {
		
		c.drawBitmap(bitmap, x, y, paint);
		
		paint.setStyle(Style.FILL);
		paint.setColor(Color.DKGRAY);
		paint.setAlpha(180);
		int w = fontSize*2;
		c.drawRoundRect(new RectF(x+numberSpace, y+numberSpace, x+w, y+w), 3,3,paint);
		
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.WHITE);
		paint.setTextAlign(Align.CENTER);
		paint.setTypeface(Typeface.MONOSPACE);
		c.drawText(""+(tile.getFinalPosition()+1),x+numberSpace+(fontSize),y+numberSpace+(fontSize+3), paint);
		paint.setTextSize(fontSize);
		
		
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
	
	
	public PuzzleTile getPuzzleTile(){
		return tile;
	}



}
