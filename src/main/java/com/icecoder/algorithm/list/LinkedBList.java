package com.icecoder.algorithm.list;

import java.util.Objects;
import java.util.Optional;

/**
 * @author libing
 * @version 1.0
 * @date 2019-05-05 21:37
 */
public class LinkedBList<E> extends BList<E> {


    private static class NodeInfo<E> {

        public int index;

        public Node<E> node;

        public Node<E> preNode;

        public NodeInfo(int index, Node<E> node, Node<E> preNode) {
            this.index = index;
            this.node = node;
            this.preNode = preNode;
        }
    }

    private static class Node<E> {

        public E data;

        public Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public boolean equals(Object obj) {
            if (Objects.isNull(data)) {
                return false;
            }
            if (obj instanceof Node) {
                Node objNode = (Node)obj;
                return data.equals(objNode.data);
            }
            return false;
        }
    }

    private Node<E> head;

    public LinkedBList() {
        head = new Node<>(null, null);
    }

    private Optional<NodeInfo<E>> findNode(Object data) {
        Node<E> dataNode;
        int i;
        Node<E> preNode;
        for (dataNode = head.next, i = 0, preNode = head;
             dataNode != null && !dataNode.data.equals(data);
             i++, preNode = dataNode, dataNode = dataNode.next){
            //ignore
        }
        if (dataNode != null) {
            return Optional.of(new NodeInfo<>(i, dataNode, preNode));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean add(E e) {
        Node<E> newFirstNode = new Node<>(e);
        newFirstNode.next = head.next;
        head.next = newFirstNode;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return findNode(o)
                .map( nodeInfo -> {
                    nodeInfo.preNode.next = nodeInfo.node.next;
                    nodeInfo.node.next = null;
                    return true;
                })
                .orElse(false);
    }

    private Optional<NodeInfo<E>> findNode(int index) {
        Node<E> dataNode;
        int i;
        Node<E> preNode;
        for (dataNode = head.next, i = 0, preNode = head;
             dataNode != null && i != index;
             i++, preNode = dataNode, dataNode = dataNode.next){
            //ignore
        }
        if (dataNode != null) {
            return Optional.of(new NodeInfo<>(i, dataNode, preNode));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public E get(int index) {
        return findNode(index).map(nodeInfo -> nodeInfo.node.data).orElse(null);
    }

    @Override
    public E set(int index, E element) {
        return findNode(index)
                .map( nodeInfo -> {
                    E oldData = nodeInfo.node.data;
                    nodeInfo.node.data = element;
                    return oldData;
                })
                .orElse(null);
    }


    @Override
    public void add(int index, E element) {
        /*
         * bug 没有处理插入元素是最后一个索引位置的情况，
         */
        findNode(index)
                .ifPresent( nodeInfo -> nodeInfo.preNode.next = new Node<>(element, nodeInfo.node));
    }

    @Override
    public E remove(int index) {
        return findNode(index)
                .map( nodeInfo -> {
                    nodeInfo.preNode.next = nodeInfo.node.next;
                    nodeInfo.node.next = null;
                    return nodeInfo.node.data;
                })
                .orElse(null);
    }
}

