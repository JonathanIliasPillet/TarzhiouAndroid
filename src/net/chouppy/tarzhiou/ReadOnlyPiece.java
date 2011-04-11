package net.chouppy.tarzhiou;

import net.chouppy.tarzhiou.listeners.PieceListener;

public abstract class ReadOnlyPiece {
	protected Player owner;
	protected PieceListener my_listener;
	
	protected ReadOnlyPiece (Player this_player)
	{
		owner = this_player;
		my_listener = null;
	}
	
	public ReadOnlyPlayer get_owner ()
	{
		return owner;
	}

	
	public void setListener (PieceListener this_listener)
	{
		my_listener = this_listener;
	}
}
