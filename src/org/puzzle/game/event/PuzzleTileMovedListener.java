package org.puzzle.game.event;

/**
 * This is the handler for {@link PuzzleTileMovedEvent}s
 * @author Hannes Dorfmann
 *
 */
public interface PuzzleTileMovedListener {

	public void onPuzzleTileMoved(PuzzleTileMovedEvent e);
}
