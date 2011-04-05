package net.chouppy.tarzhiou.sample_game.model;

import java.util.Iterator;

import net.chouppy.tarzhiou.BuildableSquareSpace;
import net.chouppy.tarzhiou.Game;
import net.chouppy.tarzhiou.LinkeableSquare;
import net.chouppy.tarzhiou.NameSquareKey;
import net.chouppy.tarzhiou.Player;

public class SampleGameModel extends Game {
	protected BuildableSquareSpace square_space;
	protected Iterator<Player> player_turn;
	protected Player current_player;
	
	public SampleGameModel ()
	{
		super ();
		
		square_space = new BuildableSquareSpace();
		
		LinkeableSquare c1 = new LinkeableSquare(new NameSquareKey("center1"));
		LinkeableSquare c2 = new LinkeableSquare(new NameSquareKey("center2"));
		LinkeableSquare c3 = new LinkeableSquare(new NameSquareKey("center3"));
		LinkeableSquare p11 = new LinkeableSquare(new NameSquareKey("peripheral11"));
		LinkeableSquare p12 = new LinkeableSquare(new NameSquareKey("peripheral12"));
		LinkeableSquare p21 = new LinkeableSquare(new NameSquareKey("peripheral21"));
		LinkeableSquare p22 = new LinkeableSquare(new NameSquareKey("peripheral22"));
		LinkeableSquare p31 = new LinkeableSquare(new NameSquareKey("peripheral31"));
		LinkeableSquare p32 = new LinkeableSquare(new NameSquareKey("peripheral32"));
		
		square_space.add_square(c1);
		square_space.add_square(c2);
		square_space.add_square(c3);
		square_space.add_square(p11);
		square_space.add_square(p12);
		square_space.add_square(p21);
		square_space.add_square(p22);
		square_space.add_square(p31);
		square_space.add_square(p32);
		
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
		
		boolean validated = square_space.validate();
		
		assert (validated);
	}
	
	public void create_player (String name)
	{
		Player p = new Player (name);
		
		players.add(p);
	}

	@Override
	public Player get_current_player() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void next_player() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void select_first_player() {
		player_turn = players.iterator();
	}
}
