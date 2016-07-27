/**
 *   APPLICATION: LoginSystem
 *         CLASS: UserIO
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provides the ability to inject and extract Objects from a binary file
 *       PACKAGE: model
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
*/

package model;

import java.io.*;

public class UserIO {
    public static void writeUsers(Object data) throws FileNotFoundException, IOException{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("users.dat"));
        output.writeObject(data);
    }

    public static Object readUsers() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("users.dat"));
        return input.readObject();
    }
}
