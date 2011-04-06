package net.chouppy.tarzhiou.listeners;

import net.chouppy.tarzhiou.Player;
import net.chouppy.tarzhiou.ReadOnlyPiece;

public interface PieceListener {
	public void on_owner_changed (ReadOnlyPiece me, Player previous_owner, Player new_owner);
}