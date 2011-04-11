package net.chouppy.tarzhiou.listeners;

import net.chouppy.tarzhiou.Player;
import net.chouppy.tarzhiou.Piece;

public interface PlayerListener {
	public void onNewPiece (Player me, Piece this_piece);
	public void onWinAPiece (Player me, Piece this_piece);
	public void onLooseAPiece (Player me, Piece this_piece);
}
