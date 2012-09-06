package org.puzzle.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class PuzzleCanvas extends View implements View.OnTouchListener{

	
	public PuzzleCanvas(Context context)
	  {
	    super(context);
	    setFocusable(true);
	    setFocusableInTouchMode(true);
	    this.setOnTouchListener(this);
	  }
	
	
	  private void init()
	  {
	 
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
