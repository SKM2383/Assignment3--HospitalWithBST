/**
 *   APPLICATION: LoginSystem
 *         CLASS: QueueUnderflowException
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: A RuntimeException subclass that is used to indicate when an empty queue tries to be dequeued
 *       PACKAGE: util.queue
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
*/

package util.queue;

public class QueueUnderflowException extends RuntimeException{
    public QueueUnderflowException(){ super(); }

    public QueueUnderflowException(String msg){ super(msg); }
}
