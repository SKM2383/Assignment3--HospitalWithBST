/**
 *   APPLICATION: LoginSystem
 *         CLASS: LinkedListStackTest
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provides test cases for each method in LinkedListStack class
 *       PACKAGE: tests.util.stack
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.stack;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListStackTest {
    @Test
    public void top() throws Exception {
        LinkedListStack<String> linkedStack = new LinkedListStack<>();

        linkedStack.push("Jordan");
        assertEquals("Jordan", linkedStack.top());

        linkedStack.push("Michael");
        assertEquals("Michael",linkedStack.top());
    }

    @Test
    public void pop() throws Exception {
        LinkedListStack<String> linkedStack = new LinkedListStack<>();

        linkedStack.push("Testing");
        linkedStack.push("ArrayStack");

        linkedStack.pop();

        assertEquals("Testing", linkedStack.top());
    }

    @Test
    public void push() throws Exception {
        LinkedListStack<String> linkedStack = new LinkedListStack<>();

        // Checking to make sure LinkedListStack is unbounded by pushing multiple values
        linkedStack.push("A");
        linkedStack.push("B");
        linkedStack.push("C");
        linkedStack.push("D");
        linkedStack.push("E");

        linkedStack.push("Testing");
        assertEquals("Testing", linkedStack.top());

        linkedStack.push("The Stack");
        assertEquals("The Stack", linkedStack.top());
    }

    @Test
    public void size() throws Exception {
        LinkedListStack<String> linkedStack = new LinkedListStack<>();

        assertEquals(0, linkedStack.size());

        linkedStack.push("Hello");
        linkedStack.push("Sweet");
        linkedStack.push("World");

        assertEquals(3, linkedStack.size());
    }

    @Test
    public void isEmpty() throws Exception {
        LinkedListStack<String> linkedStack = new LinkedListStack<>();

        assertTrue(linkedStack.isEmpty());

        linkedStack.push("Hello");
        assertFalse(linkedStack.isEmpty());

        linkedStack.pop();

        assertTrue(linkedStack.isEmpty());
    }

    @Test(expected = StackUnderflowException.class)
    public void testForStackUnderflow(){
        LinkedListStack<String> linkedStack = new LinkedListStack<>();

        linkedStack.pop();
    }
}