package net.chouppy.tarzhiou;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import net.chouppy.tarzhiou.exceptions.RuleViolationException;

public abstract class Square {
	protected Set<Square> neighbors;
	protected Set<Piece> pieces;
	protected SquareKey key;
	
	protected Square (SquareKey this_key)
	{
		neighbors = new LinkedHashSet<Square>();
		pieces = new LinkedHashSet<Piece>();
		key = (SquareKey)this_key.clone ();
	}
	
	public int get_pieces_count ()
	{
		return pieces.size();
	}
	
	public int get_capacity () {
		return neighbors.size();
	}
	
	/**
	 * Tells if this square is about to explode
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
	 * Player adds a piece is the square here
	 * 
	 * @param this_piece
	 * @throws RuleViolationException
	 */
	public void add_piece (Piece this_piece)
	{
		// The piece must be owned by a player
		assert (this_piece.get_owner() != null);
		
		// change all pieces owner
		for (Piece current_piece : pieces) {
			current_piece.change_owner(this_piece.get_owner());
		}
		// finally adds the piece to the square
		pieces.add(this_piece);
	}
	
	public void burst ()
	{
		// The square must be overloaded (otherwise, we have not
		// enough pieces to dispatch in neighbor squares
		assert (is_overloaded());
		
		Piece moved_piece;
		Iterator<Piece> i = pieces.iterator();
		for (Square current_neighbor : neighbors) {
			moved_piece = i.next ();
			i.remove();			
			current_neighbor.receive_piece(moved_piece);
		}
	}
	
	protected void receive_piece (Piece this_piece)
	{
		// change all pieces owner
		for (Piece current_piece : pieces ) {
			current_piece.change_owner(this_piece.get_owner ());
		}
		// finally adds the piece to the square
		pieces.add(this_piece);
	}
	
	public Set<Square> get_neighbors ()
	{
		return new LinkedHashSet<Square> (neighbors);
	}
	
	public int hashCode ()
	{
		return key.hashCode ();
	}
}
