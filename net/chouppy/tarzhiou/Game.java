package net.chouppy.tarzhiou;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Game {
	protected RunnableSquareSpace square_space = null;
	protected Set<Player> players;
	protected Set<Piece> pieces; 
	
	protected Game () {
		players = new LinkedHashSet<Player>();
		pieces = new LinkedHashSet<Piece>();
	}
	
	public ReadOnlySquareSpace get_square_space_view () {
		assert (square_space != null);
		return square_space;
	}
	
	public boolean play (Square this_square, Player this_player) {
		assert (square_space != null);
		
		boolean result;
		
		if (this_square.get_pieces_owner().equals(this_player)) {
			this_square.add_piece(this_player.new_piece());
			
			result = true;
		}
		else
			result = false;
		
		return result;
	}
	
	public boolean play (SquareKey this_square, Player this_player) {
		assert (square_space != null);
		return play (square_space.get_square_from_key(this_square), this_player);
	}
	
	protected abstract void select_first_player ();
	protected abstract void next_player ();
	public abstract Player get_current_player ();	
	
	public void start ()
	{
		assert (players.size () > 1);
		select_first_player();
	}
}
