package net.chouppy.tarzhiou;

import java.util.LinkedList;
import java.util.List;

import net.chouppy.tarzhiou.listeners.PieceListener;

/**
 * A piece is the object the player puts in a cell when she plays.
 *
 * @author Jonathan ILIAS-PILLET
 */
public class Piece
{
  /**
   * The player that owns this piece
   */
  private Player owner;

  /**
   * Listeners of this piece events
   */
  private List<PieceListener> myListeners;

  /**
   * Creates a new piece attached to a player
   * 
   * @param thisPlayer player to whom the piece is attached
   */
  public Piece(Player thisPlayer)
  {
    owner = thisPlayer;
    myListeners = new LinkedList<PieceListener>();
  }

  /**
   * Change the owner of the piece
   * 
   * @param thisPlayer new piece owner
   */
  public void changeOwner(Player thisPlayer)
  {
    assert thisPlayer != null;
    
    Player oldOwner = owner;
    oldOwner.looseAPiece(this);
    
    owner = thisPlayer;
    owner.winAPiece(this);

    for (PieceListener listener : myListeners)
    {
      listener.onOwnerChanged(this, oldOwner, owner);
    }
  }

  /**
   * Gives the current owner of the piece
   * 
   * @return reference to the player who owns the piece
   */
  public Player getOwner()
  {
    return owner;
  }

  /**
   * Adds a listener
   * 
   * @param thisListener the listener
   */
  public void addListener(PieceListener thisListener)
  {
    assert thisListener != null;
    
    myListeners.add(thisListener);
  }
}
