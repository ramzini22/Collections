package org.zinnatullin;

import java.util.Iterator;

public class TreeSet<T extends Comparable> implements Set<T> {
    private Node first = null;
    private int size = 0;

    @Override
    public boolean add(T t) {
        Node node = first;
        if (node == null)
            first = new Node(null, t);
        else {
            while (node != null) {
                int result = t.compareTo(node.value);
                if (result == 0) {
                    return false;
                }
                if (result > 0) {
                    if (node.more == null) {
                        node.more = new Node(node, t);
                        node = node.more;
                    }
                    node = node.more;
                } else {
                    if (node.lesser == null) {
                        node.lesser = new Node(node, t);
                        node = node.lesser;
                    }
                    node = node.lesser;
                }
            }
        }
        size++;
        return true;
    }

    @Override
    public boolean delete(T t) {
        Node node = first;
        while (node != null) {
            int result = t.compareTo(node.value);
            if (result == 0) {
                delete_easy(node);
                size--;
                return true;
            } else if (result > 0)
                node = node.more;
            else node = node.lesser;
        }
        return false;
    }

    void delete_easy(Node node) {
        int back;
        if (node.more == null || node.lesser == null) {
            back = node_back(node);
            if (back == 1) {
                node.before.lesser = node.lesser;
                if (node.more != null)
                    node.before.lesser = node.more;
            } else {
                node.before.more = node.lesser;
                if (node.more != null)
                    node.before.more = node.more;
            }
            if (node.lesser != null) node.lesser.before = node.before;
            if (node.more != null) node.more.before = node.before;
        } else delete_node(node);
    }

    void delete_node(Node node) {

        Node delete_node = node;
        if (node.more != null) {
            node = node.more;
            while (node.lesser != null)
                node = node.lesser;
        } else {
            node = node.lesser;
            while (node.more != null)
                node = node.more;
        }
        delete_easy(node);
        delete_node.value = node.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            T result;
            Node node = first;
            int back = 0;   //  (-1) - left     (0) - no action       (1) - right

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (size == 1) {
                    index++;
                    return node.value;
                }
                while (node != null) {
                    if (back == -1) {
                        back = node_back(node);
                        node = node.before;
                    } else if (back == 0) {

                        if (node.lesser == null) {
                            result = node.value;
                            if (node.more == null) {
                                back = node_back(node);
                                node = node.before;
                            } else node = node.more;
                            break;
                        } else node = node.lesser;

                    } else {
                        result = node.value;
                        if (node.more != null) {
                            back = 0;
                            node = node.more;
                        } else {
                            if (node.before != null)   //  может это убрать :)
                                back = node_back(node);
                            node = node.before;
                        }
                        break;
                    }
                }
                index++;
                return result;
            }
        };
    }

    private int node_back(Node node) {
        if (node.value.compareTo(node.before.value) < 0)
            return 1;
        return -1;
    }

    private class Node {
        Node more = null;
        Node lesser = null;
        Node before;
        T value;

        public Node(Node before, T value) {
            this.before = before;
            this.value = value;
        }
    }
}
