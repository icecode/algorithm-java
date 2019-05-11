package com.icecoder.algorithm.stack;

/**
 * @author libing
 * @version 1.0
 * @date 2019-05-11 16:51
 */
public interface BStack<E> {

    void push(E item);

    E pop();

    E peek();

    boolean empty();

    int size();

}
