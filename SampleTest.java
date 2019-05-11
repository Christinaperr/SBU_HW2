import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SampleTest {

    private java.util.List emptyList;

    /**
     * Sets up the test fixture. The @Before annotation means this is called
     * before every test method.
     */
    @Before
    public void setUp() { emptyList = new java.util.ArrayList(); }

    /**
     * Tears down the test fixture. The @After annotation means this is called
     * after every test method.
     */
    @After
    public void tearDown() { emptyList = null; }

    @Test
    public void testSomeBehavior() {
        assertEquals("Empty list should have 0 elements", 0, emptyList.size());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testForException() {
        Object o = emptyList.get(0);
    }
}


