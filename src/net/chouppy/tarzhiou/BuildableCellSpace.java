package net.chouppy.tarzhiou;

import net.chouppy.tarzhiou.listeners.LinkeableCellListener;

/**
 * A cell space which can be built. 
 * 
 * Cell space must be validated before running any burst.
 * 
 * @author Jonathan ILIAS-PILLET
 */
public class BuildableCellSpace 
	extends DisorderedCellSpace 
	implements LinkeableCellListener {

	private boolean validated;
	
	public BuildableCellSpace ()
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
	
	public void add_cell (LinkeableCell this_cell) {
		this_cell.set_linkeable_listener(this);
		validated = false;
		super.add_cell(this_cell);
	}

	public void link_cells (LinkeableCell a, LinkeableCell b) {
		validated = false;
		super.link_cells(a, b);
	}
	
	@Override
	public boolean validate ()
	{
		validated = super.validate();
		
		return validated;
	}

	@Override
	public void on_link(LinkeableCell me, LinkeableCell other) {
		validated = false;
	}
}
