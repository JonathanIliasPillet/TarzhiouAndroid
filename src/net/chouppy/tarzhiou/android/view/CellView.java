package net.chouppy.tarzhiou.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CellView extends ImageView
{
  private String my_name;
  
  public CellView (Context context, AttributeSet attrs)
  {
    super (context, attrs);
    
    my_name = attrs.getAttributeValue(null, "name");
  }
  
  public String getName ()
  {
    return my_name;
  }
}
