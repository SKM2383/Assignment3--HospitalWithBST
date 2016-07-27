/**
 *   APPLICATION: LoginSystem
 *     INTERFACE: IList
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 *       PURPOSE: this interface specifies the methods that all list classes must implement
 *       PACKAGE: util.list
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.list;

public interface IList<T> {
    int size();

    void add(T newElement);

    boolean remove(T elementToRemove);

    boolean contains(T elementToFind);

    boolean isEmpty();

    T get(T elementToGet);

    String toString();

    void reset();

    T getNext();
}
