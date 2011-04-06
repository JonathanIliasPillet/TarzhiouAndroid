package net.chouppy.tarzhiou;

import junit.framework.TestCase;

public class TestNameCellKey extends TestCase {
	public void testEquals () {
		NameCellKey a = new NameCellKey("a medium string");
		NameCellKey b = new NameCellKey("a medium string");
		
		assertEquals(a, b);
		
		NameCellKey c = new NameCellKey("c");
		NameCellKey d = new NameCellKey("c");
		
		assertEquals(c, d);
		
		NameCellKey [] population = new NameCellKey[100];
		for (int i = 0 ; i < 100 ; i++) {
			population[i] = new NameCellKey("same people");
		}
		
		for (int i = 0 ; i < 100 ; i++) {
			for (int j = 0 ; j < 100 ; j++) {
				assertEquals(population[i], population[j]);
			}
		}
	}
	
	public void testDifferent () {
		NameCellKey a = new NameCellKey("a really different string");
		NameCellKey b = new NameCellKey("a string that really is not the same");
		
		assertNotSame(a, b);
		
		NameCellKey c = new NameCellKey("a string with small difference");
		NameCellKey d = new NameCellKey("a string with  small difference");
		
		assertNotSame (c, d);
		
		NameCellKey [] population = new NameCellKey[100];
		for (int i = 0 ; i < 100 ; i++) {
			population[i] = new NameCellKey("different poeple"+i);
		}		
		for (int i = 0 ; i < 100 ; i++) {
			for (int j = 0 ; j < 100 ; j++) {
				if (i != j)
					assertNotSame(population[i], population[j]);
			}
		}
	}
}
