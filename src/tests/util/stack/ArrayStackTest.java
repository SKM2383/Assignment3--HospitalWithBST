/**
 *   APPLICATION: LoginSystem
 *         CLASS: ArrayStackTest
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *  TEST LIBRARY: JUnit4
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Test case class for each method in ArrayStack
 *       PACKAGE: tests.util.stack
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.stack;

import static org.junit.Assert.*;

public class ArrayStackTest {
    @org.junit.Test
    public void isFull() throws Exception {
        ArrayStack<String> arrayStack = new ArrayStack<>(1);

        arrayStack.push("Test");
        assertTrue(arrayStack.isFull());

        arrayStack.pop();
        assertFalse(arrayStack.isFull());
    }

    @org.junit.Test
    public void isEmpty() throws Exception {
        ArrayStack<String> arrayStack = new ArrayStack<>(1);

        assertTrue(arrayStack.isEmpty());

        arrayStack.push("Test");
        assertFalse(arrayStack.isEmpty());
    }

    @org.junit.Test
    public void top() throws Exception {
        ArrayStack<String> arrayStack = new ArrayStack<>(2);

        arrayStack.push("Jordan");
        assertEquals("Jordan", arrayStack.top());

        arrayStack.push("Michael");
        assertEquals("Michael", arrayStack.top());
    }

    @org.junit.Test
    public void pop() throws Exception {
        ArrayStack<String> arrayStack = new ArrayStack<>(2);

        // Push two Strings onto the stack
        arrayStack.push("Testing");
        arrayStack.push("ArrayStack");

        arrayStack.pop();

        // Now check if the top String of the stack is the first String that was put
        // onto the stack, this means pop took off the second String
        assertEquals("Testing", arrayStack.top());
    }

    @org.junit.Test
    public void push() throws Exception {
        ArrayStack<String> arrayStack = new ArrayStack<>(2);

        arrayStack.push("Testing");
        assertEquals("Testing", arrayStack.top());

        arrayStack.push("The Stack");
        assertEquals("The Stack", arrayStack.top());
    }

    @org.junit.Test
    public void size() throws Exception {
        ArrayStack<String> arrayStack = new ArrayStack<>(5);

        assertEquals(0, arrayStack.size());

        arrayStack.push("Hello");
        arrayStack.push("Sweet");
        arrayStack.push("World");

        assertEquals(3, arrayStack.size());
    }

    @org.junit.Test(expected = StackUnderflowException.class)
    public void testForStackUnderflow(){
        ArrayStack<String> arrayStack = new ArrayStack<>(5);

        arrayStack.pop();
    }

    @org.junit.Test(expected = StackOverflowException.class)
    public void testForStackOverflow(){
        ArrayStack<String> arrayStack = new ArrayStack<>(1);

        arrayStack.push("A");

        // Trigger overflow
        arrayStack.push("Overflow");
    }
}