package net.chouppy.tarzhiou;

import java.util.Iterator;

/**
 * A cell space which has a deterministic but undefined burst scan order 
 * 
 * @author Jonathan ILIAS-PILLET
 */
public abstract class DisorderedCellSpace extends PlayableCellSpace {

	private boolean continueBursts;

	@Override
	public boolean doOneBurstIncrement() {
		boolean result = false;

		for (PlayableCell current_cell : getCells()) {
			if (current_cell.isOverloaded()) {
				current_cell.burst();
				result = true;
			}
		}

		return result;
	}

	@Override
	public void doAllBursts() {
		PlayableCell currentCell;
		// tells if there is at least one burst in a turn
		boolean atLeastOneBurst = false;

		continueBursts = true;
		do {
			Iterator<PlayableCell> i = getCells().iterator();
			atLeastOneBurst = false;
			while (i.hasNext() && continueBursts) {
				currentCell = i.next();
				if (currentCell.isOverloaded()) {
					currentCell.burst();
					atLeastOneBurst = true;
				}
			}
		} while (continueBursts && atLeastOneBurst);
	}

	@Override
	public void stopDoingAllBursts() {
		continueBursts = false;
	}
}
