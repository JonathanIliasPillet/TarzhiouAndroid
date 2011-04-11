package net.chouppy.tarzhiou.sample_game.model;

import net.chouppy.tarzhiou.DisorderedCellSpace;
import net.chouppy.tarzhiou.LinkeableCell;
import net.chouppy.tarzhiou.NameCellKey;

public class SampleCellSpace extends DisorderedCellSpace {

	public SampleCellSpace ()
	{
		LinkeableCell c1 = new LinkeableCell(new NameCellKey("c1"));
		LinkeableCell c2 = new LinkeableCell(new NameCellKey("c2"));
		LinkeableCell c3 = new LinkeableCell(new NameCellKey("c3"));
		LinkeableCell p11 = new LinkeableCell(new NameCellKey("p11"));
		LinkeableCell p12 = new LinkeableCell(new NameCellKey("p12"));
		LinkeableCell p21 = new LinkeableCell(new NameCellKey("p21"));
		LinkeableCell p22 = new LinkeableCell(new NameCellKey("p22"));
		LinkeableCell p31 = new LinkeableCell(new NameCellKey("p31"));
		LinkeableCell p32 = new LinkeableCell(new NameCellKey("p32"));
		
		add_cell(c1);
		add_cell(c2);
		add_cell(c3);
		add_cell(p11);
		add_cell(p12);
		add_cell(p21);
		add_cell(p22);
		add_cell(p31);
		add_cell(p32);
		
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
		
		assert (validate ());
	}
	
	public boolean do_one_burst_increment() {
		return super.do_one_burst_increment();
	}
	
	public void do_all_bursts() {
		super.do_all_bursts();
	}
	
	public void stop_doing_all_bursts() {
		super.stop_doing_all_bursts();
	}
}
