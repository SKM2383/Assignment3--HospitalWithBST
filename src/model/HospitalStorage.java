/**
 *   APPLICATION: LoginSystem
 *         CLASS: HospitalStorage
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Similar in operation to model.UserStorage, this class holds a BinarySearchTree of Hospital objects
 *                and provides methods to retrieve that BinarySearchTree. This class is used by other classes to access
 *                a central BinarySearchTree database.
 *       PACKAGE: controller
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package model;

import util.tree.BinarySearchTree;

import java.io.Serializable;

public class HospitalStorage implements Serializable{
    private static BinarySearchTree<Hospital> hospitalDatabase = new BinarySearchTree<>();

    public static BinarySearchTree<Hospital> getHospitalDatabase(){
        return hospitalDatabase;
    }

    public static void setHospitalDatabase(BinarySearchTree<Hospital> newDatabase){
        hospitalDatabase = newDatabase;
    }
}
