package net.chouppy.tarzhiou;

import net.chouppy.tarzhiou.listeners.LinkeableSquareListener;

/**
 * A square space which can be built. 
 * 
 * Square space must be validated before running any burst.
 * 
 * @author Jonathan ILIAS-PILLET
 */
public class BuildableSquareSpace 
	extends DisorderedSquareSpace 
	implements LinkeableSquareListener {

	private boolean validated;
	
	public BuildableSquareSpace ()
	{
		validated = false;
	}
	
	public boolean do_one_burst_increment() {
		assert (validated);
		return super.do_one_burst_increment();
	}
	
	public void do_all_bursts() {
		assert (validated);
		super.do_all_bursts();
	}
	
	public void stop_doing_all_bursts() {
		assert (validated);
		super.stop_doing_all_bursts();
	}
	
	public void add_square (LinkeableSquare this_square) {
		this_square.set_linkeable_listener(this);
		validated = false;
		super.add_square(this_square);
	}

	public void link_squares (LinkeableSquare a, LinkeableSquare b) {
		validated = false;
		super.link_squares(a, b);
	}
	
	@Override
	public boolean validate ()
	{
		validated = super.validate();
		
		return validated;
	}

	@Override
	public void on_link(LinkeableSquare me, LinkeableSquare other) {
		validated = false;		
	}
}
