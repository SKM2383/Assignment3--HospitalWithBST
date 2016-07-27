/**
 *   APPLICATION: LoginSystem
 *         CLASS: BinarySearchTree
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provides an implementation of a Binary Search Tree data structure. Every element put within must implement Comparable
 *       PACKAGE: controller
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.tree;

import util.queue.LinkedListQueue;

public class BinarySearchTree<T extends Comparable<T>> implements IBinarySearchTree<T>{
   protected BinaryNode<T> rootNode;
   
   protected LinkedListQueue<T> inorder;
   protected LinkedListQueue<T> preorder;
   protected LinkedListQueue<T> postorder;
   
   protected int numOfNodes;
   protected boolean elementFound;

   public final int INORDER = 1;
   public final int PREORDER = 2;
   public final int POSTORDER = 3;
   
   public BinarySearchTree(){
      rootNode = null;
      numOfNodes = 0;
   }

   @Override
   public boolean isEmpty(){ return (rootNode == null); }

   @Override
   public int size(){ return numOfNodes; }

   @Override
   public boolean contains(T nodeData){
      return recursiveContains(nodeData, rootNode);
   }

   private boolean recursiveContains(T nodeData, BinaryNode<T> currentNode){
      // If the current node is null, the end of the tree has been reached and the element wasn't found
      if(currentNode == null)
         return false;

      // If compareTo returns less than 0, the nodeData is less than, so go to the left subtree
      else if(nodeData.compareTo(currentNode.getData()) < 0)
         return recursiveContains(nodeData, currentNode.getLeftChild());

      // Otherwise progress through the right subtree
      else if(nodeData.compareTo(currentNode.getData()) > 0)
         return recursiveContains(nodeData, currentNode.getRightChild());

      // Else the elements were equal, meaning a match occured
      else
         return true;
   }

   @Override
   public T get(T dataToGet){
      return recursiveGet(dataToGet, rootNode);
   }

   private T recursiveGet(T dataToGet, BinaryNode<T> node){
      // If the current node is null, the end of the tree has been reached and the element wasn't found
      if(node == null)
         return null;

      // If compareTo returns less than 0, the nodeData is less than, so go to the left subtree
      else if(dataToGet.compareTo(node.getData()) < 0)
         return recursiveGet(dataToGet, node.getLeftChild());

      // Otherwise progress through the right subtree
      else if(dataToGet.compareTo(node.getData()) > 0)
         return recursiveGet(dataToGet, node.getRightChild());

      // Else the elements were equal, meaning a match occurred
      else
         return node.getData();
   }

   @Override
   public void add(T dataToAdd){
      rootNode = recursiveAdd(dataToAdd, rootNode);
      numOfNodes++;
   }
   
   private BinaryNode<T> recursiveAdd(T dataToAdd, BinaryNode<T> currentNode){
      // If currentNode is null that means the tree was empty, so just add this new element as the root
      if(currentNode == null)
         currentNode = new BinaryNode<T>(dataToAdd);

      // If compareTo returns less than 0, the element belongs in the left subtree, then the left tree reference needs to be updated
      else if(dataToAdd.compareTo(currentNode.getData()) <= 0)
         currentNode.setLeftChild(recursiveAdd(dataToAdd, currentNode.getLeftChild()));

      // Otherwise the element is in the right subtree, and the right subtree reference needs to be updated
      else if(dataToAdd.compareTo(currentNode.getData()) > 0)
         currentNode.setRightChild(recursiveAdd(dataToAdd, currentNode.getRightChild()));

      return currentNode;
   }

   @Override
   public boolean remove(T dataToRemove){
      rootNode = recursiveRemove(dataToRemove, rootNode);
      
      // If the element was found and removed, then the numOfNodes needs to be updated
      if(elementFound){
         numOfNodes--;
         return true;
      }
      else
         return false;
   }
   
   private BinaryNode<T> recursiveRemove(T dataToRemove, BinaryNode<T> node){
      if(node == null)
         elementFound = false;
      else if(dataToRemove.compareTo(node.getData()) < 0)
         node.setLeftChild(recursiveRemove(dataToRemove, node.getLeftChild()));
      else if(dataToRemove.compareTo(node.getData()) > 0)
         node.setRightChild(recursiveRemove(dataToRemove, node.getRightChild()));
      else{
         node = removeNode(node);
         elementFound = true;
      }
      
      return node;
   }
   
   private BinaryNode<T> removeNode(BinaryNode<T> nodeToRemove){
      T predecessorData;
      
      if(nodeToRemove.getLeftChild() == null)
         return nodeToRemove.getRightChild();
      else if(nodeToRemove.getRightChild() == null)
         return nodeToRemove.getLeftChild();
      else{
         // Get the predecessor data
         predecessorData = getPredecessor(nodeToRemove.getLeftChild());
         
         // Replace the node to remove with the predecessor, then remove the predecessor node (a leaf node)
         nodeToRemove.setData(predecessorData);
         nodeToRemove.setLeftChild(recursiveRemove(predecessorData, nodeToRemove.getLeftChild()));
         
         return nodeToRemove;
      }
   }
   
   private T getPredecessor(BinaryNode<T> node){
      while(node.getRightChild() != null){
         node = node.getRightChild();
      }
      
      return node.getData();
   }

   @Override
   public int reset(int order){
      if(order == INORDER){
         inorder = new LinkedListQueue<>();
         generateInorderQueue(rootNode);
      }
      else if(order == PREORDER){
         preorder = new LinkedListQueue<>();
         generatePreorderQueue(rootNode);
      }
      else if(order == POSTORDER){
         postorder = new LinkedListQueue<>();
         generatePostorderQueue(rootNode);
      }
      
      return numOfNodes;
   }
   
   private void generateInorderQueue(BinaryNode<T> node){
      if(node != null){
         generateInorderQueue(node.getLeftChild());
         inorder.enqueue(node.getData());
         generateInorderQueue(node.getRightChild());
      }
   }
   
   private void generatePreorderQueue(BinaryNode<T> node){
      if(node != null){
         preorder.enqueue(node.getData());
         generatePreorderQueue(node.getLeftChild());
         generatePreorderQueue(node.getRightChild());
      }
   }
   
   private void generatePostorderQueue(BinaryNode<T> node){
      if(node != null){
         generatePostorderQueue(node.getLeftChild());
         generatePostorderQueue(node.getRightChild());
         postorder.enqueue(node.getData());
      }
   }

   @Override
   public T getNext(int order){
      if(order == INORDER)
         return inorder.dequeue();
      else if(order == PREORDER)
         return preorder.dequeue();
      else if(order == POSTORDER)
         return postorder.dequeue();
      else
         return null;
   }

   @Override
   public String toString(){
      return inorder.toString();
   }
}