package net.chouppy.tarzhiou.listeners;

import net.chouppy.tarzhiou.Game;
import net.chouppy.tarzhiou.Player;

public interface GameListener
{
  void onWin(Game thisGame, Player winner);
}
