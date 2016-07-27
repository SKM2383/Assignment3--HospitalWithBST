/**
 *   APPLICATION: LoginSystem
 *     INTERFACE: IArrayStack
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 *       PURPOSE: Interface used to specify the methods for a bounded array
 *       PACKAGE: util.stack
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.stack;

public interface IArrayStack<T> extends IStack<T> {
    boolean isFull();

    void push(T element);
}
