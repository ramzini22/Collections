package org.zinnatullin;
import java.util.Iterator;

public class LinkedList<T> implements List<T>{
    private int size=0;
    private Node first=null;
    private Node last=null;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(){
            private Node node=first;

            @Override
            public boolean hasNext() {
                return node!=null;
            }

            @Override
            public T next() {
                T t=(T)first.value;
                node=node.next;
                return t;
            }
        };
    }


    @Override
    public T get(T t) {
        return (T)node_from_object(t).value;
    }


    @Override
    public boolean add(T t) {
        if(size==0){
            first=new Node(null, t, null);
            last=first;
        }
        else{
            last.next=new Node(last, t, null);
            last=last.next;
        }
        size++;
        return true;
    }

    @Override
    public boolean add_from_index(T t, int index) {
        if(index_security(index) || index==size){
            Node node= new Node(null,t, null);
            if(index==0){
                first.before=node;
                node.next=first;
                first=node;

            }
            else if(index==size){
                last.next=node;
                node.before=last;
                last=node;
            }
            else{
                Node node_2=node_from_index(index);
                node.next=node_2;
                node.before=node_2.before;
                node_2.before.next=node;
                node_2.before=node;
            }
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean delete_from_index(int index) {
        if(index_security(index)){
            Node node=node_from_index(index);
            return delete_node(node);
        }
        return false;
    }

    @Override
    public boolean delete(T t) {
        Node node=node_from_object(t);
        if(node!=null){
            return delete_node(node);
        }
        return false;
    }

    @Override
    public T get_from_index(int index) {
        Node node=node_from_index(index);
        if(node!=null)
            return (T) (node.value);
        else return null;
    }

    public Node node_from_index(int index) {
        if(index_security(index)){
            Node node=first;
            for(int i=0;i<index;i++){
                node=node.next;
            }
            return node;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size=0;
        first=null;
        last=null;
    }


    private boolean index_security(int index){
        if(index<0 || index>=size || first==null)
            return false;
        return true;
    }

    private boolean delete_node(Node node){
        if(node.equals(first)){
            first=node.next;
            first.before=null;
        }
        else if(node.equals(last)){
            last=node.before;
            last.next=null;
        }
        else {
            node.next.before = node.before;
            node.before.next = node.next;
        }
        size--;
        return true;
    }

    private Node node_from_object(T t){
        Node node=first;
        for(int i=0;i<size;i++){
            if(node.value.equals(t))return node;
            node=node.next;
        }
        return null;
    }

    private class Node<T>{
        Node before;
        T value;
        Node next;

        public Node(Node before, T value, Node next) {
            this.before = before;
            this.value = value;
            this.next = next;
        }
    }
}
