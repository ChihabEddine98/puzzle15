package org.puzzle.game;

import android.util.Pair;

/**
 * This class represents a Puzzle instance.
 * It manage the current position of the {@link PuzzleTile}s and
 * checks if a puzzle is solved correctly
 * @author Hannes Dorfmann
 *
 */
public class Puzzle {
	
	private PuzzleComponent pieces[][];
	private int n;
	
	
	/**
	 * Creates a n x n puzzle
	 * @param n
	 */
	public Puzzle(int n){
		this.n = n;
		pieces = new PuzzleComponent[n][n];
		
	}
	
	/**
	 * Copy constructor:
	 * makes a deep copy of the given {@link Puzzle}
	 * @param puzzle
	 */
	public Puzzle (Puzzle puzzle){
		
		this(puzzle.getN());
		
		for (int i = 0; i<puzzle.getN(); i++)
			for (int j = 0; j<puzzle.getN(); j++)
				if (puzzle.pieces[i][j] instanceof PuzzleTile)
					pieces[i][j] = new PuzzleTile((PuzzleTile) puzzle.pieces[i][j]);
				else
					pieces[i][j] = new FreeSpace((FreeSpace)puzzle.pieces[i][j]);
	}
	
	
	
	
	public int getN(){
		return n;
	}
	
	
	public PuzzleComponent[][] getPuzzleComponents(){
		return pieces;
	}
	
	
	/**
	 * Calculates the absolute position in the array (from 0 to N x N) of a {@link PuzzleComponent} 
	 * @param i The {@link #getPuzzleComponents()} Array position
	 * @param j The {@link #getPuzzleComponents()} Array position
	 * @return
	 */
	public int getAbsolutePosition(int i, int j){
		return i*n + j;
	}
	
	
	/**
	 * Calculate the array indexes of a given absolute position
	 * @param absolutePosition
	 * @return
	 */
	public Pair<Integer, Integer> getArrayCoordinate(int absolutePosition){
		return new Pair<Integer, Integer>(absolutePosition/n, absolutePosition % n);
	}
	
	

}
