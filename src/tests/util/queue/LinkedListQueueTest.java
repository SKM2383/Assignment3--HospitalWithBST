/**
 *   APPLICATION: LoginSystem
 *         CLASS: LinkedListQueueTest
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *  TEST LIBRARY: JUnit4
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provides test cases for each method in the LinkedListQueue class
 *       PACKAGE: tests.util.queue
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListQueueTest {
    @Test
    public void enqueue() throws Exception {
        LinkedListQueue<String> linkedQueue = new LinkedListQueue<>();

        linkedQueue.enqueue("This is a test");
        linkedQueue.enqueue("of");
        linkedQueue.enqueue("a linked list queue");

        assertEquals("This is a test", linkedQueue.dequeue());

        linkedQueue.enqueue("hopefully it passes");

        assertEquals("of", linkedQueue.dequeue());
    }

    @Test
    public void dequeue() throws Exception {
        LinkedListQueue<String> linkedQueue = new LinkedListQueue<>();

        linkedQueue.enqueue("Testing dequeue");
        assertEquals("Testing dequeue", linkedQueue.dequeue());

        linkedQueue.enqueue("And again");
        assertEquals("And again", linkedQueue.dequeue());
    }

    @Test
    public void isEmpty() throws Exception {
        LinkedListQueue<String> linkedQueue = new LinkedListQueue<>();

        assertTrue(linkedQueue.isEmpty());

        linkedQueue.enqueue("Not empty");

        assertFalse(linkedQueue.isEmpty());

        linkedQueue.dequeue();

        assertTrue(linkedQueue.isEmpty());
    }

    @Test
    public void size() throws Exception {
        LinkedListQueue<String> linkedQueue = new LinkedListQueue<>();

        assertEquals(0, linkedQueue.size());

        linkedQueue.enqueue("1");
        linkedQueue.enqueue("2");
        linkedQueue.enqueue("3");
        linkedQueue.enqueue("4");
        linkedQueue.enqueue("5");
        linkedQueue.enqueue("6");
        linkedQueue.enqueue("7");

        assertEquals(7, linkedQueue.size());

        linkedQueue.dequeue();
        linkedQueue.dequeue();

        assertEquals(5, linkedQueue.size());
    }

    @Test(expected = QueueUnderflowException.class)
    public void testForQueueUnderflow(){
        LinkedListQueue<String> linkedQueue = new LinkedListQueue<>();

        linkedQueue.dequeue();
    }
}