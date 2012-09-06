package org.puzzle.game.event;

/**
 * The listener for {@link PuzzleSolvedEvent}s;
 * @author Hannes Dorfmann
 *
 */
public interface PuzzleSolvedListener {

	/**
	 * This method is called to dispatch a {@link PuzzleSolvedEvent}
	 * @param e
	 */
	public void onPuzzleSolved(PuzzleSolvedEvent e);
}
