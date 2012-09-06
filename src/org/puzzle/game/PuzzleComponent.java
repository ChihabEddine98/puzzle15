package org.puzzle.game;

/**
 * This is the super class for a every puzzle component
 * @author Hannes Dorfmann
 *
 */
public class PuzzleComponent {
	
	/**
	 * The position beetween 0..N where this components belongs
	 */
	private int finalPosition;
	
	/**
	 * The current position
	 */
	private int currentPosition;
	
	public PuzzleComponent(int finalPosition, int currentPosition){
		this.finalPosition = finalPosition;
		this.currentPosition = currentPosition;
	}
	
	
	public int getFinalPosition(){
		return finalPosition;
	}
	
	public int getCurrentPosition(){
		return currentPosition;
	}
	
	
}
