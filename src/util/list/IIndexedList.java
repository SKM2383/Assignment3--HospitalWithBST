/**
 *   APPLICATION: LoginSystem
 *     INTERFACE: IIndexedList
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 *       PURPOSE: This interface specifies the methods for indexed lists, which means including an index argument for
 *                certain list methods
 *       PACKAGE: util.list
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.list;

public interface IIndexedList<T> extends IList<T> {
    void add(int index, T elementToAdd);

    T set(int index, T replacementElement);

    T get(int index);

    int indexOf(T element);

    T remove(int index);
}
