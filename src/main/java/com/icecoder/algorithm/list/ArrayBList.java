package com.icecoder.algorithm.list;

/**
 * @author libing
 * @version 1.0
 * @date 2019-05-05 09:17
 */
public class ArrayBList<E> extends BList<E> {

    private long capacity;

    private Object[] data;

    private int index;

    public ArrayBList(int initCapacity) {
        this.capacity = initCapacity;
        this.data = new Object[initCapacity];
        this.index = 0;
    }

    public long getCapacity() {
        return capacity;
    }

    private void newCapacity() {
        if (index + 1 >= capacity) {
            long newCapacity = capacity * 2;
            if (newCapacity > Integer.MAX_VALUE) {
                throw new OutOfMemoryError();
            }
            Object[] newData = new Object[(int)newCapacity];
            System.arraycopy(data, 0, newData, 0, index);
            capacity = newCapacity;
            data = newData;
        }
    }

    @Override
    public boolean add(E e) {
        newCapacity();
        data[index++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int elmIndex = 0;
        while (elmIndex < index && !o.equals(data[elmIndex])) {
            elmIndex++;
        }
        return remove(elmIndex) != null;
    }

    @Override
    public E get(int elmIndex) {
        requireIndexSuccess(elmIndex);
        return (E) (data[elmIndex]);
    }

    @Override
    public E set(int elmIndex, E element) {
        requireIndexSuccess(elmIndex);
        E oldElm = get(elmIndex);
        data[elmIndex] = element;
        return oldElm;
    }

    @Override
    public void add(int elmIndex, E element) {
        requireIndexSuccess(elmIndex);
        int reIndex = index;
        while (reIndex > elmIndex) {
            data[reIndex] = data[reIndex - 1];
            reIndex--;
        }
        data[elmIndex] = element;
        index++;
    }

    @Override
    public E remove(int elmIndex) {
        requireIndexSuccess(elmIndex);
        if (elmIndex < index) {
            E elmData = (E) data[elmIndex];
            while (elmIndex < index) {
                data[elmIndex] = data[elmIndex + 1];
                elmIndex++;
            }
            index--;
            return elmData;
        }
        return null;
    }

    private static void requireIndexSuccess(int elmIndex) {
        if (elmIndex < 0 || elmIndex == Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("Illegal array index:" + elmIndex);
        }
    }
}
