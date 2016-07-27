/**
 *   APPLICATION: LoginSystem
 *         CLASS: ArrayQueueTest
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *  TEST LIBRARY: JUnit4
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provides test cases for each method implementation within the ArrayQueue class
 *       PACKAGE: tests.util.queue
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.queue;

import org.junit.Test;

import static org.junit.Assert.*;


public class ArrayQueueTest {
    @Test
    public void isFull() throws Exception {
        ArrayQueue<String> arrayQueue = new ArrayQueue<>(3);

        assertFalse(arrayQueue.isFull());

        arrayQueue.enqueue("Testing");
        arrayQueue.enqueue("isFull");
        arrayQueue.enqueue("Method");

        assertTrue(arrayQueue.isFull());
    }

    @Test
    public void isEmpty() throws Exception {
        ArrayQueue<String> arrayQueue = new ArrayQueue<>(5);

        assertTrue(arrayQueue.isEmpty());

        arrayQueue.enqueue("Testing");

        assertFalse(arrayQueue.isEmpty());
    }

    @Test
    public void enqueue() throws Exception {
        ArrayQueue<String> arrayQueue = new ArrayQueue<>(5);

        arrayQueue.enqueue("Testing");

        assertEquals("Testing", arrayQueue.dequeue());

        arrayQueue.enqueue("Checking");
        arrayQueue.enqueue("Queue");
        arrayQueue.enqueue("Order");

        assertEquals("Checking", arrayQueue.dequeue());
    }

    @Test
    public void dequeue() throws Exception {
        ArrayQueue<String> arrayQueue = new ArrayQueue<>(5);

        arrayQueue.enqueue("This is an ArrayQueue Unit Test");

        assertEquals("This is an ArrayQueue Unit Test", arrayQueue.dequeue());
    }

    @Test
    public void size() throws Exception {
        ArrayQueue<String> arrayQueue = new ArrayQueue<>(6);

        arrayQueue.enqueue("1");
        arrayQueue.enqueue("2");

        assertEquals(2, arrayQueue.size());

        arrayQueue.enqueue("3");
        arrayQueue.enqueue("4");
        arrayQueue.enqueue("5");
        arrayQueue.enqueue("6");

        assertEquals(6, arrayQueue.size());

        arrayQueue.dequeue();

        assertEquals(5, arrayQueue.size());
    }

    @Test(expected = QueueUnderflowException.class)
    public void testForQueueUnderflow(){
        ArrayQueue<String> arrayQueue = new ArrayQueue<>();

        arrayQueue.dequeue();
    }

    @Test(expected = QueueOverflowException.class)
    public void testForQueueOverflow(){
        ArrayQueue<String> arrayQueue = new ArrayQueue<>(1);

        arrayQueue.enqueue("A");

        // Trigger overflow
        arrayQueue.enqueue("Overflow");
    }
}
