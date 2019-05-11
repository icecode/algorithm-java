package com.icecoder.algorithm.stack;

import java.util.NoSuchElementException;

/**
 * @author libing
 * @version 1.0
 * @date 2019-05-11 17:53
 */
public class CircularQueue<E> {

    private final int maxSize;

    private final Object[] arr;

    private int front;

    private int rear;

    public CircularQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new Object[maxSize];
        this.front = rear = 0;
    }

    public boolean push(E e) {
        if (isFull()) {
            return false;
        }
        arr[rear++] = e;
        if (rear == maxSize) {
            rear = 0;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E data = (E)arr[front++];
        if (front == maxSize) {
            front = 0;
        }
        return data;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return front == rear;
    }
}
