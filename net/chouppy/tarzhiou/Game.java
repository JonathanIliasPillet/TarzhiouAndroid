package net.chouppy.tarzhiou;

import java.util.LinkedHashSet;
import java.util.Set;

import net.chouppy.tarzhiou.listeners.GameListener;

public abstract class Game {
	protected RunnableSquareSpace square_space = null;
	protected Set<Player> players;
	protected Set<Piece> pieces;
	private boolean started;
	private GameListener my_listener;
	
	protected Game () {
		players = new LinkedHashSet<Player>();
		pieces = new LinkedHashSet<Piece>();
		started = false;
		my_listener = null;
	}
	
	public ReadOnlySquareSpace get_square_space_view () {
		assert (square_space != null);
		return square_space;
	}
	
	public boolean play (Square this_square, Player this_player) {
		assert (square_space != null);
		assert (started);
		
		boolean result;
		
		if ((this_square.get_pieces_count() == 0) ||
				this_player.equals(this_square.get_pieces_owner())) 
		{
			// adds the new piece 
			this_square.add_piece(this_player.new_piece());
			
			// do bursts
			process_bursts ();
			
			// jump to next player
			next_player();
			
			result = true;
		}
		else
			result = false;
		
		return result;
	}
	
	public boolean play (SquareKey this_square_key, Player this_player) {
		assert (square_space != null);
		assert (started);
		Square this_square = square_space.get_square_from_key(this_square_key);
		
		if (this_square != null)
			return play (this_square, this_player);
		else
			return false;
	}
	
	protected abstract void select_first_player ();
	protected abstract void next_player ();
	public abstract Player get_current_player ();
	protected abstract void process_bursts ();
	
	protected void win (Player winner)
	{
		if (my_listener != null)
			my_listener.on_win (this, winner);
	}
	
	public void start ()
	{
		assert (players.size () > 1);
		select_first_player();
		started = true;
	}
	
	public void set_listener (GameListener this_listener)
	{
		my_listener = this_listener;
	}
}
