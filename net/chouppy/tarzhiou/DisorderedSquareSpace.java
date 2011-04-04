package net.chouppy.tarzhiou;

public class DisorderedSquareSpace extends SquareSpace {
	
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
}
