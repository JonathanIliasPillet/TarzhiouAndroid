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
		
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).addPiece(p1.newPiece());
		tested_cell_space.doAllBursts();
		
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p12")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p21")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p31")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p32")).getPiecesCount());
	}
	
	public void test_simple_burst_at_c2 ()
	{
		Player p1 = new Player ("p1");
		
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).addPiece(p1.newPiece());
		tested_cell_space.doAllBursts();
		
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p12")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p21")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p31")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p32")).getPiecesCount());
	}
	
	public void test_heavy_load_burst ()
	{
		Player p1 = new Player ("p1");

		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).addPiece(p1.newPiece());
		tested_cell_space.doAllBursts();

		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).getPiecesCount());
		assertEquals(3, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p12")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p21")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p31")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p32")).getPiecesCount());
	}

	public void test_burst_chain_at_center ()
	{
		Player p1 = new Player ("p1");

		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).addPiece(p1.newPiece());
		tested_cell_space.doAllBursts();

		assertEquals(2, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p12")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p21")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p31")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p32")).getPiecesCount());
	}

	public void test_simple_burst_at_p11 ()
	{
		Player p1 = new Player ("p1");

		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).addPiece(p1.newPiece());
		tested_cell_space.doAllBursts();

		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p12")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p21")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p31")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p32")).getPiecesCount());
	}

	public void test_simple_burst_at_p22 ()
	{
		Player p1 = new Player ("p1");

		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).addPiece(p1.newPiece());
		tested_cell_space.doAllBursts();

		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p12")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p21")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p31")).getPiecesCount());
		assertEquals(0, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p32")).getPiecesCount());
	}

	public void test_burst_chain_around ()
	{
		Player p1 = new Player ("p1");

		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p12")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p12")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p21")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p21")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p31")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p31")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p32")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p32")).addPiece(p1.newPiece());
		tested_cell_space.doAllBursts();

		assertEquals(2, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c1")).getPiecesCount());
		assertEquals(2, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c2")).getPiecesCount());
		assertEquals(2, tested_cell_space.get_playable_cell_from_key(new NameCellKey("c3")).getPiecesCount());
		assertEquals(2, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p12")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p21")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p22")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p31")).getPiecesCount());
		assertEquals(1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p32")).getPiecesCount());
	}

	public void test_player_eaten ()
	{
		Player p1 = new Player ("p1");
		Player p2 = new Player ("p2");

		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p11")).addPiece(p1.newPiece());
		tested_cell_space.get_playable_cell_from_key(new NameCellKey("p12")).addPiece(p2.newPiece());
		tested_cell_space.doAllBursts();

		assertEquals(4, p1.get_pieces_count());
		assertEquals(0, p2.get_pieces_count());
		assertEquals(p1, tested_cell_space.get_playable_cell_from_key(new NameCellKey("p12")).getPiecesOwner());
	}
}
