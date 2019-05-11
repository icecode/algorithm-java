package com.icecoder.algorithm.stack;

import java.util.NoSuchElementException;

/**
 * @author libing
 * @version 1.0
 * @date 2019-05-11 17:13
 */
public class LinkedStack<E> implements BStack<E> {

    private static class Stack<E> {

        E data;

        Stack<E> next;

        public Stack(E data, Stack<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Stack<E> top;

    private int size;

    public LinkedStack() {
        this.size = 0;
    }

    @Override
    public void push(E item) {
        top = new Stack<>(item, top);
        size++;
    }

    @Override
    public E pop() {
        if (size == 0) {
            throw new NoSuchElementException("stack empty");
        }
        E data = top.data;
        top = top.next;
        size--;
        return data;
    }

    @Override
    public E peek() {
        if (size == 0) {
            throw new NoSuchElementException("stack empty");
        }
        return top.data;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}
