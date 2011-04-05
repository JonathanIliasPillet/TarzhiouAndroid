package net.chouppy.tarzhiou;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class ReadOnlyCellSpace {
	
	protected Set<Cell> cells;
	
	public ReadOnlyCellSpace () {
		cells = new LinkedHashSet<Cell> ();
	}
	
	public Iterator<Cell> get_iterator () {
		return cells.iterator();
	}
	
	/**
	 * Returns the cell given a key.
	 *
	 * @param this_key the key that represents the cell
	 * @return the required cell or null if none found
	 */
	public Cell get_cell_from_key (CellKey this_key) {
		Iterator<Cell> i = cells.iterator();
		Cell result = null;
		
		while ((i.hasNext()) && (result == null))
		{
			Cell temp = i.next();
			if (temp.hashCode() == this_key.hashCode())
				result = temp;
		}
		
		return result;
	}
	
	/**
	 * Provides DOT output
	 */
	public String toString ()
	{
		StringBuffer result = new StringBuffer();
		
		result.append("graph\"Tarzhiou Cell Space\" {\n");
		for (Cell current_cell : cells) 
		{
			result.append("cell_");
			if (current_cell.get_key() instanceof NameCellKey)
				result.append (((NameCellKey)current_cell.get_key()).toString());
			else
				result.append(current_cell.hashCode());
			result.append(" [label = \"");
			result.append(current_cell.get_pieces_count());
			if (current_cell.get_pieces_count() > 0)
			{
				result.append(" - ");
				result.append(current_cell.get_pieces_owner().get_name());
			}
			result.append("\"];\n");
			for (Cell neighbor : current_cell.get_neighbors())
			{
				result.append("cell_");
				if (current_cell.get_key() instanceof NameCellKey)
					result.append (((NameCellKey)current_cell.get_key()).toString());
				else
					result.append(current_cell.hashCode());
				result.append(" -- cell_");
				if (current_cell.get_key() instanceof NameCellKey)
					result.append (((NameCellKey)neighbor.get_key()).toString());
				else
					result.append(neighbor.hashCode());
				result.append(";\n");
			}
		}
		result.append("}\n");
		
		return result.toString();
	}
	
	protected void add_cell (Cell this_cell) {
		cells.add(this_cell);
	}
	
	protected void link_cells (LinkeableCell a, LinkeableCell b) {
		assert (cells.contains(a));
		assert (cells.contains(b));
		
		a.link_to(b);
	}
	
	protected boolean validate () {
		boolean is_valid = true;
		Iterator<Cell> i = cells.iterator();
		Cell current_cell;
	
		while (i.hasNext() && is_valid) {
			current_cell = i.next ();
			if (current_cell.get_capacity() < 2)
				is_valid = false;
		}
		
		return is_valid;
	}
}
