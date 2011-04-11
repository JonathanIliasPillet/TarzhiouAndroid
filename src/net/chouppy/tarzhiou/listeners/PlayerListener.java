package net.chouppy.tarzhiou.listeners;

import net.chouppy.tarzhiou.Player;
import net.chouppy.tarzhiou.ReadOnlyPiece;

public interface PlayerListener {
	public void onNewPiece (Player me, ReadOnlyPiece this_piece);
	public void onWinAPiece (Player me, ReadOnlyPiece this_piece);
	public void onLooseAPiece (Player me, ReadOnlyPiece this_piece);
}
