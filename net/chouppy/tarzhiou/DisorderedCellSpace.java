package net.chouppy.tarzhiou;

import java.util.Iterator;

/**
 * A cell space which has a deterministic but undefined burst scan order 
 * 
 * @author Jonathan ILIAS-PILLET
 */
public abstract class DisorderedCellSpace extends RunnableCellSpace {

	private boolean continue_bursts;

	@Override
	public boolean do_one_burst_increment() {
		boolean result = false;

		for (Cell current_cell : cells) {
			if (current_cell.is_overloaded()) {
				current_cell.burst();
				result = true;
			}
		}

		return result;
	}

	@Override
	public void do_all_bursts() {
		Cell current_cell;
		// tells if there is at least one burst in a turn
		boolean at_least_one_burst = false;

		continue_bursts = true;
		do {
			Iterator<Cell> i = cells.iterator();
			at_least_one_burst = false;
			while (i.hasNext() && continue_bursts) {
				current_cell = i.next();
				if (current_cell.is_overloaded()) {
					current_cell.burst();
					at_least_one_burst = true;
				}
			}
		} while (continue_bursts && at_least_one_burst);
	}

	@Override
	public void stop_doing_all_bursts() {
		continue_bursts = false;
	}
}
