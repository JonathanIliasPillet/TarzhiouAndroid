package net.chouppy.tarzhiou.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TarzhiouPlayground extends Activity
{
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    
    setContentView(R.layout.cell_space);
  }

  public void onCellClick (View source)
  {
    TextView tv = (TextView)findViewById(R.id.textView1);
    
    tv.setText("clicked");
  }
}
