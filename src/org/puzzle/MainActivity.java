package org.puzzle;

import org.puzzle.game.Game;
import org.puzzle.game.Puzzle;
import org.puzzle.game.Util;
import org.puzzle.game.event.PuzzleGeneratedEvent;
import org.puzzle.game.event.PuzzleGeneratedListener;
import org.puzzle.game.event.PuzzleSolvedEvent;
import org.puzzle.game.event.PuzzleSolvedListener;
import org.puzzle.ui.PuzzleCanvas;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
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

/**
 * This is the main activity, the entry point on android
 * @author Hannes Dorfmann
 *
 */
public class MainActivity extends Activity implements PuzzleGeneratedListener,
														  PuzzleSolvedListener,
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
        game.setPuzzleSolvedListener(this);
        
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
        
        
        
        canvas = new PuzzleCanvas(game, this);
        
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
		
		
		runOnUiThread(new Runnable() {
		     public void run() {
		    	
		    	 mainLayout.removeView(loadingView);
		    	 mainLayout.addView(puzzleView);
		    	 initCanvas(e.getPuzzle());
		    	 
		    	 if (e.getPuzzle().isSolvable())
			   		 updateStatusText("Lösbar");
			   	 else
			   		updateStatusText("Nicht lösbar");
		    }
		});
		
		
	}
	
	
	/**
	 * Initialize the {@link PuzzleCanvas} (if not done yet) and 
	 * set the current puzzle instance
	 * @param p
	 */
	private void initCanvas(final Puzzle p){
		
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				Display display = getWindowManager().getDefaultDisplay();
				Point size = new Point();
				display.getSize(size);
				final int margin = 10;
				final int width = size.x - (2*margin);
				
				// inject lazily
			   	 if (canvasPlaceHolder== null)
			   	 {
			   		canvasPlaceHolder = (RelativeLayout) findViewById(R.id.puzzlePlaceHolder);
			   		
			   		 RelativeLayout.LayoutParams params = 
			   				 new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, width);
			   		 params.addRule(RelativeLayout.CENTER_IN_PARENT);
			   		 params.setMargins(margin, 0, margin, 0);
			   		 
			   		 canvasPlaceHolder.addView(canvas, params);
			   		
			   		 // Assumption: background picture is quadratic
				   	 canvas.setPuzzleBackgroundBitMap(
				   	 		Util.loadBitmap(getResources(), R.drawable.beach, width, width));
				   	 
				   	
			   	 }
			   	 
			   	 //enable moving
			   	 canvas.setCanMoveTiles(true);
			   	 canvas.setPuzzle(p, width);
			   	
			}
		});
		
	}
	
	
	/**
	 * Set the message label
	 * @param msg
	 */
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
	
	/**
	 * Start a new game by generating a new Puzzle
	 */
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

	@Override
	public void onPuzzleSolved(PuzzleSolvedEvent e) {
		// disable moving
		canvas.setCanMoveTiles(false);
		updateStatusText("Gelöst in "+e.getElapsedTime()+" Sek. mit "+e.getMoves()+" Zügen");
	}
	
	
	
	
}
