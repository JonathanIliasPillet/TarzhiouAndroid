package net.chouppy.tarzhiou;

public class LinkeableSquare extends Square {
	public LinkeableSquare (SquareKey this_key) {
		super (this_key);
	}
	
	public void link_to (Square new_neighbor) {
		// link is bidirectional
		neighbors.add(new_neighbor);
		new_neighbor.neighbors.add (this);
	}
}
