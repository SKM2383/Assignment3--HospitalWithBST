/**
 *   APPLICATION: LoginSystem
 *         CLASS: LinkedListStack
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provides an unbounded stack ADT using a linked list
 *       PACKAGE: util.stack
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.stack;

import util.LinkedListNode;

import java.io.Serializable;

public class LinkedListStack<T> implements IStack<T>, Serializable{
    private LinkedListNode<T> head;
    private int listSize = 0;

    public LinkedListStack(){
        head = null;
    }

    @Override
    public T top() {
        return head.getData();
    }

    @Override
    public void pop() {
        if(isEmpty()){
            throw new StackUnderflowException("LinkListStack is empty. Could not perform pop.");
        }

        // Point the head to the second element from the top, allowing the top to be garbage collected
        head = head.getLink();

        // Then decrement the size by 1
        listSize--;
    }

    @Override
    public void push(T element) {
        // Create a new node
        LinkedListNode<T> newNode = new LinkedListNode<>(element);

        // Set the new node link to whatever is currently at the top of the stack
        newNode.setLink(head);

        // Then set the head of the stack to the new node
        head = newNode;

        listSize++;
    }

    @Override
    public int size(){
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        /** If the head doesn't reference a node, that means there is
         * nothing on the top of the stack, otherwise an element is there **/
        return (head == null);
    }

    private String getStackRepresentation(LinkedListNode node){
        /**This private helper method recursively goes through this linked list
         * and extracts the String representation of its data. The recursion stops
         * when the current node link is null, meaning that node is the last element*/
        if(node.getLink() == null){
            return node.getData() + "\n";
        }
        else{
            // By placing the concatenation is this order, the bottom of the stack will be printed on top
            return (node.getData() + "\n") + getStackRepresentation(node.getLink());
        }
    }

    @Override
    public String toString(){
        return getStackRepresentation(head);
    }
}
