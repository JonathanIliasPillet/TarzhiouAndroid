package net.chouppy.tarzhiou;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BaseTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for net.chouppy.tarzhiou");
		//$JUnit-BEGIN$
		suite.addTestSuite(CellTests.class);
		suite.addTestSuite(TestNameCellKey.class);
		suite.addTestSuite(PlayerTest.class);
		//$JUnit-END$
		return suite;
	}

}
