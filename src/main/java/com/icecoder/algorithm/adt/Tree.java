package com.icecoder.algorithm.adt;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-29 23:19
 */
public interface Tree<T extends Comparable<T>> {

    interface Node<T extends Comparable<T>> extends Comparable<Node<T>> {

        /**
         *
         * 返回顶点数据
         *
         * @return
         */
        T data();

        /**
         * 左树
         * @return
         */
        Node<T> left();

        /**
         * 右树
         * @return
         */
        Node<T> right();

    }

    /**
     * 查找一个节点
     * @param data
     * @return
     */
    Node<T> find(T data);

    /**
     * 插入一个节点， 如果数据不存在就返回null
     * @param data
     * @return
     */
    Node<T> insert(T data);

    /**
     * 返回根节点
     *
     * @return
     */
    Node<T> root();

    /**
     * 更新树的高度
     * @param data
     */
    void updateHeight(T data);

    /**
     * 返回元素的大小
     * @return
     */
    int size();


    /**
     * 是否为一个空树
     * @return
     */
    default boolean empty() {
        return size() == 0;
    }

}
