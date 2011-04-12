package net.chouppy.tarzhiou;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.chouppy.tarzhiou.listeners.GameListener;

/**
 * This abstract class represents basically what is a game.
 * 
 * It should be derived to take into account cell space characteristics and some
 * other features.
 * 
 * @author Jonathan ILIAS-PILLET
 */
public abstract class Game
{
  /**
   * The related cell space
   */
  private PlayableCellSpace myCellSpace;

  /**
   * Set of all players in the game
   */
  private List<Player> myPlayers;

  /**
   * The player whom we're waiting to play
   */
  private Player currentPlayer;

  /**
   * Player iterator
   */
  private Iterator<Player> playerTurn;

  /**
   * Tells if the game is started or is being built
   * 
   * (true if game is started)
   */
  private boolean gameIsStarted;

  /**
   * List of listeners
   */
  private LinkedList<GameListener> myListeners;

  /**
   * Builds an empty game (no cell space and no players).
   * 
   * Game is initially not started
   */
  protected Game()
  {
    myCellSpace = null;
    myPlayers = new LinkedList<Player>();
    gameIsStarted = false;
    myListeners = new LinkedList<GameListener>();
  }

  /**
   * Returns a read-only reference of the cell space.
   * 
   * This is especially useful for views which needs to draw fully the space
   * (not only at each change).
   * 
   * @warning the cell space is not a copy, but a reference. It not
   *          synchronized.
   * 
   * @return the read only reference to the cell space
   */
  public final ReadOnlyCellSpace getCellSpaceView()
  {
    assert myCellSpace != null;

    return myCellSpace;
  }

  /**
   * A player plays on a given cell.
   * 
   * @param thisCell
   *          the given cell
   * @param thisPlayer
   *          the player which plays
   * 
   * @return false if playing is refused (cell not free, ...), true if playing
   *         is accepted
   */
  public final boolean play(Cell thisCell, Player thisPlayer)
  {
    assert myCellSpace != null;
    assert gameIsStarted;
    assert (thisCell instanceof PlayableCell);

    boolean result;

    if ((thisCell.getPiecesCount() == 0)
          || thisPlayer.equals(thisCell.getPiecesOwner()))
    {
      // adds the new piece
      ((PlayableCell) thisCell).addPiece(thisPlayer.newPiece());

      // do bursts (abstract method, implemented in subclass)
      processBursts();

      // jump to next player
      nextPlayer();

      // all went well, returns true
      result = true;
    }
    else
    {
      result = false;
    }

    return result;
  }

  /**
   * Like {@link #play(Cell, Player)} but the cell is referred to by its key
   * 
   * @param thisCellKey
   *          key of the wanted cell
   * @param thisPlayer
   *          playing player
   * 
   * @return true if playing is accepted, otherwise false
   */
  public final boolean play(CellKey thisCellKey, Player thisPlayer)
  {
    boolean result;

    assert (myCellSpace != null);
    assert (gameIsStarted);

    Cell this_cell = myCellSpace.getCellFromKey(thisCellKey);

    if (this_cell != null)
    {
      result = play(this_cell, thisPlayer);
    }
    else
    {
      result = false;
    }

    return result;
  }

  /**
   * Adds a listener
   * 
   * @param thisListener
   *          the listener
   */
  public void addListener(GameListener thisListener)
  {
    assert thisListener != null;

    myListeners.add(thisListener);
  }

  /**
   * gives a reference to the player we are waiting to play
   * 
   * @warning this is not a copy of the player but a reference to it.
   * 
   * @return reference to the player
   */
  public Player getCurrentPlayer()
  {
    return currentPlayer;
  }

  /**
   * Starts the game. Actions such playing are now enabled. Modifying the game
   * is no longer allowed.
   */
  protected void start()
  {
    assert myPlayers.size() > 1;

    selectFirstPlayer();
    gameIsStarted = true;
  }

  /**
   * Internally jumps to next player
   * 
   * See {@link #getCurrentPlayer()}
   */
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

  /**
   * Selects the first player. Useful when starting the
   * game or when the end of the player list has been reached
   */
  private void selectFirstPlayer()
  {
    playerTurn = getPlayers().iterator();
    currentPlayer = playerTurn.next();
    if (!currentPlayer.isAlive())
      nextPlayer();
    assert (currentPlayer != null);
  }

  /**
   * Gives the number of still alive players
   * 
   * @return number of alive players
   */
  protected int countAlivePlayers()
  {
    int result = 0;

    for (Player current_player : myPlayers)
    {
      if (current_player.isAlive())
        result++;
    }

    return result;
  }

  /**
   * Returns any alive player. Mainly used to get the winner, when
   * we know she is the last player.
   * 
   * @return reference to the player
   */
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

  /**
   * Tells the game has been won (calls the listener)
   * 
   * @param winner the player who won the game
   */
  protected void win(Player winner)
  {
    assert gameIsStarted == true;

    for (GameListener listener : myListeners)
    {
      listener.onWin(this, winner);
    }
  }

  /**
   * Called to process all existing bursts, and maybe declare the winner (if any)
   */
  protected abstract void processBursts();

  /**
   * sets the cell space
   * 
   * @param thisCellSpace the cell space to set
   */
  protected void setCellSpace(PlayableCellSpace thisCellSpace)
  {
    assert thisCellSpace != null;
    assert gameIsStarted == false;

    myCellSpace = thisCellSpace;
  }

  /**
   * gives the associated cell space reference
   * 
   * @return the cell space reference
   * 
   * @deprecated
   */
  protected PlayableCellSpace getCellSpace()
  {
    return myCellSpace;
  }

  /**
   * Adds another player to the game
   * 
   * @param thisPlayer the player to be added
   */
  protected void addPlayer(Player thisPlayer)
  {
    assert gameIsStarted == false;
    
    myPlayers.add(thisPlayer);
  }

  /**
   * Gives the full list of players
   * 
   * @return the list of playes
   * 
   * @deprecated
   */
  protected List<Player> getPlayers()
  {
    return myPlayers;
  }
}
