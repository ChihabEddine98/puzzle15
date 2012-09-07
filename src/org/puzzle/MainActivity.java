package org.puzzle;

import org.puzzle.game.Game;
import org.puzzle.game.Puzzle;
import org.puzzle.game.event.PuzzleGeneratedEvent;
import org.puzzle.game.event.PuzzleGeneratedListener;
import org.puzzle.ui.PuzzleCanvas;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements PuzzleGeneratedListener, 
														  OnClickListener {
	
	private static final int N = 4;
	
	private Game game;
	
	private FrameLayout mainLayout;
	private View initialView;
	private View loadingView;
	private View puzzleView;
	
	private RelativeLayout canvasPlaceHolder;
	private PuzzleCanvas canvas;
	private TextView infoLabel;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init the game
        game = new Game();
        game.setPuzzleGeneratedListener(this);
        
        // init gui
        setContentView(R.layout.activity_main);
        mainLayout = (FrameLayout) findViewById(R.id.mainLayout);
        initialView = findViewById(R.id.initialLayout);
        
        // Load layouts from xml
        LayoutInflater factory = getLayoutInflater();
        // Load the LoadingView from xml
        loadingView = factory.inflate(R.layout.loading, null);
        //Load the PuzzleView from xml
        puzzleView = factory.inflate(R.layout.puzzle_container, null);
        
        
        
        canvas = new PuzzleCanvas(this);
        
        
        Log.i("deb", ""+mainLayout+ " "+initialView+" "+loadingView + " "+ puzzleView );
        
        Button b = (Button) findViewById(R.id.startNewGameButton);
        b.setOnClickListener(this);
        
        
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
       
        return true;
    }

	@Override
	public void onPuzzleGenerated(final PuzzleGeneratedEvent e) {
		
		Puzzle p = e.getPuzzle();
   	 	final boolean solvable = p.isSolvable();
		
   	 	Log.i("solvable", ""+solvable);
   	 	
	   	
   	 	
		runOnUiThread(new Runnable() {
		     public void run() {
		    	
		    	 mainLayout.removeView(loadingView);
		    	 mainLayout.addView(puzzleView);
		    	 
		    	 setPuzzle(e.getPuzzle());
		    	 
		    	 if (solvable)
		    		 updateStatusText("Lösbar");
		    	else
		    		updateStatusText("Nicht lösbar");
		    	 
		    }
		});
		
		
	}
	
	
	private void setPuzzle(final Puzzle p){
		
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// inject lazily
			   	 if (canvasPlaceHolder== null)
			   	 {
			   		 canvasPlaceHolder = (RelativeLayout) findViewById(R.id.puzzlePlaceHolder);
			   		 Log.i("deb", "CPH "+canvasPlaceHolder.getWidth()+" "+canvasPlaceHolder.getHeight());
			   		 
			   		 
			   		Display display = getWindowManager().getDefaultDisplay();
			   		Point size = new Point();
			   		display.getSize(size);
			   		int width = size.x;
			   		int margin = 10;
			   		
			   		 RelativeLayout.LayoutParams params = 
			   				 new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, width-2*margin);
			   		 params.addRule(RelativeLayout.CENTER_IN_PARENT);
			   		 params.setMargins(margin, 0, margin, 0);
			   		 
			   		 canvasPlaceHolder.addView(canvas, params);
			   		 
			   		 
			   	 }
			   	 
		    	 canvas.setPuzzle(p);
		    	
			}
		});
	}
	
	
	
	private void updateStatusText(final String msg){
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				if (infoLabel == null)
	    	 		infoLabel = (TextView) findViewById(R.id.puzzleInfoLabel);
				
				infoLabel.setText(msg);
			}
		});
	}
	

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.startNewGameButton || v.getId() == R.id.newGameItem)
		{
			startNewGame();
		}
	}
	
	
	private void startNewGame(){
		mainLayout.removeAllViews();
		mainLayout.addView(loadingView);
		game.startWithNewPuzzle(N);
	}
	
	@Override
	public boolean onOptionsItemSelected (MenuItem item){
		
		if (item.getItemId() == R.id.newGameItem){
			startNewGame();
		}
		
		return super.onOptionsItemSelected(item);
	}

	
}
