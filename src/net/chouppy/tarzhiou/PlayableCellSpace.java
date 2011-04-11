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

	/**
	 * Do bursts as long as needed or until external stop is requested (see {@link #stopDoingAllBursts()}).
	 */
	public abstract void doAllBursts ();
	
	/**
	 * Stops a burst chain while doing {@link #doAllBursts()}
	 * 
	 * This should not be called by a concurrent thread. This should be called back
	 * from a listener while doing {@link #doAllBursts()}.
	 */
	public abstract void stopDoingAllBursts ();

	/**
	 * Returns the specified playable cell
	 * 
	 * @param this_key the key of the playable cell
	 * 
	 * @return the requested cell, or null if none found
	 */
	public PlayableCell getPlayableCellFromKey (CellKey this_key) {
		return (PlayableCell)getCellFromKey (this_key);
	}
}
