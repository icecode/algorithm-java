package com.icecoder.algorithm.adt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-22 20:38
 */
public class ArrayStackTest {

    @Test
    public void empty() {
        ArrayStack<Object> as = new ArrayStack<>();
        Assertions.assertTrue(as.empty());
        as.push(new Object());
        Assertions.assertFalse(as.empty());
    }

    @Test
    public void push() {
        Object obj = new Object();
        ArrayStack<Object> as = new ArrayStack<>();
        Assertions.assertTrue(as.empty());
        as.push(obj);
        Assertions.assertEquals(obj, as.pop());
    }

    @Test
    public void pop() {
        Object obj = new Object();
        ArrayStack<Object> as = new ArrayStack<>();
        Assertions.assertTrue(as.empty());
        as.push(obj);
        Assertions.assertEquals(obj, as.pop());
    }

    @Test
    public void top() {
        Object obj = new Object();
        ArrayStack<Object> as = new ArrayStack<>();
        Assertions.assertTrue(as.empty());
        as.push(obj);
        Assertions.assertEquals(obj, as.top());
        Assertions.assertEquals(obj, as.top());
    }

    @Test
    public void size() {
        Object obj = new Object();
        ArrayStack<Object> as = new ArrayStack<>();
        as.push(obj);
        Assertions.assertEquals(1, as.size());
        as.push(obj);
        Assertions.assertEquals(2, as.size());
        as.pop();
        Assertions.assertEquals(1, as.size());
        as.pop();
        Assertions.assertEquals(0, as.size());
    }

    @Test
    public void maxSize() {
        ArrayStack<Object> as = new ArrayStack<>(1);
        Assertions.assertEquals(1, as.maxSize());
    }
}
