/**
 *   APPLICATION: LoginSystem
 *         CLASS: BinaryNode
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provide a special linked-list style node that references its predecessor and successor nodes. This
 *                is used to implement the util.tree.BinarySearchTree, but can also be used to implement more advanced
 *                linked-list style structures
 *       PACKAGE: controller
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.tree;

public class BinaryNode<T extends Comparable<T>>{
   private T data;
   private BinaryNode<T> leftChild;
   private BinaryNode<T> rightChild;
   
   public BinaryNode(T initData){
      this.data = initData;
      leftChild = null;
      rightChild = null;
   }
   
   public void setData(T newData){
      this.data = newData;
   }
   
   public T getData(){
      return this.data;
   }
   
   public void setLeftChild(BinaryNode<T> newLeft){
      this.leftChild = newLeft;
   }
   
   public BinaryNode<T> getLeftChild(){
      return this.leftChild;
   }
   
   public void setRightChild(BinaryNode<T> newRight){
      this.rightChild = newRight;
   }
   
   public BinaryNode<T> getRightChild(){
      return this.rightChild;
   }
}