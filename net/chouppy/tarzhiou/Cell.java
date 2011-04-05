package net.chouppy.tarzhiou;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import net.chouppy.tarzhiou.listeners.CellListener;

public abstract class Cell {
	protected Set<Cell> neighbors;
	protected Set<Piece> pieces;
	protected CellKey key;
	private CellListener my_listener;
	
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
			return pieces.iterator().next().get_owner();
		else
			return null;
	}
	
	/**
	 * Player adds a piece is the cell here
	 * 
	 * @param this_piece
	 */
	public void add_piece (Piece this_piece)
	{
		// The piece must be owned by a player
		assert (this_piece.get_owner() != null);
		
		// The cell pieces must all be owned
		// by the same player
		if (!pieces.isEmpty())
			assert (pieces.iterator().next().get_owner().equals(this_piece.get_owner()));

		// finally adds the piece to the cell
		pieces.add(this_piece);
		
		// calls listener if exists
		if (my_listener != null)
			my_listener.on_add_piece(this, this_piece);
	}
	
	public void burst ()
	{
		// The cell must be overloaded (otherwise, we have not
		// enough pieces to dispatch in neighbor cells
		assert (is_overloaded());
		
		Piece moved_piece;
		Iterator<Piece> i = pieces.iterator();
		for (Cell current_neighbor : neighbors) {
			moved_piece = i.next ();
			i.remove();			
			current_neighbor.receive_piece(moved_piece);
		}
		
		// call listener if any
		if (my_listener != null)
			my_listener.on_burst(this);
	}
	
	public void set_listener (CellListener this_listener)
	{
		my_listener = this_listener;
	}
	
	protected void receive_piece (Piece this_piece)
	{
		// change all pieces owner
		for (Piece current_piece : pieces ) {
			current_piece.change_owner(this_piece.get_owner ());
		}
		// finally adds the piece to the cell
		pieces.add(this_piece);
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
