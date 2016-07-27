/**
 *   APPLICATION: LoginSystem
 *         CLASS: LinkedListNode
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Represents a node within a linked list structure. This class holds some data and a link to another
 *                LinkedListNode, which allows for a linked list to be implemented
 *       PACKAGE: util
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util;

public class LinkedListNode<T>{
    private T data;
    private LinkedListNode<T> link;

    public LinkedListNode(T nodeData){
        this.data = nodeData;
        this.link = null;
    }

    public LinkedListNode(T nodeData, LinkedListNode nextNode){
        this.data = nodeData;
        this.link = nextNode;
    }

    public void setData(T newData){
        this.data = newData;
    }

    public T getData(){
        return this.data;
    }

    public void setLink(LinkedListNode newLink){
        this.link = newLink;
    }

    public LinkedListNode getLink(){
        return this.link;
    }
}
