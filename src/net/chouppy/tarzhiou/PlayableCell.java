package net.chouppy.tarzhiou;

public class PlayableCell extends Cell
{
	public PlayableCell (CellKey this_key)
	{
		super (this_key);
	}
	
	/**
	 * Player adds a piece in the cell here
	 * 
	 * @param this_piece the unique piece added
	 */
	@Override
	public void addPiece (Piece thisPiece)
	{
		super.addPiece(thisPiece);
	}
	
	@Override
	public void burst ()
	{
		super.burst();
	}
}
