package com.icecoder.algorithm.tree;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author libing
 * @version 1.0
 * @date 2019-05-16 21:13
 */
public class BST<K extends Comparable<K>, V> {


    private static class Node<K extends Comparable<K>, V> {

        public K key;

        public V value;

        public Node<K, V> leftChild;

        public Node<K, V> rightChild;

        public int level;

    }

    private Node<K, V> root;

    public void put(K key, V value) {
        if (root == null) {
            root = new Node<>();
            root.key = key;
            root.value = value;
            root.level = 1;
        } else {
            putNode(root, key, value);
        }
    }

    private void putNode(Node<K, V> currentNode, K key, V value) {
        if (currentNode.key.compareTo(key) > 0) {
            if (currentNode.leftChild == null) {
                currentNode.leftChild = new Node<>();
                currentNode.leftChild.key = key;
                currentNode.leftChild.value = value;
                currentNode.leftChild.level = currentNode.level + 1;
            } else {
                putNode(currentNode.leftChild, key, value);
            }
        } else if (currentNode.key.compareTo(key) < 0) {
            if (currentNode.rightChild == null) {
                currentNode.rightChild = new Node<>();
                currentNode.rightChild.key = key;
                currentNode.rightChild.value = value;
                currentNode.rightChild.level = currentNode.level + 1;
            } else {
                putNode(currentNode.rightChild, key, value);
            }
        } else {
            //相等？
        }
    }

    public V min() {
        return minNode(root).value;
    }

    private Node<K, V> minNode(Node<K, V> node) {
        if (node.leftChild != null) {
            return minNode(node.leftChild);
        } else {
            return node;
        }
    }

    public V max() {
        return maxNode(root).value;
    }

    private Node<K, V> maxNode(Node<K, V> node) {
        if (node.rightChild != null) {
            return maxNode(node.rightChild);
        } else {
            return node;
        }
    }

    @Override
    public String toString() {
        return nodeToString(root);
    }

    public int level() {
        return maxLevel(root, 0);
    }

    private int maxLevel(Node<K, V> node, int preLevel) {
        if (node == null) {
            return preLevel;
        }
        if (node.leftChild == null && node.rightChild == null) {
            return node.level;
        }
        preLevel = node.level;
        int leftMaxLevel = maxLevel(node.leftChild, preLevel);
        int rightMaxLevel = maxLevel(node.rightChild, preLevel);
        return Math.max(leftMaxLevel, rightMaxLevel);
    }

    public String levelToString() {
        List<V> data = new LinkedList<>();



        return Joiner.on(",").join(data);
    }

    private void levelNodeToString(Node<K, V> node, List<V> data) {
        if (node == null) {
            return;
        }
        data.add(node.value);
        levelNodeToString(node.leftChild, data);
        levelNodeToString(node.rightChild, data);
    }

    private String nodeToString(Node<K, V> node) {
        return Strings.repeat("-", node.level) + " " + node.value + "\n"
                + (node.leftChild == null ? "" : "L" + nodeToString(node.leftChild))
                + (node.rightChild == null ? "" :"R" + nodeToString(node.rightChild));
    }

    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        for(int i = 0; i < 40; i++) {
            int v = ThreadLocalRandom.current().nextInt(0, 20);
            bst.put(v,  v + "");
        }
        System.out.println(bst.toString());
        System.out.println("min:" + bst.min());
        System.out.println("max:" + bst.max());
        System.out.println("maxLevel:" + bst.level());
        System.out.println("zeroMaxLevel:" + new BST<>().level());
        System.out.println(bst.levelToString());
    }
}
