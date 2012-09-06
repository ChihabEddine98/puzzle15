package org.puzzle.game;

/**
 * This class represents a move.
 * This class is used by the solver, to get a solution for the current puzzle
 * @author Hannes Dorfmann
 *
 */
public class Move {

	
	public enum MoveDirection{
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	
	
	private int sourceIndex;
	private int destinationIndex;

	public Move(int sourceIndex, int destinationIndex) {
		super();
		this.sourceIndex = sourceIndex;
		this.destinationIndex = destinationIndex;
	}
	
	
	/**
	 * Get the start position in the puzzle
	 * @return
	 */
	public int getSourceIndex() {
		return sourceIndex;
	}
	
	
	/**
	 * Get the destination position
	 * @return
	 */
	public int getDestinationIndex() {
		return destinationIndex;
	}
	
	
}
