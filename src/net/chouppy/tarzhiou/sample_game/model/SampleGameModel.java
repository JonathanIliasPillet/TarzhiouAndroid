package net.chouppy.tarzhiou.sample_game.model;

import java.util.Iterator;

import net.chouppy.tarzhiou.BuildableCellSpace;
import net.chouppy.tarzhiou.Game;
import net.chouppy.tarzhiou.LinkeableCell;
import net.chouppy.tarzhiou.NameCellKey;
import net.chouppy.tarzhiou.Player;
import net.chouppy.tarzhiou.ReadOnlyPiece;
import net.chouppy.tarzhiou.listeners.GameListener;
import net.chouppy.tarzhiou.listeners.PlayerListener;

public class SampleGameModel extends Game implements PlayerListener {
	protected Iterator<Player> player_turn;
	protected Player current_player;
	
	public SampleGameModel ()
	{
		super ();
		
		BuildableCellSpace sp = new BuildableCellSpace();
		cell_space = sp;
		
		LinkeableCell c1 = new LinkeableCell(new NameCellKey("center1"));
		LinkeableCell c2 = new LinkeableCell(new NameCellKey("center2"));
		LinkeableCell c3 = new LinkeableCell(new NameCellKey("center3"));
		LinkeableCell p11 = new LinkeableCell(new NameCellKey("peripheral11"));
		LinkeableCell p12 = new LinkeableCell(new NameCellKey("peripheral12"));
		LinkeableCell p21 = new LinkeableCell(new NameCellKey("peripheral21"));
		LinkeableCell p22 = new LinkeableCell(new NameCellKey("peripheral22"));
		LinkeableCell p31 = new LinkeableCell(new NameCellKey("peripheral31"));
		LinkeableCell p32 = new LinkeableCell(new NameCellKey("peripheral32"));
		
		sp.add_cell(c1);
		sp.add_cell(c2);
		sp.add_cell(c3);
		sp.add_cell(p11);
		sp.add_cell(p12);
		sp.add_cell(p21);
		sp.add_cell(p22);
		sp.add_cell(p31);
		sp.add_cell(p32);
		
		// Center triangle link
		c1.link_to(c2);
		c2.link_to(c3);
		c3.link_to(c1);
		// connect peripherals to centers
		p11.link_to(c1);
		p12.link_to(c1);
		p21.link_to(c2);
		p22.link_to(c2);
		p31.link_to(c3);
		p32.link_to(c3);
		// connect peripherals each other (hexagon)
		p11.link_to(p12);
		p12.link_to(p21);
		p21.link_to(p22);
		p22.link_to(p31);
		p31.link_to(p32);
		p32.link_to(p11);
		
		boolean validated = sp.validate();
		
		assert (validated);
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
