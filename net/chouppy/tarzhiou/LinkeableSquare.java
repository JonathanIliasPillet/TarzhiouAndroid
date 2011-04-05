package net.chouppy.tarzhiou;

import net.chouppy.tarzhiou.listeners.LinkeableSquareListener;

public class LinkeableSquare extends Square {
	private LinkeableSquareListener my_listener;
	
	public LinkeableSquare (SquareKey this_key) {
		super (this_key);
		my_listener = null;
	}
	
	public void link_to (LinkeableSquare new_neighbor) {
		// link is bidirectional
		neighbors.add(new_neighbor);
		new_neighbor.neighbors.add (this);
		
		// calls listener if any
		if (my_listener != null)
			my_listener.on_link(this, new_neighbor);
	}
	
	public void set_linkeable_listener (LinkeableSquareListener this_listener) {
		my_listener = this_listener;
	}
}
