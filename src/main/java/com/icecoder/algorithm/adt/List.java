package com.icecoder.algorithm.adt;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-20 22:20
 */
public interface List<T> {

    public interface Node<T> {

        /**
         * 获取前驱节点
         * @return
         */
        Node<T> pred();

        /**
         * 获取后驱节点
         * @return
         */
        Node<T> succ();

        /**
         * 获取数据
         * @return
         */
        T data();

        /**
         * 插入前驱节点
         * @param e
         */
        void insertAsPred(Node<T> e);

        /**
         * 插入后驱节点
         * @param e
         */
        void insertAsSucc(Node<T> e);

    }


    int size();

    T first();

    T last();

    void insertAsFirst();

    void insertAsLast();

    void insertBefore();

    void insertAfter();

    T remove(T e);

    boolean disordered();

    void sort();

    T find(T e);

    void uniquify();

    void traverse();

}
