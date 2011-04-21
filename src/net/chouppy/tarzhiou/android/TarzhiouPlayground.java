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
import android.widget.RelativeLayout;

public class TarzhiouPlayground extends Activity implements CellListener
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
    
    for (Cell aCell : myGame.getCellSpaceView().getReadOnlyCells())
    {
      aCell.addListener(this);
    }
    
    myGame.start ();
    
    System.out.println("création");
  }

  public void onCellClick (View source)
  {

    NameCellKey cellKey;
    
    switch (source.getId())
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
      myGame.play(cellKey, myGame.getCurrentPlayer());
    }
  }

  public void onAddPiece(Cell me, Piece thisPiece)
  {
    updateCell(me);
  }

  public void onBurst(Cell me)
  {
    updateCell(me);
    for (Cell neighbor : me.getNeighbors())
      updateCell(neighbor);/*
    try {
      Thread.sleep(500);
    }
    catch (Exception e){}*/
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
  
  private void updateCell (Cell thisCell)
  {
    ImageView cellView = getCellViewFromKey ((NameCellKey)thisCell.getKey());
    
    switch (thisCell.getPiecesCount())
    {
      case 0: cellView.setImageResource(R.drawable.cell); break;
      case 1: cellView.setImageResource(R.drawable.cell1); break;
      case 2: cellView.setImageResource(R.drawable.cell2); break;
      case 3: cellView.setImageResource(R.drawable.cell3); break;
      case 4: cellView.setImageResource(R.drawable.cell4); break;
      case 5: cellView.setImageResource(R.drawable.cell5); break;
      case 6: cellView.setImageResource(R.drawable.cell6); break;
      case 7: cellView.setImageResource(R.drawable.cell7); break;
      default: cellView.setImageResource(R.drawable.cell7);
    }
    
    cellView.invalidate();
  }
}
