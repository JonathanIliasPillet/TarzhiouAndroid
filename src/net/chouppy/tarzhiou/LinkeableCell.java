package net.chouppy.tarzhiou;

import java.util.LinkedList;

import net.chouppy.tarzhiou.listeners.LinkeableCellListener;

public class LinkeableCell extends PlayableCell {
	private LinkedList<LinkeableCellListener> myListeners;
	
	public LinkeableCell (CellKey thisKey) {
		super (thisKey);
		myListeners = new LinkedList<LinkeableCellListener>();
	}
	
	public void linkTo (LinkeableCell newNeighbor) {
		// link is bidirectional
		addNeighbor(newNeighbor);
		newNeighbor.addNeighbor(this);
		
		// calls listener if any
		for (LinkeableCellListener listener : myListeners)
		  listener.onLink(this, newNeighbor);
	}
	
	public void setLinkeableListener (LinkeableCellListener thisListener) {
		myListeners.add (thisListener);
	}
}
