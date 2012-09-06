package org.puzzle.game;

import android.graphics.Bitmap;

/**
 * This class reprecents a puzzle piece
 * @author Hannes Dorfmann
 *
 */
public class PuzzleTile extends PuzzleComponent {
	
	/**
	 * The graphical representation of this puzzle piece
	 */
	private Bitmap bitmap;
	
	
	public PuzzleTile(int finalPosition, int currentPosition, Bitmap bitmap){
		super(finalPosition, currentPosition);
		this.bitmap = bitmap;
	}

	
	/**
	 * Copy constructor
	 * @param p
	 */
	public PuzzleTile(PuzzleTile p){
		this(p.getFinalPosition(), p.getCurrentPosition(), p.getBitmap());
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}

		

}
