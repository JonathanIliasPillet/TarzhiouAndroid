package net.chouppy.tarzhiou;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Game {
	protected RunnableSquareSpace square_space;
	protected Set<Player> players;
	protected Set<Piece> pieces;
	
	protected Game () {
		players = new LinkedHashSet<Player>();
		pieces = new LinkedHashSet<Piece>();
	}
	
	public ReadOnlySquareSpace get_square_space_view () {
		return square_space;
	}
	
	public boolean play (Square this_square, Player this_player) {
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
		return play (square_space.get_square_from_key(this_square), this_player);
	}
}
