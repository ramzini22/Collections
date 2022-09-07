package org.zinnatullin;

public interface Set<T> extends Collection<T> {
    boolean add(T t);
    boolean delete(T t);
    int size();
    void clear();
}







