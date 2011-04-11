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
		c1.linkTo(c2);
		c2.linkTo(c3);
		c3.linkTo(c1);
		// connect peripherals to centers
		p11.linkTo(c1);
		p12.linkTo(c1);
		p21.linkTo(c2);
		p22.linkTo(c2);
		p31.linkTo(c3);
		p32.linkTo(c3);
		// connect peripherals each other (hexagon)
		p11.linkTo(p12);
		p12.linkTo(p21);
		p21.linkTo(p22);
		p22.linkTo(p31);
		p31.linkTo(p32);
		p32.linkTo(p11);
		
		assert (validate ());
	}
	
	public boolean doOneBurstIncrement() {
		return super.doOneBurstIncrement();
	}
	
	public void doAllBursts() {
		super.doAllBursts();
	}
	
	public void stopDoingAllBursts() {
		super.stopDoingAllBursts();
	}
}
