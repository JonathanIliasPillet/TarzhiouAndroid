package net.chouppy.tarzhiou;

public class BuildableSquareSpace extends DisorderedSquareSpace {
	
	public void add_square (Square this_square) {
		super.add_square(this_square);
	}

	public void link_squares (LinkeableSquare a, LinkeableSquare b) {
		super.link_squares(a, b);
	}
}
