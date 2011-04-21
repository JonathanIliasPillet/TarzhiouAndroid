package net.chouppy.tarzhiou.android.game_model;

import net.chouppy.tarzhiou.Game;
import net.chouppy.tarzhiou.Piece;
import net.chouppy.tarzhiou.Player;
import net.chouppy.tarzhiou.listeners.PlayerListener;

public class AndroidTarzhiouGame extends Game implements PlayerListener
{
  public AndroidTarzhiouGame()
  {
    super();

    setCellSpace(new AndroidCellSpace());
  }

  public void createPlayer(String name)
  {
    Player p = new Player(name);

    addPlayer(p);
    p.addListener(this);
  }

  public void start()
  {
    super.start();
  }

  public void onLooseAPiece(Player me, Piece thisPiece)
  {
    assert me != null : "me cannot be null";

    if (!me.isAlive())
    {
      System.out.println(me.toString() + " died"); // FIXME: remove this

      if (countAlivePlayers() == 1)
      {
        getCellSpace().stopDoingAllBursts();
        win(getAnAlivePlayer());
      }
    }
  }

  public void onNewPiece(Player me, Piece thisPiece)
  {
  }

  public void onWinAPiece(Player me, Piece thisPiece)
  {
  }

  protected void processBursts()
  {
    getCellSpace().doAllBursts();
  }
}
