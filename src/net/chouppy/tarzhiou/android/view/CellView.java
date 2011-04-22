package net.chouppy.tarzhiou.android.view;

import net.chouppy.tarzhiou.Cell;
import net.chouppy.tarzhiou.NameCellKey;
import net.chouppy.tarzhiou.Piece;
import net.chouppy.tarzhiou.Player;
import net.chouppy.tarzhiou.listeners.CellListener;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CellView extends ImageView implements CellListener
{
  private NameCellKey myName;
  
  public CellView (Context context, AttributeSet attrs)
  {
    super (context, attrs);
    
    myName = new NameCellKey (attrs.getAttributeValue(null, "cellname"));
  }
  
  public NameCellKey getCellKey ()
  {
    return myName;
  }

  public void onAddPiece(Cell me, Piece thisPiece)
  {
    // TODO Auto-generated method stub
    
  }

  public void onBurst(Cell me)
  {
    // TODO Auto-generated method stub
    
  }

  public void onGetPiece(Cell me, Player oldPlayer, Player newPlayer)
  {
    // TODO Auto-generated method stub
    
  }
}
