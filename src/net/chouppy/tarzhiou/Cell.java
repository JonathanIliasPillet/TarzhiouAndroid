package net.chouppy.tarzhiou;

import java.util.LinkedHashSet;
import java.util.Set;

import net.chouppy.tarzhiou.listeners.CellListener;

public abstract class Cell {
	protected Set<Cell> neighbors;
	protected Set<Piece> pieces;
	protected CellKey key;
	protected CellListener my_listener;
	
	protected Cell (CellKey this_key)
	{
		neighbors = new LinkedHashSet<Cell>();
		pieces = new LinkedHashSet<Piece>();
		key = (CellKey)this_key.clone ();
		my_listener = null;
	}
	
	public int get_pieces_count ()
	{
		return pieces.size();
	}
	
	public int get_capacity () {
		return neighbors.size();
	}
	
	public CellKey get_key ()
	{
		return (CellKey)key.clone ();
	}
	
	/**
	 * Tells if this cell is about to explode
	 */
	public boolean is_overloaded ()
	{
		if (pieces.size() >= neighbors.size())
			return true;
		else
			return false;
	}
	
	public Player get_pieces_owner ()
	{
		if (pieces.size() > 0)
			return (Player)pieces.iterator().next().get_owner();
		else
			return null;
	}
	
	public void set_listener (CellListener this_listener)
	{
		my_listener = this_listener;
	}
	
	
	
	public Set<Cell> get_neighbors ()
	{
		return new LinkedHashSet<Cell> (neighbors);
	}
	
	public int hashCode ()
	{
		return key.hashCode ();
	}
}
