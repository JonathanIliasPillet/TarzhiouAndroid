package net.chouppy.tarzhiou.listeners;

import net.chouppy.tarzhiou.Piece;
import net.chouppy.tarzhiou.Cell;

/**
 * 
 * @author Jonathan ILIAS-PILLET
 */
public interface CellListener {
	public void onAddPiece (Cell me, Piece this_piece);
	public void onBurst (Cell me);
}
