package net.chouppy.tarzhiou;

import java.util.LinkedList;
import java.util.List;

import net.chouppy.tarzhiou.listeners.PlayerListener;

/**
 * Represents a player
 * 
 * @author Jonathan ILIAS-PILLET
 */
public class Player
{
  /**
   * Canonical name of the player
   */
  private String myCanonicalName;

  /**
   * List of all the pieces the player owns
   */
  private List<Piece> myPieces;

  /**
   * List of listeners of this player
   */
  private List<PlayerListener> myListeners;

  /**
   * Tells if the player is still alive in the game.
   * 
   * If the player falls to zero pieces or retracts, she dies. After creation, a
   * player is alive.
   */
  private boolean alive;

  /**
   * Creates a player.
   * 
   * @note The given player name should be unique for a game. The hashCode of
   *       the player is derived from its name
   * 
   * @param thisCanonicalName
   *          the canonical name of the player
   */
  public Player(String thisCanonicalName)
  {
    myCanonicalName = thisCanonicalName;
    myPieces = new LinkedList<Piece>();
    myListeners = new LinkedList<PlayerListener>();
    alive = true;
  }

  /**
   * Creates a piece for this player.
   * 
   * The created piece is to be added to a cell
   * 
   * @return the created piece
   */
  public Piece newPiece()
  {
    Piece result = new Piece(this);
    myPieces.add(result);

    for (PlayerListener listener : myListeners)
      listener.onNewPiece(this, result);

    return result;
  }

  /**
   * Called when a player looses a piece
   * 
   * @param thisPiece
   *          the loosed piece
   */
  public void looseAPiece(Piece thisPiece)
  {
    myPieces.remove(thisPiece);

    if (myPieces.isEmpty())
      alive = false;

    for (PlayerListener listener : myListeners)
      listener.onLooseAPiece(this, thisPiece);
  }

  /**
   * Adds an existing piece to the player list.
   * 
   * @warning this piece's owner must be set to this player
   * 
   * @param this_piece
   *          the piece the player wins
   */
  public void winAPiece(Piece this_piece)
  {
    assert (this_piece.getOwner().equals(this_piece.getOwner()));

    myPieces.add(this_piece);

    for (PlayerListener listener : myListeners)
      listener.onWinAPiece(this, this_piece);
  }

  /**
   * This player retracts from game. Its pieces remains on cell space, but the
   * player won't player anymore.
   */
  public void retract()
  {
    alive = false;
  }

  /**
   * Tells if player is still alive.
   * 
   * @return true if the player is alive.
   */
  public boolean isAlive()
  {
    return alive;
  }

  /**
   * Gives the canonical name of the player
   * 
   * @return the canonical name of the player
   */
  public String getCanonicalName()
  {
    return new String(myCanonicalName);
  }

  /**
   * Gives the number of pieces the player owns
   * 
   * @return number of pieces own by the player
   */
  public int getPiecesCount()
  {
    return myPieces.size();
  }

  /**
   * Adds a listener
   * 
   * @see #PlayerListener
   * 
   * @param thisListener
   *          the listener to add
   */
  public void addListener(PlayerListener thisListener)
  {
    myListeners.add(thisListener);
  }

  @Override
  public String toString()
  {
    return getCanonicalName();
  }

  @Override
  public boolean equals(Object o)
  {
    return o == this;
  }

  @Override
  public int hashCode()
  {
    return myCanonicalName.hashCode();
  }
}
