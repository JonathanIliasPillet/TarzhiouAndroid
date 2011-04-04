package net.chouppy.tarzhiou.listeners;

import net.chouppy.tarzhiou.Piece;
import net.chouppy.tarzhiou.Player;

public interface PieceListener {
	public void on_owner_changed (Piece me, Player previous_owner, Player new_owner);
}