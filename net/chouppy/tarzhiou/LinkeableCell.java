package net.chouppy.tarzhiou;

import net.chouppy.tarzhiou.listeners.LinkeableCellListener;

public class LinkeableCell extends Cell {
	private LinkeableCellListener my_listener;
	
	public LinkeableCell (CellKey this_key) {
		super (this_key);
		my_listener = null;
	}
	
	public void link_to (LinkeableCell new_neighbor) {
		// link is bidirectional
		neighbors.add(new_neighbor);
		new_neighbor.neighbors.add (this);
		
		// calls listener if any
		if (my_listener != null)
			my_listener.on_link(this, new_neighbor);
	}
	
	public void set_linkeable_listener (LinkeableCellListener this_listener) {
		my_listener = this_listener;
	}
}
