package org.zinnatullin;

public interface List{
    boolean add(Car car);
    boolean add_from_index(Car car, int i);
    boolean delete_from_index(int i);
    boolean delete(Car car);
    Car get_from_index(int i);
    Car get(Car car);
    int size();
    void clear();
}
