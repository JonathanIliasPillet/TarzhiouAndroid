package net.chouppy.tarzhiou.listeners;

import net.chouppy.tarzhiou.Player;
import net.chouppy.tarzhiou.ReadOnlyPiece;

public interface PlayerListener {
	public void on_new_piece (Player me, ReadOnlyPiece this_piece);
	public void on_win_a_piece (Player me, ReadOnlyPiece this_piece);
	public void on_loose_a_piece (Player me, ReadOnlyPiece this_piece);
}
