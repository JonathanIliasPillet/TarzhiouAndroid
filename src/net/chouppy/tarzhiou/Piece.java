package net.chouppy.tarzhiou;

public class Piece extends ReadOnlyPiece {
	
	public Piece (Player this_player)
	{
		super (this_player);
	}
	
	public void change_owner (Player this_player)
	{
		Player old_owner = owner;
		old_owner.looseAPiece (this);
		owner = this_player;
		owner.winAPiece(this);
		
		if (my_listener != null)
			my_listener.on_owner_changed(this, old_owner, owner);
	}
}
