package com.icecoder.algorithm.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author libing
 * @version 1.0
 * @date 2019-05-05 19:36
 */
public class ArrayBListTest {

    @Test
    public void add() {
        new ArrayList<>()
        ArrayBList<Integer> integers = new ArrayBList<>(1);
        integers.add(Integer.valueOf(1));
        Assertions.assertEquals(Integer.valueOf(1), integers.get(0));
        Assertions.assertEquals(2, integers.getCapacity());
        integers.add(Integer.valueOf(2));
        Assertions.assertEquals(Integer.valueOf(2), integers.get(1));
        Assertions.assertEquals(4, integers.getCapacity());
        integers.add(2, Integer.valueOf(3));
        Assertions.assertEquals(Integer.valueOf(3), integers.get(2));
        Assertions.assertEquals(4, integers.getCapacity());
        integers.add(0, Integer.valueOf(4));
        Assertions.assertEquals(Integer.valueOf(1), integers.get(1));
        Assertions.assertEquals(Integer.valueOf(2), integers.get(2));
        Assertions.assertEquals(Integer.valueOf(3), integers.get(3));
    }

    @Test
    public void set() {
        ArrayBList<Integer> integers = new ArrayBList<>(1);
        Assertions.assertEquals(null, integers.set(0, Integer.valueOf(0)));
        Assertions.assertEquals(Integer.valueOf(0), integers.set(0, Integer.valueOf(1)));
    }

    @Test
    public void remove() {
        ArrayBList<Integer> integers = new ArrayBList<>(1);
        Assertions.assertEquals(null, integers.get(0));
        integers.add(Integer.valueOf(0));
        integers.add(Integer.valueOf(1));
        integers.remove(Integer.valueOf(0));
        Assertions.assertEquals(Integer.valueOf(1), integers.get(0));
        integers.add(Integer.valueOf(2));
        integers.add(Integer.valueOf(3));
        integers.add(Integer.valueOf(4));
        integers.add(Integer.valueOf(5));
        integers.remove(1);
        Assertions.assertEquals(Integer.valueOf(3), integers.get(1));
        Assertions.assertEquals(null, integers.get(4));
    }

    @Test
    public void exception() {
        ArrayBList<Integer> integers = new ArrayBList<>(1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            integers.get(-1);
        });
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            integers.get(Integer.MAX_VALUE);
        });
    }
}
