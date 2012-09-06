package org.puzzle.game;

import java.util.Date;

import org.puzzle.game.event.PuzzleGeneratedListener;
import org.puzzle.game.event.PuzzleSolvedEvent;
import org.puzzle.game.event.PuzzleSolvedListener;
import org.puzzle.game.event.PuzzleTileMovedEvent;
import org.puzzle.game.event.PuzzleTileMovedListener;

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
	

	public Game(){
	}
	
	
	public void setPuzzleSolvedListener(PuzzleSolvedListener solvedListener) {
		this.solvedListener = solvedListener;
	}


	public void setPuzzleMovedListener(PuzzleTileMovedListener movedListener) {
		this.movedListener = movedListener;
	}


	public void setPuzzleGeneratedListener(PuzzleGeneratedListener generatedListener) {
		this.generatedListener = generatedListener;
	}
	
	public Puzzle getPuzzle(){
		return puzzle;
	}
	
	public void setPuzzle(Puzzle puzzle) {
		this.puzzle = puzzle;
	}
	
	public void start(){
		moveCount = 0;
		startTime = new Date().getTime();
		endTime = null;
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
		
		if (isValidMove(m))
			throw new InvalidMoveException();
		
		PuzzleComponent c = puzzle.getComponentAt(m.getSourcePosition());
		PuzzleComponent space = puzzle.getComponentAt(m.getTargetPosition());
		
		puzzle.setComponentAt(m.getTargetPosition(), c);
		puzzle.setComponentAt(m.getSourcePosition(), space);
		
		moveCount++;
		// Inform about the move
		movedListener.onPuzzleTileMoved(new PuzzleTileMovedEvent((PuzzleTile) c, moveCount));
		
		// Check if puzzle is solved
		if (puzzle.isPuzzleSolved()){
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

		// out of bounds check
		if (tp <0 || tp >=n)
			return false;
		
		PuzzleComponent source = puzzle.getComponentAt(m.getSourcePosition());
		PuzzleComponent target = puzzle.getComponentAt(m.getTargetPosition());
		
		
		if (!(source instanceof PuzzleTile) || !(target instanceof FreeSpace))
			return false;
		
		
		
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
	
	
	
	public int getMoveCount(){
		return moveCount;
	}
}
