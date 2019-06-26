package com.icecoder.algorithm.graph;

/**
 * @author libing
 * @version 1.0
 * @date 2019-06-22 15:06
 */
public class GraphImplForMatrix<T> implements Graph<T> {

    private Object[] nodes;

    private int[][] arcs;

    private int size;

    private int capacity;

    public GraphImplForMatrix(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        nodes = new Object[capacity];
        arcs = new int[capacity][capacity];
    }

    @Override
    public boolean insertVex(T node) {
        if ((size + 1) >= capacity) {
            return false;
        }
        nodes[size++] = node;
        return true;
    }

    public int findNode(T node) {
        int readIndex = 0;
        while (readIndex < size) {
            if (nodes[readIndex].equals(node)) {
                return readIndex;
            }
            readIndex++;
        }
        return -1;
    }

    @Override
    public void insertArc(T from, T to) {
        int fromIndex = findNode(from);
        int toIndex = findNode(to);
        if (fromIndex != -1 && toIndex != -1) {
            arcs[fromIndex][toIndex] = 1;
            arcs[toIndex][fromIndex] = 1;
        }
    }

    public void deleteArc(T from, T to) {
        int fromIndex = findNode(from);
        int toIndex = findNode(to);
        if (fromIndex != -1 && toIndex != -1) {
            arcs[fromIndex][toIndex] = 0;
            arcs[toIndex][fromIndex] = 0;
        }
    }

    @Override
    public void dfsTraverse() {
        int[] readNodes = new int[size];
        System.out.println("start depth:" + 0 + " v:" + nodes[0]);
        readNodes[0] = 1;
        dfsTraverseNode(readNodes, 0, 1);
    }

    public void dfsTraverseNode(int[] readNodes, int nodeIndex, int readDepth) {
        if (nodeIndex >= size) {
            return;
        }
        for (int i = 0; i < size; i++){
            if (arcs[nodeIndex][i] == 1 && readNodes[i] == 0) {
                readNodes[i] = 1;
                System.out.println("depth:" + readDepth + " v:" + nodes[i]);
                //说明节点是连通的，并且该节点没有被读取过
                dfsTraverseNode(readNodes, i, readDepth + 1);
            }
        }
    }
}
