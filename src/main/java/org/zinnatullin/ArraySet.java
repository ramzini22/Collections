package org.zinnatullin;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ArraySet<T> implements Set<T>{
    private int size=0;
    private Object[] array=new Object[16];


    @Override
    public boolean add(T t) {
        if(size>=array.length*0.75)
            increase_array();
        if(add(t, array)){
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArraySet<?> arraySet = (ArraySet<?>) o;
        return size == arraySet.size && Arrays.equals(array, arraySet.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    private boolean add(T t, Object[] array2){
        int position=get_element_position(t);
        if(array2[position]==null){
            array2[position]=new Entry(t, null);
            return true;
        }
        else if(((Entry) array2[position]).value.equals(t)){
            return false;
        }
        else {
            Entry entry=(Entry)array2[position];
            while (true ){
                if(entry.value.equals(t)){
                    return false;
                }else if(entry.next==null){
                    entry.next=new Entry(t, null);
                    return true;
                }else{
                    entry=entry.next;
                }
            }
        }
    }


    @Override
    public boolean delete(T t) {
        int position=get_element_position(t);
        if(array[position]==null){
            return false;
        }
        Entry entry=(Entry)array[position];
        Entry next=entry.next;
        if (entry.value.equals(t)){
            array[position]=next;
            size--;
            return true;
        }
        while (next!=null){
            if(next.value.equals(t)){
                entry.next=next.next;
                size--;
                return true;
            }
            else{
                entry=next;
                next=next.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size=0;
        array=new Object[16];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index=0;
            int array_index=0;
            Entry entry;

            @Override
            public boolean hasNext() {
                return index<size;
            }

            @Override
            public T next() {
                while (array[array_index]==null)
                    array_index++;
                if(entry==null)
                    entry=(Entry) array[array_index];
                T result=entry.value;
                entry=entry.next;
                if (entry==null)
                    array_index++;
                index++;
                return entry.value;
            }
        };
    }

    private int get_element_position(T t){
        return Math.abs(t.hashCode()%array.length);
    }

    private void increase_array(){
        Object[] newArray=new Object[array.length*2];
        for(Object entry: array){
            Entry entry1=(Entry) entry;
            while (entry1!=null){
                add(((Entry) entry1).value, newArray);
                entry1=((Entry) entry1).next;
            }
        }
        array=newArray;
    }

    private class Entry{
        T value;
        Entry next;

        public Entry(T value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }
}
