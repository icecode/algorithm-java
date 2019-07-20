package com.icecoder.algorithm.adt;

import javax.annotation.Nonnull;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-20 15:02
 */
public class ArrayVector<T extends Comparable<T>> implements Vector<T> {

    /**
     * 元素存放容器
     */
    private Object[] elmContainer;

    /**
     * 当前大小
     */
    private int size;

    /**
     * 装填因子
     */
    private double loadFactor;

    public static final int DEFAULT_SIZE = 4;

    public static final double DEFAULT_LOAD_FACTOR = 0.7;

    public ArrayVector() {
        this(DEFAULT_SIZE, DEFAULT_LOAD_FACTOR);
    }

    public ArrayVector(int initSize, double loadFactor) {
        this.elmContainer = new Object[initSize];
        this.loadFactor = loadFactor;
    }

    private void expend() {
        if ((elmContainer.length * loadFactor) < (size + 1)) {
            //扩容
            int newCapacity = elmContainer.length * 2;
            Object[] newElmContainer = new Object[newCapacity];
            System.arraycopy(elmContainer, 0, newElmContainer, 0, size);
            elmContainer = newElmContainer;
        }
    }

    @Override
    public int insert(@Nonnull T elm) {
        return insert(0, elm);
    }

    @Override
    public int insert(int rank, T elm) {
        if (rank > size || rank < 0) {
            return -1;
        }
        expend();
        int sizeIndex = size + 1;
        while (sizeIndex > rank) {
            elmContainer[sizeIndex] = elmContainer[sizeIndex - 1];
            sizeIndex--;
        }
        elmContainer[rank] = elm;
        size++;
        return rank;
    }

    @Override
    public int remove(@Nonnull T elm) {
        int elmRank = find(elm);
        return elmRank == -1 ? -1 : remove(elmRank);
    }

    @Override
    public int remove(int rank) {
        if (rank < 0 || rank > size) {
            return -1;
        }
        int reIndex = rank;
        while (reIndex < size) {
            elmContainer[reIndex] = elmContainer[reIndex + 1];
            reIndex++;
        }
        elmContainer[size] = null;
        size--;
        return rank;
    }

    @Override
    public int removeRange(int lo, int hi) {
        if (lo < 0 || lo > size || hi < 0 || hi >= size || lo > hi || hi >= elmContainer.length) {
            return -1;
        }
        int deleteElement = (hi + 1) - lo;
        int elmIndex = hi + 1;
        while (lo <= hi) {
            if (elmIndex >= size) {
                elmContainer[lo] = null;
            } else {
                elmContainer[lo] = elmContainer[elmIndex];
            }
            lo++;
            elmIndex++;
        }
        size -= deleteElement;
        return size;
    }

    @Override
    public int find(@Nonnull T elm) {
        for (int i = 0; i < size; i++) {
            if (elm.equals(elmContainer[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int uniquify() {
        if (sorted()) {
            int readIndex = 0;
            int cmpIndex = readIndex + 1;
            while (cmpIndex < size) {
                if (!elmContainer[readIndex].equals(elmContainer[cmpIndex])) {
                    elmContainer[readIndex + 1] = elmContainer[cmpIndex];
                    readIndex++;
                }
                cmpIndex++;
            }
            int oldSize = size;
            size = readIndex + 1;
            int realSize = readIndex;
            while (size < oldSize) {
                elmContainer[--oldSize] = null;
            }
            return size;
        }
        return 0;
    }

    @Override
    public int search(T elm) {
        return 0;
    }

    @Override
    public void bubbleSort() {
        T tmp = null;
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size - i; j++) {
                if (((T) elmContainer[j - 1]).compareTo((T) elmContainer[j]) > 0) {
                    tmp = (T) elmContainer[j - 1];
                    elmContainer[j - 1] = elmContainer[j];
                    elmContainer[j] = tmp;
                }
            }
        }
    }

    @Override
    public boolean sorted() {
        for (int i = 1; i < size; i++) {
            if (((T) elmContainer[i]).compareTo((T) elmContainer[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(i).append("=").append(elmContainer[i]).append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
