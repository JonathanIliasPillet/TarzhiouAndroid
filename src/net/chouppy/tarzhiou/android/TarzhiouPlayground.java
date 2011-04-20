package net.chouppy.tarzhiou.android;

import net.chouppy.tarzhiou.android.view.CellView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class TarzhiouPlayground extends Activity
{
  private int counter = 0;
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    
    setContentView(R.layout.cell_space);
  }

  public void onCellClick (View source)
  {
    assert source instanceof CellView;
    
    CellView me = (CellView)source;
    
    //me.setImageResource(R.drawable.cell1);
    if (me.getId() == R.id.cell1)
    {
      switch (counter)
      {
        case 0: me.setImageResource(R.drawable.cell); break;
        case 1: me.setImageResource(R.drawable.cell1); break;
        case 2: me.setImageResource(R.drawable.cell2); break;
        case 3: me.setImageResource(R.drawable.cell3); break;
        case 4: me.setImageResource(R.drawable.cell4); break;
        case 5: me.setImageResource(R.drawable.cell5); break;
        case 6: me.setImageResource(R.drawable.cell6); break;
        case 7: me.setImageResource(R.drawable.cell7); break;
      }
      
      counter++;
    }
    else
      me.setImageResource(R.drawable.cell1);
  }
}
