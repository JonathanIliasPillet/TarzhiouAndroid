package net.chouppy.tarzhiou.listeners;

import net.chouppy.tarzhiou.Piece;
import net.chouppy.tarzhiou.Cell;

/**
 * 
 * @author Jonathan ILIAS-PILLET
 */
public interface CellListener
{
  void onAddPiece(Cell me, Piece thisPiece);

  void onBurst(Cell me);
}
