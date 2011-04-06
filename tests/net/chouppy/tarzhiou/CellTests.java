package net.chouppy.tarzhiou;

import java.util.Set;

import net.chouppy.tarzhiou.listeners.CellListener;
import net.chouppy.tarzhiou.listeners.LinkeableCellListener;

import junit.framework.TestCase;

public class CellTests extends TestCase implements CellListener, LinkeableCellListener
{

	/* mainly tested cell 
	 */
	private LinkeableCell tested_cell;
	
	/*
	 * neighbors of tested cell
	 */
	private LinkeableCell n1;
	private LinkeableCell n2;
	private LinkeableCell n3;
	
	/*
	 * variables used to get back informations from listener callbacks
	 */
	private Cell listener_me;
	private Piece listener_piece;
	private boolean listener_add_trace;
	private boolean listener_burst_trace;
	private boolean listener_link_trace;

	private LinkeableCell listener_other;
	
	private static final String CELL_KEY_NAME = "a";
	
	protected void setUp() throws Exception {
		super.setUp();
	
		tested_cell = new LinkeableCell(new NameCellKey(CELL_KEY_NAME));
		
		n1 = new LinkeableCell(new NameCellKey("b"));
		n2 = new LinkeableCell(new NameCellKey("c"));
		n3 = new LinkeableCell(new NameCellKey("d"));
		
		tested_cell.link_to(n1);
		tested_cell.link_to(n2);
		tested_cell.link_to(n3);
	}
	
	public void test_get_neighbors ()
	{
		Set<Cell> n = tested_cell.get_neighbors();
		
		assertEquals(3, n.size());
		assertTrue (n.contains(n1));
		assertTrue (n.contains(n2));
		assertTrue (n.contains(n3));
	}
	
	public void test_hashCode ()
	{
		assertEquals((new NameCellKey (CELL_KEY_NAME)).hashCode (), tested_cell.hashCode());
	}
	
	public void test_capacity ()
	{
		assertEquals(3, tested_cell.get_capacity());
		
		tested_cell.link_to(new LinkeableCell(new NameCellKey("e")));
		assertEquals(4, tested_cell.get_capacity());
	}
	
	public void test_get_key ()
	{
		assertEquals (new NameCellKey(CELL_KEY_NAME), tested_cell.get_key());
		
		assertNotSame(new NameCellKey (""), tested_cell.get_key());
	}
	
	public void test_filling ()
	{
		Player p = new Player ("1");
		
		assertEquals(0, tested_cell.get_pieces_count());
		assertEquals(false, tested_cell.is_overloaded());
		
		tested_cell.add_piece(p.new_piece());
		assertEquals(1, tested_cell.get_pieces_count());
		assertEquals(false, tested_cell.is_overloaded());
		
		tested_cell.add_piece(p.new_piece());
		tested_cell.add_piece(p.new_piece());
		assertEquals(3, tested_cell.get_pieces_count());
		assertEquals(true, tested_cell.is_overloaded());
		
		tested_cell.add_piece(p.new_piece());
		tested_cell.add_piece(p.new_piece());
		assertEquals(5, tested_cell.get_pieces_count());
		assertEquals(true, tested_cell.is_overloaded());
	}
	
	public void test_burst ()
	{
		Player p = new Player ("1");
		
		tested_cell.add_piece(p.new_piece());
		tested_cell.add_piece(p.new_piece());
		tested_cell.add_piece(p.new_piece());
		tested_cell.burst();
		
		assertEquals(0, tested_cell.get_pieces_count());
		assertEquals(false, tested_cell.is_overloaded());
		assertEquals(null, tested_cell.get_pieces_owner());
		
		for (Cell neighbor : tested_cell.get_neighbors())
		{
			assertEquals(1, neighbor.get_pieces_count());
			assertEquals(false, tested_cell.is_overloaded());
			assertSame(p, neighbor.get_pieces_owner());
		}	
	}

	public void test_listener ()
	{
		Player p1 = new Player ("p1");
		Piece added_piece = p1.new_piece();
		
		tested_cell.set_listener(this);
		
		listener_add_trace = false;
		listener_burst_trace = false;
		tested_cell.add_piece(added_piece);
		assertTrue (listener_add_trace);
		assertFalse (listener_burst_trace);
		assertSame (tested_cell, listener_me);
		assertSame(added_piece, listener_piece);
		
		listener_add_trace = false;
		tested_cell.add_piece(p1.new_piece());
		tested_cell.add_piece(p1.new_piece());
		tested_cell.add_piece(p1.new_piece());
		assertTrue (listener_add_trace);
		assertFalse (listener_burst_trace);
		
		tested_cell.burst();
		assertTrue (listener_burst_trace);
		assertSame (tested_cell, listener_me);
	}
	
	public void test_link_listener ()
	{
		LinkeableCell other = new LinkeableCell(new NameCellKey("o"));
		
		tested_cell.set_linkeable_listener (this);
		
		listener_link_trace = false;
		tested_cell.link_to(other);
		assertTrue (listener_link_trace);
		assertSame (tested_cell, listener_me);
		assertSame (other, listener_other);
		assertSame (tested_cell, other.get_neighbors().iterator().next());
	}

	@Override
	public void on_add_piece(Cell me, Piece thisPiece) {
		listener_add_trace = true;
		listener_me = me;
		listener_piece = thisPiece;		
	}

	@Override
	public void on_burst(Cell me) {
		listener_me = me;
		listener_burst_trace = true;
		
	}

	@Override
	public void on_link(LinkeableCell me, LinkeableCell other) {
		listener_link_trace = true;
		listener_me = me;
		listener_other = other;
	}
}
