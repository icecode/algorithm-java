package com.icecoder.algorithm.adt;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-17 21:51
 */
public interface Vector<T> {

    /**
     * 插入元素，返回插入元素位置的索引
     * @param elm
     * @return
     */
    int insert(T elm);

    /**
     * 插入元素，返回插入元素位置的索引
     * @param rank 不能超过当前元素最大位置+1
     * @param elm
     * @return
     */
    int insert(int rank, T elm);

    /**
     * 删除单个元素，返回删除元素的索引
     * @param elm
     * @return
     */
    int remove(T elm);

    /**
     * 删除单个元素，返回删除后容量大小
     * @param rank
     * @return
     */
    int remove(int rank);

    /**
     * 删除区间元素，返回删除后容量大小
     * @param lo
     * @param hi
     * @return
     */
    int removeRange(int lo, int hi);

    /**
     * 查找元素，查找失败返回-1
     * @param elm
     * @return
     */
    int find(T elm);

    /**
     * 去重，返回去重后的容量大小
     * @return
     */
    int uniquify();

    /**
     * 返回小于elm的最后一个元素的索引
     * @param elm
     * @return
     */
    int search(T elm);

    /**
     * 冒泡排序
     */
    void bubbleSort();

    /**
     * 归并排序
     */
    void mergeSort();

    /**
     * 是否是排序状态
     * @return
     */
    boolean sorted();

    /**
     * 返回容器大小
     * @return
     */
    int size();

}
