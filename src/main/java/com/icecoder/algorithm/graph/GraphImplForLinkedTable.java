package com.icecoder.algorithm.graph;

import java.util.*;

/**
 * @author libing
 * @version 1.0
 * @date 2019-06-22 16:34
 */
public class GraphImplForLinkedTable<T> {

    private LinkedList<Vec<T>> vertexSet;

    public GraphImplForLinkedTable() {
        this.vertexSet = new LinkedList<>();
    }
    /**
     * 判断图是否是无回路连通
     *
     * @return
     */
    static <T> boolean isFullConnected(GraphImplForLinkedTable<T> graph) {
        int od = 0;
        for (Vec<T> vec : graph.vertexSet) {
            if (vec.arcs.size() > 0) {
                od++;
            }
        }
        return (od == (graph.vertexSet.size() - 1));
    }

    private static <T> boolean isConnect(GraphImplForLinkedTable<T> graph, T fromVec, T toVec) {
        return isConnect(graph, fromVec, toVec, new HashSet<>());
    }

    private static <T> boolean isConnect(GraphImplForLinkedTable<T> graph, T fromVec, T toVec, Set<T> testedVec) {
        if (testedVec.contains(fromVec)) {
            return false;
        }
        testedVec.add(fromVec);
        Optional<Vec<T>> fromVecNode = graph.findNode(fromVec);
        if (fromVecNode.isEmpty()) {
            return false;
        }
        for (Arc<T> arc : fromVecNode.get().arcs) {
            if (arc.getVec().data.equals(toVec)) {
                return true;
            }
            if (isConnect(graph, arc.vec.data, toVec, testedVec)) {
                return true;
            }
        }
        return false;
    }

    public boolean insertVex(T vecData) {
        Optional<Vec<T>> vec = findNode(vecData);
        if (vec.isEmpty()) {
            vertexSet.addLast(new Vec<>(vecData));
        }
        return true;
    }

    private Optional<Vec<T>> findNode(T vecData) {
        return vertexSet.stream().filter(vec -> vec.data.equals(vecData)).findFirst();
    }

    public void insertArc(T from, T to, int weight) {
        Optional<Vec<T>> fromVec = findNode(from);
        Optional<Vec<T>> toVec = findNode(to);
        if (fromVec.isPresent() && toVec.isPresent()) {
            //检查到改顶点的边是否存在
            //如果不存在，就添加
            boolean existsNode = fromVec.get().arcs.stream().anyMatch(sourceArc -> sourceArc.vec.data.equals(toVec.get().data));
            if (!existsNode) {
                fromVec.get().arcs.add(new Arc<>(weight, toVec.get()));
            }
        }
    }

    public void dfsTraverse() {
        Set<T> readVec = new HashSet<>();
        if (vertexSet.size() > 0) {
            dfsTraverseVec(vertexSet.getFirst(), readVec, 0);
        }
    }

    /**
     * 根据普里姆算法生成最小生成树
     *
     * @return
     */
    public GraphImplForLinkedTable<T> genPrimMiniTree() {
        return genPrimMiniTree(0);
    }

    /**
     * 根据普里姆算法生成最小生成树
     *
     * @param vecIndex 随机选择顶点的索引
     * @return
     */
    public GraphImplForLinkedTable<T> genPrimMiniTree(int vecIndex) {
        GraphImplForLinkedTable<T> miniTree = new GraphImplForLinkedTable<>();
        LinkedList<Vec<T>> waitVertexSet = new LinkedList<>();
        for (Vec<T> copyVertex : this.vertexSet) {
            waitVertexSet.add(new Vec<T>(copyVertex.data));
        }
        miniTree.insertVex(waitVertexSet.remove(vecIndex % waitVertexSet.size()).data);
        Arc<T> minArc;
        Vec<T> minFromVec;
        do {
            minArc = null;
            minFromVec = null;
            for (Vec<T> fromVec : miniTree.vertexSet) {
                Optional<Vec<T>> node = findNode(fromVec.data);
                if (node.isPresent()) {
                    final Vec<T> vecNode = node.get();
                    for (Arc<T> arc : vecNode.arcs) {
                        if (miniTree.findNode(arc.vec.data).isEmpty()) {
                            if (minArc == null || arc.weight < minArc.weight) {
                                minArc = arc;
                                minFromVec = fromVec;
                            }
                        }
                    }
                }
            }
            waitVertexSet.remove(minArc.vec);
            miniTree.insertVex(minArc.vec.data);
            miniTree.insertArc(minFromVec.data, minArc.vec.data, minArc.weight);
        } while (!waitVertexSet.isEmpty());
        return miniTree;
    }


    /**
     * 根据克鲁斯卡尔算法生成最小生成树
     *
     * @return
     */
    public GraphImplForLinkedTable<T> genKruskalMiniTree() {
        GraphImplForLinkedTable<T> miniTree = new GraphImplForLinkedTable<>();
        LinkedList<ArcPair<T>> graphArcSet = new LinkedList<>();
        /*
         * 初始化边或弧的集合，
         */
        for (Vec<T> vec : vertexSet) {
            for (Arc<T> arc : vec.arcs) {
                graphArcSet.add(new ArcPair<>(vec.data, arc.vec.data, arc.weight));
            }
        }

        /*
         * 初始化顶点集合
         */
        for (Vec<T> copyVertex : this.vertexSet) {
            miniTree.insertVex(copyVertex.data);
        }
        /*
         * 每次选取一个代价最小的边，如果这2个顶点没有联通，就添加进最小生成树中
         */
        ArcPair<T> minArc;
        do {
            minArc = graphArcSet.getFirst();
            for (ArcPair<T> arcPair : graphArcSet) {
                if (minArc.weight > arcPair.weight) {
                    minArc = arcPair;
                }
            }
            //删除这条权重最小的边
            graphArcSet.remove(minArc);
            //判断1和3是否连通， 如果不连通，就添加边
            if (!isConnect(miniTree, minArc.fromVec, minArc.toVec) && !isConnect(miniTree, minArc.toVec, minArc.fromVec)) {
                miniTree.insertArc(minArc.fromVec, minArc.toVec, minArc.weight);
            }
        } while (!isFullConnected(miniTree));
        return miniTree;
    }

    private void dfsTraverseVec(Vec<T> vec, Set<T> readVec, int readDepth) {
        if (!readVec.contains(vec.data)) {
            readVec.add(vec.data);
            System.out.println("depth:" + readDepth + " v:" + vec.data);
            vec.arcs.forEach(vecArc -> {
                dfsTraverseVec(vecArc.vec, readVec, readDepth + 1);
            });
        }
    }

    /**
     * 存储顶点到另外一个顶点的元组
     */
    static class ArcPair<T> {

        T fromVec;

        T toVec;

        int weight;

        public ArcPair(T fromVec, T toVec, int weight) {
            this.fromVec = fromVec;
            this.toVec = toVec;
            this.weight = weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(fromVec, toVec, weight);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ArcPair)) {
                return false;
            }
            ArcPair<T> o = (ArcPair<T>) obj;
            return weight == o.weight
                    && Objects.equals(fromVec, o.fromVec)
                    && Objects.equals(toVec, o.toVec);
        }
    }

    /**
     * 存储边信息
     */
    public static class Arc<T> {

        private int weight;

        private Vec<T> vec;

        public Arc(int weight, Vec<T> vec) {
            this.weight = weight;
            this.vec = vec;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Vec<T> getVec() {
            return vec;
        }

        public void setVec(Vec<T> vec) {
            this.vec = vec;
        }
    }

    /**
     * 存储顶点信息
     */
    public static class Vec<T> {

        T data;

        LinkedList<Arc<T>> arcs;

        public Vec(T data) {
            this.data = data;
            this.arcs = new LinkedList<>();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Vec)) {
                return false;
            }
            return Objects.equals(this.data, ((Vec) obj).data);
        }
    }
}
