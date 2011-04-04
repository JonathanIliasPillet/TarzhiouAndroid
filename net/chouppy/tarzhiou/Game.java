package net.chouppy.tarzhiou;

import java.util.Set;

public abstract class Game {
	protected SquareSpace square_space;
	protected Set<Player> players;
	protected Set<Piece> pieces;
	
	public SquareSpace get_square_space ()
	{
		return square_space;
	}
	
	public Set<Player> get_all_players ()
	{
		return players;
	}
	
	public Set<Piece> get_all_pieces ()
	{
		return pieces;
	}
}
