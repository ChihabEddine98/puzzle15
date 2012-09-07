package org.puzzle.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Pair;

public class Util {

	
	/**
	 * Calculates the absolute position in the array (from 0 to N x N) of a {@link PuzzleComponent} 
	 * @param i The {@link #getPuzzleComponents()} Array position
	 * @param j The {@link #getPuzzleComponents()} Array position
	 * @param n 
	 * @return
	 */
	public static int getAbsolutePosition(int i, int j, int n){
		return i*n + j;
	}
	
	
	/**
	 * Calculate the array indexes of a given absolute position
	 * @param absolutePosition
	 * @param n
	 * @return
	 */
	public static Pair<Integer, Integer> getArrayCoordinate(int absolutePosition, int n){
		return new Pair<Integer, Integer>(absolutePosition/n, absolutePosition % n);
	}
	
	
	/**
	 * Loads a bitmap and scales it to the required width and height
	 * @param res
	 * @param resId
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static Bitmap loadBitmap(Resources res, int resId,
	        int reqWidth, int reqHeight) {
		
			Bitmap bitmap = BitmapFactory.decodeResource(res, resId);
			bitmap = Bitmap.createScaledBitmap(bitmap, reqWidth, reqHeight, true);
			
			
			return bitmap;

	}
	
}
