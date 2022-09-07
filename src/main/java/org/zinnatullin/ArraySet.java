package org.zinnatullin;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ArraySet implements Set{
    private int size=0;
    private Object[] array=new Object[16];


    @Override
    public boolean add(Car car) {
        if(size>=array.length*0.75)
            increase_array();
        if(add(car, array)){
            size++;
            return true;
        }
        return false;
    }


    private boolean add(Car car, Object[] array2){
        int position=get_element_position(car);
        if(array2[position]==null){
            array2[position]=new Entry(car, null);
            return true;
        }
        else if(((Entry) array2[position]).value.equals(car)){
            return false;
        }
        else {
            Entry entry=(Entry)array2[position];
            while (true ){
                if(entry.value.equals(car)){
                    return false;
                }else if(entry.next==null){
                    entry.next=new Entry(car, null);
                    return true;
                }else{
                    entry=entry.next;
                }
            }
        }
    }


    @Override
    public boolean delete(Car car) {
        int position=get_element_position(car);
        if(array[position]==null){
            return false;
        }
        Entry entry=(Entry)array[position];
        Entry next=entry.next;
        if (entry.value.equals(car)){
            array[position]=next;
            size--;
            return true;
        }
        while (next!=null){
            if(next.value.equals(car)){
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


    private int get_element_position(Car car){
        return Math.abs(car.hashCode()%array.length);
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
        Car value;
        Entry next;

        public Entry(Car value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }
}
