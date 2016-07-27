/**
 *   APPLICATION: LoginSystem
 *         CLASS: ArrayOrderedList
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: An unbounded list created with an array. This list is ordered using the compareTo method of the
 *                generic object T passed into the class
 *       PACKAGE: util.list
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.list;

import java.io.Serializable;

public class ArrayOrderedList<T> extends ArrayUnsortedList<T> implements IList<T>, Serializable{

    public ArrayOrderedList(){
        super();
    }

    public ArrayOrderedList(int initialSize){
        super(initialSize);
    }

    @Override
    protected void search(T elementToFind){
        int firstIndex = 0;
        int lastIndex = elementAmount - 1;

        Comparable comparableElement = (Comparable) elementToFind;

        elementFound = false;

        while(firstIndex <= lastIndex){
            elementLocationIndex = (firstIndex + lastIndex) / 2;
            int comparisonResult = comparableElement.compareTo(list[elementLocationIndex]);

            if(comparisonResult == 0){
                elementFound = true;
                break;
            }
            else if(comparisonResult < 0){
                lastIndex = elementLocationIndex - 1;
            }
            else{
                firstIndex = elementLocationIndex + 1;
            }
        }
    }

    @Override
    public boolean contains(T elementToFind) {
        this.search(elementToFind);

        return elementFound;
    }

    @Override
    public T get(T elementToGet) {
        this.search(elementToGet);

        return elementFound ? list[elementLocationIndex] : null;
    }

    @Override
    public void add(T newElement) {
        if(elementAmount == list.length){
            expandList();
        }

        /** If there aren't any elements in the list, then just set the first index to newElement.
         * Without this check a NullPointerException will be thrown when the list is empty because
         * later in the method, each list element will try to be cast to Comparable, but if the list
         * is all null values, the cast will throw an exception*/
        if(elementAmount == 0){
            list[0] = newElement;
            elementAmount++;
            return;
        }

        int insertLocation = 0;
        /**Loop through the list and cast each list element to a Comparable object. If the
         * Comparable object and newElement return 0 from .compareTo then they are duplicates
         * so throw an exception. If the compareTo result is negative it means that newElement
         * belongs further down the list, so continue the loop. If the result of compareTo is
         * positive, then the elements from that index need to be shifted and the newElement inserted*/
        while(insertLocation < elementAmount){
            int compareResult = ((Comparable) list[insertLocation]).compareTo(newElement);

            if(compareResult == 0){
                throw new DuplicateElementException("Duplicate entry already in the list");
            }
            else if(compareResult < 0){
                insertLocation++;
            }
            else{
                break;
            }
        }

        for(int index = elementAmount; index > insertLocation; index--){
            list[index] = list[index - 1];
        }

        list[insertLocation] = newElement;
        elementAmount++;
    }

    @Override
    public boolean remove(T elementToRemove) {
        this.search(elementToRemove);

        if(elementFound){
            /**Starting from where the element to remove is up to the second from the last list element,
             * shift the list to the left by setting the current index to the element of the index to the right*/
            for(int index = elementLocationIndex; index <= elementAmount - 2; index++){
                list[index] = list[index + 1];
            }

            /**After shifting the last element needs to be made null, otherwise it would be duplicated in last
             * two indexes*/
            list[elementAmount - 1] = null;

            elementAmount--;
        }

        return elementFound;
    }
}
