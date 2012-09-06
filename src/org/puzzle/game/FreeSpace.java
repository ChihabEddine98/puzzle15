package org.puzzle.game;

/**
 * This class represents the free space of the puzzle game
 * @author Hannes Dorfmann
 *
 */
public class FreeSpace extends PuzzleComponent{

	public FreeSpace(int finalPosition, int currentPosition) {
		super(finalPosition, currentPosition);
		
	}

	public FreeSpace(FreeSpace f){
		this(f.getFinalPosition(), f.getCurrentPosition());
	}
}
