package net.chouppy.tarzhiou.sample_game.model;

import java.util.Iterator;

import net.chouppy.tarzhiou.Game;
import net.chouppy.tarzhiou.Player;
import net.chouppy.tarzhiou.ReadOnlyPiece;
import net.chouppy.tarzhiou.listeners.PlayerListener;

public class SampleGameModel extends Game implements PlayerListener {
	protected Iterator<Player> player_turn;
	protected Player current_player;
	
	public SampleGameModel ()
	{
		super ();
		
		cell_space = new SampleCellSpace();
	}
	
	public void create_player (String name)
	{
		Player p = new Player (name);
		
		players.add(p);
	}

	@Override
	public Player get_current_player() {
		return current_player;
	}

	@Override
	protected void next_player() {
		if (player_turn.hasNext())
		{
			current_player = player_turn.next ();
			if (!current_player.is_alive())
				next_player();
			assert (current_player != null);
		}
		else
			select_first_player();
	}

	@Override
	protected void select_first_player() {
		player_turn = players.iterator();
		current_player = player_turn.next();
		if (!current_player.is_alive())
			next_player();
		assert (current_player != null);
	}

	@Override
	protected void process_bursts() {
		cell_space.do_all_bursts();
	}

	@Override
	public void on_loose_a_piece(Player me, ReadOnlyPiece thisPiece) {
		if (!me.is_alive())
		{
			System.out.println (me.toString() + " died");
	
			if (count_alive_players () == 1)
			{
				cell_space.stop_doing_all_bursts();
				win (get_an_alive_player());
			}
		}
	}

	private int count_alive_players() {
		int result = 0;
		
		for (Player current_player : players) 
		{
			if (current_player.is_alive())
				result++;
		}
		
		return result;
	}
	
	private Player get_an_alive_player ()
	{
		Iterator<Player> i = players.iterator();
		Player result = null;
		
		while (i.hasNext() && (result == null))
		{
			Player current = i.next();
			if (current.is_alive())
				result = current;
		}
		
		assert (result != null);
		
		return result;
	}

	@Override
	public void on_new_piece(Player me, ReadOnlyPiece thisPiece) {
		
	}

	@Override
	public void on_win_a_piece(Player me, ReadOnlyPiece thisPiece) {
		
	}
}
