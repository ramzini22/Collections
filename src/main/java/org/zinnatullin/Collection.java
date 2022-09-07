package org.zinnatullin;

public interface Collection<T> extends Iterable<T> {
    boolean add(T t);
    boolean delete(T t);
    void clear();
    int size();
}
