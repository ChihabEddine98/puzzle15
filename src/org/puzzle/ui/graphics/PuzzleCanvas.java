package org.puzzle.ui.graphics;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class PuzzleCanvas extends View implements View.OnTouchListener{

	private List<Drawable> drawables;
	
	public PuzzleCanvas(Context context)
	  {
	    super(context);
	    setFocusable(true);
	    setFocusableInTouchMode(true);
	    this.setOnTouchListener(this);
	    
	    drawables = new ArrayList<Drawable>();
	  }
	
	
	  
	  
	  public void setPuzzleBackgroundBitMap(Bitmap bitmap){
		  
	  }
	  
	  
	  
	  @Override
	  public void onDraw(Canvas canvas)
	  {
		  
	  }


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}
