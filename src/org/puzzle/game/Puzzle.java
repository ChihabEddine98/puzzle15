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
	 * Get the component that is at the given absolute position
	 * @param absolutePosition The position starting by 0 going to N-1
	 * @return The {@link PuzzleComponent} at the given position
	 */
	public PuzzleComponent getComponentAt(int absolutePosition){
		Pair<Integer, Integer> pos = Util.getArrayCoordinate(absolutePosition, n);
		return pieces[pos.first][pos.second];
	}
	
	
	public void setComponentAt(int absolutePosition, PuzzleComponent c){
		Pair<Integer, Integer> pos = Util.getArrayCoordinate(absolutePosition, n);
		pieces[pos.first][pos.second] = c;
		c.setCurrentPosition(absolutePosition);
	}
	
	
	
	/**
	 * Checks, if a puzzle is solved
	 * @return
	 */
	public boolean isPuzzleSolved(){
		for (int i = 0; i<n; i++)
			for (int j = 0; j<n; j++)
				if (pieces[i][j].getCurrentPosition() != pieces[i][j].getFinalPosition())
					return false;
		
		
		return true;
	}
	
	

}
