package net.chouppy.tarzhiou;

import net.chouppy.tarzhiou.sample_game.model.SampleGameTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{

  public static Test suite()
  {
    TestSuite suite = new TestSuite("Test for net.chouppy.tarzhiou");
    //$JUnit-BEGIN$
    suite.addTest(BaseTestSuite.suite());
    suite.addTest(SampleGameTestSuite.suite());
    //$JUnit-END$
    return suite;
  }

}
