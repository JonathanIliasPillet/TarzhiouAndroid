package net.chouppy.tarzhiou;

/**
 * Abstract class that represents a cell key. A cell key is an
 * unique identifier, useful to reference a cell inside a view for
 * example.  
 *
 * A CellKey can be a string, an enumerate or a number.
 *
 * @author Jonathan ILIAS-PILLET
 */
public abstract class CellKey implements Cloneable
{
  @Override
  public Object clone() throws CloneNotSupportedException
  {
    return super.clone();
  }

  /**
   * Must be unique for each cell key
   */
  abstract public int hashCode();
}
