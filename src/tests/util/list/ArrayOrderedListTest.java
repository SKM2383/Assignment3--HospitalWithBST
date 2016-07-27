/**
 *   APPLICATION: LoginSystem
 *         CLASS: ArrayOrderedListTest
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *  TEST LIBRARY: JUnit4
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provides test cases for methods that are overridden within the ArrayOrderedList class. The test
 *                cases for the other methods are within the parent class test case class [ArrayUnsortedListTest]
 *       PACKAGE: tests.util.list
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayOrderedListTest {
    @Test
    public void add() throws Exception {
        ArrayOrderedList<String> arrayOrderedList = new ArrayOrderedList<>(1);

        arrayOrderedList.add("World");
        arrayOrderedList.add("Hello");

        // Create a String to represent what ArrayOrderedList should print out {the elements in order}
        String correctList = "Hello\nWorld\n";

        assertEquals(correctList, arrayOrderedList.toString());
    }

    @Test
    public void remove() throws Exception {
        ArrayOrderedList<String> arrayOrderedList = new ArrayOrderedList<>(2);

        arrayOrderedList.add("Zach");
        arrayOrderedList.add("Cody");
        arrayOrderedList.add("Mary");

        assertFalse(arrayOrderedList.remove("Jerry"));

        assertTrue(arrayOrderedList.remove("Mary"));

        // Create a String to represent what ArrayOrderedList should print out {the elements in order}
        // despite having an element removed
        String correctList = "Cody\nZach\n";

        assertEquals(correctList, arrayOrderedList.toString());
    }

    @Test(expected = DuplicateElementException.class)
    public void testDuplicateElementException(){
        ArrayOrderedList<String> arrayOrderedList = new ArrayOrderedList<>();

        arrayOrderedList.add("Testing");
        arrayOrderedList.add("Testing");
    }
}