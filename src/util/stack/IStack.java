/**
 *   APPLICATION: LoginSystem
 *     INTERFACE: IStack
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 *       PURPOSE: Interface that specifies the methods that are common to all stacks
 *       PACKAGE: util.stack
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.stack;

public interface IStack<T> {
    T top();

    void pop();

    void push(T element);

    int size();

    boolean isEmpty();

    String toString();
}
