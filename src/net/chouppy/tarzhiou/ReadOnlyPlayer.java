package net.chouppy.tarzhiou;

import java.util.LinkedList;
import java.util.List;

import net.chouppy.tarzhiou.listeners.PlayerListener;

public abstract class ReadOnlyPlayer {
	protected String displayed_name;
	List<Piece> my_pieces;
	protected PlayerListener my_listener;
	protected boolean alive;
	
	protected ReadOnlyPlayer (String this_name) 
	{
		displayed_name = this_name;
		my_pieces = new LinkedList<Piece>();
		my_listener = null;
		alive = true;
	}
	
	public boolean is_alive ()
	{
		return alive;
	}
	
	public String get_name ()
	{
		return displayed_name;
	}
	
	public int get_pieces_count ()
	{
		return my_pieces.size();
	}
	
	public String toString ()
	{
		return get_name();
	}
	
	public boolean equals (Object o)
	{	
		return o == this;
	}
}
