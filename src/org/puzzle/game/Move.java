package org.puzzle.game;

/**
 * This class represents a move.
 * This class is used by the solver, to get a solution for the current puzzle
 * @author Hannes Dorfmann
 *
 */
public class Move {

	private int sourcePosition;
	private int targetPosition;

	public Move(int sourcePosition, int targetPosition) {
		super();
		this.sourcePosition = sourcePosition;
		this.targetPosition = targetPosition;
	}
	
	
	/**
	 * Get the start position in the puzzle
	 * @return
	 */
	public int getSourcePosition() {
		return sourcePosition;
	}
	
	
	/**
	 * Get the destination position
	 * @return
	 */
	public int getTargetPosition() {
		return targetPosition;
	}
	
	
}
