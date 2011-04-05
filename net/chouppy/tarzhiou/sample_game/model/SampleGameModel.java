package net.chouppy.tarzhiou.sample_game.model;

import java.util.Iterator;

import net.chouppy.tarzhiou.BuildableSquareSpace;
import net.chouppy.tarzhiou.Game;
import net.chouppy.tarzhiou.LinkeableSquare;
import net.chouppy.tarzhiou.NameSquareKey;
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
		
		BuildableSquareSpace sp = new BuildableSquareSpace();
		square_space = sp;
		
		LinkeableSquare c1 = new LinkeableSquare(new NameSquareKey("center1"));
		LinkeableSquare c2 = new LinkeableSquare(new NameSquareKey("center2"));
		LinkeableSquare c3 = new LinkeableSquare(new NameSquareKey("center3"));
		LinkeableSquare p11 = new LinkeableSquare(new NameSquareKey("peripheral11"));
		LinkeableSquare p12 = new LinkeableSquare(new NameSquareKey("peripheral12"));
		LinkeableSquare p21 = new LinkeableSquare(new NameSquareKey("peripheral21"));
		LinkeableSquare p22 = new LinkeableSquare(new NameSquareKey("peripheral22"));
		LinkeableSquare p31 = new LinkeableSquare(new NameSquareKey("peripheral31"));
		LinkeableSquare p32 = new LinkeableSquare(new NameSquareKey("peripheral32"));
		
		sp.add_square(c1);
		sp.add_square(c2);
		sp.add_square(c3);
		sp.add_square(p11);
		sp.add_square(p12);
		sp.add_square(p21);
		sp.add_square(p22);
		sp.add_square(p31);
		sp.add_square(p32);
		
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
		square_space.do_all_bursts();
	}

	@Override
	public void on_loose_a_piece(Player me, ReadOnlyPiece thisPiece) {
		if (!me.is_alive())
		{
			System.out.println (me.toString() + " died");
	
			if (count_alive_players () == 1)
			{
				square_space.stop_doing_all_bursts();
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
