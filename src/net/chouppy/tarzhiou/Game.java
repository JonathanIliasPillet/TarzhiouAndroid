package net.chouppy.tarzhiou;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import net.chouppy.tarzhiou.listeners.GameListener;

public abstract class Game
{
  private PlayableCellSpace myCellSpace = null;

  private Set<Player> myPlayers;

  private boolean started;

  private GameListener myListener;

  private Player currentPlayer;

  private Iterator<Player> playerTurn;

  protected Game()
  {
    myPlayers = new LinkedHashSet<Player>();
    started = false;
    myListener = null;
  }

  public ReadOnlyCellSpace get_cell_space_view()
  {
    assert (myCellSpace != null);
    return myCellSpace;
  }

  public boolean play(Cell this_cell, Player this_player)
  {
    assert (myCellSpace != null);
    assert (started);

    boolean result;

    if ((this_cell.getPiecesCount() == 0)
          || this_player.equals(this_cell.getPiecesOwner()))
    {
      // adds the new piece 
      assert (this_cell instanceof PlayableCell);
      ((PlayableCell) this_cell).addPiece(this_player.newPiece());

      // do bursts
      processBursts();

      // jump to next player
      nextPlayer();

      result = true;
    }
    else
      result = false;

    return result;
  }

  public boolean play(CellKey this_cell_key, Player this_player)
  {
    assert (myCellSpace != null);
    assert (started);
    Cell this_cell = myCellSpace.getCellFromKey(this_cell_key);

    if (this_cell != null)
      return play(this_cell, this_player);
    else
      return false;
  }

  public void start()
  {
    assert (myPlayers.size() > 1);
    selectFirstPlayer();
    started = true;
  }

  public void setListener(GameListener this_listener)
  {
    myListener = this_listener;
  }

  protected abstract void processBursts();

  public Player get_current_player()
  {
    return currentPlayer;
  }

  protected void nextPlayer()
  {
    if (playerTurn.hasNext())
    {
      currentPlayer = playerTurn.next();
      if (!currentPlayer.isAlive())
        nextPlayer();
      assert (currentPlayer != null);
    }
    else
      selectFirstPlayer();
  }

  protected void selectFirstPlayer()
  {
    playerTurn = getPlayers().iterator();
    currentPlayer = playerTurn.next();
    if (!currentPlayer.isAlive())
      nextPlayer();
    assert (currentPlayer != null);
  }

  protected int countAlivePlayers ()
  {
    int result = 0;

    for (Player current_player : myPlayers)
    {
      if (current_player.isAlive())
        result++;
    }

    return result;
  }

  protected Player getAnAlivePlayer()
  {
    Iterator<Player> i = myPlayers.iterator();
    Player result = null;

    while (i.hasNext() && (result == null))
    {
      Player current = i.next();
      if (current.isAlive())
        result = current;
    }

    assert (result != null);

    return result;
  }

  protected void win(Player winner)
  {
    if (myListener != null)
    {
      myListener.onWin(this, winner);
    }
  }

  protected void setCellSpace(PlayableCellSpace thisCellSpace)
  {
    assert (thisCellSpace != null);
    assert (started = false);

    myCellSpace = thisCellSpace;
  }

  protected PlayableCellSpace getCellSpace()
  {
    return myCellSpace;
  }

  protected void addPlayer(Player thisPlayer)
  {
    myPlayers.add(thisPlayer);
  }

  protected Set<Player> getPlayers()
  {
    return myPlayers;
  }
}
