package net.chouppy.tarzhiou;

public class NameSquareKey extends SquareKey {
	private String my_name;
	
	private NameSquareKey () {	
	}
	
	public NameSquareKey (String name) {
		my_name = name;
	}

	public String toString ()
	{
		return new String (my_name);
	}
	
	@Override
	public Object clone() {
		NameSquareKey result = new NameSquareKey();
		result.my_name = new String (my_name);
		return result;
	}

	@Override
	public int hashCode() {
		return my_name.hashCode();
	}
	
	@Override
	public boolean equals (Object o) {
		if (o == null)
			return false;
		else
		if (!(o instanceof NameSquareKey))
			return false;
		else
		if (o.hashCode() == hashCode())
			return true;
		else
			return false;
	}
}
