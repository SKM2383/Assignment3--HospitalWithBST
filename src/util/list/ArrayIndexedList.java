/**
 *   APPLICATION: LoginSystem
 *         CLASS: ArrayOrderedList
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Implements a list that is accessed with the aid of indexes. This list is unbounded in size
 *       PACKAGE: util.list
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.list;

import java.io.Serializable;

public class ArrayIndexedList<T> extends ArrayUnsortedList<T> implements IIndexedList<T>, Serializable{

    public ArrayIndexedList(){
        super();
    }

    public ArrayIndexedList(int initialSize){
        super(initialSize);
    }

    @Override
    public void add(int index, T elementToAdd) {
        if(index < 0 || index > elementAmount)
            throw new IndexOutOfBoundsException();

        search(elementToAdd);
        if(elementFound)
            throw new DuplicateElementException("Element is already in the list");

        if(elementAmount == list.length)
            expandList();


        for(int i = elementAmount; i > index; i--){
            list[i] = list[i-1];
        }

        list[index] = elementToAdd;

        elementAmount++;
    }

    @Override
    public T set(int index, T replacementElement) {
        if(index < 0 || index > elementAmount)
            throw new IndexOutOfBoundsException();

        T originalElement = list[index];

        list[index] = replacementElement;

        return originalElement;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index > elementAmount){
            throw new IndexOutOfBoundsException();
        }

        return list[index];
    }

    @Override
    public int indexOf(T element) {
        search(element);

        return (elementFound) ? elementLocationIndex : -1;
    }

    @Override
    public T remove(int index) {
        if(index < 0 || index > elementAmount)
            throw new IndexOutOfBoundsException();

        T originalElement = list[index];

        /**Starting from where the element to remove is up to the second from the last list element,
         * shift the list to the left by setting the current index to the element of the index to the right*/
        for(int i = index; i < elementAmount - 2; i++){
            list[i] = list[i + 1];
        }

        /**After shifting the last element needs to be made null, otherwise it would be duplicated in last
         * two indexes*/
        list[elementAmount - 1] = null;

        elementAmount--;

        return originalElement;
    }

    @Override
    public String toString(){
        String representation = "";

        for(int i = 0; i < elementAmount; i++){
            representation += ("Index " + i + ": " + list[i] + "\n");
        }

        return representation;
    }
}
