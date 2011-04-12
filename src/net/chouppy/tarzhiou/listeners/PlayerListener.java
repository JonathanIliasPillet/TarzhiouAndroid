package net.chouppy.tarzhiou.listeners;

import net.chouppy.tarzhiou.Player;
import net.chouppy.tarzhiou.Piece;

public interface PlayerListener {
	void onNewPiece (Player me, Piece thisPiece);
	void onWinAPiece (Player me, Piece thisPiece);
	void onLooseAPiece (Player me, Piece thisPiece);
}
