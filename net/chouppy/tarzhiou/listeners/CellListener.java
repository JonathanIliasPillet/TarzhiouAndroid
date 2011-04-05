package net.chouppy.tarzhiou.listeners;

import net.chouppy.tarzhiou.Piece;
import net.chouppy.tarzhiou.Cell;

public interface CellListener {
	public void on_add_piece (Cell me, Piece this_piece);
	public void on_burst (Cell me);
}
