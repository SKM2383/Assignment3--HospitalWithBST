/**
 *   APPLICATION: LoginSystem
 *         CLASS: ArrayUnsortedListTest
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *  TEST LIBRARY: JUnit4
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provides test cases for the methods within the ArrayUnsortedList class
 *       PACKAGE: tests.util.list
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayUnsortedListTest {
    @Test
    public void size() throws Exception {
        ArrayUnsortedList<String> arrayUnsorted = new ArrayUnsortedList<>(3);

        assertEquals(0, arrayUnsorted.size());

        arrayUnsorted.add("Hello");
        arrayUnsorted.add("World");

        assertEquals(2, arrayUnsorted.size());

        arrayUnsorted.remove("Hello");

        assertEquals(1, arrayUnsorted.size());
    }

    @Test
    public void add() throws Exception {
        ArrayUnsortedList<String> arrayUnsorted = new ArrayUnsortedList<>(3);

        // Make sure the list expands when the max size is reached
        arrayUnsorted.add("Testing");
        arrayUnsorted.add("the");
        arrayUnsorted.add("add");
        arrayUnsorted.add("method");

        // Check the size of the array to make sure add() put in 4 elements
        assertEquals(4, arrayUnsorted.size());

        // Now check that an element can be removed, meaning it was added to the list already
        assertTrue(arrayUnsorted.remove("add"));
    }

    @Test
    public void remove() throws Exception {
        ArrayUnsortedList<String> arrayUnsorted = new ArrayUnsortedList<>(3);

        arrayUnsorted.add("Testing");

        // Check to make sure remove returns false if an element wasn't found
        assertFalse(arrayUnsorted.remove("Hello"));

        assertTrue(arrayUnsorted.remove("Testing"));

        arrayUnsorted.add("remove");
        arrayUnsorted.add("method");

        assertTrue(arrayUnsorted.remove("method"));
    }

    @Test
    public void contains() throws Exception {
        ArrayUnsortedList<String> arrayUnsorted = new ArrayUnsortedList<>(3);

        arrayUnsorted.add("Element");

        assertTrue(arrayUnsorted.contains("Element"));

        assertFalse(arrayUnsorted.contains("Hello"));
    }

    @Test
    public void isEmpty() throws Exception {
        ArrayUnsortedList<String> arrayUnsorted = new ArrayUnsortedList<>(3);

        assertTrue(arrayUnsorted.isEmpty());

        arrayUnsorted.add("Not empty");

        assertFalse(arrayUnsorted.isEmpty());
    }

    @Test
    public void get() throws Exception {
        ArrayUnsortedList<String> arrayUnsorted = new ArrayUnsortedList<>(3);

        arrayUnsorted.add("Testing get");

        assertEquals("Testing get", arrayUnsorted.get("Testing get"));

        assertNull(arrayUnsorted.get("Hello"));
    }

    @Test
    public void reset() throws Exception {
        ArrayUnsortedList<String> arrayUnsorted = new ArrayUnsortedList<>(3);

        arrayUnsorted.add("Testing");
        arrayUnsorted.add("the");
        arrayUnsorted.add("reset");
        arrayUnsorted.add("method");
        arrayUnsorted.add("logic");

        // Use the class iterator variable to get values
        assertEquals("Testing", arrayUnsorted.getNext());
        assertEquals("the", arrayUnsorted.getNext());

        // Now reset the iterator variable and test that getNext returns the first element
        arrayUnsorted.reset();
        assertEquals("Testing", arrayUnsorted.getNext());
    }

    @Test
    public void getNext() throws Exception {
        ArrayUnsortedList<String> arrayUnsorted = new ArrayUnsortedList<>(3);

        arrayUnsorted.add("Testing");
        arrayUnsorted.add("the");
        arrayUnsorted.add("getNext");
        arrayUnsorted.add("method");

        // Use the class iterator variable to get values
        assertEquals("Testing", arrayUnsorted.getNext());
        assertEquals("the", arrayUnsorted.getNext());
        assertEquals("getNext", arrayUnsorted.getNext());
        assertEquals("method", arrayUnsorted.getNext());

        // Remove an element, rest getNext, then test that it iterates correctly
        arrayUnsorted.remove("getNext");

        arrayUnsorted.reset();

        assertEquals("Testing", arrayUnsorted.getNext());
        assertEquals("the", arrayUnsorted.getNext());
        assertEquals("method", arrayUnsorted.getNext());
    }

    @Test
    public void hasNext() throws Exception {
        ArrayUnsortedList<String> arrayUnsorted = new ArrayUnsortedList<>(3);

        arrayUnsorted.add("hasNext test");

        assertTrue(arrayUnsorted.hasNext());

        arrayUnsorted.remove("hasNext test");

        assertFalse(arrayUnsorted.hasNext());
    }

    @Test(expected = DuplicateElementException.class)
    public void testDuplicateElementException(){
        ArrayUnsortedList<String> arrayUnsorted = new ArrayUnsortedList<>(3);

        arrayUnsorted.add("Testing");
        arrayUnsorted.add("Testing");
    }
}