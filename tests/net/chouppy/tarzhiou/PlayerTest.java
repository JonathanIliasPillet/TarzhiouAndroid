package net.chouppy.tarzhiou;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {

	private static final String PLAYER_NAME = "tested_player";
	
	private Player tested_player;
	
	protected void setUp() throws Exception {
		tested_player = new Player (PlayerTest.PLAYER_NAME);
	}
	
	public void test_name ()
	{
		assertSame (PlayerTest.PLAYER_NAME, tested_player.get_name());
	}
	
	public void test_alive ()
	{
		assertTrue (tested_player.is_alive());
		
		Piece p = tested_player.new_piece();
		assertTrue (tested_player.is_alive());
		
		tested_player.loose_a_piece(p);
		
		assertFalse (tested_player.is_alive());
	}
	
	public void test_retract ()
	{
		assertTrue (tested_player.is_alive());
		
		tested_player.new_piece();
		
		tested_player.retract();
		
		assertFalse(tested_player.is_alive());
		//assertEquals(1, tested_player.get_pieces_count());
	}

}
