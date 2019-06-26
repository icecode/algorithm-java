package com.icecoder.algorithm.graph;

/**
 * @author libing
 * @version 1.0
 * @date 2019-06-22 16:32
 */
public interface Graph<T> {

    boolean insertVex(T node);

    void insertArc(T from, T to);

    void dfsTraverse();
}
