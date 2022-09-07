package org.zinnatullin;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayList implements List{
    private int size=0;
    private Car[] array=new Car[10];


    @Override
    public boolean add(Car car) {
        array_security();
        array[size]=car;
        size++;
        return true;
    }
    @Override
    public boolean add_from_index(Car car, int index) {
        array_security();
        if(index_security(index) || index==size){
            System.arraycopy(array, index, array, index + 1, size - index);	//	передвигает size-index элементов на b+1 ячейки
            array[index]=car;
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean delete_from_index(int index) {
        if(index_security(index)){
            System.arraycopy(array, index+1, array, index, size - index-1);	//	передвигает size-index элементов на b+1 ячейки
            array[size]=null;
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Car car) {
        int index = find_Object_from_index(car);
        if(index!=-1)return delete_from_index(index);
        return false;
    }

    @Override
    public Car get_from_index(int i) {
        return array[i];
    }

    @Override
    public Car get(Car car) {
        int index = find_Object_from_index(car);
        if(index != -1) return get_from_index(index);
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array=new Car[10];
        size=0;
    }

    private void array_security(){
        if(size+1==array.length)
            array= Arrays.copyOf(array, size*2);
    };

    private boolean index_security(int index){
            if(index<0 || index>=size)return false;
            return true;
    };

    private int find_Object_from_index(Car car){
        for (int i=0;i<size;i++){
            if(car.equals(array[i]))
                return i;}
        return -1;
    }
}
