package com.icecoder.algorithm.adt;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-22 20:20
 */
public interface Stack<T> {

    /**
     * 返回栈是否为空
     * @return
     */
    boolean empty();

    /**
     * 栈顶push一个元素
     * @param val
     */
    void push(T val);

    /**
     * 弹出栈顶元素
     * @return
     */
    T pop();

    /**
     * 返回栈顶元素
     * @return
     */
    T top();

    /**
     * 返回栈大小
     * @return
     */
    int size();

    /**
     * 返回栈的最大深度
     * @return
     */
    int maxSize();

}
