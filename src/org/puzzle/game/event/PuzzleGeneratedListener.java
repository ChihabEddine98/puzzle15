package org.puzzle.game.event;


/**
 * This is the listener for {@link PuzzleGeneratedEvent}s
 * @author Hannes Dorfmann
 *
 */
public interface PuzzleGeneratedListener {

	/**
	 * Called when the Game is generated and started successfully
	 * @param e {@link PuzzleGeneratedEvent}
	 */
	public void onPuzzleGenerated(PuzzleGeneratedEvent e);
}
