package net.chouppy.tarzhiou.exceptions;

public class VoidPlayerCannotPlay extends RuleViolationException {
	public VoidPlayerCannotPlay ()
	{
		super ("Rule violation : a piece with no owner has been added ");
	}
}
