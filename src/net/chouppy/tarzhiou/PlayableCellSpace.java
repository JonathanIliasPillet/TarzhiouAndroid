package net.chouppy.tarzhiou;

public abstract class PlayableCellSpace extends ReadOnlyCellSpace {
	/**
	 * Do bursts on many cells. The cells
	 * are scanned in a specific order (see in subclass).
	 * 
	 * Depending on the scan policy, the increment will
	 * stop after some partial work to be able to check
	 * if any (or all) player died. If result is true,
	 * this means the method must be called again.
	 * 
	 * @return false if no burst occurred, else true (in
	 * which case another increment should be done).
	 */
	public abstract boolean doOneBurstIncrement ();

	public abstract void doAllBursts ();
	public abstract void stopDoingAllBursts ();

	public PlayableCell get_playable_cell_from_key (CellKey this_key) {
		return (PlayableCell)get_cell_from_key (this_key);
	}
}
