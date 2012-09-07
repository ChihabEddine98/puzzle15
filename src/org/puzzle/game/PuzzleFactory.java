package org.puzzle.game;

import java.util.Random;

import android.util.Log;
import android.util.Pair;

/**
 * This static factory creates solvable {@link Puzzle}s by calling 
 * {@link #create(int)}
 * @author Hannes Dorfmann
 *
 */
public class PuzzleFactory {
	
	
	/**
	 * Create a new Puzzle that can be solved
	 * @param n The size parameter (creates a NxN Puzzle)
	 * @return A solvable {@link Puzzle}
	 */
	public static Puzzle create(int n){
		
		Puzzle p = new Puzzle(n);
		int tmp = Util.getAbsolutePosition(n-1, n-1, n);
		
		
		
		PuzzleComponent[][] c = p.getPuzzleComponents();
		
		for (int i = 0; i<n; i++)
			for (int j = 0; j<n; j++){
				int pos = Util.getAbsolutePosition(i, j, n);
					c[i][j] = new PuzzleTile(pos, pos);
			}
		
		// Place the FreeSpace in the buttom-right corner
		p.getPuzzleComponents()[n-1][n-1] = new FreeSpace(tmp, tmp);
				
		
		Random r = new Random();
		
		// shuffle
		for (int i =0; i<n*n-2; i++)
		{
			int posToSwap = r.nextInt(n * n - 2 - i ) + i;
			Log.i("val", i+" "+" swap "+posToSwap);
			swapComponents(i, posToSwap, n, c);
		}
	
		
		p.setSolvable(isSolvable(p));
		
		return p;
		
	}
	

	

	/**
	 * Swaps the positon of two {@link PuzzleComponent}s
	 * @param pos
	 * @param posToSwap
	 * @param n
	 * @param c
	 */
	private static void swapComponents(int pos, int posToSwap, int n,
			PuzzleComponent[][] c) {
		
		Pair<Integer, Integer> org = Util.getArrayCoordinate(pos, n);
		Pair<Integer, Integer> swap = Util.getArrayCoordinate(posToSwap, n);
		
		PuzzleComponent c1 = c[org.first][org.second];
		PuzzleComponent c2 = c[swap.first][swap.second];
		
		c1.setCurrentPosition(posToSwap);
		c2.setCurrentPosition(pos);
		
		c[org.first][org.second] = c2;
		c[swap.first][swap.second] = c1;
		
	}
	
	
	/**
	 * Determines if the given puzzle can be solved,
	 * by counting the permutation inversions.
	 * If the count of permutation inversions is odd, then the 
	 * puzzle can be solved, otherwise it can't be solved.
	 * For more details see http://mathworld.wolfram.com/15Puzzle.html
	 * @param p The {@link Puzzle}
	 * @return
	 */
	public static boolean isSolvable(Puzzle p){
		
		int sum = 0;
		int n = p.getN();
		
		for (int i =1; i<n*n-1; i++)
		{
			PuzzleComponent tile = p.getComponentAt(i);		
			for (int j = i; j>=0; j--){
				PuzzleComponent c = p.getComponentAt(j);
				
				if (c.getFinalPosition()>tile.getFinalPosition())
					sum++;
			}
		}
		
		return sum % 2 == 0;
	}

}
