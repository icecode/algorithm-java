package com.icecoder.algorithm.adt;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-22 20:27
 */
public class ArrayStack<T> implements Stack<T> {

    public static final int MAX_STACK_SIZE = Integer.MAX_VALUE;

    public static final int INIT_STACK_SIZE = 1024;

    private final int maxStackSize;

    private Object[] stack;

    private int size;

    public ArrayStack() {
        this(MAX_STACK_SIZE);
    }

    public ArrayStack(int maxStackSize) {
        this.maxStackSize = maxStackSize;
        int initStackSize = INIT_STACK_SIZE < maxStackSize ? INIT_STACK_SIZE : maxStackSize;
        this.stack = new Object[initStackSize];
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public void push(T val) {
        if ((size + 1) >= maxStackSize) {
            throw new StackOverflowError("max stack size :" + maxStackSize);
        }
        if ((size + 1) >= stack.length) {
            //扩容
            int newStackSize = size * 2 > MAX_STACK_SIZE ? MAX_STACK_SIZE : size * 2;
            Object[] newStack = new Stack[newStackSize];
            System.arraycopy(stack, 0, newStack, 0, stack.length);
            stack = newStack;
        }
        stack[size++] = val;
    }

    @Override
    public T pop() {
        if (size == 0) {
            throw new StackOverflowError("stack is empty");
        }
        return (T) stack[--size];
    }

    @Override
    public T top() {
        return (T) stack[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int maxSize() {
        return maxStackSize;
    }
}
