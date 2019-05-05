package com.icecoder.algorithm.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author libing
 * @version 1.0
 * @date 2019-05-05 22:21
 */
public class LinkedBListTest {

    @Test
    public void add() {
        LinkedBList<Integer> integers = new LinkedBList<>();
        integers.add(Integer.valueOf(0));
        Assertions.assertEquals(Integer.valueOf(0), integers.get(0));
        integers.add(Integer.valueOf(1));
        Assertions.assertEquals(Integer.valueOf(1), integers.get(0));
        integers.add(Integer.valueOf(2));
        Assertions.assertEquals(Integer.valueOf(2), integers.get(0));
        integers.add(0, Integer.valueOf(3));
        Assertions.assertEquals(Integer.valueOf(3), integers.get(0));
        Assertions.assertEquals(Integer.valueOf(2), integers.get(1));
        Assertions.assertEquals(Integer.valueOf(1), integers.get(2));
        Assertions.assertEquals(Integer.valueOf(0), integers.get(3));
        integers.add(3, Integer.valueOf(4));
        Assertions.assertEquals(Integer.valueOf(4), integers.get(3));
        Assertions.assertEquals(Integer.valueOf(0), integers.get(4));
        //bug  没有处理插入所有一个索引的情况
        //integers.add(5, Integer.valueOf(5));
        //Assertions.assertEquals(Integer.valueOf(5), integers.get(5));
    }

    @Test
    public void set() {
        LinkedBList<Integer> integers = new LinkedBList<>();
        integers.add(Integer.valueOf(0));
        Assertions.assertEquals(Integer.valueOf(0), integers.get(0));
        integers.set(0, Integer.valueOf(1));
        Assertions.assertEquals(Integer.valueOf(1), integers.get(0));
    }

    @Test
    public void remove() {
        LinkedBList<Integer> integers = new LinkedBList<>();
        integers.add(Integer.valueOf(2));
        integers.add(Integer.valueOf(1));
        integers.add(Integer.valueOf(0));
        Assertions.assertEquals(Integer.valueOf(0), integers.get(0));
        Assertions.assertTrue(integers.remove(Integer.valueOf(0)));
        Assertions.assertEquals(Integer.valueOf(1), integers.get(0));
        integers.remove(0);
        Assertions.assertEquals(Integer.valueOf(2), integers.get(0));
    }
}
