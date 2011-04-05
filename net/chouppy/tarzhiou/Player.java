package net.chouppy.tarzhiou;

import java.util.LinkedList;
import java.util.List;

import net.chouppy.tarzhiou.listeners.PlayerListener;

public class Player {
	protected String displayed_name;
	List<Piece> my_pieces;
	private PlayerListener my_listener;
	
	public Player (String this_name)
	{
		displayed_name = this_name;
		my_pieces = new LinkedList<Piece>();
		my_listener = null;
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
		
		if (my_listener != null)
			my_listener.on_loose_a_piece(this, this_piece);
	}
	
	public void win_a_piece (Piece this_piece)
	{
		my_pieces.add (this_piece);
		
		if (my_listener != null)
			my_listener.on_win_a_piece(this, this_piece);
	}
	
	public String get_name ()
	{
		return displayed_name;
	}
	
	public String toString ()
	{
		return get_name();
	}
	
	public boolean equals (Object o)
	{	
		return o == this;
	}
	
	public void set_listener (PlayerListener this_listener)
	{
		my_listener = this_listener;
	}
}
