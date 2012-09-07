package org.puzzle.game;

/**
 * This class represents a move.
 * "move" means to move a puzzle tile from one position to another
 * @author Hannes Dorfmann
 *
 */
public class Move {

	/**
	 * The position where the puzzle tile is before the movement
	 */
	private int sourcePosition;
	
	/**
	 * The position where the puzzle tile is after the movement
	 */
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
