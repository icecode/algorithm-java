package com.icecoder.algorithm.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;

/**
 * @author libing
 * @version 1.0
 * @date 2019-06-22 16:34
 */
public class GraphImplForLinkedTable<T extends Object> implements Graph<T> {

    static class Vec<T> {

        T data;

        LinkedList<Vec> arcs;

        public Vec(T data) {
            this.data = data;
            this.arcs = new LinkedList<>();
        }
    }

    private LinkedList<Vec<T>> vecs;

    public GraphImplForLinkedTable() {
        this.vecs = new LinkedList<>();
    }

    @Override
    public boolean insertVex(T vecData) {
        Optional<Vec<T>> vec = findNode(vecData);
        if (vec.isEmpty()) {
            vecs.addLast(new Vec<>(vecData));
        }
        return true;
    }

    private Optional<Vec<T>> findNode(T vecData) {
        return vecs.stream().filter( vec -> vec.data.equals(vecData)).findFirst();
    }

    @Override
    public void insertArc(T from, T to) {
        Optional<Vec<T>> fromVec = findNode(from);
        Optional<Vec<T>> toVec = findNode(to);
        if (fromVec.isPresent() && toVec.isPresent()) {
            fromVec.get().arcs.add(toVec.get());
        }
    }

    @Override
    public void dfsTraverse() {
        Set<T> readVec = new HashSet<>();
        if (vecs.size() > 0) {
            dfsTraverseVec(vecs.getFirst(), readVec, 0);
        }
    }

    private void dfsTraverseVec(Vec<T> vec, Set<T> readVec, int readDepth) {
        readVec.add(vec.data);
        System.out.println("depth:" + readDepth + " v:" + vec.data);
        vec.arcs.forEach( vecArc -> {
            dfsTraverseVec(vecArc, readVec, readDepth + 1);
        });
    }
}
