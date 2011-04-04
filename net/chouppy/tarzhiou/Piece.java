package net.chouppy.tarzhiou;

import net.chouppy.tarzhiou.listeners.PieceListener;

public class Piece {
	protected Player owner;
	private PieceListener my_listener;
	
	public Piece (Player this_player)
	{
		owner = this_player;
		my_listener = null;
	}

	public void change_owner (Player this_player)
	{
		Player old_owner = owner;
		old_owner.loose_piece (this);
		owner = this_player;
		
		if (my_listener != null)
			my_listener.on_owner_changed(this, old_owner, owner);
	}

	public Player get_owner ()
	{
		return owner;
	}
	
	public void setListener (PieceListener this_listener)
	{
		my_listener = this_listener;
	}
}
