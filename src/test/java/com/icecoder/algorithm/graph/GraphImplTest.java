package com.icecoder.algorithm.graph;

import org.junit.jupiter.api.Test;

/**
 * @author libing
 * @version 1.0
 * @date 2019-06-22 15:49
 */
public class GraphImplTest {


    @Test
    public void testImplForMatrix() {
        GraphImplForMatrix<Integer> inputGraph = new GraphImplForMatrix<>(10);
        testImplFunction(inputGraph);
    }

    @Test
    public void testImplForLinkedTable() {
        GraphImplForLinkedTable<Integer> inputGraph = new GraphImplForLinkedTable<>();
        testImplFunction(inputGraph);
    }

    static void testImplFunction(Graph<Integer> inputGraph) {
        inputGraph.insertVex(1);
        inputGraph.insertVex(3);
        inputGraph.insertVex(4);
        inputGraph.insertVex(5);
        inputGraph.insertVex(6);
        inputGraph.insertVex(7);
        inputGraph.insertVex(8);
        inputGraph.insertVex(9);

        inputGraph.insertArc(1, 2);
        inputGraph.insertArc(1, 4);
        inputGraph.insertArc(2, 3);
        inputGraph.insertArc(3, 6);
        inputGraph.insertArc(6, 7);
        inputGraph.insertArc(7, 8);
        inputGraph.insertArc(8, 9);
        inputGraph.insertArc(4, 5);
        inputGraph.dfsTraverse();
    }
}
