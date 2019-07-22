package com.icecoder.algorithm.adt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-20 17:01
 */
public class ArrayVectorTest {


    @Test
    public void insert() {
        ArrayVector<Integer> integerVector = new ArrayVector<>();
        integerVector.insert(5);
        integerVector.insert(4);
        integerVector.insert(3);
        integerVector.insert(2);
        integerVector.insert(1);
        integerVector.insert(0);

        Assertions.assertEquals(0, integerVector.find(0));
        Assertions.assertEquals(1, integerVector.find(1));
        Assertions.assertEquals(2, integerVector.find(2));
        Assertions.assertEquals(3, integerVector.find(3));
        Assertions.assertEquals(4, integerVector.find(4));
        Assertions.assertEquals(5, integerVector.find(5));

        integerVector.insert(6, 6);
        Assertions.assertEquals(6, integerVector.find(6));
        Assertions.assertEquals(7, integerVector.size());
    }

    @Test
    public void remove() {
        ArrayVector<Integer> integerVector = new ArrayVector<>();
        integerVector.insert(5);
        integerVector.insert(4);
        integerVector.insert(3);
        integerVector.insert(2);
        integerVector.insert(1);
        integerVector.insert(0);

        Assertions.assertEquals(0, integerVector.remove(0));
        Assertions.assertEquals(5, integerVector.size());

        Assertions.assertEquals(0, integerVector.remove(Integer.valueOf(1)));
        Assertions.assertEquals(4, integerVector.size());
    }

    @Test
    public void removeRange() {
        ArrayVector<Integer> integerVector = new ArrayVector<>();
        integerVector.insert(5);
        integerVector.insert(4);
        integerVector.insert(3);
        integerVector.insert(2);
        integerVector.insert(1);
        integerVector.insert(0);

        Assertions.assertEquals(-1, integerVector.removeRange(0, 6));
        Assertions.assertEquals(0, integerVector.removeRange(0, 5));
        Assertions.assertEquals(0, integerVector.size());
        Assertions.assertEquals(-1, integerVector.removeRange(0, 5));
        integerVector.insert(5);
        integerVector.insert(4);
        integerVector.insert(3);
        integerVector.insert(2);
        integerVector.insert(1);
        integerVector.insert(0);
        Assertions.assertEquals(4, integerVector.removeRange(2, 3));
        Assertions.assertEquals(0, integerVector.find(0));
        Assertions.assertEquals(1, integerVector.find(1));
        Assertions.assertEquals(2, integerVector.find(4));
        Assertions.assertEquals(3, integerVector.find(5));
    }

    @Test
    public void find() {
        ArrayVector<Integer> integerVector = new ArrayVector<>();
        integerVector.insert(5);
        integerVector.insert(4);
        integerVector.insert(3);
        integerVector.insert(2);
        integerVector.insert(1);
        integerVector.insert(0);
        Assertions.assertEquals(0, integerVector.find(0));
        Assertions.assertEquals(1, integerVector.find(1));
        Assertions.assertEquals(2, integerVector.find(2));
        Assertions.assertEquals(3, integerVector.find(3));
        Assertions.assertEquals(4, integerVector.find(4));
        Assertions.assertEquals(5, integerVector.find(5));
        Assertions.assertEquals(-1, integerVector.find(6));
    }

    @Test
    public void uniquify() {
        ArrayVector<Integer> integerVector = new ArrayVector<>();
        integerVector.insert(0);
        integerVector.insert(1);
        integerVector.insert(2);
        integerVector.insert(2);
        integerVector.insert(3);
        integerVector.insert(5);
        integerVector.bubbleSort();
        Assertions.assertEquals(5, integerVector.uniquify());
        Assertions.assertEquals(0, integerVector.find(0));
        Assertions.assertEquals(1, integerVector.find(1));
        Assertions.assertEquals(2, integerVector.find(2));
        Assertions.assertEquals(3, integerVector.find(3));
        Assertions.assertEquals(4, integerVector.find(5));
    }

    @Test
    public void bubbleSort() {
        ArrayVector<Integer> integerVector = new ArrayVector<>();
        integerVector.insert(0);
        integerVector.insert(1);
        integerVector.insert(2);
        integerVector.insert(3);
        integerVector.insert(4);
        integerVector.insert(5);
        Assertions.assertEquals(5, integerVector.find(0));
        Assertions.assertEquals(4, integerVector.find(1));
        Assertions.assertEquals(3, integerVector.find(2));
        Assertions.assertEquals(2, integerVector.find(3));
        Assertions.assertEquals(1, integerVector.find(4));
        Assertions.assertEquals(0, integerVector.find(5));
        integerVector.bubbleSort();
        Assertions.assertEquals(0, integerVector.find(0));
        Assertions.assertEquals(1, integerVector.find(1));
        Assertions.assertEquals(2, integerVector.find(2));
        Assertions.assertEquals(3, integerVector.find(3));
        Assertions.assertEquals(4, integerVector.find(4));
        Assertions.assertEquals(5, integerVector.find(5));
    }

    @Test
    public void sorted() {
        ArrayVector<Integer> integerVector = new ArrayVector<>();
        integerVector.insert(0);
        integerVector.insert(1);
        integerVector.insert(2);
        integerVector.insert(3);
        integerVector.insert(4);
        integerVector.insert(5);
        Assertions.assertFalse(integerVector.sorted());
        integerVector.bubbleSort();
        Assertions.assertTrue(integerVector.sorted());
    }

    @Test
    public void mergeSort() {
        ArrayVector<Integer> integerVector = new ArrayVector<>();
        integerVector.insert(0);
        integerVector.insert(1);
        integerVector.insert(2);
        integerVector.insert(3);
        integerVector.insert(4);
        integerVector.insert(5);
        Assertions.assertEquals(5, integerVector.find(0));
        Assertions.assertEquals(4, integerVector.find(1));
        Assertions.assertEquals(3, integerVector.find(2));
        Assertions.assertEquals(2, integerVector.find(3));
        Assertions.assertEquals(1, integerVector.find(4));
        Assertions.assertEquals(0, integerVector.find(5));
        integerVector.mergeSort();
        System.out.println(integerVector.toString());
        Assertions.assertEquals(0, integerVector.find(0));
        Assertions.assertEquals(1, integerVector.find(1));
        Assertions.assertEquals(2, integerVector.find(2));
        Assertions.assertEquals(3, integerVector.find(3));
        Assertions.assertEquals(4, integerVector.find(4));
        Assertions.assertEquals(5, integerVector.find(5));
    }

    @Test
    public void selectionSort() {
        ArrayVector<Integer> integerVector = new ArrayVector<>();
        integerVector.insert(0);
        integerVector.insert(1);
        integerVector.insert(2);
        integerVector.insert(3);
        integerVector.insert(4);
        integerVector.insert(5);
        Assertions.assertEquals(5, integerVector.find(0));
        Assertions.assertEquals(4, integerVector.find(1));
        Assertions.assertEquals(3, integerVector.find(2));
        Assertions.assertEquals(2, integerVector.find(3));
        Assertions.assertEquals(1, integerVector.find(4));
        Assertions.assertEquals(0, integerVector.find(5));
        integerVector.selectionSort();
        System.out.println(integerVector.toString());
        Assertions.assertEquals(0, integerVector.find(0));
        Assertions.assertEquals(1, integerVector.find(1));
        Assertions.assertEquals(2, integerVector.find(2));
        Assertions.assertEquals(3, integerVector.find(3));
        Assertions.assertEquals(4, integerVector.find(4));
        Assertions.assertEquals(5, integerVector.find(5));
    }
}
