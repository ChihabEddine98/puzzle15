package org.puzzle.game.event;

import org.puzzle.game.Puzzle;


/**
 * This event is fired to inform that a solvable puzzle has been generated 
 * successfully and is now ready to be played. The game has started.
 * @author Hannes Dorfmann
 *
 */
public class GameStartedEvent {
	
	private Puzzle puzzle;
	
	public GameStartedEvent(Puzzle puzzle){
		this.puzzle = puzzle;
	}
	
	public Puzzle getPuzzle()
	{
		return puzzle;
	}
}
