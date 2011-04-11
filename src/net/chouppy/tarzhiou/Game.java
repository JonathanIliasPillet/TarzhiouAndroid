package net.chouppy.tarzhiou;

import java.util.LinkedHashSet;
import java.util.Set;

import net.chouppy.tarzhiou.listeners.GameListener;

public abstract class Game {
	protected PlayableCellSpace cell_space = null;
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
	
	public ReadOnlyCellSpace get_cell_space_view () {
		assert (cell_space != null);
		return cell_space;
	}
	
	public boolean play (Cell this_cell, Player this_player) {
		assert (cell_space != null);
		assert (started);
		
		boolean result;
		
		if ((this_cell.getPiecesCount() == 0) ||
				this_player.equals(this_cell.getPiecesOwner())) 
		{
			// adds the new piece 
			assert (this_cell instanceof PlayableCell);
			((PlayableCell)this_cell).addPiece(this_player.new_piece());
			
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
	
	public boolean play (CellKey this_cell_key, Player this_player) {
		assert (cell_space != null);
		assert (started);
		Cell this_cell = cell_space.get_cell_from_key(this_cell_key);
		
		if (this_cell != null)
			return play (this_cell, this_player);
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
