package com.icecoder.algorithm.list;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * @author libing
 * @version 1.0
 * @date 2019-05-05 09:18
 */
public abstract class BList<E> implements List<E> {

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public void sort(Comparator<? super E> c) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public Stream<E> stream() {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public Stream<E> parallelStream() {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("unsupported method");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("unsupported method");
    }
}
