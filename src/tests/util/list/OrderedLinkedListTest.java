/**
 *   APPLICATION: LoginSystem
 *         CLASS: OrderedLinkedListTest
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *  TEST LIBRARY: JUnit4
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provides test cases for each method within the OrderedLinkedList class
 *       PACKAGE: tests.util.list
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.list;

import org.junit.Test;

import static org.junit.Assert.*;


public class OrderedLinkedListTest {
    @Test
    public void size() throws Exception {
        OrderedLinkedList<Integer> linkedOrdered = new OrderedLinkedList<>();

        assertEquals(0, linkedOrdered.size());

        linkedOrdered.add(12);
        assertEquals(1, linkedOrdered.size());

        linkedOrdered.remove(12);
        assertEquals(0, linkedOrdered.size());
    }

    @Test
    public void isEmpty() throws Exception {
        OrderedLinkedList<Integer> linkedOrdered = new OrderedLinkedList<>();

        assertTrue(linkedOrdered.isEmpty());

        linkedOrdered.add(23);
        assertFalse(linkedOrdered.isEmpty());

        linkedOrdered.remove(23);
        assertTrue(linkedOrdered.isEmpty());
    }

    @Test
    public void add() throws Exception {
        OrderedLinkedList<String> linkedOrdered = new OrderedLinkedList<>();

        linkedOrdered.add("Such");
        linkedOrdered.add("Sweet");
        linkedOrdered.add("Sorrow");

        assertFalse(linkedOrdered.contains("Romeo"));
        assertTrue(linkedOrdered.contains("Sorrow"));

        // The add method should order the elements inserted using their compareTo method
        // so the toString method should return this String
        String correctList = "Sorrow\nSuch\nSweet\n";

        assertEquals(correctList, linkedOrdered.toString());
    }

    @Test
    public void remove() throws Exception {
        OrderedLinkedList<Integer> linkedOrdered = new OrderedLinkedList<>();

        linkedOrdered.add(455);
        linkedOrdered.add(32);

        assertFalse(linkedOrdered.remove(87));
        assertTrue(linkedOrdered.remove(455));
    }

    @Test
    public void contains() throws Exception {
        OrderedLinkedList<Integer> linkedOrdered = new OrderedLinkedList<>();

        linkedOrdered.add(455);
        linkedOrdered.add(32);

        assertTrue(linkedOrdered.contains(455));

        linkedOrdered.remove(455);

        assertFalse(linkedOrdered.contains(455));
    }

    @Test
    public void get() throws Exception {
        OrderedLinkedList<String> linkedOrdered = new OrderedLinkedList<>();

        linkedOrdered.add("Computer");
        linkedOrdered.add("Science");
        linkedOrdered.add("202");

        assertNull(linkedOrdered.get("200"));

        assertEquals("Science", linkedOrdered.get("Science"));
    }

    @Test
    public void reset() throws Exception {
        OrderedLinkedList<String> linkedOrdered = new OrderedLinkedList<>();

        linkedOrdered.add("Yelp");
        linkedOrdered.add("Jump");
        linkedOrdered.add("Alpha");

        assertEquals("Alpha", linkedOrdered.getNext());
        assertEquals("Jump", linkedOrdered.getNext());

        linkedOrdered.reset();

        assertEquals("Alpha", linkedOrdered.getNext());
    }

    @Test
    public void getNext() throws Exception {
        OrderedLinkedList<String> linkedOrdered = new OrderedLinkedList<>();

        linkedOrdered.add("Stop");
        linkedOrdered.add("Hammer");
        linkedOrdered.add("Time");

        linkedOrdered.reset();

        assertEquals("Hammer", linkedOrdered.getNext());
        assertEquals("Stop", linkedOrdered.getNext());
        assertEquals("Time", linkedOrdered.getNext());

        linkedOrdered.remove("Stop");

        linkedOrdered.reset();

        assertEquals("Hammer", linkedOrdered.getNext());
        assertEquals("Time", linkedOrdered.getNext());
    }

    @Test(expected = DuplicateElementException.class)
    public void testDuplicateElementException(){
        OrderedLinkedList<String> linkedOrdered = new OrderedLinkedList<>();

        linkedOrdered.add("Testing");
        linkedOrdered.add("Testing");
    }
}