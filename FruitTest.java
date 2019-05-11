import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class FruitTest {
    private List<Fruit> emptyList;
    private Fruit oldFruit;
    // write tests for (i) the getter/setter methods, and (ii) the
    // equality-checking method equals(Object o).
    // Hint: you should initialize a collection of Fruit objects before the
    // actual tests happen. Use the @Before annotation for this.

    @Before
    public void setUp() { emptyList = new ArrayList();
        oldFruit = new Fruit("Orange", 30);
        emptyList.add(oldFruit);
    }


    @After
    public void tearDown() { emptyList = null; }

    @Test
    public void testGetterName() {
        emptyList.get(0).setName("Apple");
        assertEquals("Fields didn't match", emptyList.get(0).getName(), "Apple");
    }

    @Test
    public void testGetterQty() {
        emptyList.get(0).setQty(10);
        assertEquals("Fields didn't match", emptyList.get(0).getQty(), 10);
    }

    @Test
    public void testSetterName() {
        emptyList.get(0).setName("Apple");
        assertEquals("Fields didn't match", emptyList.get(0).getName(), "Apple");
    }

    @Test
    public void testSetterQty() {
        emptyList.get(0).setQty(10);
        assertEquals("Fields didn't match", emptyList.get(0).getQty(), 10);
    }

    @Test
    public void testEquals() {
        Fruit newFruit = new Fruit("Apple", 40);
        assertEquals("Do not equal each other", newFruit.equals(oldFruit), false);
    }

}
