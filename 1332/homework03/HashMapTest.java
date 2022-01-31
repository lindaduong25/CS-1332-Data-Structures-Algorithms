import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;


/**
 * Passing these tests doesn't guarantee any grade on the assignment.
 *
 * Some JUnit versions seem to have a problem with assertThrows.
 * I tested my code successfully using JUnit 4.13. If you cannot
 * get it to work for your version, remove the assertThrows lines
 * and you should be fine.
 *
 * If you are failing a lot of test cases, you may have handled
 * resizing or put in a different way than I did: your implementation
 * could be incorrect, correct, or even better than what I came up with.
 *
 * @author Prakhar Mittal
 * @version 1.0
 */
public class HashMapTest {

    private static final int TIMEOUT = 200;
    private HashMap<Integer, String> map;

    @Before
    public void setUp() {
        map = new HashMap<>(5);
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, map.size());
        assertArrayEquals(new MapEntry[5], map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutNullKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            map.put(null, "A");
        });
    }

    @Test(timeout = TIMEOUT)
    public void testPutNullValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            map.put(0, null);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testPutAdd() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(55, "A"));
        assertEquals(3, map.size());

        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[]) new MapEntry[5];
        expected[0] = new MapEntry<>(55, "A");
        expected[4] = new MapEntry<>(4, "E");
        assertArrayEquals(expected, map.getTable());

        assertEquals(new MapEntry<>(0, "A"), map.getTable()[0].getNext());

        assertNull(map.put(1, "B"));
        assertNull(map.put(12, "B"));
        assertNull(map.put(5, "F"));
        assertNull(map.put(23, "B"));
        assertEquals(7, map.size());

        expected = (MapEntry<Integer, String>[]) new MapEntry[11];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(23, "B");
        expected[4] = new MapEntry<>(4, "E");
        expected[5] = new MapEntry<>(5, "F");
        assertArrayEquals(expected, map.getTable());

        assertEquals(new MapEntry<>(55, "A"), map.getTable()[0].getNext());
        assertEquals(new MapEntry<>(12, "B"), map.getTable()[1].getNext());
        assertEquals(new MapEntry<>(1, "B"), map.getTable()[1].getNext().getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testPutReplace() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(55, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(12, "B"));
        assertNull(map.put(23, "B"));
        assertEquals(6, map.size());

        assertEquals("A", map.put(0, "AA"));
        assertEquals("B", map.put(12, "BB"));
        assertEquals("B", map.put(1, "BBB"));
        assertEquals(6, map.size());

        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[]) new MapEntry[11];
        expected[0] = new MapEntry<>(0, "AA");
        expected[1] = new MapEntry<>(23, "B");
        expected[4] = new MapEntry<>(4, "E");
        assertArrayEquals(expected, map.getTable());

        assertEquals(new MapEntry<>(55, "A"), map.getTable()[0].getNext());
        assertEquals(new MapEntry<>(12, "BB"), map.getTable()[1].getNext());
        assertEquals(new MapEntry<>(1, "BBB"), map.getTable()[1].getNext().getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveNullKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            map.remove(null);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveNullMapEntry() {
        assertNull(map.put(0, "A"));
        assertEquals(1, map.size());

        assertThrows(NoSuchElementException.class, () -> {
            map.remove(1);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveNotFound() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(5, "A"));
        assertNull(map.put(10, "A"));
        assertEquals(3, map.size());

        assertThrows(NoSuchElementException.class, () -> {
            map.remove(15);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFirstNode() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "BB"));
        assertNull(map.put(6, "B"));
        assertEquals(3, map.size());

        assertEquals("B", map.remove(6));

        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[]) new MapEntry[5];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "BB");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveMiddleNode() {
        assertNull(map.put(1, "BBB"));
        assertNull(map.put(6, "BB"));
        assertNull(map.put(11, "B"));
        assertEquals(3, map.size());

        assertEquals("BB", map.remove(6));

        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[]) new MapEntry[5];
        expected[1] = new MapEntry<>(11, "B");
        assertArrayEquals(expected, map.getTable());

        assertEquals(new MapEntry<>(1, "BBB"), map.getTable()[1].getNext());

        assertEquals("BBB", map.remove(1));

        assertArrayEquals(expected, map.getTable());

        assertNull(map.getTable()[1].getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testGetNullKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            map.get(null);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testGetNullMapEntry() {
        assertNull(map.put(0, "A"));
        assertEquals(1, map.size());

        assertThrows(NoSuchElementException.class, () -> {
            map.get(1);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testGetNotFound() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(5, "A"));
        assertNull(map.put(10, "A"));
        assertEquals(3, map.size());

        assertThrows(NoSuchElementException.class, () -> {
            map.get(15);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testGetFound() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(55, "AA"));
        assertNull(map.put(1, "BBB"));
        assertNull(map.put(12, "BB"));
        assertNull(map.put(5, "F"));
        assertNull(map.put(23, "B"));
        assertEquals(7, map.size());

        assertEquals("A", map.get(0));
        assertEquals("E", map.get(4));
        assertEquals("AA", map.get(55));
        assertEquals("BBB", map.get(1));
        assertEquals("BB", map.get(12));
        assertEquals("F", map.get(5));
        assertEquals("B", map.get(23));
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(55, "AA"));
        assertNull(map.put(1, "BBB"));
        assertNull(map.put(12, "BB"));
        assertNull(map.put(5, "F"));
        assertNull(map.put(23, "B"));
        assertEquals(7, map.size());

        assertTrue(map.containsKey(0));
        assertTrue(map.containsKey(4));
        assertTrue(map.containsKey(55));
        assertTrue(map.containsKey(1));
        assertTrue(map.containsKey(12));
        assertTrue(map.containsKey(5));
        assertTrue(map.containsKey(23));

        assertFalse(map.containsKey(11));
        assertFalse(map.containsKey(34));
        assertFalse(map.containsKey(2));
        assertFalse(map.containsKey(10));
    }

    @Test(timeout = TIMEOUT)
    public void testKeys() {
        Set<Integer> expected = new HashSet<>();

        assertEquals(expected, map.keySet());

        assertNull(map.put(0, "A"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(55, "AA"));
        assertNull(map.put(1, "BBB"));
        assertNull(map.put(12, "BB"));
        assertNull(map.put(5, "F"));
        assertNull(map.put(23, "B"));
        assertEquals(7, map.size());

        expected.add(0);
        expected.add(55);
        expected.add(23);
        expected.add(12);
        expected.add(1);
        expected.add(4);
        expected.add(5);
        assertEquals(expected, map.keySet());
    }

    @Test(timeout = TIMEOUT)
    public void testValues() {
        List<String> expected = new LinkedList<>();

        assertEquals(expected, map.values());

        assertNull(map.put(0, "A"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(55, "AA"));
        assertNull(map.put(1, "BBB"));
        assertNull(map.put(12, "BB"));
        assertNull(map.put(5, "F"));
        assertNull(map.put(23, "B"));
        assertEquals(7, map.size());

        expected.add("A");
        expected.add("AA");
        expected.add("B");
        expected.add("BB");
        expected.add("BBB");
        expected.add("E");
        expected.add("F");
        assertEquals(expected, map.values());
    }

    @Test(timeout = TIMEOUT)
    public void testResizeIllegalLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            map.resizeBackingTable(-1);
        });

        assertNull(map.put(0, "A"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(55, "A"));
        assertEquals(3, map.size());

        assertThrows(IllegalArgumentException.class, () -> {
            map.resizeBackingTable(2);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testResizeZeroLength() {
        map.resizeBackingTable(0);

        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[]) new MapEntry[0];
        assertArrayEquals(expected, map.getTable());

        assertNull(map.put(0, "A"));

        expected = (MapEntry<Integer, String>[]) new MapEntry[1];
        expected[0] = new MapEntry<>(0, "A");
        assertArrayEquals(expected, map.getTable());

        assertNull(map.put(4, "E"));

        expected = (MapEntry<Integer, String>[]) new MapEntry[3];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(4, "E");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testResizeDown() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(55, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(12, "B"));
        assertNull(map.put(5, "F"));
        assertNull(map.put(23, "B"));
        assertEquals(7, map.size());

        map.resizeBackingTable(7);

        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[]) new MapEntry[7];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(23, "B");
        expected[4] = new MapEntry<>(4, "E");
        expected[5] = new MapEntry<>(5, "F");
        expected[6] = new MapEntry<>(55, "A");
        assertArrayEquals(expected, map.getTable());

        assertEquals(new MapEntry<>(12, "B"), map.getTable()[5].getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        assertNull(map.put(0, "A"));
        assertEquals(1, map.size());

        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[]) new MapEntry[5];
        expected[0] = new MapEntry<>(0, "A");
        assertArrayEquals(expected, map.getTable());

        map.clear();
        assertEquals(0, map.size());

        expected = (MapEntry<Integer, String>[]) new MapEntry[HashMap.INITIAL_CAPACITY];
        assertArrayEquals(expected, map.getTable());
    }
}
