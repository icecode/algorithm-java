package com.icecoder.algorithm.list;

import java.util.Objects;

/**
 * @author libing
 * @version 1.0
 * @date 2019-05-11 10:19
 */
public class DoubleLinkedBList<E> extends BList<E> {

    private static class Node<NE> {

        Node<NE> pre;

        NE data;

        Node<NE> next;

        public Node(NE data) {
            this.pre = null;
            this.next = null;
            this.data = data;
        }
    }

    private Node<E> head;

    private int size;

    public DoubleLinkedBList() {
        head = new Node<>(null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean add(E e) {
        return addHead(e);
    }

    @Override
    public void add(int elmIndex, E element) {
        if (elmIndex == 0) {
            addHead(element);
        } else if (elmIndex == size) {
            addTail(element);
        } else {
            NodeAndIndex<E> value = findValue(elmIndex);
            if (value != null) {
                Node<E> newNode = new Node<>(element);
                value.node.pre.next = newNode;
                newNode.pre = value.node.pre;
                value.node.pre = newNode;
                newNode.next = value.node;
                size++;
            } else {
                throw new IndexOutOfBoundsException("size:" + size + " index:" + elmIndex);
            }
        }
    }

    private boolean addHead(E e) {
        Objects.requireNonNull(e);
        Node<E> newNode = new Node<>(e);
        newNode.pre = head;
        newNode.next = head.next;
        if (head.next != null) {
            head.next.pre = newNode;
        }
        head.next = newNode;
        size++;
        return true;
    }

    private boolean addTail(E e) {
        Objects.requireNonNull(e);
        Node<E> dataNode = head;
        while (dataNode.next != null) {
            dataNode = dataNode.next;
        }
        Node<E> newNode = new Node<>(e);
        newNode.pre = dataNode;
        dataNode.next = newNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        NodeAndIndex<E> value = findValue(o);
        if (value == null) {
            return false;
        } else {
            Node<E> dataNode = value.node;
            dataNode.pre.next = dataNode.next;
            if (dataNode.next != null) {
                dataNode.next.pre = dataNode.pre;
            }
            dataNode.pre = dataNode.next = null;
            dataNode.data = null;
            size--;
            return true;
        }
    }

    @Override
    public E remove(int elmIndex) {
        NodeAndIndex<E> value = findValue(elmIndex);
        E oldVal = null;
        if (value != null) {
            value.node.pre.next = value.node.next;
            if (value.node.next != null) {
                value.node.next.pre = value.node.pre;
            }
            oldVal = value.node.data;
            value.node.pre = value.node.next = null;
            value.node.data = null;
            size--;
        }
        return oldVal;
    }


    private NodeAndIndex<E> findValue(Object o) {
        int findIndex;
        Node<E> dataNode;
        for (findIndex = 0, dataNode = head.next;
             dataNode != null && !dataNode.data.equals(o);
             findIndex++, dataNode = dataNode.next) {

        }
        return dataNode != null ? new NodeAndIndex<>(findIndex, dataNode) : null;
    }

    /**
     * 检查是否是合法的索引
     * @param index
     */
    private void checkIndexRange(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("size:" + size + " index:" + index);
        }
    }

    /**
     * 获取Index的值，如果不存在返回 null
     * @param index
     * @return
     */
    private NodeAndIndex<E> findValue(int index) {
        checkIndexRange(index);
        int findIndex;
        Node<E> dataNode;
        for (findIndex = 0, dataNode = head.next;
             dataNode != null && findIndex < index;
             findIndex++, dataNode = dataNode.next) {
        }
        return dataNode != null ? new NodeAndIndex<>(findIndex, dataNode) : null;
    }



    @Override
    public E get(int elmIndex) {
        NodeAndIndex<E> value = findValue(elmIndex);
        return value == null ? null : value.node.data;
    }

    @Override
    public E set(int elmIndex, E element) {
        NodeAndIndex<E> value = findValue(elmIndex);
        E oldVal = null;
        if (value != null) {
            oldVal = value.node.data;
            value.node.data = element;
        }
        return oldVal;
    }


    private static class NodeAndIndex<E> {

        public int index;

        public Node<E> node;

        public NodeAndIndex(int index, Node<E> node) {
            this.index = index;
            this.node = node;
        }
    }
}
