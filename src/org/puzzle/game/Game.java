package org.puzzle.game;

import java.util.Date;

import org.puzzle.game.event.PuzzleGeneratedEvent;
import org.puzzle.game.event.PuzzleGeneratedListener;
import org.puzzle.game.event.PuzzleSolvedEvent;
import org.puzzle.game.event.PuzzleSolvedListener;
import org.puzzle.game.event.PuzzleTileMovedEvent;
import org.puzzle.game.event.PuzzleTileMovedListener;

import android.util.Log;

/**
 * This class represents a game instance and holds the puzzle,
 * the moves and the timer
 * @author Hannes Dorfmann
 *
 */
public class Game {

	private Puzzle puzzle;
	private int moveCount;
	/**
	 * In a real application another solution must be found to avoid cheating,
	 * by changing the systems clock time
	 */
	private long startTime;
	private Long endTime;
	
	private PuzzleSolvedListener solvedListener;
	private PuzzleTileMovedListener movedListener;
	private PuzzleGeneratedListener generatedListener;
	
	private Thread generatorThread;

	public Game(){
	}
	
	
	public void setPuzzleSolvedListener(PuzzleSolvedListener solvedListener) {
		this.solvedListener = solvedListener;
	}


	public void setPuzzleMovedListener(PuzzleTileMovedListener movedListener) {
		this.movedListener = movedListener;
	}


	public void setPuzzleGeneratedListener(PuzzleGeneratedListener listener) {
		this.generatedListener = listener;
	}
	
	public Puzzle getPuzzle(){
		return puzzle;
	}
	
	public void setPuzzle(Puzzle puzzle) {
		this.puzzle = puzzle;
	}
	
	
	
	/**
	 * Start a new Puzzle game with a new generated and solvable puzzle
	 */
	public void startWithNewPuzzle(final int n){
		
		// stop previous running threads
		if (generatorThread!= null && generatorThread.isAlive())
			generatorThread.interrupt();
		
		generatorThread = new Thread(){
			
			public void run(){
				puzzle = PuzzleFactory.create(n);
				moveCount = 0;
				startTime = new Date().getTime();
				endTime = null;
				generatedListener.onPuzzleGenerated(new PuzzleGeneratedEvent(puzzle));
			}
		};
		
		generatorThread.start();
	}
	
	
	public void stop(){
		endTime = new Date().getTime();
	}
	
	/**
	 * Get the elapsed time (in seconds)
	 * or if the game is stopped the elapsed time to solve the puzzle
	 * @return
	 */
	public long getElapsedTime(){
		if (endTime == null)
			return (new Date().getTime() - startTime ) / 1000;
		
		else
			return endTime - startTime / 1000;
		
	}
	
	
	/**
	 * This method execute a move
	 * @param m The {@link Move} object that represents a players move
	 * @throws InvalidMoveException If the move is invalid
	 */
	public void executeMove(Move m) throws InvalidMoveException {
		
		boolean valid = isValidMove(m);
		
		if (valid)
			throw new InvalidMoveException();
		
		PuzzleComponent c = puzzle.getComponentAt(m.getSourcePosition());
		PuzzleComponent space = puzzle.getComponentAt(m.getTargetPosition());
		
		puzzle.setComponentAt(m.getTargetPosition(), c);
		puzzle.setComponentAt(m.getSourcePosition(), space);
		
		moveCount++;
		// Inform about the move
		movedListener.onPuzzleTileMoved(new PuzzleTileMovedEvent((PuzzleTile) c, moveCount));
		
		// Check if puzzle is solved
		if (puzzle.isSolved()){
			stop();
			solvedListener.onPuzzleSolved(new PuzzleSolvedEvent(getElapsedTime(), moveCount));
		}
	}
	
	
	/**
	 * Checkes if a move is valid
	 * @param m {@link Move}
	 * @return true or false
	 */
	private boolean isValidMove(Move m){
		
		int n = puzzle.getN();
		int sp = m.getSourcePosition();
		int tp = m.getTargetPosition();

		Log.i("valid", sp+"|"+tp);
		
		// out of bounds check
		if (tp <0 || tp >=n || sp<0 || sp>=n)
			return false;
		
		PuzzleComponent source = puzzle.getComponentAt(m.getSourcePosition());
		PuzzleComponent target = puzzle.getComponentAt(m.getTargetPosition());
		
		Log.i("valid", "before instance check passed");
		
		
		if (!(source instanceof PuzzleTile) || !(target instanceof FreeSpace))
			return false;
		
		Log.i("valid", "instance check passed");
		
		
		
		// check if source tile is on the left edge
		if (m.getSourcePosition() % n == 0){
			
			if (!(tp == sp+1 || tp == sp -n || tp == sp+n))
				return false;
			
		}
		else // source tile is in on the right edge
		if (m.getSourcePosition() % n == n - 1){
			
			if (!(tp == sp-1 || tp == sp -n || tp == sp+n))
				return false;
			
		}
		else{ // source tile is somewhere in the middle
			
			if (!(tp == sp-n || tp == sp + n || tp == sp +1 || tp == sp-1))
				return false;
		}
		
		
		return true;
	}
	
	
	/**
	 * Get the current number of moves, that were already performed
	 * @return
	 */
	public int getMoveCount(){
		return moveCount;
	}
}
