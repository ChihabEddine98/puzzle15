package org.puzzle.game;

import java.util.Random;

public class PuzzleFactory {
	
	
	public static Puzzle create(int n){
		
		Puzzle p = new Puzzle(n);
		int tmp = p.getAbsolutePosition(n-1, n-1);
		p.getPuzzleComponents()[n-1][n-1] = new FreeSpace(tmp, tmp);
		
		Random r = new Random();
		
		int i , j;
		for (int k = 0; k< n*n; k++){
			do{
				i = r.nextInt(n);
				j = r.nextInt(n);		
			until(i!=n-1 && j!=n-1)
		}
	}

}
