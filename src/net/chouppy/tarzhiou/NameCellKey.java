package net.chouppy.tarzhiou;

/**
 * String key for a cell
 * 
 * @author Jonathan ILIAS-PILLET
 */
public class NameCellKey extends CellKey
{
  private String myName;

  /**
   * Builds a cell key with a name
   * 
   * @param name
   *          name of the cell key
   */
  public NameCellKey(String name)
  {
    myName = name;
  }

  /**
   * Returns the name of the cell key
   */
  public String toString()
  {
    return myName;
  }

  @Override
  public Object clone() throws CloneNotSupportedException
  {
    NameCellKey result = (NameCellKey) super.clone();

    result.myName = myName;

    return result;
  }

  @Override
  public int hashCode()
  {
    return myName.hashCode();
  }

  @Override
  public boolean equals(Object o)
  {
    boolean result;

    if (o == null)
    {
      result = false;
    }
    else
    {
      if (!(o instanceof NameCellKey))
      {
        result = false;
      }
      else
      {
        if (o.hashCode() == hashCode())
        {
          result = true;
        }
        else
        {
          result = false;
        }
      }
    }

    return result;
  }
}
