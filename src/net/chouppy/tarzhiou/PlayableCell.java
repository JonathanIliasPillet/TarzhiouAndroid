package net.chouppy.tarzhiou;

import java.util.Iterator;

public class PlayableCell extends Cell
{
	public PlayableCell (CellKey this_key)
	{
		super (this_key);
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
			assert (current_neighbor instanceof PlayableCell);
			((PlayableCell)current_neighbor).receive_piece(moved_piece);
		}
		
		// call listener if any
		if (my_listener != null)
			my_listener.on_burst(this);
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
}
