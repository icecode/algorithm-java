package com.icecoder.algorithm.graph;

import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author libing
 * @version 1.0
 * @date 2019-06-22 15:49
 */
public class GraphImplTest {


    public class City {

        private String name;


        public City(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof City)) return false;
            City city = (City) o;
            return Objects.equals(getName(), city.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName());
        }
    }

    @Test
    public void testImplForMatrix() {
        GraphImplForMatrix<Integer> inputGraph = new GraphImplForMatrix<>(10);
        testImplFunction(inputGraph);
    }

//    @Test
//    public void testImplForLinkedTable() {
//        GraphImplForLinkedTable<Integer> inputGraph = new GraphImplForLinkedTable<>();
//        testImplFunction(inputGraph);
//    }

    @Test
    public void testGenMiniTree() {
        GraphImplForLinkedTable<City> cityGraph = new GraphImplForLinkedTable<>();
        City hz = new City("杭州");
        City sh = new City("上海");
        City nj = new City("南京");
        City hf = new City("合肥");
        City wh = new City("武汉");
        City nc = new City("南昌");
        City cs = new City("长沙");
        cityGraph.insertVex(sh);
        cityGraph.insertVex(hz);
        cityGraph.insertVex(nj);
        cityGraph.insertVex(hf);
        cityGraph.insertVex(wh);
        cityGraph.insertVex(nc);
        cityGraph.insertVex(cs);

        cityGraph.insertArc(sh, hz, 1);
        cityGraph.insertArc(sh, nj, 2);
        cityGraph.insertArc(sh, hf, 3);

        cityGraph.insertArc(nj, hf, 1);
        cityGraph.insertArc(nj, sh, 2);
        cityGraph.insertArc(nj, hz, 1);

        cityGraph.insertArc(hz, hf, 2);
        cityGraph.insertArc(hz, sh, 1);
        cityGraph.insertArc(hz, nj, 1);

        cityGraph.insertArc(hf, hz, 2);
        cityGraph.insertArc(hf, nj, 1);
        cityGraph.insertArc(hf, wh, 3);

        cityGraph.insertArc(wh, hf, 3);
        cityGraph.insertArc(wh, nc, 2);
        cityGraph.insertArc(wh, cs, 3);

        cityGraph.insertArc(nc, hz, 3);
        cityGraph.insertArc(nc, wh, 2);
        cityGraph.insertArc(nc, cs, 2);

        cityGraph.insertArc(cs, nc, 3);
        cityGraph.insertArc(cs, wh, 3);


//        cityGraph.genPrimMiniTree().dfsTraverse();
//        System.out.println("----------------vec 1");
//        cityGraph.genPrimMiniTree(1).dfsTraverse();
//        System.out.println("----------------vec 2");
//        cityGraph.genPrimMiniTree(2).dfsTraverse();
        System.out.println("----------------Kruskal vec 2");
        GraphImplForLinkedTable<City> cityGraphImplForLinkedTable = cityGraph.genKruskalMiniTree();
        cityGraphImplForLinkedTable.dfsTraverse();
        System.out.println(cityGraph.genVecShortestPath());


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
