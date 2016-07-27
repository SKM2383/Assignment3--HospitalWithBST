/**
 *   APPLICATION: LoginSystem
 *     INTERFACE: IQueue
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 *       PURPOSE: An interface to provide specifications for all basic queues, this can be extended by other interfaces
 *                to provide specifications for more complex queues
 *       PACKAGE: util.queue
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.queue;

public interface IQueue<T> {
    void enqueue(T element);

    T dequeue() throws QueueUnderflowException;

    boolean isEmpty();

    int size();

    String toString();
}
