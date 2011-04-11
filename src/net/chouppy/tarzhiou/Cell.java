package net.chouppy.tarzhiou;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import net.chouppy.tarzhiou.listeners.CellListener;

public abstract class Cell {
	private Set<Cell> myNeighbors;
	private Set<Piece> myPieces;
	private CellKey myKey;
	private CellListener myListener;
	
	protected Cell (CellKey thisKey)
	{
		myNeighbors = new LinkedHashSet<Cell>();
		myPieces = new LinkedHashSet<Piece>();
		myKey = (CellKey)thisKey.clone ();
		myListener = null;
	}

	protected void addPiece (Piece thisPiece)
    {
        // The piece must be owned by a player
        assert (thisPiece.getOwner() != null);
        
        // The cell pieces must all be owned
        // by the same player
        if (!myPieces.isEmpty())
            assert (myPieces.iterator().next().getOwner().equals(thisPiece.getOwner()));

        // finally adds the piece to the cell
        myPieces.add(thisPiece);
        
        // calls listener if exists
        if (myListener != null) 
        {
          myListener.on_add_piece(this, thisPiece);
        }
    }
	
	protected void burst ()
    {
        // The cell must be overloaded (otherwise, we have not
        // enough pieces to dispatch in neighbor cells
        assert (isOverloaded());
        
        Piece movedPiece;
        Iterator<Piece> i = myPieces.iterator();
        for (Cell currentNeighbor : myNeighbors) {
            movedPiece = i.next ();
            i.remove();
            assert (currentNeighbor instanceof PlayableCell);
            ((PlayableCell)currentNeighbor).receivePiece(movedPiece);
        }
        
        // call listener if any
        if (myListener != null) 
        {
          myListener.on_burst(this);
        }
    }
	
	protected void receivePiece (Piece this_piece)
    {
        // change all pieces owner
        for (Piece current_piece : myPieces ) {
            current_piece.change_owner((Player)this_piece.getOwner ());
        }
        // finally adds the piece to the cell
        myPieces.add(this_piece);
    }
	
	protected void addNeighbor (Cell otherCell)
	{
      myNeighbors.add (otherCell);
    }
	
	protected void removeNeighbor (Cell thisCell)
	{
	  myNeighbors.remove (thisCell);
	}
	
	public int getPiecesCount ()
	{
		return myPieces.size();
	}
	
	public int getCapacity () {
		return myNeighbors.size();
	}
	
	public CellKey getKey ()
	{
		return (CellKey)myKey.clone ();
	}
	
	/**
	 * Tells if this cell is about to explode
	 */
	public boolean isOverloaded ()
	{
		if (myPieces.size() >= myNeighbors.size())
			return true;
		else
			return false;
	}
	
	public Player getPiecesOwner ()
	{
		if (myPieces.size() > 0)
			return (Player)myPieces.iterator().next().getOwner();
		else
			return null;
	}
	
	public void setListener (CellListener thisListener)
	{
		myListener = thisListener;
	}

	public Set<Cell> getNeighbors ()
	{
		return new LinkedHashSet<Cell> (myNeighbors);
	}
	
	public int hashCode ()
	{
		return myKey.hashCode ();
	}
}
