import net.chouppy.tarzhiou.*;

public class Example {
	private DisorderedCellSpace my_cell_space;
	private Player[] players;
	
	public static void main (String [] args)
	{
		Example single = new Example ();
		
		single.start ();
	}
	
	public Example ()
	{
		BuildableCellSpace cell_space = new BuildableCellSpace();
		my_cell_space = cell_space;
		
		LinkeableCell a = new LinkeableCell(new NameCellKey("a"));
		LinkeableCell b = new LinkeableCell(new NameCellKey("b"));
		LinkeableCell c = new LinkeableCell(new NameCellKey("c"));
		LinkeableCell d = new LinkeableCell(new NameCellKey("d"));
		LinkeableCell e = new LinkeableCell(new NameCellKey("e"));
		LinkeableCell f = new LinkeableCell(new NameCellKey("f"));
		LinkeableCell g = new LinkeableCell(new NameCellKey("g"));
		LinkeableCell h = new LinkeableCell(new NameCellKey("h"));
		LinkeableCell i = new LinkeableCell(new NameCellKey("i"));
		
		cell_space.add_cell(a);
		cell_space.add_cell(b);
		cell_space.add_cell(c);
		cell_space.add_cell(d);
		cell_space.add_cell(e);
		cell_space.add_cell(f);
		cell_space.add_cell(g);
		cell_space.add_cell(h);
		cell_space.add_cell(i);
		
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
		Cell a = my_cell_space.get_cell_from_key(new NameCellKey ("a"));
		Cell b = my_cell_space.get_cell_from_key(new NameCellKey ("b"));
		
		a.add_piece(players[0].new_piece());
		while (my_cell_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_cell_space);
		
		b.add_piece(players[1].new_piece());
		while (my_cell_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_cell_space);
		
		a.add_piece(players[0].new_piece());
		while (my_cell_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_cell_space);
		
		b.add_piece(players[1].new_piece());
		while (my_cell_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_cell_space);
		
		a.add_piece(players[0].new_piece());
		while (my_cell_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_cell_space);
		
		b.add_piece(players[1].new_piece());
		while (my_cell_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_cell_space);
		
		a.add_piece(players[0].new_piece());
		while (my_cell_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_cell_space);
		
		b.add_piece(players[1].new_piece());
		while (my_cell_space.do_one_burst_increment());
		System.out.println (turn++);
		System.out.println (my_cell_space);
	}
}
