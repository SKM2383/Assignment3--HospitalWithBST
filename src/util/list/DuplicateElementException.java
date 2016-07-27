/**
 *   APPLICATION: LoginSystem
 *         CLASS: DuplicateElementException
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Exception which is thrown when an element is added to a list that doesn't allow duplicate elements
 *       PACKAGE: util.list
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.list;

public class DuplicateElementException extends RuntimeException{
    public DuplicateElementException(){ super(); }

    public DuplicateElementException(String msg){ super(msg); }
}
