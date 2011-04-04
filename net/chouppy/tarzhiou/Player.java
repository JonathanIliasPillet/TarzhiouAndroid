package net.chouppy.tarzhiou;

import java.util.LinkedList;
import java.util.List;

public class Player {
	protected String displayed_name;
	List<Piece> my_pieces;
	
	public Player (String this_name)
	{
		displayed_name = this_name;
		my_pieces = new LinkedList<Piece>();
	}
	
	public Piece new_piece ()
	{
		Piece result = new Piece (this);
		my_pieces.add (result);
		
		return result;
	}
	
	public void loose_piece (Piece this_piece)
	{
		my_pieces.remove(this_piece);
	}
	
	public String get_name ()
	{
		return displayed_name;
	}
	
	public String toString ()
	{
		return get_name();
	}
}
