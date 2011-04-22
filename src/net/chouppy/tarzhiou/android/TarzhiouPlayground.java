package net.chouppy.tarzhiou.android;

import net.chouppy.tarzhiou.Cell;
import net.chouppy.tarzhiou.NameCellKey;
import net.chouppy.tarzhiou.Piece;
import net.chouppy.tarzhiou.PlayableCell;
import net.chouppy.tarzhiou.PlayableCellSpace;
import net.chouppy.tarzhiou.Player;
import net.chouppy.tarzhiou.ReadOnlyCellSpace;
import net.chouppy.tarzhiou.android.game_model.AndroidCellSpace;
import net.chouppy.tarzhiou.android.game_model.AndroidTarzhiouGame;
import net.chouppy.tarzhiou.android.view.CellView;
import net.chouppy.tarzhiou.listeners.CellListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TarzhiouPlayground extends Activity
{
  private AndroidTarzhiouGame myGame;
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    
    setContentView(R.layout.cell_space);
    
    myGame = new AndroidTarzhiouGame();
    myGame.createPlayer("player 1");
    myGame.createPlayer("player 2");
    
//    for (Cell aCell : myGame.getCellSpaceView().getReadOnlyCells())
//    {
//      aCell.addListener(this);
//    }
    
    myGame.start ();
    
    System.out.println("création");
  }

  public void onCellClick (View source)
  {
    assert source instanceof CellView;
    
    NameCellKey cellKey = ((CellView)source).getCellKey();
        
    if (cellKey != null)
    {
      myGame.play(cellKey, myGame.getCurrentPlayer());
    }
  }
  
  private ImageView getCellViewFromKey (NameCellKey thisKey)
  {
    ImageView result = null;
    
    if (thisKey.equals(new NameCellKey("c1")))
      result = (ImageView)findViewById(R.id.cell1);
    if (thisKey.equals(new NameCellKey("c2")))
      result = (ImageView)findViewById(R.id.cell2);
    if (thisKey.equals(new NameCellKey("c3")))
      result = (ImageView)findViewById(R.id.cell3);
    if (thisKey.equals(new NameCellKey("c4")))
      result = (ImageView)findViewById(R.id.cell4);
    if (thisKey.equals(new NameCellKey("c5")))
      result = (ImageView)findViewById(R.id.cell5);
    if (thisKey.equals(new NameCellKey("c6")))
      result = (ImageView)findViewById(R.id.cell6);
    if (thisKey.equals(new NameCellKey("c7")))
      result = (ImageView)findViewById(R.id.cell7);
    if (thisKey.equals(new NameCellKey("c8")))
      result = (ImageView)findViewById(R.id.cell8);
    if (thisKey.equals(new NameCellKey("c9")))
      result = (ImageView)findViewById(R.id.cell9);
    if (thisKey.equals(new NameCellKey("c10")))
      result = (ImageView)findViewById(R.id.cell10);
    
    return result;
  }
}
