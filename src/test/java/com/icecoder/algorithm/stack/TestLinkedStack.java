package com.icecoder.algorithm.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

/**
 * @author libing
 * @version 1.0
 * @date 2019-05-11 17:32
 */
public class TestLinkedStack {

    @Test
    public void pushAndPopAndEmptyAndSize() {
        BStack<Integer> stack = new LinkedStack<>();
        Assertions.assertTrue(stack.empty());
        stack.push(Integer.valueOf(1));
        stack.push(Integer.valueOf(2));
        stack.push(Integer.valueOf(3));
        stack.push(Integer.valueOf(4));
        stack.push(Integer.valueOf(5));
        Assertions.assertEquals(5, stack.size());
        Assertions.assertEquals(Integer.valueOf(5), stack.peek());
        Assertions.assertEquals(Integer.valueOf(5), stack.pop());
        Assertions.assertEquals(4, stack.size());
        Assertions.assertEquals(Integer.valueOf(4), stack.pop());
        Assertions.assertEquals(3, stack.size());
        Assertions.assertEquals(Integer.valueOf(3), stack.pop());
        Assertions.assertEquals(2, stack.size());
        Assertions.assertEquals(Integer.valueOf(2), stack.pop());
        Assertions.assertEquals(1, stack.size());
        Assertions.assertEquals(Integer.valueOf(1), stack.pop());
        Assertions.assertThrows(NoSuchElementException.class, () -> stack.pop());
        Assertions.assertEquals(0, stack.size());
    }
}
