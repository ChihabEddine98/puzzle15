package org.puzzle.ui;

import java.util.ArrayList;
import java.util.List;

import org.puzzle.game.Puzzle;
import org.puzzle.game.event.PuzzleTileMovedEvent;
import org.puzzle.game.event.PuzzleTileMovedListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class PuzzleCanvas extends View implements View.OnTouchListener,
													  PuzzleTileMovedListener{

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
	
	
	
	public void setPuzzle(Puzzle p){
		
		Log.i("set puzzle"," "+getWidth()+" "+ getHeight());
		invalidate();
	}
	  
	  
	  public void setPuzzleBackgroundBitMap(Bitmap bitmap){
		  
	  }
	  
	  
	  
	  @Override
	  public void onDraw(Canvas canvas)
	  {
		  
		  paint.setColor(Color.BLACK);
		  paint.setStyle(Style.FILL); 
		  Log.i("WH", getWidth()+" "+getHeight());
		  canvas.drawRect(0, 0, getWidth(), getHeight(), paint);  
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
