package org.puzzle.game.event;


/**
 * This is the listener for {@link GameStartedEvent}s
 * @author Hannes Dorfmann
 *
 */
public interface GameStartedListener {

	/**
	 * Called when the Game is generated and started successfully
	 * @param e {@link GameStartedEvent}
	 */
	public void onGameStarted(GameStartedEvent e);
}
