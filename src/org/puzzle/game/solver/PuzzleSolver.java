package org.puzzle.game.solver;

import java.util.List;

import org.puzzle.game.Move;
import org.puzzle.game.Puzzle;

public interface PuzzleSolver {

	/**
	 * Tries to found a solution for the given puzzle
	 * @param p The Puzzle, that should be solved
	 * @return A {@link List} with {@link Move}s (the solution) or null, 
	 * if no solution for the given puzzle can be found (or not exists).
	 */
	public List<Move> solve(Puzzle p);

}
