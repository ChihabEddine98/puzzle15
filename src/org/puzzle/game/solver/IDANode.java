package org.puzzle.game.solver;

import java.util.Comparator;

import org.puzzle.game.Puzzle;
import org.puzzle.game.PuzzleComponent;


/**
 * This {@link Comparator} compares {@link IDANode}s by the heurestic
 * @author Hannes Dorfmann
 *
 */
class AStarNodeComparator implements Comparator<IDANode>{

	@Override
	public int compare(IDANode lhs, IDANode rhs) {
		return ( lhs.getHeuristic() - rhs.getHeuristic() ) ;
	}
	
}


public class IDANode {

	private PuzzleComponent component;
	private Puzzle puzzle;
	
	public IDANode(Puzzle p, PuzzleComponent c)
	{
		this.component = c;
		this.puzzle = p;
	}
	
	
	public int getHeuristic(){
		return 0;
	}
	
}
