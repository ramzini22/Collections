package org.zinnatullin;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<T> implements List<T>{
    private int size=0;
    private Object[] array=new Object[10];


    @Override
    public boolean add(T t) {
        array_security();
        array[size]=t;
        size++;
        return true;
    }
    @Override
    public boolean add_from_index(T t, int index) {
        array_security();
        if(index_security(index) || index==size){
            System.arraycopy(array, index, array, index + 1, size - index);	//	передвигает size-index элементов на b+1 ячейки
            array[index]=t;
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
    public boolean delete(T t) {
        int index = find_Object_from_index(t);
        if(index!=-1)return delete_from_index(index);
        return false;
    }

    @Override
    public T get_from_index(int i) {
        return (T) array[i];
    }

    @Override
    public T get(T t) {
        int index = find_Object_from_index(t);
        if(index != -1) return get_from_index(index);
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array=new Object[10];
        size=0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index<size;
            }

            @Override
            public T next() {
                return (T) array[index++];
            }
        };
    }

    private void array_security(){
        if(size+1==array.length)
            array= Arrays.copyOf(array, size*2);
    };

    private boolean index_security(int index){
        if(index<0 || index>=size)return false;
        return true;
    };

    private int find_Object_from_index(T t){
        for (int i=0;i<size;i++){
            if(t.equals(array[i]))
                return i;}
        return -1;
    }

}
