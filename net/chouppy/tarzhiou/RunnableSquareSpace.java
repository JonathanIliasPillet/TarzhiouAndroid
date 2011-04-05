package net.chouppy.tarzhiou;

public abstract class RunnableSquareSpace extends ReadOnlySquareSpace {
	/**
	 * Do bursts on many squares. The squares
	 * are scanned in a specific order (see in subclass).
	 * 
	 * Depending on the scan policy, the increment will
	 * stop after some partial work to be able to check
	 * if any (or all) player died. If result is true,
	 * this means the method must be called again.
	 * 
	 * @return false if not burst occurred, else true (in
	 * which case another cycle should be done).
	 */
	public abstract boolean do_one_burst_increment ();

	public abstract void do_all_bursts ();
	public abstract void stop_doing_all_bursts ();
}
