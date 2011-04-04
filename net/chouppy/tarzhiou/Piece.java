package net.chouppy.tarzhiou;

public class Piece {
	protected Player owner;
	
	public Piece (Player this_player)
	{
		owner = this_player;
	}

	public void change_owner (Player this_player)
	{
		owner.loose_piece (this);
		owner = this_player;
	}

	public Player get_owner ()
	{
		return owner;
	}
}
