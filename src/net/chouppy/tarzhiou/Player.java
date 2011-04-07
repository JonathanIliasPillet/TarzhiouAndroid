package net.chouppy.tarzhiou;

import net.chouppy.tarzhiou.listeners.PlayerListener;

public class Player extends ReadOnlyPlayer {

	public Player (String this_name)
	{
		super (this_name);
	}
		
	public Piece new_piece ()
	{
		Piece result = new Piece (this);
		my_pieces.add (result);
		
		if (my_listener != null)
			my_listener.on_new_piece(this, result);
		
		return result;
	}
	
	public void loose_a_piece (Piece this_piece)
	{
		my_pieces.remove(this_piece);
		
		if (my_pieces.isEmpty())
			alive = false;
		
		if (my_listener != null)
			my_listener.on_loose_a_piece(this, this_piece);
	}
	
	/**
	 * Adds a piece to the player list.
	 * 
	 * @warning this piece's owner must be set to
	 * this player
	 * 
	 * @param this_piece the piece the player wins
	 */
	public void win_a_piece (Piece this_piece)
	{
		assert (this_piece.get_owner().equals(this_piece));
		
		my_pieces.add (this_piece);
		
		if (my_listener != null)
			my_listener.on_win_a_piece(this, this_piece);
	}
	
	/**
	 * This player retracts from game. Its pieces remains
	 * on cell space, but the player won't player anymore.
	 */
	public void retract ()
	{
		alive = false;
	}

	public void set_listener (PlayerListener this_listener)
	{
		my_listener = this_listener;
	}
}
