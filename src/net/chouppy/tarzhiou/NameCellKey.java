package net.chouppy.tarzhiou;

public class NameCellKey extends CellKey
{
  private String my_name;

  private NameCellKey()
  {
  }

  public NameCellKey(String name)
  {
    my_name = name;
  }

  public String toString()
  {
    return new String(my_name);
  }

  @Override
  public Object clone()
  {
    NameCellKey result = new NameCellKey();
    result.my_name = new String(my_name);
    return result;
  }

  @Override
  public int hashCode()
  {
    return my_name.hashCode();
  }

  @Override
  public boolean equals(Object o)
  {
    if (o == null)
      return false;
    else
      if (!(o instanceof NameCellKey))
        return false;
      else
        if (o.hashCode() == hashCode())
          return true;
        else
          return false;
  }
}
