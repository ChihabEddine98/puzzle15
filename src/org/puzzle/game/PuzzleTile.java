package org.puzzle.game;

import org.puzzle.game.solver.PuzzleSolver;

/**
 * This class represents a puzzle piece
 * @author Hannes Dorfmann
 *
 */
public class PuzzleTile extends PuzzleComponent {
	
	
	
	public PuzzleTile(int finalPosition, int currentPosition ){
		super(finalPosition, currentPosition);
	}

	
	/**
	 * Copy constructor, may be useful for a {@link PuzzleSolver}
	 * @param p
	 */
	public PuzzleTile(PuzzleTile p){
		this(p.getFinalPosition(), p.getCurrentPosition());
	}
	
	
	/**
	 * Get the number of this puzzle tile. This is the number that is displayed
	 * on screen and is equal to {@link #getFinalPosition()}
	 * @return
	 */
	public int getTileNumber(){
		return getFinalPosition();
	}
		

}
