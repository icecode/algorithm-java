package com.icecoder.algorithm.adt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author libing
 * @version 1.0
 * @date 2019-08-01 23:09
 */
public class BinaryTreeTest {

    @Test
    public void find() {
        BinaryTree<Integer> b = new BinaryTree<Integer>();
        Assertions.assertEquals(b.insert(1), b.find(1));
    }

    @Test
    public void insert() {
        BinaryTree<Integer> b = new BinaryTree<Integer>();
        Tree.Node<Integer> b1 = b.insert(1);
        Tree.Node<Integer> b2 = b.insert(2);
        Tree.Node<Integer> b3 = b.insert(3);
        Assertions.assertEquals(3, b.size());
        Assertions.assertEquals(b1, b.find(1));
        Assertions.assertEquals(b2, b.find(2));
        Assertions.assertEquals(b3, b.find(3));
    }

    @Test
    public void root() {
        BinaryTree<Integer> b = new BinaryTree<Integer>();
        Assertions.assertEquals(b.insert(1), b.root());
    }

    @Test
    public void empty() {
        BinaryTree<Integer> b = new BinaryTree<Integer>();
        Assertions.assertTrue(b.empty());
        b.insert(1);
        Assertions.assertFalse(b.empty());
    }

    @Test
    public void preTraverseRecursive() {
        BinaryTree<Integer> b = new BinaryTree<Integer>();
        b.insert(1);
        b.insert(2);
        b.insert(3);
        b.insert(4);
        b.insert(5);
        Assertions.assertEquals(Arrays.asList(1, 2, 3, 4, 5), b.preTraverseRecursive());

        BinaryTree<Integer> b2 = new BinaryTree<Integer>();
        b2.insert(5);
        b2.insert(3);
        b2.insert(4);
        b2.insert(2);
        b2.insert(1);
        b2.insert(7);
        b2.insert(6);
        Assertions.assertEquals(Arrays.asList(5, 3, 2, 1, 4, 7, 6), b2.preTraverseRecursive());
    }
}
