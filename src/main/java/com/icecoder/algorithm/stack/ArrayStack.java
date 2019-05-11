package com.icecoder.algorithm.stack;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author libing
 * @version 1.0
 * @date 2019-05-11 16:54
 */
public class ArrayStack<E> implements BStack<E> {

    private final int maxStackSize;

    private final Object[] stack;

    private int top;

    public ArrayStack(int maxStackSize) {
        this.maxStackSize = maxStackSize;
        this.stack = new Object[maxStackSize];
        this.top = 0;
    }

    @Override
    public void push(E item) {
        if ((top + 1) > maxStackSize) {
            throw new StackOverflowError("max stack size:" + maxStackSize);
        }
        stack[top++] = item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() {
        if (top == 0) {
            throw new NoSuchElementException("stack empty");
        }
        return (E) stack[--top];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        if (top == 0) {
            throw new NoSuchElementException("stack empty");
        }
        return (E) stack[top-1];
    }

    @Override
    public boolean empty() {
        return top == 0;
    }

    @Override
    public int size() {
        return top;
    }
}
