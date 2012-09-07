package org.puzzle.ui;

import java.util.HashMap;

import org.puzzle.game.FreeSpace;
import org.puzzle.game.Game;
import org.puzzle.game.InvalidMoveException;
import org.puzzle.game.Move;
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
	private HashMap<PuzzleComponent, Drawable> drawableMapping;
	
	private PuzzleComponent sourceTile;
	private PuzzleComponent targetTile;
	
	private FreeSpace freeSpace;
	
	private Game game;
	
	
	public PuzzleCanvas(Game game, Context context)
	  {
	    super(context);
	    this.game = game;
	    game.setPuzzleMovedListener(this);
	    
	    setFocusable(true);
	    setFocusableInTouchMode(true);
	    this.setOnTouchListener(this);
	    paint = new Paint();
	    drawableMapping = new HashMap<PuzzleComponent, Drawable>();
	   
	  }
	
	
	
	public void setPuzzle(Puzzle p, int width){
		
		drawableMapping.clear();
		int n = p.getN();
		// calculate the width of a PuzzleTile
		int w = width / n;
		Pair<Integer, Integer> pair;
		int cx , cy, x, y;
		// generate PuzzleTileDrawables
		for (int i = 0; i<=(n*n-2); i++)
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
			Drawable d = new PuzzleTileDrawable((PuzzleTile) c, tilePic, x, y );
			drawableMapping.put(c, d);
		}
		
		
		// finaly the free space is placed in the bottom right corner
		freeSpace = (FreeSpace) p.getComponentAt(n*n-1);
		pair = Util.getArrayCoordinate(freeSpace.getCurrentPosition(), n);
		x = pair.first * w;
		y = pair.second * w;
		FreeSpaceDrawable fsd = new FreeSpaceDrawable(freeSpace, x, y, w);
		drawableMapping.put( freeSpace, fsd );
		
		
			
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
		  for (Drawable d : drawableMapping.values())
			  d.draw(canvas, paint);
	  }


	@Override
	public boolean onTouch(View v, MotionEvent me) {
		
		switch (me.getAction()) {
			case MotionEvent.ACTION_DOWN:
				sourceTile = getComponentWithCoordinate(me.getX(), me.getY());
			break;
			
			
			case MotionEvent.ACTION_UP:
				targetTile = getComponentWithCoordinate(me.getX(), me.getY());
				
				try {
					Move m = new Move(sourceTile.getCurrentPosition(), targetTile.getCurrentPosition());
					game.executeMove(m);
				} catch (InvalidMoveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				sourceTile = null;
				targetTile = null;
				break;

		}
		
		
		return true;
	}

	
	
	private PuzzleComponent getComponentWithCoordinate(float x, float y){
		
		float tileWidth = getWidth() / game.getPuzzle().getN();
		
		int arrayX = (int) (x / tileWidth);
		int arrayY = (int) (y / tileWidth);
		
		int pos = Util.getAbsolutePosition(arrayX, arrayY, game.getPuzzle().getN());
		Log.i("move", pos +" "+arrayX + " "+ arrayY);
		
		return game.getPuzzle().getComponentAt(pos);
		
	}



	@Override
	public void onPuzzleTileMoved(PuzzleTileMovedEvent e) {
		
		// recalcuate the  x and y of the moved piece
		int n = game.getPuzzle().getN();
		int w = getWidth() / n;
		PuzzleComponent c = e.getPuzzleTile();
		Pair<Integer, Integer> pair = Util.getArrayCoordinate(c.getCurrentPosition(), n);
		int x = pair.first * w;
		int y = pair.second * w;
		
		Drawable d = drawableMapping.get(c);
		d.setX(x);
		d.setY(y);
		

		// recalculate position of freespace
		pair = Util.getArrayCoordinate(freeSpace.getCurrentPosition(), n);
		x = pair.first * w;
		y = pair.second * w;
		
		d = drawableMapping.get(freeSpace);
		d.setX(x);
		d.setY(y);
		
		Log.i("redraw", "redraw");
		
		invalidate();
	}
}
