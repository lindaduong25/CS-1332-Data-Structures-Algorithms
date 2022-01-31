import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;


/**
 * Passing these tests doesn't guarantee any grade on the assignment.
 *
 * @author Prakhar Mittal
 * @version 1.0
 */
public class CircularSinglyLinkedListTest {

    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<String> list;

    @Before
    public void setUp() {
        list = new CircularSinglyLinkedList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.addAtIndex(-1, "A");
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.addAtIndex(1, "A");
        });
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexSizeZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.addAtIndex(0, null);
        });

        list.addAtIndex(0, "A");

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexSizeOneIndexZero() {
        list.addAtIndex(0, "B");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addAtIndex(0, null);
        });

        list.addAtIndex(0, "A");

        assertEquals(2, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexSizeOneIndexOne() {
        list.addAtIndex(0, "A");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addAtIndex(1, null);
        });

        list.addAtIndex(1, "B");

        assertEquals(2, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexSizeTwoIndexZero() {
        list.addAtIndex(0, "B");
        list.addAtIndex(1, "C");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addAtIndex(0, null);
        });

        list.addAtIndex(0, "A");

        assertEquals(3, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("C", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexSizeTwoIndexOne() {
        list.addAtIndex(0, "A");
        list.addAtIndex(1, "C");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addAtIndex(1, null);
        });

        list.addAtIndex(1, "B");

        assertEquals(3, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("C", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexSizeTwoIndexTwo() {
        list.addAtIndex(0, "A");
        list.addAtIndex(1, "B");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addAtIndex(2, null);
        });

        list.addAtIndex(2, "C");

        assertEquals(3, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("C", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontSizeZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.addToFront(null);
        });

        list.addToFront("A");

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontSizeOne() {
        list.addToFront("B");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addToFront(null);
        });

        list.addToFront("A");

        assertEquals(2, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontSizeTwo() {
        list.addToFront("C");
        list.addToFront("B");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addToFront(null);
        });

        list.addToFront("A");

        assertEquals(3, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("C", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackSizeZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.addToBack(null);
        });

        list.addToBack("A");

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackSizeOne() {
        list.addToBack("A");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addToBack(null);
        });

        list.addToBack("B");

        assertEquals(2, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());          

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackSizeTwo() {
        list.addToBack("A");
        list.addToBack("B");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addToBack(null);
        });

        list.addToBack("C");

        assertEquals(3, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("C", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexSizeZero() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.removeAtIndex(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.removeAtIndex(0);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.removeAtIndex(1);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexSizeOne() {
        list.addToFront("A");

        assertEquals(1, list.size());

        assertSame("A", list.removeAtIndex(0));

        assertEquals(0, list.size());

        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexSizeTwoIndexZero() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertSame("A", list.removeAtIndex(0));

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexSizeTwoIndexOne() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertSame("B", list.removeAtIndex(1));

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexSizeThreeIndexOne() {
        list.addToFront("C");
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(3, list.size());

        assertSame("B", list.removeAtIndex(1));

        assertEquals(2, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("C", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontSizeZero() {
        assertThrows(NoSuchElementException.class, () -> {
            list.removeFromFront();
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontSizeOne() {
        list.addToFront("A");

        assertEquals(1, list.size());

        assertSame("A", list.removeFromFront());

        assertEquals(0, list.size());

        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontSizeTwo() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertSame("A", list.removeFromFront());

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackSizeZero() {
        assertThrows(NoSuchElementException.class, () -> {
            list.removeFromBack();
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackSizeOne() {
        list.addToFront("A");

        assertEquals(1, list.size());

        assertSame("A", list.removeFromBack());

        assertEquals(0, list.size());

        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackSizeTwo() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertSame("B", list.removeFromBack());

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testGetInvalidIndex() {
        list.addToFront("A");

        assertEquals(1, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(1);
        });

    }

    @Test(timeout = TIMEOUT)
    public void testGetSizeZero() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(1);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testGetSizeOne() {
        list.addToFront("A");

        assertEquals(1, list.size());

        assertEquals("A", list.get(0));
    }

    @Test(timeout = TIMEOUT)
    public void testGetSizeTwoIndexZero() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertEquals("A", list.get(0));
    }

    @Test(timeout = TIMEOUT)
    public void testGetSizeTwoIndexOne() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertEquals("B", list.get(1));
    }

    @Test(timeout = TIMEOUT)
    public void testGetSizeThreeIndexTwo() {
        list.addToFront("C");
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(3, list.size());

        assertEquals("C", list.get(2));
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(list.isEmpty());

        list.addToFront("A");

        assertEquals(1, list.size());

        assertFalse(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        list.addToFront("A");

        assertEquals(1, list.size());

        list.clear();

        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testToArraySizeZero() {
        String[] expected = {};

        assertArrayEquals(expected, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testToArraySizeOne() {
        list.addToFront("A");

        assertEquals(1, list.size());

        String[] expected = {"A"};

        assertArrayEquals(expected, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testToArraySizeFive() {
        list.addToFront("E");
        list.addToFront("D");
        list.addToFront("C");
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(5, list.size());

        String[] expected = {"A", "B", "C", "D", "E"};

        assertArrayEquals(expected, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceNullData() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.removeLastOccurrence(null);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceSizeZero() {
        assertThrows(NoSuchElementException.class, () -> {
            list.removeLastOccurrence("A");
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceSizeOneFound() {
        list.addToFront("A");

        assertEquals(1, list.size());

        assertSame("A", list.removeLastOccurrence("A"));

        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceSizeOneNotFound() {
        list.addToFront("A");

        assertEquals(1, list.size());

        assertThrows(NoSuchElementException.class, () -> {
            list.removeLastOccurrence("B");
        });

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceSizeTwoFoundIndexOne() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertSame("A", list.removeLastOccurrence("A"));

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceSizeTwoFoundIndexTwo() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertSame("B", list.removeLastOccurrence("B"));

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceSizeTwoNotFound() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertThrows(NoSuchElementException.class, () -> {
            list.removeLastOccurrence("C");
        });

        assertEquals(2, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceSizeFiveWithDuplicates() {
        String a = new String("A");
        String b = new String("B");

        list.addToFront("A");
        list.addToFront("B");
        list.addToFront(a);
        list.addToFront(b);
        list.addToFront("A");

        assertEquals(5, list.size());

        assertSame("A", list.removeLastOccurrence("A"));

        assertEquals(4, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);

        assertSame(a, list.removeLastOccurrence("A"));

        assertEquals(3, list.size());

        cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);

        assertSame("B", list.removeLastOccurrence("B"));

        assertEquals(2, list.size());

        cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);

        assertSame("A", list.removeLastOccurrence("A"));

        assertEquals(1, list.size());

        cur = list.getHead();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }
}
