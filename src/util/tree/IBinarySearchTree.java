/**
 *   APPLICATION: LoginSystem
 *         CLASS: IBinarySearchTree
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provides an outline of common operations for Binary Search Trees
 *       PACKAGE: controller
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.tree;

public interface IBinarySearchTree<T extends Comparable<T>>{
   boolean isEmpty();
   
   int size();
   
   boolean contains(T nodeData);
   
   boolean remove(T nodeToRemove);
   
   T get(T nodeDataToGet);
   
   void add(T nodeToAdd);
   
   int reset(int orderType);
   
   T getNext(int orderType);

   String toString();
}