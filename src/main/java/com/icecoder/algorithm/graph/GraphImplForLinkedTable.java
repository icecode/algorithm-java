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

    /**
     * 最短路径算法-迪杰斯特拉
     * @return
     */
    public LinkedList<T> genVecShortestPath() {
        Object[] vecVector = vertexSet.toArray();
        boolean[] visited = new boolean[vecVector.length];
        int[] pathDistance = new int[vecVector.length];
        int[] parentPath = new int[vecVector.length];
        for (int i = 0; i < vecVector.length; i++) {
            visited[i] = false;
            pathDistance[i] = Integer.MAX_VALUE;
            parentPath[i] = -1;
        }

        int currentVecIndex;
        //初始化from顶点数据
//        for (int i = 0; i < vecVector.length; i++) {
//            if (vecVector[i].equals(from)) {
//                visited[i] = true;
//                pathDistance[i] = 0;
//                parentPath[i] = 0;
//                currentVecIndex = i;
//            }
//        }
        //设定第一个顶点为起始顶点
        visited[0] = true;
        pathDistance[0] = 0;
        parentPath[0] = -1;
        currentVecIndex = 0;
        boolean finish = false;
        do {
            /*
             * 更新当前顶点到连通顶点的距离
             */
            Vec<T> fromVec = (Vec<T>) vecVector[currentVecIndex];
            for (Arc<T> arc: fromVec.arcs){
                int vecArcIndex = 0;
                for (int i = 0; i < vecVector.length; i++) {
                    if (arc.getVec().data.equals(((Vec<T>)vecVector[i]).data)) {
                        vecArcIndex = i;
                    }
                }
                if (!visited[vecArcIndex]) {
                    pathDistance[vecArcIndex] = pathDistance[currentVecIndex] + arc.weight;
                    parentPath[vecArcIndex] = currentVecIndex;
                }
            }
            /*
             * 查新当前顶点到下个顶点最短节点，并作为下次迭代的节点
             */
            int nextShortestWeight = Integer.MAX_VALUE;
            int nextShortestIndex = -1;
            for (int i = 0; i < vecVector.length; i++) {
                if (parentPath[i] == currentVecIndex) {
                    if (pathDistance[i] < nextShortestWeight) {
                        nextShortestWeight = pathDistance[i];
                        nextShortestIndex = i;
                    }
                }
            }
            currentVecIndex = nextShortestIndex;
            visited[nextShortestIndex] = true;
            for (int i = 0; i < vecVector.length; i++) {
                finish = visited[i];
            }
        } while (!finish);
        int parentIndex = -1;
        LinkedList<T> shortPath = new LinkedList<>();
        for (int i = 0; i < vecVector.length; i++) {
            if (parentPath[i] == parentIndex) {
                shortPath.add(((Vec<T>)vecVector[i]).data);
                parentIndex = i;
            }
        }
        return shortPath;
    }


    public void genFloydShortestPath() {
        Object[] vecVector = vertexSet.toArray();
        long[][] distanceMatrix = new long[vertexSet.size()][vertexSet.size()];
        long[][] pathMatrix = new long[vertexSet.size()][vertexSet.size()];

        //初始化权值矩阵
        int initIndex = 0;
        for (Vec<T> vec: vertexSet) {
            for (int rowIndex = 0; rowIndex < vertexSet.size(); rowIndex++) {
                distanceMatrix[initIndex][rowIndex] = Integer.MAX_VALUE;
            }
            distanceMatrix[initIndex][initIndex] = 0;
            for (Arc<T> arc: vec.arcs) {
                for (int i = 0; i < vecVector.length; i++) {
                    if (arc.getVec().data.equals(((Vec<T>)vecVector[i]).data)) {
                        distanceMatrix[initIndex][i] = arc.weight;
                    }
                }
            }
            initIndex++;
        }
        for (int i = 0; i < vecVector.length; i++) {
            System.out.print(String.format("%4d", i));
            for (int j = 0; j < vecVector.length; j++) {
                System.out.print(String.format("%4d:(%d,%d)", j, distanceMatrix[i][j], pathMatrix[i][j]));
            }
            System.out.println();
        }
        System.out.println("--------------");
        //开始计算
        for (int k = 0; k < vecVector.length; k++) {
            for (int i = 0; i < vecVector.length; i++) {
                for (int j = 0; j < vecVector.length; j++) {
                    if ((distanceMatrix[i][k] + distanceMatrix[k][j]) < distanceMatrix[i][j]) {
                        distanceMatrix[i][j] = (distanceMatrix[i][k] + distanceMatrix[k][j]);
                        pathMatrix[i][j] = k;
                    }
                }
            }
        }
        for (int i = 0; i < vecVector.length; i++) {
            System.out.print(String.format("%4s", ((Vec<T>)vecVector[i]).data));
            for (int j = 0; j < vecVector.length; j++) {
                System.out.print(String.format("%4d:(%d,%d)", j, distanceMatrix[i][j], pathMatrix[i][j]));
            }
            System.out.println();
        }

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
