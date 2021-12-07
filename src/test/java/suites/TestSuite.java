package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTests;
import tests.GetStartedTest;
import tests.MyListsTest;
import tests.SearchTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ArticleTests.class,
        GetStartedTest.class,
        MyListsTest.class,
        SearchTests.class
})

public class TestSuite { }
