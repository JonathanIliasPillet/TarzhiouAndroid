package net.chouppy.tarzhiou.sample_game.model;

import net.chouppy.tarzhiou.NameCellKey;
import net.chouppy.tarzhiou.Player;
import junit.framework.TestCase;

public class SampleCellSpaceTests extends TestCase {

	private SampleCellSpace tested_cell_space;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		tested_cell_space = new SampleCellSpace();
	}
	
	public void test_simple_burst_at_c1 ()
	{
		Player p1 = new Player ("p1");
		
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
		tested_cell_space.do_all_bursts();
		
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p12")).get_pieces_count());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p21")).get_pieces_count());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).get_pieces_count());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p31")).get_pieces_count());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p32")).get_pieces_count());
	}
	
	public void test_simple_burst_at_c2 ()
	{
		Player p1 = new Player ("p1");
		
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).add_piece(p1.new_piece());
		tested_cell_space.do_all_bursts();
		
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).get_pieces_count());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).get_pieces_count());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).get_pieces_count());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p12")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p21")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).get_pieces_count());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p31")).get_pieces_count());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p32")).get_pieces_count());
	}
	
	public void test_burst_chain_at_center ()
	{
		Player p1 = new Player ("p1");
		
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).add_piece(p1.new_piece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).add_piece(p1.new_piece());
		tested_cell_space.do_all_bursts();
		
		assertEquals(2, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p12")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p21")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p31")).get_pieces_count());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p32")).get_pieces_count());
	}

}
