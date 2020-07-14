package tests;

import com.sun.istack.internal.NotNull;
import junit.framework.JUnit4TestAdapter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserInterfaceTest.class, AlbumCollectionManagerTest.class})
/**
 * Class to run tests
 * @author Jack Wagner
 */
public class TestRunner {

    /**
     * Init tests
     * @return j unit tester
     */
    @NotNull
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TestRunner.class);
    }

    /**
     * Run tests
     * @param args main default argument
     */
    public static void main (String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}