package com.icecoder.algorithm.ads.stack;

import com.icecoder.algorithm.adt.Tree;

import java.util.Objects;

/**
 * @author libing
 * @version 1.0
 * @date 2019-08-01 20:23
 */
public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    static class Node<T extends Comparable<T>> implements Tree.Node<T> {

        private T data;

        private Node<T> left;

        private Node<T> right;

        protected Node(T data) {
            this.data = data;
        }

        @Override
        public Tree.Node<T> left() {
            return left;
        }

        @Override
        public Tree.Node<T> right() {
            return right;
        }

        @Override
        public T data() {
            return data;
        }

        @Override
        public int compareTo(Tree.Node<T> o) {
            return data.compareTo(o.data());
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Node)) {
                return false;
            }
            return Objects.equals(data, ((Node) obj).data());
        }
    }

    private Node<T> root;

    private int size;

    public BinaryTree() {
        this.size = 0;
    }

    @Override
    public Tree.Node<T> find(T data) {
        return findNode(root, new Node<>(data));
    }

    /**
     * 查找一个节点是否存在
     *
     * @param parentNode
     * @param matchNode
     * @return
     */
    Tree.Node<T> findNode(Tree.Node<T> parentNode, Tree.Node<T> matchNode) {
        if (parentNode == null) {
            return null;
        }
        if (parentNode.equals(matchNode)) {
            return parentNode;
        }
        if (matchNode.compareTo(parentNode) > 0) {
            return findNode(parentNode.right(), matchNode);
        } else {
            return findNode(parentNode.left(), matchNode);
        }
    }

    @Override
    public Tree.Node<T> insert(T data) {
        size++;
        if (root == null) {
            root = new Node<>(data);
            return root;
        }
        /*
         * 先判断Node是否存在
         */
        Tree.Node<T> node = find(data);
        return (node == null ? insertNode(root, new Node<>(data)) : node);
    }

    private Node<T> insertNode(Node<T> parentNode, Node<T> newNode) {
        if (newNode.compareTo(parentNode) > 0) {
            //右树
            if (parentNode.right == null) {
                parentNode.right = newNode;
                return newNode;
            } else {
                return insertNode(parentNode.right, newNode);
            }
        } else {
            //左树
            if (parentNode.left == null) {
                parentNode.left = newNode;
                return newNode;
            } else {
                return insertNode(parentNode.left, newNode);
            }
        }
    }

    @Override
    public Node<T> root() {
        return root;
    }

    @Override
    public void updateHeight(T data) {

    }

    @Override
    public int size() {
        return size;
    }
}
