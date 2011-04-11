package net.chouppy.tarzhiou;

import net.chouppy.tarzhiou.listeners.LinkeableCellListener;

public class LinkeableCell extends PlayableCell {
	private LinkeableCellListener myListener;
	
	public LinkeableCell (CellKey this_key) {
		super (this_key);
		myListener = null;
	}
	
	public void linkTo (LinkeableCell newNeighbor) {
		// link is bidirectional
		addNeighbor(newNeighbor);
		newNeighbor.addNeighbor(this);
		
		// calls listener if any
		if (myListener != null)
			myListener.on_link(this, newNeighbor);
	}
	
	public void setLinkeableListener (LinkeableCellListener thisListener) {
		myListener = thisListener;
	}
}
