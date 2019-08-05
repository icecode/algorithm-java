package com.icecoder.algorithm.adt;

import com.icecoder.algorithm.adt.Tree;

import java.util.LinkedList;
import java.util.List;
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
        if (root == null) {
            root = new Node<>(data);
            size++;
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
                size++;
                return newNode;
            } else {
                return insertNode(parentNode.right, newNode);
            }
        } else {
            //左树
            if (parentNode.left == null) {
                parentNode.left = newNode;
                size++;
                return newNode;
            } else {
                return insertNode(parentNode.left, newNode);
            }
        }
    }

    @Override
    public java.util.List<T> preTraverseRecursive() {
        LinkedList<T> res = new LinkedList<>();
        preTraverseRecursive(root, res);
        return res;
    }

    public void preTraverseRecursive(Node<T> node, java.util.LinkedList<T> elms) {
        if (node == null) {
            return;
        }
        elms.addLast(node.data);
        preTraverseRecursive(node.left, elms);
        preTraverseRecursive(node.right, elms);
    }

    @Override
    public List<T> preTraverse() {
        LinkedList<T> res = new LinkedList<>();
        Stack<Node<T>> waitVisitNodes = new ArrayStack<>();
        waitVisitNodes.push(root);
        while (!waitVisitNodes.empty()) {
            Node<T> n = waitVisitNodes.pop();
            res.addLast(n.data());
            if (n.right() != null) {
                waitVisitNodes.push((Node<T>) n.right());
            }
            if (n.left() != null) {
                waitVisitNodes.push((Node<T>)n.left());
            }
        }
        return res;
    }

    @Override
    public List<T> preTraverse2() {
        LinkedList<T> res = new LinkedList<>();
        Stack<Node<T>> waitVisitNodes = new ArrayStack<>();
        waitVisitNodes.push(root);
        while (!waitVisitNodes.empty()) {
            Node<T> n = waitVisitNodes.pop();
            res.addLast(n.data());
            if (n.right != null) {
                waitVisitNodes.push(n.right);
            }
            traverseLeftTree(n.left, res, waitVisitNodes);
        }
        return res;
    }

    /**
     * 遍历左子树
     */
    private void traverseLeftTree(Node<T> leftNode, LinkedList<T> visitNodes, Stack<Node<T>> waitVisitRight) {
        while (leftNode != null) {
            visitNodes.addLast(leftNode.data());
            if (leftNode.right() != null) {
                waitVisitRight.push(leftNode.right);
            }
            leftNode = leftNode.left;
        }
    }


    @Override
    public List<T> infixTraverseRecursive() {
        LinkedList<T> res = new LinkedList<>();
        infixTraverseRecursive(root, res);
        return res;
    }

    private void infixTraverseRecursive(Node<T> node, LinkedList<T> elms) {
        if (node == null) {
            return;
        }
        infixTraverseRecursive(node.left, elms);
        elms.addLast(node.data());
        infixTraverseRecursive(node.right, elms);
    }


    @Override
    public List<T> infixTraverse() {
        LinkedList<T> res = new LinkedList<>();
        Stack<Node<T>> waitVisitNodes = new ArrayStack<>();
        waitVisitNodes.push(root);
        infixTraverseLeft(root.left, res);
        res.addLast(root.data());
        if (root.right() != null) {
            infixTraverseLeft(root.right, res);
        }
        return res;
    }

    public void infixTraverseLeft(Node<T> node, LinkedList<T> res) {
        if (node == null) {
            return;
        }
        Stack<Node<T>> leftNodes = new ArrayStack<>();
        while (node != null) {
            leftNodes.push(node);
            node = node.left;
        }
        while (!leftNodes.empty()) {
            Node<T> l = leftNodes.pop();
            res.add(l.data);
            infixTraverseLeft(l.right, res);
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
