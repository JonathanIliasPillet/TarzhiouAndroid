package net.chouppy.tarzhiou;

import net.chouppy.tarzhiou.listeners.LinkeableCellListener;

/**
 * A cell space which can be built.
 * 
 * Cell space must be validated before running any burst.
 * 
 * @author Jonathan ILIAS-PILLET
 */
public class BuildableCellSpace extends DisorderedCellSpace implements
      LinkeableCellListener
{
  /**
   * Indicates that the buildable cell space has been validated. This flag is
   * cleared when the cell space changes.
   */
  private boolean validated;

  /**
   * Builds a new and empty cell space
   */
  public BuildableCellSpace()
  {
    validated = false;
  }

  public boolean doOneBurstIncrement()
  {
    assert (validated);
    return super.doOneBurstIncrement();
  }

  public void doAllBursts()
  {
    assert (validated);
    super.doAllBursts();
  }

  public void stopDoingAllBursts()
  {
    assert (validated);
    super.stopDoingAllBursts();
  }

  /**
   * Adds an other cell to the cell space
   * 
   * @param thisCell the cell to add
   */
  public void addCell(LinkeableCell thisCell)
  {
    thisCell.setLinkeableListener(this);
    validated = false;
    super.addCell(thisCell);
  }

  /**
   * Links two cells in the cell space
   */
  public void linkCells(LinkeableCell a, LinkeableCell b)
  {
    assert (hasCell(a));
    assert (hasCell(b));
    validated = false;
    super.linkCells(a, b);
  }

  /**
   * Validates the cell space
   * 
   * @return true if it is validated (and then, until it is modified, this cell space is playable)
   */
  @Override
  public boolean validate()
  {
    validated = super.validate();

    return validated;
  }

  @Override
  public void onLink(LinkeableCell me, LinkeableCell other)
  {
    validated = false;
  }
}
