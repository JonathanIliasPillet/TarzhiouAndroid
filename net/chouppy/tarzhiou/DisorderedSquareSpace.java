package net.chouppy.tarzhiou;

public class DisorderedSquareSpace extends SquareSpace {
	
	@Override
	public void do_bursts() {
		boolean at_least_one_burst;
		
		do {
			at_least_one_burst = false;
			for (Square current_square : squares) {
				if (current_square.is_overloaded()) {
					current_square.burst();
					at_least_one_burst = true;
				}
			}
		} while (at_least_one_burst);		
	}
}
