package org.puzzle.game.event;


/**
 * This is the listener for {@link PuzzleGeneratedEvent}s
 * @author Hannes Dorfmann
 *
 */
public interface PuzzleGeneratedListener {

	public void onPuzzleGeneratedSuccessfully(PuzzleGeneratedEvent e);
}
