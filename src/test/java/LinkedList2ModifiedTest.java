import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedList2ModifiedTest {
    private LinkedList2Modified emptyList;
    private LinkedList2Modified singleList;
    private LinkedList2Modified list;

    @Before
    public void setUp() {
        emptyList = new LinkedList2Modified();

        singleList = new LinkedList2Modified();
        singleList.addInTail(new Node(1));

        list = new LinkedList2Modified();
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.addInTail(new Node(3));
        list.addInTail(new Node(2));
    }

    @Test
    public void testAddInTail() {
        emptyList.addInTail(new Node(1));
        assertEquals(1, emptyList.getHead().value);
        assertEquals(1, emptyList.getTail().value);

        assertEquals(1, list.getHead().value);
        assertEquals(2, list.getTail().value);
        assertEquals(3, list.getTail().prev.value);
    }

    @Test
    public void testFind() {
        assertNull(emptyList.find(1));

        Node foundNode = list.find(2);
        assertNotNull(foundNode);
        assertEquals(2, foundNode.value);

        assertNull(list.find(4));
    }

    @Test
    public void testFindAll() {
        assertEquals(0, emptyList.findAll(1).size());

        assertEquals(1, list.findAll(1).size());
        assertEquals(2, list.findAll(2).size());
        assertEquals(3, list.findAll(3).get(0).value);

        assertTrue(list.findAll(4).isEmpty());
    }

    @Test
    public void testRemove() {
        assertFalse(emptyList.remove(1));

        assertTrue(singleList.remove(1));

        assertTrue(list.remove(2));
        assertEquals(1, list.getHead().value);
        assertEquals(2, list.getTail().value);
        assertEquals(1, list.getHead().next.prev.value);

        assertFalse(list.remove(4));
    }

    @Test
    public void testRemoveAll() {
        emptyList.removeAll(1);
        assertEquals(0, emptyList.count());

        singleList.remove(2);
        assertEquals(1, singleList.count());

        list.removeAll(2);
        assertEquals(2, list.count());
        assertEquals(1, list.getHead().value);
        assertEquals(3, list.getTail().value);
        assertEquals(1, list.getTail().prev.value);

        list.removeAll(1);
        assertEquals(1, list.count());
        assertEquals(3, list.getHead().value);
        assertEquals(3, list.getTail().value);

        list.removeAll(3);
        assertEquals(0, list.count());
    }

    @Test
    public void testClear() {
        emptyList.clear();
        assertEquals(0, emptyList.count());

        singleList.clear();
        assertEquals(0, emptyList.count());

        list.clear();
        assertEquals(0, emptyList.count());
    }

    @Test
    public void testCount() {
        assertEquals(0, emptyList.count());
        assertEquals(1, singleList.count());
        assertEquals(4, list.count());
    }

    @Test
    public void testInsertAfter() {
        emptyList.insertAfter(null, new Node(1));
        assertEquals(1, emptyList.getHead().value);
        assertEquals(1, emptyList.getTail().value);

        singleList.insertAfter(singleList.getTail(), new Node(2));
        assertEquals(1, singleList.getHead().value);
        assertEquals(2, singleList.getTail().value);
        assertEquals(1, list.getHead().next.prev.value);

        list.insertAfter(list.getHead(), new Node(4));
        assertEquals(4, list.getHead().next.value);
        assertEquals(2, list.getTail().value);
        assertEquals(1, list.getHead().next.prev.value);

        Node node = list.getHead().next.next;
        assertEquals(4, node.prev.value);

        list.insertAfter(list.getHead(), null);
        assertEquals(4, list.getHead().next.value);

        list.insertAfter(list.getTail(), new Node(5));
        assertEquals(5, list.getTail().value);
        assertEquals(2, list.getTail().prev.value);
    }
}

