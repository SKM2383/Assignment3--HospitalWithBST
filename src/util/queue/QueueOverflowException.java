/**
 *   APPLICATION: LoginSystem
 *         CLASS: QueueOverflowException
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: A RuntimeException subclass that is used to indicate when an element tries to be enqueued to a full bounded queue
 *       PACKAGE: util.queue
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
*/

package util.queue;

public class QueueOverflowException extends RuntimeException{
    public QueueOverflowException(){ super(); }

    public QueueOverflowException(String msg){ super(msg); }
}
