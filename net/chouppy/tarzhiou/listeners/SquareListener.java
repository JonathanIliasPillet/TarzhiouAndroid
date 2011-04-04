package net.chouppy.tarzhiou.listeners;

import net.chouppy.tarzhiou.Piece;
import net.chouppy.tarzhiou.Square;

public interface SquareListener {
	public void on_add_piece (Square me, Piece this_piece);
	public void on_burst (Square me);
}
