/**
 *   APPLICATION: LoginSystem
 *         CLASS: OrderedLinkedList
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: An unbounded list created with a linked list. This list is ordered using the compareTo method of the
 *                generic object T passed into the class
 *       PACKAGE: util.list
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.list;

import util.LinkedListNode;

import java.io.Serializable;

public class OrderedLinkedList<T extends Comparable<T>> implements IList<T>, Serializable{
	private int elementAmount = 0;
	private boolean elementFound;
	
	private LinkedListNode<T> head;
	private LinkedListNode<T> currentNode;
	private LinkedListNode<T> previousNode;
	private LinkedListNode<T> iteratorLocation;
	
	public OrderedLinkedList(){
		head = null;
		currentNode = null;
		previousNode = null;
		iteratorLocation = null;
	}
	
	@Override
	public int size(){ return elementAmount; }
	
	@Override
	public boolean isEmpty(){ return (elementAmount == 0); }
	
	private void search(T elementToFind){
		elementFound = false;
		
		currentNode = head;
		previousNode = head;
		while(currentNode != null){
			if(currentNode.getData().equals(elementToFind)){
				elementFound = true;
				break;
			}
			else{
				previousNode = currentNode;
				currentNode = currentNode.getLink();
			}
		}
	}
	
	@Override
	public void add(T newElement){
		if(this.contains(newElement))
			throw new DuplicateElementException("Element is already in the list");
		
		currentNode = head;
		previousNode = null;

		while(currentNode != null){
			T currentElement = currentNode.getData();

			if(currentElement.compareTo(newElement) < 0){
				previousNode = currentNode;
				currentNode = currentNode.getLink();
			}
			else
				break;
		}

		LinkedListNode<T> newNode = new LinkedListNode<>(newElement);

		if(previousNode == null){
			newNode.setLink(head);
			head = newNode;
			iteratorLocation = head;
		}
		else{
			newNode.setLink(currentNode);
			previousNode.setLink(newNode);
		}

		elementAmount++;
	}
	
	@Override
	public boolean remove(T elementToRemove){
		search(elementToRemove);
		
		if(elementFound){
			if(head == currentNode)
				head = head.getLink();
			else
				previousNode.setLink(currentNode.getLink());

			elementAmount--;

			return true;
		}
		else
			return false;
	}

	@Override
	public boolean contains(T element){
		search(element);
		
		return elementFound;
	}
	
	@Override
	public T get(T elementToGet){
		search(elementToGet);
		
		return (elementFound) ? currentNode.getData() : null;
	}
	
	@Override
	public void reset(){ iteratorLocation = head; }
	
	@Override
	public T getNext(){
		T nextElement = iteratorLocation.getData();
		
		if(iteratorLocation.getLink() == null)
			iteratorLocation = head;
		else{
			iteratorLocation = iteratorLocation.getLink();
		}

		return nextElement;
	}
	
	@Override
	public String toString(){
		String representation = "";
		
		LinkedListNode<T> loopNode = head;
		while(loopNode != null){
			representation += (loopNode.getData() + "\n");
			loopNode = loopNode.getLink();
		}
		
		return representation;
	}
}
