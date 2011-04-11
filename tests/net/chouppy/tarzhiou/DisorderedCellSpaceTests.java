package net.chouppy.tarzhiou;

import net.chouppy.tarzhiou.listeners.CellListener;
import junit.framework.TestCase;

public class DisorderedCellSpaceTests extends TestCase implements CellListener
{

  private DisorderedCellSpace tested_cellspace;
  Player p1;
  Player p2;
  
  boolean listener_trace_add_piece;
  boolean listener_trace_burst;
  
  boolean test_stop_all_bursts;
  
  protected void setUp() throws Exception
  {
    super.setUp();
    
    BuildableCellSpace bcs = new BuildableCellSpace();
    tested_cellspace = bcs;
    
    LinkeableCell c1 = new LinkeableCell(new NameCellKey("c1"));
    LinkeableCell c2 = new LinkeableCell(new NameCellKey("c2"));
    LinkeableCell c3 = new LinkeableCell(new NameCellKey("c3"));
    LinkeableCell c4 = new LinkeableCell(new NameCellKey("c4"));
    LinkeableCell c5 = new LinkeableCell(new NameCellKey("c5"));
    
    bcs.add_cell(c1);
    bcs.add_cell(c2);
    bcs.add_cell(c3);
    bcs.add_cell(c4);
    bcs.add_cell(c5);
    
    bcs.link_cells(c1, c2);
    bcs.link_cells(c2, c3);
    bcs.link_cells(c3, c4);
    bcs.link_cells(c4, c1);
    bcs.link_cells(c5, c1);
    bcs.link_cells(c5, c2);
    bcs.link_cells(c5, c3);
    
    bcs.validate();
    
    p1 = new Player ("p1");
    p2 = new Player ("p2");
    
    // initialize flags for listener behavior
    test_stop_all_bursts = false;
  }
  
  public void test_simple_burst_do_all ()
  {
    PlayableCell cell = tested_cellspace.get_playable_cell_from_key(new NameCellKey("c4"));
    
    for (int i = 0 ; i < cell.get_capacity() ; i++)
      cell.add_piece(p1.new_piece());
    
    tested_cellspace.do_all_bursts();
    
    assertEquals (1, tested_cellspace.get_cell_from_key(new NameCellKey("c1")).get_pieces_count());
    assertEquals (0, tested_cellspace.get_cell_from_key(new NameCellKey("c2")).get_pieces_count());
    assertEquals (1, tested_cellspace.get_cell_from_key(new NameCellKey("c3")).get_pieces_count());
    assertEquals (0, tested_cellspace.get_cell_from_key(new NameCellKey("c4")).get_pieces_count());
    assertEquals (0, tested_cellspace.get_cell_from_key(new NameCellKey("c5")).get_pieces_count());
  }
  
  public void test_simple_burst_do_increment ()
  {
    PlayableCell cell = tested_cellspace.get_playable_cell_from_key(new NameCellKey("c4"));
    
    for (int i = 0 ; i < cell.get_capacity() ; i++)
      cell.add_piece(p1.new_piece());
    
    boolean result = tested_cellspace.do_one_burst_increment();
    assertTrue (result);
    result = tested_cellspace.do_one_burst_increment();
    assertFalse(result);
    result = tested_cellspace.do_one_burst_increment();
    assertFalse(result);
    
    assertEquals (1, tested_cellspace.get_cell_from_key(new NameCellKey("c1")).get_pieces_count());
    assertEquals (0, tested_cellspace.get_cell_from_key(new NameCellKey("c2")).get_pieces_count());
    assertEquals (1, tested_cellspace.get_cell_from_key(new NameCellKey("c3")).get_pieces_count());
    assertEquals (0, tested_cellspace.get_cell_from_key(new NameCellKey("c4")).get_pieces_count());
    assertEquals (0, tested_cellspace.get_cell_from_key(new NameCellKey("c5")).get_pieces_count());
  }
  
  public void test_player_eats_another ()
  {
    PlayableCell cell_p1 = tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1"));
    PlayableCell cell_p2 = tested_cellspace.get_playable_cell_from_key(new NameCellKey("c2"));
    
    for (int i = 0 ; i < cell_p1.get_capacity() ; i++)
      cell_p1.add_piece(p1.new_piece());
    cell_p2.add_piece(p2.new_piece());
    
    tested_cellspace.do_all_bursts();
    
    assertEquals(0, p2.get_pieces_count());
    assertEquals(cell_p1.get_capacity()+1, p1.get_pieces_count());
  }
  
  public void test_long_burst_chain ()
  {
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c2")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c2")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c5")).add_piece(p1.new_piece());
    
    tested_cellspace.do_all_bursts();
    
    assertEquals(2, tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1")).get_pieces_count());
    assertEquals(1, tested_cellspace.get_playable_cell_from_key(new NameCellKey("c2")).get_pieces_count());
    assertEquals(2, tested_cellspace.get_playable_cell_from_key(new NameCellKey("c3")).get_pieces_count());
    assertEquals(1, tested_cellspace.get_playable_cell_from_key(new NameCellKey("c4")).get_pieces_count());
    assertEquals(0, tested_cellspace.get_playable_cell_from_key(new NameCellKey("c5")).get_pieces_count());
  }
  
  public void test_infinite_burst_chain_incremental ()
  {
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c2")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c2")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c3")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c3")).add_piece(p1.new_piece());
    
    boolean result = true;
    for (int i = 0 ; i < 100 ; i++)
      result = tested_cellspace.do_one_burst_increment();
    assertTrue(result);
  }
  
  public void test_infinite_burst_chain_stoppable ()
  {
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c2")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c2")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c3")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c3")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c3")).add_piece(p1.new_piece());

    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c5")).set_listener(this);

    test_stop_all_bursts = true;
    
    tested_cellspace.do_all_bursts();

    assertTrue (true);
  }
  
  public void test_listeners ()
  {
    listener_trace_add_piece = false;
    listener_trace_burst = false;
    
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1")).set_listener(this);
    
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
    
    assertTrue (listener_trace_add_piece);
    assertFalse (listener_trace_burst);
    
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
    tested_cellspace.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
        
    listener_trace_add_piece = false;
    listener_trace_burst = false;
    
    tested_cellspace.do_all_bursts();
    
    assertFalse (listener_trace_add_piece);
    assertTrue (listener_trace_burst);
  }

  @Override
  public void on_add_piece(Cell me, Piece thisPiece) 
  {
    listener_trace_add_piece = true;
  }

  @Override
  public void on_burst(Cell me)
  {
    if (test_stop_all_bursts && me.get_key().equals(new NameCellKey("c5")))
      tested_cellspace.stop_doing_all_bursts();
    
    listener_trace_burst = true;
  }
}
