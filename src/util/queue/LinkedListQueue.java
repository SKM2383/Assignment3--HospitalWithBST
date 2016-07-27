/**
 *   APPLICATION: LoginSystem
 *         CLASS: LinkedListQueue
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provide a queue adt implementation using a linked list
 *       PACKAGE: util.queue
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.queue;

import util.LinkedListNode;

import java.io.Serializable;

public class LinkedListQueue<T> implements IQueue<T>, Serializable{
    private LinkedListNode head;
    private LinkedListNode tail;
    private int queueSize = 0;

    public LinkedListQueue(){
        head = null;
        tail = null;
    }

    @Override
    public void enqueue(T element) {
        // Create a new node
        LinkedListNode<T> newNode = new LinkedListNode<>(element);

        // If the tail is null, that means the queue is empty and this is the first node being added
        if(tail == null) {
            // When the queue is empty, the head must also reference the new node
            head = newNode;
        }
        else{
            // Otherwise just link the current last node to the new node to set it at the end
            tail.setLink(newNode);
        }

        // Update the tail to point to the new node that was placed at the end of the queue
        tail = newNode;

        queueSize++;
    }

    @Override
    public T dequeue() {
        if(isEmpty()){
            throw new QueueUnderflowException("Queue is empty. Could not dequeue");
        }

        // Hold the data of the node that is about to be dereferenced
        T oldHeadData = (T) head.getData();

        // Have the head reference the next node in the queue or null is there aren't any
        head = head.getLink();

        // If the head was set to null, which means the only node was dequeued, then
        // the tail must also be set to null
        if(head == null){
            tail = null;
        }

        queueSize--;

        return oldHeadData;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public int size() {
        return queueSize;
    }

    @Override
    public String toString(){
        String representation = "";

        LinkedListNode<T> currentNode = head;

        while(currentNode != null){
            representation += (currentNode.getData() + "\n");
            currentNode = currentNode.getLink();
        }

        return representation;
    }
}
