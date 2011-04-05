package net.chouppy.tarzhiou;

import java.util.Iterator;

public class DisorderedSquareSpace extends RunnableSquareSpace {

	private boolean continue_bursts;

	@Override
	public boolean do_one_burst_increment() {
		boolean result = false;

		for (Square current_square : squares) {
			if (current_square.is_overloaded()) {
				current_square.burst();
				result = true;
			}
		}

		return result;
	}

	@Override
	public void do_all_bursts() {
		Square current_square;
		// tells if there is at least one burst in a turn
		boolean at_least_one_burst = false;

		continue_bursts = true;
		do {
			Iterator<Square> i = squares.iterator();
			at_least_one_burst = false;
			while (i.hasNext() && continue_bursts) {
				current_square = i.next();
				if (current_square.is_overloaded()) {
					current_square.burst();
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
