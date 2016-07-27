/**
 *   APPLICATION: LoginSystem
 *         CLASS: ArrayIndexedListTest
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *  TEST LIBRARY: JUnit4
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provides test cases for methods that are overridden within the ArrayIndexedList class. The test
 *                cases for the other methods are within the parent class test case class [ArrayUnsortedListTest]
 *       PACKAGE: tests.util..list
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayIndexedListTest {
    @Test
    public void add() throws Exception {
        ArrayIndexedList<String> arrayIndexed = new ArrayIndexedList<>(3);

        // Use the superclass add method to fill the list
        arrayIndexed.add("Hello");
        arrayIndexed.add("Cruel");
        arrayIndexed.add("World");

        // Now replace an element using the indexed add method
        arrayIndexed.add(1, "Sweet");

        String correctList = "Index 0: Hello\nIndex 1: Sweet\nIndex 2: Cruel\nIndex 3: World\n";

        assertEquals(correctList, arrayIndexed.toString());
    }

    @Test
    public void set() throws Exception {
        ArrayIndexedList<String> arrayIndexed = new ArrayIndexedList<>(3);

        arrayIndexed.add("Computer");
        arrayIndexed.add("Economics");

        assertEquals("Economics", arrayIndexed.set(1, "Science"));

        String correctList = "Index 0: Computer\nIndex 1: Science\n";

        assertEquals(correctList, arrayIndexed.toString());
    }

    @Test
    public void get() throws Exception {
        ArrayIndexedList<String> arrayIndexed = new ArrayIndexedList<>(3);

        arrayIndexed.add("Testing");
        arrayIndexed.add("get");

        assertEquals("Testing", arrayIndexed.get(0));
        assertEquals("get", arrayIndexed.get(1));

        arrayIndexed.add("method");

        assertEquals("method", arrayIndexed.get(2));
    }

    @Test
    public void indexOf() throws Exception {
        ArrayIndexedList<String> arrayIndexed = new ArrayIndexedList<>(3);

        arrayIndexed.add("Testing");
        arrayIndexed.add("indexOf");
        arrayIndexed.add("method");

        assertEquals(1, arrayIndexed.indexOf("indexOf"));

        arrayIndexed.remove("indexOf");

        assertEquals(1, arrayIndexed.indexOf("method"));
    }

    @Test
    public void remove() throws Exception {
        ArrayIndexedList<String> arrayIndexed = new ArrayIndexedList<>(3);

        arrayIndexed.add("Testing");
        arrayIndexed.add("indexed remove");

        assertEquals("indexed remove", arrayIndexed.remove(1));
        assertEquals(1, arrayIndexed.size());

        arrayIndexed.add("another element");

        assertEquals("Testing", arrayIndexed.remove(0));
        assertEquals(1, arrayIndexed.size());
    }

    @Test(expected = DuplicateElementException.class)
    public void testDuplicateElementException(){
        ArrayIndexedList<String> arrayIndexed = new ArrayIndexedList<>(3);

        arrayIndexed.add("Testing");
        arrayIndexed.add("Testing");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBounds(){
        ArrayIndexedList<String> arrayIndexed = new ArrayIndexedList<>(3);

        arrayIndexed.set(7, "Error");
    }
}