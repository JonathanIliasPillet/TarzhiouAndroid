package net.chouppy.tarzhiou.sample_game.model;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SampleGameTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for net.chouppy.tarzhiou.sample_game.model");
		//$JUnit-BEGIN$
		suite.addTestSuite(SampleCellSpaceTests.class);
		//$JUnit-END$
		return suite;
	}

}
