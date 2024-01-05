package model.util;

// Creating a simple linked list with limited functionality for storing items
// Using generic types to store various kind of object
public class SimpleList<T> {

    // Head to keep track the SimpleList itself, tail to keep track the last item for inserting
    private Node<T> head;
    private Node<T> tail;

    // EFFECTS : creating an empty SimpleList
    public SimpleList() {
        head = tail = null;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    // REQUIRES : obj not null
    // MODIFIES : this
    // EFFECTS : adding one node add the end of the SimpleList
    public void add(T obj) {
        Node<T> node = new Node<>(obj);

        if (head == null) { // case 1 if the SimpleList still empty
            head = tail = node;
        } else { // case 2 if SimpleList already has object(s) inside it
            tail.next = node;
            tail = node;
        }
    }

    // REQUIRES : i < size() and i >= 0
    // MODIFIES : this
    // EFFECTS : object at index i will be deleted
    public void delete(int i) {
        if (i == 0) { // case 1 if delete the first item
            head =  head.next;
        } else { // case 3 if delete item in the middle or last
            Node<T> toTravel = getTo(i - 1);
            toTravel.next = toTravel.next.next;
        }
    }

    // REQUIRES : i >= 0 and i < size of SimpleList
    // EFFECTS : get the T (Generic) object at i
    public T get(int i) {
        return getTo(i).object;
    }

    // REQUIRES : i < size() and i >= 0
    // MODIFIES : this
    // EFFECTS : object at index i will be changed to obj
    public void set(int i, T obj) {
        Node<T> replace = new Node<>(obj);
        if (i == 0) { // case 1 if change the first item
            replace.next = head.next;
            head = replace;
        } else { // case 2 if change item in the middle or last
            Node<T> toTravel = getTo(i - 1);
            Node<T> temp = toTravel.next.next;
            toTravel.next = replace;
            replace.next = temp;
            if (i == size() - 1) {
                tail = replace;
            }
        }
    }

    // Helper method for delete, get, and set
    // REQUIRES : i < size() and i >= 0
    // EFFECTS : get to node at index i
    private Node<T> getTo(int i) {
        Node<T> toTravel = head;
        int j = 0;
        while (j < i) {
            toTravel = toTravel.next;
            j++;
        }
        return toTravel;
    }

    // EFFECTS : get the size/length of the SimpleList
    public int size() {
        Node<T> toTravel = head;
        return sizeRecursive(toTravel);
    }

    // Helper method for size()
    // EFFECTS : get the size/length of the SimpleList
    private int sizeRecursive(Node<T> n) {
        return (n == null) ? 0 : sizeRecursive(n.next) + 1;
    }

    // REQUIRES : other not null and other's size == this' size
    // EFFECTS : make String of two SimpleLists side by side, e.g, x.printTwoList(y)
    //              x1 y1 x2 y2 x3 y3 NOT x1 x2 x3 y1 y2 y3
    public String stringTwoLists(SimpleList<?> other) {
        String str = "";
        Node<T> toTravelThis = head;
        Node<T> toTravelOther = (Node<T>) other.getHead();
        while (toTravelThis != null) {
            str = str.concat(toTravelThis.object + "\n" + toTravelOther.object + "\n");
            toTravelThis = toTravelThis.next;
            toTravelOther = toTravelOther.next;
        }
        return str;
    }

    // Overriding method for printing SimpleList
    // EFFECTS : make string of a SimpleList object
    public String toString() {
        String str = "";
        Node<T> toTravel = head;
        while (toTravel != null) {
            str = str.concat(toTravel.object + " ");
            toTravel = toTravel.next;
        }
        return str;
    }
}
