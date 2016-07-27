/**
 *   APPLICATION: LoginSystem
 *         CLASS: ArrayQueue
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: This class represents a bounded queue that has a max size, if an element tries to be enqueued when
 *                the queue is full, a QueueOverflowException occurs
 *       PACKAGE: util.queue
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.queue;

import java.io.Serializable;

public class ArrayQueue<T> implements IArrayQueue<T>, Serializable{

    private T[] arrayQueue;
    private int queueElements = 0;
    private int front = 0;
    private int rear = -1;

    public ArrayQueue(){
        arrayQueue = (T[]) new Object[50];
    }

    public ArrayQueue(int max){
        arrayQueue = (T[]) new Object[max];
    }

    @Override
    public boolean isFull() {
        return (queueElements == arrayQueue.length);
    }

    @Override
    public boolean isEmpty() {
        return (queueElements == 0);
    }

    @Override
    public void enqueue(T newElement) throws QueueOverflowException {
        if(isFull()){
            throw new QueueOverflowException("The queue is full. Enqueue operation failed");
        }

        /**The use of modulo allows us to see how close the rear index is to the end of the arrayQueue
         * If it goes beyond its bounds, the modulo operation will return the remainder [how far out of
         * bounds we went] and will allow the rear to reference the beginning of the array. This allows
         * values to wrap around the queue*/
        rear = (rear + 1) % arrayQueue.length;
        arrayQueue[rear] = newElement;
        queueElements++;
    }

    @Override
    public T dequeue() {
        if(isEmpty()){
            throw new QueueUnderflowException("Queue is empty. Dequeue operation failed");
        }

        // Get the value to return
        T dequeuedValue = arrayQueue[front];

        // Set the dequeued array space to null in order for old value to be garbage collected
        arrayQueue[front] = null;
        front = (front + 1) % arrayQueue.length;

        queueElements--;

        return dequeuedValue;
    }

    @Override
    public int size() {
        return queueElements;
    }

    @Override
    public String toString(){
        // Create a String object to add the queue elements to
        String queueRepresentation = "";

        /**In order to print this ArrayQueue you need to sequentially
         * access each index starting from the front variable up to
         * the number of queueElements. While this happens the loop
         * checks if the current index of the next element is outside
         * the array bounds and if so wrap it around to the front using
         * the modulo tricks from the methods above*/
        for(int i = 0;i < queueElements; i++){
            int index = i + front;

            if(index > arrayQueue.length - 1){
                index %= arrayQueue.length;
                queueRepresentation += (arrayQueue[index] + "\n");
            }
            else{
                queueRepresentation += (arrayQueue[index] + "\n");
            }
        }

        return queueRepresentation;
    }
}
