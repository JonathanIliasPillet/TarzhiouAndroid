package net.chouppy.tarzhiou.listeners;

import net.chouppy.tarzhiou.Game;
import net.chouppy.tarzhiou.Player;

public interface GameListener {
	public void on_win (Game this_game, Player winner);
}
