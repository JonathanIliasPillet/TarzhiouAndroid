package net.chouppy.tarzhiou;

import net.chouppy.tarzhiou.listeners.PlayerListener;

public class Player extends ReadOnlyPlayer
{

  public Player(String thisName)
  {
    super(thisName);
  }

  public Piece newPiece()
  {
    Piece result = new Piece(this);
    my_pieces.add(result);

    for (PlayerListener listener : myListeners)
      listener.onNewPiece(this, result);

    return result;
  }

  public void looseAPiece(Piece this_piece)
  {
    my_pieces.remove(this_piece);

    if (my_pieces.isEmpty())
      alive = false;

    for (PlayerListener listener : myListeners)
      listener.onLooseAPiece(this, this_piece);
  }

  /**
   * Adds a piece to the player list.
   * 
   * @warning this piece's owner must be set to this player
   * 
   * @param this_piece
   *          the piece the player wins
   */
  public void winAPiece(Piece this_piece)
  {
    assert (this_piece.getOwner().equals(this_piece.getOwner()));

    my_pieces.add(this_piece);

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

  public void addListener(PlayerListener thisListener)
  {
    myListeners.add (thisListener);
  }
}
