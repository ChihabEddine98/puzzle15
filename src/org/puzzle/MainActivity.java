package org.puzzle;

import org.puzzle.game.Game;
import org.puzzle.game.event.GameStartedEvent;
import org.puzzle.game.event.GameStartedListener;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements GameStartedListener, 
														  OnClickListener {
	
	private static final int N = 4;
	
	private Game game;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
        game.setGameStartedListener(this);
        
        Button b = (Button) findViewById(R.id.startNewGameButton);
        b.setOnClickListener(this);
        
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        Button item = (Button) findViewById(R.id.newGameItem);
        item.setOnClickListener(this);
        return true;
    }

	@Override
	public void onGameStarted(GameStartedEvent e) {
//		setContentView(R.layout.puzzle_container);
		Log.i("Info", "game started");
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.startNewGameButton || v.getId() == R.id.newGameItem)
		{
			Log.i("Info", "starting game");
			setContentView(R.layout.loading);
			game.startWithNewPuzzle(N);
		}
	}

	
}
