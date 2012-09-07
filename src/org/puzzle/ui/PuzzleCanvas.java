package org.puzzle.ui;

import java.util.ArrayList;
import java.util.List;

import org.puzzle.game.FreeSpace;
import org.puzzle.game.Puzzle;
import org.puzzle.game.PuzzleComponent;
import org.puzzle.game.PuzzleTile;
import org.puzzle.game.Util;
import org.puzzle.game.event.PuzzleTileMovedEvent;
import org.puzzle.game.event.PuzzleTileMovedListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

public class PuzzleCanvas extends View implements View.OnTouchListener,
													  PuzzleTileMovedListener{

	private Bitmap background;
	private Paint paint;
	private List<Drawable> drawables;
	
	public PuzzleCanvas(Context context)
	  {
	    super(context);
	    setFocusable(true);
	    setFocusableInTouchMode(true);
	    this.setOnTouchListener(this);
	    paint = new Paint();
	    drawables = new ArrayList<Drawable>();
	   
	  }
	
	
	
	public void setPuzzle(Puzzle p, int width){
		
		Log.i("wh ", ""+getWidth()+ " "+getHeight() + width);
		
		// Assumption: background is quadratic
//		if (getWidth() != background.getWidth())
//			background = Bitmap.createScaledBitmap(background, getWidth(), getHeight(), true);
		
		
		drawables.clear();
		int n = p.getN();
		// calculate the width of a PuzzleTile
		int w = width / n;
		Pair<Integer, Integer> pair;
		int cx , cy, x, y;
		// generate PuzzleTileDrawables
		for (int i = 0; i<(n*n-2); i++)
		{
			PuzzleComponent c = p.getComponentAt(i);
			// calculate crop coordinates
			pair = Util.getArrayCoordinate(c.getFinalPosition(), n);
			cx = pair.first * w;
			cy = pair.second * w;
			
			pair = Util.getArrayCoordinate(c.getCurrentPosition(), n);
			x = pair.first * w;
			y = pair.second * w;
			
			Log.i("bitmap", i+" "+x+" "+y+" | "+cx+" "+cy);
			Bitmap tilePic = Bitmap.createBitmap(background, cx, cy, w, w);
			
			drawables.add(new PuzzleTileDrawable((PuzzleTile) c, tilePic, x, y ));
		}
		
		
//		// finaly the free space is placed in the bottom right corner
//		FreeSpace f = (FreeSpace) p.getComponentAt(n*n-1);
//		pair = Util.getArrayCoordinate(f.getCurrentPosition(), n);
//		x = pair.first * w;
//		y = pair.second * w;
//		drawables.add( new FreeSpaceDrawable(f, x, y, w) );
		
		
			
		Log.i("set puzzle"," "+getWidth()+" "+ getHeight());
		invalidate();
	}
	  
	  
	  public void setPuzzleBackgroundBitMap(Bitmap bitmap){
		  this.background = bitmap;
	  }
	  
	  
	  public Bitmap getPuzzleBackgroundBitMap()
	  {
		  return background;
	  }
	  
	  @Override
	  public void onDraw(Canvas canvas)
	  {
		  for (Drawable d : drawables)
			  d.draw(canvas, paint);
	  }


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public void onPuzzleTileMoved(PuzzleTileMovedEvent e) {
		
		invalidate();
	}
}
