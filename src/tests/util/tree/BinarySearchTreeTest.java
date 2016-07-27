/**
 *   APPLICATION: LoginSystem
 *         CLASS: BinarySearchTreeTest
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Provides test cases for the different operations within util.tree.BinarySearchTree
 *       PACKAGE: controller
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package util.tree;

import org.junit.Test;
import util.queue.QueueUnderflowException;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {
    @Test
    public void isEmpty() throws Exception {
        BinarySearchTree<String> binaryTree = new BinarySearchTree<>();

        assertTrue(binaryTree.isEmpty());

        binaryTree.add("Hello");

        assertFalse(binaryTree.isEmpty());
    }

    @Test
    public void size() throws Exception {
        BinarySearchTree<String> binaryTree = new BinarySearchTree<>();

        assertEquals(0, binaryTree.size());

        binaryTree.add("Hello");
        binaryTree.add("World");
        binaryTree.add("Sweet");

        assertEquals(3, binaryTree.size());

        binaryTree.remove("World");

        assertEquals(2, binaryTree.size());
    }

    @Test
    public void contains() throws Exception {
        BinarySearchTree<String> binaryTree = new BinarySearchTree<>();

        assertFalse(binaryTree.contains("False"));

        binaryTree.add("Hello");
        binaryTree.add("World");
        binaryTree.add("Sweet");
        binaryTree.add("Old");

        assertTrue(binaryTree.contains("Hello"));
        assertTrue(binaryTree.contains("Sweet"));
    }

    @Test
    public void get() throws Exception {
        BinarySearchTree<String> binaryTree = new BinarySearchTree<>();

        binaryTree.add("Hello");
        binaryTree.add("World");
        binaryTree.add("Sweet");
        binaryTree.add("Old");

        assertEquals("World", binaryTree.get("World"));
        assertNull(binaryTree.get("Should Get Null"));
    }

    @Test
    public void add() throws Exception {
        BinarySearchTree<String> binaryTree = new BinarySearchTree<>();

        binaryTree.add("Hello");
        binaryTree.add("World");
        binaryTree.add("Sweet");
        binaryTree.add("Old");

        binaryTree.reset(binaryTree.INORDER);

        String correctTreeString = "Hello\nOld\nSweet\nWorld\n";

        assertEquals(correctTreeString, binaryTree.toString());
    }

    @Test
    public void remove() throws Exception {
        BinarySearchTree<String> binaryTree = new BinarySearchTree<>();

        binaryTree.add("Hello");
        binaryTree.add("World");
        binaryTree.add("Cruel");
        binaryTree.add("Sweet");
        binaryTree.add("Yellow");
        binaryTree.add("Tweet");
        binaryTree.add("Eskimo");

        // Remove a node with two children
        assertTrue(binaryTree.remove("World"));

        binaryTree.reset(binaryTree.INORDER);

        String correctTreeFormat = "Cruel\nEskimo\nHello\nSweet\nTweet\nYellow\n";
        assertEquals(correctTreeFormat, binaryTree.toString());

        // Remove a node with one child
        binaryTree.remove("Cruel");

        binaryTree.reset(binaryTree.INORDER);

        correctTreeFormat = "Eskimo\nHello\nSweet\nTweet\nYellow\n";
        assertEquals(correctTreeFormat, binaryTree.toString());

        // Remove a leaf node
        binaryTree.remove("Yellow");

        binaryTree.reset(binaryTree.INORDER);

        correctTreeFormat = "Eskimo\nHello\nSweet\nTweet\n";
        assertEquals(correctTreeFormat, binaryTree.toString());

        // Make sure false is returned if an element wasn't in the list
        assertFalse(binaryTree.remove("Element that doesn't exist"));
    }

    @Test
    public void reset() throws Exception {
        BinarySearchTree<String> binaryTree = new BinarySearchTree<>();

        binaryTree.add("Hello");
        binaryTree.add("World");
        binaryTree.add("Cruel");
        binaryTree.add("Sweet");
        binaryTree.add("Yellow");
        binaryTree.add("Tweet");
        binaryTree.add("Eskimo");

        binaryTree.reset(binaryTree.INORDER);
        assertEquals("Cruel", binaryTree.getNext(binaryTree.INORDER));
        assertEquals("Eskimo", binaryTree.getNext(binaryTree.INORDER));

        binaryTree.reset(binaryTree.PREORDER);
        assertEquals("Hello", binaryTree.getNext(binaryTree.PREORDER));
        assertEquals("Cruel", binaryTree.getNext(binaryTree.PREORDER));

        binaryTree.reset(binaryTree.POSTORDER);
        assertEquals("Eskimo", binaryTree.getNext(binaryTree.POSTORDER));
        assertEquals("Cruel", binaryTree.getNext(binaryTree.POSTORDER));
    }

    @Test
    public void getNext() throws Exception {
        BinarySearchTree<String> binaryTree = new BinarySearchTree<>();

        binaryTree.add("Hello");
        binaryTree.add("World");
        binaryTree.add("Cruel");
        binaryTree.add("Sweet");
        binaryTree.add("Yellow");
        binaryTree.add("Tweet");
        binaryTree.add("Eskimo");

        binaryTree.reset(binaryTree.PREORDER);

        assertEquals("Hello", binaryTree.getNext(binaryTree.PREORDER));
        assertEquals("Cruel", binaryTree.getNext(binaryTree.PREORDER));
        assertEquals("Eskimo", binaryTree.getNext(binaryTree.PREORDER));
        assertEquals("World", binaryTree.getNext(binaryTree.PREORDER));
        assertEquals("Sweet", binaryTree.getNext(binaryTree.PREORDER));
        assertEquals("Tweet", binaryTree.getNext(binaryTree.PREORDER));
        assertEquals("Yellow", binaryTree.getNext(binaryTree.PREORDER));
    }

    @Test(expected = QueueUnderflowException.class)
    public void getNextExceptionTest() throws Exception{
        BinarySearchTree<String> binaryTree = new BinarySearchTree<>();

        binaryTree.add("Hello");
        binaryTree.add("World");

        binaryTree.reset(binaryTree.PREORDER);

        binaryTree.getNext(binaryTree.PREORDER);
        binaryTree.getNext(binaryTree.PREORDER);

        // Cause an underflow
        binaryTree.getNext(binaryTree.PREORDER);
    }
}