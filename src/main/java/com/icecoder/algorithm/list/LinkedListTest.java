package com.icecoder.algorithm.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

/**
 * @author libing
 * @version 1.0
 * @date 2019-04-27 18:23
 */
public class LinkedListTest {


    static class LinkedList<T> {

        public static class Node<T> {

            private T data;

            private Node<T> next;

            public Node() {
                data = null;
                next = null;
            }

            public Node(T data) {
                this.data = data;
            }

            public T getData() {
                return data;
            }

            public void setData(T data) {
                this.data = data;
            }

            public Node<T> getNext() {
                return next;
            }

            public void setNext(Node<T> next) {
                this.next = next;
            }
        }

        private Node<T> head;

        public LinkedList() {
            head = new Node<>();
        }


        /**
         * 添加元素
         * @param data
         */
        public void add(T data) {
            Node<T> dataNode = new Node<>(data);
            Node<T> nextNext = head.getNext();
            dataNode.setNext(nextNext);
            head.setNext(dataNode);
        }

        /**
         * 删除元素
         * @param data
         * @return
         */
        public T remove(T data) {
            if (data == null) {
                return null;
            }
            Node<T> preNode = head;
            Node<T> dataNode = head.getNext();
            while (dataNode != null) {
                if (dataNode.getData().equals(data)) {
                    preNode.setNext(dataNode.getNext());
                    return dataNode.getData();
                }
                preNode = dataNode;
                dataNode = dataNode.getNext();
            }
            return null;
        }

        /**
         * 查询原始是否在容器内
         * @param data
         * @return
         */
        public boolean contains(T data) {
            if (data == null) {
                return false;
            }
            Node<T> dataNode = head.getNext();
            while (dataNode != null && !dataNode.getData().equals(data)) {
                dataNode = dataNode.getNext();
            }
            return dataNode != null;
        }

        /**
         * 链表反转
         * @return
         */
        public LinkedList<T> revers() {
            LinkedList<T> newInstance = new LinkedList<>();
            Node<T> newHead = null;
            Node<T> newNextNode = null;
            Node<T> iteratorNode = head.getNext();
            while (iteratorNode != null) {
                newHead = new Node<>(iteratorNode.getData());
                newHead.setNext(newNextNode);
                if (iteratorNode.getNext() == null) {
                    break;
                }
                newNextNode = newHead;
                iteratorNode = iteratorNode.getNext();
            }
            newInstance.head.setNext(newHead);
            return newInstance;
        }

        public Iterator<T> iterator() {

            return new Iterator<T>() {

                private Node<T> currentNode = head.getNext();

                @Override
                public boolean hasNext() {
                    return currentNode != null;
                }

                @Override
                public T next() {
                    T data = currentNode.getData();
                    currentNode = currentNode.getNext();
                    return data;
                }
            };
        }
    }

    @Test
    public void add() {
        String a = "A";
        LinkedList<String> testObj = new LinkedList<>();
        testObj.add(a);
        testObj.add(a);
        Assertions.assertTrue(testObj.contains(a));
        Iterator<String> it = testObj.iterator();
        Assertions.assertTrue(it.hasNext());
        Assertions.assertEquals(a, it.next());
        Assertions.assertEquals(a, it.next());
        Assertions.assertFalse(it.hasNext());
    }

    @Test
    public void remove() {
        String a = "A";
        String b = "B";
        String c = "C";
        LinkedList<String> testObj = new LinkedList<>();
        testObj.add(a);
        testObj.add(b);
        testObj.add(c);
        testObj.remove(b);
        Iterator<String> it = testObj.iterator();
        Assertions.assertEquals(c, it.next());
        Assertions.assertEquals(a, it.next());
        Assertions.assertTrue(testObj.contains(a));
        Assertions.assertTrue(testObj.contains(c));
        Assertions.assertFalse(testObj.contains(b));
        testObj.remove(a);
        Assertions.assertFalse(testObj.contains(a));
        testObj.remove(c);
        Assertions.assertFalse(testObj.contains(c));
    }

    @Test
    public void revers() {
        String a = "A";
        String b = "B";
        String c = "C";
        LinkedList<String> testObj = new LinkedList<>();
        testObj.add(a);
        testObj.add(b);
        testObj.add(c);
        Iterator<String> t = testObj.iterator();
        Assertions.assertEquals("C", t.next());
        Assertions.assertEquals("B", t.next());
        Assertions.assertEquals("A", t.next());

        Iterator<String> rt = testObj.revers().iterator();
        Assertions.assertEquals("A", rt.next());
        Assertions.assertEquals("B", rt.next());
        Assertions.assertEquals("C", rt.next());
        testObj.remove(b);

        Iterator<String> rt2 = testObj.revers().iterator();
        Assertions.assertEquals("A", rt2.next());
        Assertions.assertEquals("C", rt2.next());

        testObj.remove(a);
        Iterator<String> rt3 = testObj.revers().iterator();
        Assertions.assertEquals("C", rt3.next());
        Assertions.assertFalse(rt3.hasNext());

        testObj.remove(c);
        Iterator<String> rt4 = testObj.revers().iterator();
        Assertions.assertFalse(rt4.hasNext());
    }
}
