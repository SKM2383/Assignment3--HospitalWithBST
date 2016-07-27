/**
 *   APPLICATION: LoginSystem
 *         CLASS: UserStorage
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: This class is used to act as the database when it is loaded into the program. The
 *                actual database is a tree structure of Users that is sorted by username.
 *       PACKAGE: model
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
*/

package model;



import util.list.ArrayOrderedList;

public class UserStorage {
    private static ArrayOrderedList<User> userDatabase = new ArrayOrderedList<>(10);

    public static ArrayOrderedList<User> getUserDatabase() {
        return userDatabase;
    }

    public static void setUserDatabase(ArrayOrderedList<User> newUserDatabase) {
        userDatabase = newUserDatabase;
    }
}
