package org.zinnatullin;

public interface List <T> extends Collection<T> {
    boolean add(T t);
    boolean add_from_index(T t, int i);
    boolean delete_from_index(int i);
    boolean delete(T t);
    T get_from_index(int i);
    T get(T t);
    int size();
    void clear();
}
