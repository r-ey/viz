package model.util;

// Creating a Node for SimpleList
public class Node<T> {
    // T (Generic) object to be added to the SimpleList, next to point to the next node
    T object;
    Node<T> next;

    // REQUIRES : obj not null
    // EFFECTS : construct a node inside SimpleList
    public Node(T obj) {
        object = obj;
        next = null;
    }
}
