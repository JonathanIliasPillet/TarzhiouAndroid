package net.chouppy.tarzhiou;

import java.util.LinkedList;

import net.chouppy.tarzhiou.listeners.PieceListener;

public class Piece
{
  private Player owner;

  private LinkedList<PieceListener> myListeners;

  public Piece(Player this_player)
  {
    owner = this_player;
    myListeners = new LinkedList<PieceListener>();
  }

  public void changeOwner(Player this_player)
  {
    Player old_owner = owner;
    old_owner.looseAPiece(this);
    owner = this_player;
    owner.winAPiece(this);

    for (PieceListener listener : myListeners)
      listener.onOwnerChanged(this, old_owner, owner);
  }

  public Player getOwner()
  {
    return owner;
  }

  public void addListener(PieceListener this_listener)
  {
    myListeners.add(this_listener);
  }
}
