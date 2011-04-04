import net.chouppy.tarzhiou.*;

public class Example {
	private DisorderedSquareSpace my_square_space;
	private Player[] players;
	
	public static void main (String [] args)
	{
		Example single = new Example ();
		
		single.start ();
	}
	
	public Example ()
	{
		BuildableSquareSpace square_space = new BuildableSquareSpace();
		my_square_space = square_space;
		
		LinkeableSquare a = new LinkeableSquare(new NameSquareKey("a"));
		LinkeableSquare b = new LinkeableSquare(new NameSquareKey("b"));
		LinkeableSquare c = new LinkeableSquare(new NameSquareKey("c"));
		LinkeableSquare d = new LinkeableSquare(new NameSquareKey("d"));
		LinkeableSquare e = new LinkeableSquare(new NameSquareKey("e"));
		LinkeableSquare f = new LinkeableSquare(new NameSquareKey("f"));
		LinkeableSquare g = new LinkeableSquare(new NameSquareKey("g"));
		LinkeableSquare h = new LinkeableSquare(new NameSquareKey("h"));
		LinkeableSquare i = new LinkeableSquare(new NameSquareKey("i"));
		
		square_space.add_square(a);
		square_space.add_square(b);
		square_space.add_square(c);
		square_space.add_square(d);
		square_space.add_square(e);
		square_space.add_square(f);
		square_space.add_square(g);
		square_space.add_square(h);
		square_space.add_square(i);
		
		a.link_to(b);
		b.link_to(c);
		c.link_to(a);
		d.link_to(a);
		e.link_to(a);
		f.link_to(b);
		g.link_to(b);
		h.link_to(c);
		i.link_to(c);
		d.link_to(e);
		e.link_to(f);
		f.link_to(g);
		g.link_to(h);
		h.link_to(i);
		i.link_to(d);
		
		players = new Player[2];
		for (int n = 0 ; n < 2 ; n++) {
			players[n] = new Player ("player"+n);
		}
	}
	
	public void start ()
	{
		int turn = 0;
		Square a = my_square_space.get_square_from_key(new NameSquareKey ("a"));
		Square b = my_square_space.get_square_from_key(new NameSquareKey ("b"));
		
		a.add_piece(players[0].new_piece());
		while (my_square_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_square_space);
		
		b.add_piece(players[1].new_piece());
		while (my_square_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_square_space);
		
		a.add_piece(players[0].new_piece());
		while (my_square_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_square_space);
		
		b.add_piece(players[1].new_piece());
		while (my_square_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_square_space);
		
		a.add_piece(players[0].new_piece());
		while (my_square_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_square_space);
		
		b.add_piece(players[1].new_piece());
		while (my_square_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_square_space);
		
		a.add_piece(players[0].new_piece());
		while (my_square_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_square_space);
		
		b.add_piece(players[1].new_piece());
		while (my_square_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_square_space);
	}
}
