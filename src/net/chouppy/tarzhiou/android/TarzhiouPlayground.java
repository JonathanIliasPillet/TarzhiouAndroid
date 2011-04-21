package net.chouppy.tarzhiou.android;

import net.chouppy.tarzhiou.NameCellKey;
import net.chouppy.tarzhiou.PlayableCell;
import net.chouppy.tarzhiou.Player;
import net.chouppy.tarzhiou.android.game_model.AndroidCellSpace;
import net.chouppy.tarzhiou.android.view.CellView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class TarzhiouPlayground extends Activity
{
  private AndroidCellSpace my_cell_space;
  private Player player1;
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    
    setContentView(R.layout.cell_space);
    
    my_cell_space = new AndroidCellSpace();
    player1 = new Player ("moi");
  }

  public void onCellClick (View source)
  {
    assert source instanceof CellView;
    
    CellView me = (CellView)source;
    
    NameCellKey cellKey;
    
    switch (me.getId())
    {
      case R.id.cell1: cellKey = new NameCellKey("c1"); break;
      case R.id.cell2: cellKey = new NameCellKey("c2"); break;
      case R.id.cell3: cellKey = new NameCellKey("c3"); break;
      case R.id.cell4: cellKey = new NameCellKey("c4"); break;
      case R.id.cell5: cellKey = new NameCellKey("c5"); break;
      case R.id.cell6: cellKey = new NameCellKey("c6"); break;
      case R.id.cell7: cellKey = new NameCellKey("c7"); break;
      case R.id.cell8: cellKey = new NameCellKey("c8"); break;
      case R.id.cell9: cellKey = new NameCellKey("c9"); break;
      case R.id.cell10: cellKey = new NameCellKey("c10"); break;
      default: cellKey = null;
    }
    
    if (cellKey != null)
    {
      PlayableCell cell = my_cell_space.getPlayableCellFromKey(cellKey);
      cell.addPiece(player1.newPiece());
      switch (cell.getPiecesCount())
      {
        case 0: me.setImageResource(R.drawable.cell); break;
        case 1: me.setImageResource(R.drawable.cell1); break;
        case 2: me.setImageResource(R.drawable.cell2); break;
        case 3: me.setImageResource(R.drawable.cell3); break;
        case 4: me.setImageResource(R.drawable.cell4); break;
        case 5: me.setImageResource(R.drawable.cell5); break;
        case 6: me.setImageResource(R.drawable.cell6); break;
        case 7: me.setImageResource(R.drawable.cell7); break;
        default: me.setImageResource(R.drawable.cell7);
      }
    }
  }
}
