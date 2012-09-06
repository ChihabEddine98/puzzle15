package org.puzzle.game.event;

/**
 * This event is fired to inform {@link PuzzleSolvedListener}s,
 * that a puzzle has been solved correctly
 * @author Hannes Dorfmann
 *
 */
public class PuzzleSolvedEvent {

	private long moves;
	private long elapsedTimeInSeconds;
	
	
	public PuzzleSolvedEvent(long seconds, long moves){
		this.moves = moves;
		this.elapsedTimeInSeconds = seconds;
	}

	/**
	 * Get the number of moves, that are made by the player to solve the puzzle
	 * @return The number of moves
	 */
	public long getMoves() {
		return moves;
	}


	/**
	 * Get the time that has been elapsed
	 * @return The elapsed time in seconds
	 */
	public long getElapsedTime() {
		return elapsedTimeInSeconds;
	}
}
