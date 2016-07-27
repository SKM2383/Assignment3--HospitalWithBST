/**
 *   APPLICATION: LoginSystem
 *     INTERFACE: IArrayQueue
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 *       PURPOSE: Provides a specification for a bounded queue that is implemented using arrays
 *       PACKAGE: util.queue
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.queue;

public interface IArrayQueue<T> extends IQueue<T> {
    boolean isFull();

    void enqueue(T element) throws QueueOverflowException;
}
