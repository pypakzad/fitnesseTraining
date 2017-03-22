package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ArrowTest.class, GameTest.class, PlayerTest.class, UserInputTest.class, BatTest.class,
		MoveInMapTest.class, MapTest.class })
public class AllTests {

}
