package net.chouppy.tarzhiou.android.game_model;

import net.chouppy.tarzhiou.DisorderedCellSpace;
import net.chouppy.tarzhiou.LinkeableCell;
import net.chouppy.tarzhiou.NameCellKey;

public class AndroidCellSpace extends DisorderedCellSpace
{
  public AndroidCellSpace()
  {
    LinkeableCell c1 = new LinkeableCell(new NameCellKey("c1"));
    LinkeableCell c2 = new LinkeableCell(new NameCellKey("c2"));
    LinkeableCell c3 = new LinkeableCell(new NameCellKey("c3"));
    LinkeableCell c4 = new LinkeableCell(new NameCellKey("c4"));
    LinkeableCell c5 = new LinkeableCell(new NameCellKey("c5"));
    LinkeableCell c6 = new LinkeableCell(new NameCellKey("c6"));
    LinkeableCell c7 = new LinkeableCell(new NameCellKey("c7"));
    LinkeableCell c8 = new LinkeableCell(new NameCellKey("c8"));
    LinkeableCell c9 = new LinkeableCell(new NameCellKey("c9"));
    LinkeableCell c10 = new LinkeableCell(new NameCellKey("c10"));

    addCell(c1);
    addCell(c2);
    addCell(c3);
    addCell(c4);
    addCell(c5);
    addCell(c6);
    addCell(c7);
    addCell(c8);
    addCell(c9);
    addCell(c10);

    linkCells(c1, c3);
    linkCells(c1, c4);
    linkCells(c2, c4);
    linkCells(c3, c4);
    linkCells(c3, c5);
    linkCells(c4, c6);
    linkCells(c4, c7);
    linkCells(c5, c6);
    linkCells(c5, c8);
    linkCells(c6, c7);
    linkCells(c6, c8);
    linkCells(c7, c9);
    linkCells(c8, c9);
    linkCells(c9, c10);

    assert validate();
  }

  public boolean doOneBurstIncrement()
  {
    return super.doOneBurstIncrement();
  }

  public void doAllBursts()
  {
    super.doAllBursts();
  }

  public void stopDoingAllBursts()
  {
    super.stopDoingAllBursts();
  }
}
