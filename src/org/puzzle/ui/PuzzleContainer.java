package org.puzzle.ui;

import org.puzzle.game.Puzzle;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PuzzleContainer extends LinearLayout{

	private TextView infoLabel;
	private RelativeLayout placeHolder;
	
	
	public PuzzleContainer(Context context) {
		super(context);
		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT));
		
		this.setOrientation(VERTICAL);
		
		placeHolder = new RelativeLayout(context);
		placeHolder.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT));
		
		
		infoLabel = new TextView(context);
		infoLabel.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.WRAP_CONTENT));
		
		infoLabel.setTextSize(20);
		infoLabel.setGravity(Gravity.CENTER);
		
		
		addView(placeHolder);
		addView(infoLabel);
		
	}
	
	
	
	public void setPuzzle(Puzzle p){
	
		Log.i("LinearLayout ", getWidth()+" "+getHeight());
		if (p.isSolvable())
			infoLabel.setText("Lösbar");
		else
			infoLabel.setText("Nicht lösbar");
	}
	
	

}
