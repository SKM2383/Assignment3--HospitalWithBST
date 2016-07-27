/**
 *   APPLICATION: LoginSystem
 *         CLASS: ArrayStack
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Defines a bounded stack implementation using arrays, this stack has a max size
 *       PACKAGE: util.stack
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.stack;

import util.LinkedListNode;

import java.io.Serializable;

public class ArrayStack<T> implements IArrayStack<T>, Serializable{
    private T[] arrayStack;
    private int topIndex = -1;

    public ArrayStack(){
        arrayStack = (T[]) new Object[50];
    }

    public ArrayStack(int max){
        arrayStack = (T[]) new Object[max];
    }

    @Override
    public boolean isFull() {
        return (topIndex == arrayStack.length - 1);
    }

    @Override
    public boolean isEmpty() {
        return (topIndex == -1);
    }

    @Override
    public T top() {
        if(isEmpty()){
            throw new StackUnderflowException("Stack is empty. Could not perform top operation");
        }

        return arrayStack[topIndex];
    }

    @Override
    public void pop() {
        if(isEmpty()){
            throw new StackUnderflowException("Stack is empty. Could not perform pop operation");
        }

        arrayStack[topIndex] = null;
        topIndex--;
    }

    @Override
    public void push(T newElement) {
        if(isFull()){
            throw new StackOverflowException("Stack is full. Could not perform push operation");
        }

        topIndex++;
        arrayStack[topIndex] = newElement;
    }

    @Override
    public int size(){
        return topIndex + 1;
    }

    @Override
    public String toString(){
        String representation = "";

        if(!this.isEmpty()){
            for(int i = topIndex; i >= 0; i--){
                representation += (arrayStack[i] + "\n");
            }
        }

        return representation;
    }
}
