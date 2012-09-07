package org.puzzle.game.event;

import org.puzzle.game.Puzzle;


/**
 * This event is fired to inform that a puzzle (solveable or not) has been generated 
 * successfully and is now ready to be played.
 * @author Hannes Dorfmann
 *
 */
public class PuzzleGeneratedEvent {
	
	private Puzzle puzzle;
	
	public PuzzleGeneratedEvent(Puzzle puzzle){
		this.puzzle = puzzle;
	}
	
	public Puzzle getPuzzle()
	{
		return puzzle;
	}
}
