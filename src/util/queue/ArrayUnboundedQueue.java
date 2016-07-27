/**
 *   APPLICATION: LoginSystem
 *         CLASS: ArrayUnboundedQueue
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provide a array implemented queue that can dynamically increase its size when its max length is reached
 *       PACKAGE: util.queue
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.queue;

public class ArrayUnboundedQueue<T> implements IQueue<T>{
    private T[] queue;
    private int elementAmount = 0;
    private int front = 0;
    private int rear;

    public ArrayUnboundedQueue(){
        queue = (T[]) new Object[50];
        rear = 49;
    }

    public ArrayUnboundedQueue(int initSize) {
        queue = (T[]) new Object[initSize];
        rear = initSize - 1;
    }

    private void increaseSize(){
        T[] largerQueue = (T[]) new Object[queue.length * 2];

        for(int index = 0; index < elementAmount; index++){
            largerQueue[index] = queue[index];
        }

        queue = largerQueue;
        front = 0;
        rear = elementAmount - 1;
    }

    @Override
    public void enqueue(T element) {
        if(elementAmount == queue.length){
            increaseSize();
        }

        rear = (rear + 1) % queue.length;
        queue[rear] = element;

        elementAmount++;
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if(isEmpty()){
            throw new QueueUnderflowException("No elements in the queue. Operation dequeue failed");
        }

        T dequeuedData = queue[front];

        queue[front] = null;
        front = (front + 1) % queue.length;

        elementAmount--;

        return dequeuedData;
    }

    @Override
    public boolean isEmpty() { return (elementAmount == 0); }

    @Override
    public int size() { return elementAmount; }
}
