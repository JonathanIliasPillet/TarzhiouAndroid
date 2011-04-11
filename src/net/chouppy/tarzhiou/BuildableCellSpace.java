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
	
	public boolean doOneBurstIncrement() {
		assert (validated);
		return super.doOneBurstIncrement();
	}
	
	public void doAllBursts() {
		assert (validated);
		super.doAllBursts();
	}
	
	public void stopDoingAllBursts() {
		assert (validated);
		super.stopDoingAllBursts();
	}
	
	public void addCell (LinkeableCell thisCell) {
		thisCell.setLinkeableListener(this);
		validated = false;
		super.add_cell(thisCell);
	}

	public void linkCells (LinkeableCell a, LinkeableCell b) {
		validated = false;
		super.linkCells(a, b);
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
