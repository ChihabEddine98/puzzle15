package org.puzzle.game.event;

import org.puzzle.game.PuzzleTile;

/**
 * This Event is fired that a {@link PuzzleTile} has been moved (changed position)
 * @author Hannes Dorfmann
 *
 */
public class PuzzleTileMovedEvent {
	
	private PuzzleTile tile;
	private int moveCount;
	
	public PuzzleTileMovedEvent(PuzzleTile t, int moveCount){
		this.tile = t;
		this.moveCount = moveCount;
	}
	
	
	public PuzzleTile getPuzzleTile(){
		return tile;
	}
	
	
	public int getMoveCount(){
		return moveCount;
	}
	
}
