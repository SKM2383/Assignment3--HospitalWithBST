/**
 *   APPLICATION: LoginSystem
 *         CLASS: ArrayUnboundedQueueTest
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *  TEST LIBRARY: JUnit4
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provides test cases for each method implementation in the ArrayUnboundedQueue class
 *       PACKAGE: tests.util.queue
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.queue;

import org.junit.Test;

import static org.junit.Assert.*;


public class ArrayUnboundedQueueTest {
    @Test
    public void enqueue() throws Exception {
        ArrayUnboundedQueue<String> arrayUnboundedQueue = new ArrayUnboundedQueue<>(10);

        arrayUnboundedQueue.enqueue("A");
        arrayUnboundedQueue.enqueue("B");
        arrayUnboundedQueue.enqueue("C");

        assertEquals("A", arrayUnboundedQueue.dequeue());

        arrayUnboundedQueue.enqueue("D");
        arrayUnboundedQueue.enqueue("E");
        arrayUnboundedQueue.enqueue("F");
        arrayUnboundedQueue.enqueue("G");
        arrayUnboundedQueue.enqueue("H");

        assertEquals("B", arrayUnboundedQueue.dequeue());
        assertEquals("C", arrayUnboundedQueue.dequeue());
    }

    @Test
    public void dequeue() throws Exception {
        ArrayUnboundedQueue<String> arrayUnboundedQueue = new ArrayUnboundedQueue<>();

        arrayUnboundedQueue.enqueue("C");
        arrayUnboundedQueue.enqueue("A");
        arrayUnboundedQueue.enqueue("T");

        assertEquals("C", arrayUnboundedQueue.dequeue());
        assertEquals("A", arrayUnboundedQueue.dequeue());
        assertEquals("T", arrayUnboundedQueue.dequeue());
    }

    @Test
    public void isEmpty() throws Exception {
        ArrayUnboundedQueue<Integer> arrayUnboundedQueue = new ArrayUnboundedQueue<>();

        arrayUnboundedQueue.enqueue(56);
        arrayUnboundedQueue.enqueue(32);

        assertFalse(arrayUnboundedQueue.isEmpty());

        arrayUnboundedQueue.dequeue();
        arrayUnboundedQueue.dequeue();

        assertTrue(arrayUnboundedQueue.isEmpty());
    }

    @Test
    public void size() throws Exception {
        ArrayUnboundedQueue<Integer> arrayUnboundedQueue = new ArrayUnboundedQueue<>();

        arrayUnboundedQueue.enqueue(421);
        arrayUnboundedQueue.enqueue(876);
        arrayUnboundedQueue.enqueue(8);
        arrayUnboundedQueue.enqueue(909);

        assertEquals(4, arrayUnboundedQueue.size());

        arrayUnboundedQueue.dequeue();
        arrayUnboundedQueue.dequeue();

        assertEquals(2, arrayUnboundedQueue.size());

        arrayUnboundedQueue.dequeue();
        arrayUnboundedQueue.dequeue();

        assertEquals(0, arrayUnboundedQueue.size());
    }

    @Test(expected = QueueUnderflowException.class)
    public void testForQueueUnderflow(){
        ArrayUnboundedQueue<Integer> arrayUnboundedQueue = new ArrayUnboundedQueue<>();

        arrayUnboundedQueue.dequeue();
    }
}